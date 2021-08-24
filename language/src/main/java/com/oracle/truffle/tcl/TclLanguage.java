/*
 * Copyright (c) 2012, 2020, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * The Universal Permissive License (UPL), Version 1.0
 *
 * Subject to the condition set forth below, permission is hereby granted to any
 * person obtaining a copy of this software, associated documentation and/or
 * data (collectively the "Software"), free of charge and under any and all
 * copyright rights in the Software, and any and all patent rights owned or
 * freely licensable by each licensor hereunder covering either (i) the
 * unmodified Software as contributed to or provided by such licensor, or (ii)
 * the Larger Works (as defined below), to deal in both
 *
 * (a) the Software, and
 *
 * (b) any piece of software and/or hardware listed in the lrgrwrks.txt file if
 * one is included with the Software each a "Larger Work" to which the Software
 * is contributed by such licensors),
 *
 * without restriction, including without limitation the rights to copy, create
 * derivative works of, display, perform, and distribute the Software and make,
 * use, sell, offer for sale, import, export, have made, and have sold the
 * Software and the Larger Work(s), and to sublicense the foregoing rights on
 * either these or other terms.
 *
 * This license is subject to the following condition:
 *
 * The above copyright notice and either this complete permission notice or at a
 * minimum a reference to the UPL must be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.oracle.truffle.tcl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.oracle.truffle.api.Assumption;
import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.TruffleLanguage.ContextPolicy;
import com.oracle.truffle.api.debug.DebuggerTags;
import com.oracle.truffle.api.dsl.NodeFactory;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.instrumentation.AllocationReporter;
import com.oracle.truffle.api.instrumentation.ProvidedTags;
import com.oracle.truffle.api.instrumentation.StandardTags;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.nodes.RootNode;
import com.oracle.truffle.api.object.DynamicObject;
import com.oracle.truffle.api.object.DynamicObjectLibrary;
import com.oracle.truffle.api.object.Shape;
import com.oracle.truffle.api.source.Source;
import com.oracle.truffle.tcl.builtins.TclBuiltinNode;
import com.oracle.truffle.tcl.builtins.TclGetsBuiltin;
import com.oracle.truffle.tcl.builtins.TclProcBuiltin;
import com.oracle.truffle.tcl.builtins.TclPutsBuiltin;
import com.oracle.truffle.tcl.nodes.TclEvalRootNode;
import com.oracle.truffle.tcl.nodes.TclExpressionNode;
import com.oracle.truffle.tcl.nodes.TclRootNode;
import com.oracle.truffle.tcl.nodes.TclTypes;
import com.oracle.truffle.tcl.nodes.TclUndefinedFunctionRootNode;
import com.oracle.truffle.tcl.nodes.controlflow.TclBlockNode;
import com.oracle.truffle.tcl.nodes.controlflow.TclBreakNode;
import com.oracle.truffle.tcl.nodes.controlflow.TclContinueNode;
import com.oracle.truffle.tcl.nodes.controlflow.TclDebuggerNode;
import com.oracle.truffle.tcl.nodes.controlflow.TclIfNode;
import com.oracle.truffle.tcl.nodes.controlflow.TclReturnNode;
import com.oracle.truffle.tcl.nodes.controlflow.TclWhileNode;
import com.oracle.truffle.tcl.nodes.expression.TclAddNode;
import com.oracle.truffle.tcl.nodes.expression.TclBigIntegerLiteralNode;
import com.oracle.truffle.tcl.nodes.expression.TclDivNode;
import com.oracle.truffle.tcl.nodes.expression.TclEqualNode;
import com.oracle.truffle.tcl.nodes.expression.TclFunctionLiteralNode;
import com.oracle.truffle.tcl.nodes.expression.TclInvokeNode;
import com.oracle.truffle.tcl.nodes.expression.TclLessOrEqualNode;
import com.oracle.truffle.tcl.nodes.expression.TclLessThanNode;
import com.oracle.truffle.tcl.nodes.expression.TclLogicalAndNode;
import com.oracle.truffle.tcl.nodes.expression.TclLogicalOrNode;
import com.oracle.truffle.tcl.nodes.expression.TclMulNode;
import com.oracle.truffle.tcl.nodes.expression.TclReadPropertyNode;
import com.oracle.truffle.tcl.nodes.expression.TclStringLiteralNode;
import com.oracle.truffle.tcl.nodes.expression.TclSubNode;
import com.oracle.truffle.tcl.nodes.expression.TclWritePropertyNode;
import com.oracle.truffle.tcl.nodes.local.TclReadArgumentNode;
import com.oracle.truffle.tcl.nodes.local.TclReadLocalVariableNode;
import com.oracle.truffle.tcl.nodes.local.TclReadVariableNode;
import com.oracle.truffle.tcl.nodes.local.TclWriteLocalVariableNode;
import com.oracle.truffle.tcl.parser.TclNodeFactory;
import com.oracle.truffle.tcl.parser.TclParser;
import com.oracle.truffle.tcl.runtime.TclBigNumber;
import com.oracle.truffle.tcl.runtime.TclContext;
import com.oracle.truffle.tcl.runtime.TclFunction;
import com.oracle.truffle.tcl.runtime.TclFunctionRegistry;
import com.oracle.truffle.tcl.runtime.TclLanguageView;
import com.oracle.truffle.tcl.runtime.TclNull;
import com.oracle.truffle.tcl.runtime.TclObject;

/**
 * Tcl is a simple language to demonstrate and showcase features of Truffle. The
 * implementation is as simple and clean as possible in order to help
 * understanding the ideas and concepts of Truffle. The language has first class
 * functions, and objects are key-value stores.
 * <p>
 * Tcl is dynamically typed, i.e., there are no type names specified by the
 * programmer. Tcl is strongly typed, i.e., there is no automatic conversion
 * between types. If an operation is not available for the types encountered at
 * run time, a type error is reported and execution is stopped. For example,
 * {@code 4 - "2"} results in a type error because subtraction is only defined
 * for numbers.
 *
 * <p>
 * <b>Types:</b>
 * <ul>
 * <li>Number: arbitrary precision integer numbers. The implementation uses the
 * Java primitive type {@code long} to represent numbers that fit into the 64
 * bit range, and {@link TclBigNumber} for numbers that exceed the range. Using
 * a primitive type such as {@code long} is crucial for performance.
 * <li>Boolean: implemented as the Java primitive type {@code boolean}.
 * <li>String: implemented as the Java standard type {@link String}.
 * <li>Function: implementation type {@link TclFunction}.
 * <li>Object: efficient implementation using the object model provided by
 * Truffle. The implementation type of objects is a subclass of
 * {@link DynamicObject}.
 * <li>Null (with only one value {@code null}): implemented as the singleton
 * {@link TclNull#SINGLETON}.
 * </ul>
 * The class {@link TclTypes} lists these types for the Truffle DSL, i.e., for
 * type-specialized operations that are specified using Truffle DSL annotations.
 *
 * <p>
 * <b>Language concepts:</b>
 * <ul>
 * <li>Literals for {@link TclBigIntegerLiteralNode numbers} ,
 * {@link TclStringLiteralNode strings}, and {@link TclFunctionLiteralNode
 * functions}.
 * <li>Basic arithmetic, logical, and comparison operations: {@link TclAddNode
 * +}, {@link TclSubNode -}, {@link TclMulNode *}, {@link TclDivNode /},
 * {@link TclLogicalAndNode logical and}, {@link TclLogicalOrNode logical or},
 * {@link TclEqualNode ==}, !=, {@link TclLessThanNode &lt;},
 * {@link TclLessOrEqualNode &le;}, &gt;, &ge;.
 * <li>Local variables: local variables must be defined (via a
 * {@link TclWriteLocalVariableNode write}) before they can be used (by a
 * {@link TclReadVariableNode read}). This is used for dynamic variables.
 * {@link TclReadLocalVariableNode read}). Local variables are not visible
 * outside of the block where they were first defined.
 * <li>Basic control flow statements: {@link TclBlockNode blocks},
 * {@link TclIfNode if}, {@link TclWhileNode while} with {@link TclBreakNode
 * break} and {@link TclContinueNode continue}, {@link TclReturnNode return}.
 * <li>Debugging control: {@link TclDebuggerNode debugger} statement uses
 * {@link DebuggerTags#AlwaysHalt} tag to halt the execution when run under the
 * debugger.
 * <li>Function calls: {@link TclInvokeNode invocations} are efficiently
 * implemented with {@link TclDispatchNode polymorphic inline caches}.
 * <li>Object access: {@link TclReadPropertyNode} and
 * {@link TclWritePropertyNode} use a cached {@link DynamicObjectLibrary} as the
 * polymorphic inline cache for property reads and writes, respectively.
 * </ul>
 *
 * <p>
 * <b>Syntax and parsing:</b><br>
 * The syntax is described as an attributed grammar. The {@link TclParser} and
 * {@link TclLexer} are automatically generated by ANTLR 4. The grammar contains
 * semantic actions that build the AST for a method. To keep these semantic
 * actions short, they are mostly calls to the {@link TclNodeFactory} that
 * performs the actual node creation. All functions found in the tcl source are
 * added to the {@link TclFunctionRegistry}, which is accessible from the
 * {@link TclContext}.
 *
 * <p>
 * <b>Builtin functions:</b><br>
 * Library functions that are available to every tcl source without prior
 * definition are called builtin functions. They are added to the
 * {@link TclFunctionRegistry} when the {@link TclContext} is created. Some of
 * the current builtin functions are
 * <ul>
 * <li>{@link TclGetsBuiltin readln}: Read a String from the
 * {@link TclContext#getInput() standard input}.
 * <li>{@link TclPutsBuiltin println}: Write a value to the
 * {@link TclContext#getOutput() standard output}.
 * <li>{@link TclNanoTimeBuiltin nanoTime}: Returns the value of a
 * high-resolution time, in nanoseconds.
 * <li>{@link TclProcBuiltin defineFunction}: Parses the functions provided as a
 * String argument and adds them to the function registry. Functions that are
 * already defined are replaced with the new version.
 * <li>{@link TclStackTraceBuiltin stckTrace}: Print all function activations
 * with all local variables.
 * </ul>
 */
@TruffleLanguage.Registration(id = TclLanguage.ID, name = "Tcl", defaultMimeType = TclLanguage.MIME_TYPE, characterMimeTypes = TclLanguage.MIME_TYPE, contextPolicy = ContextPolicy.SHARED, fileTypeDetectors = TclFileDetector.class)
@ProvidedTags({ StandardTags.CallTag.class, StandardTags.StatementTag.class, StandardTags.RootTag.class,
        StandardTags.RootBodyTag.class, StandardTags.ExpressionTag.class, DebuggerTags.AlwaysHalt.class,
        StandardTags.ReadVariableTag.class, StandardTags.WriteVariableTag.class })
public final class TclLanguage extends TruffleLanguage<TclContext> {
    public static volatile int counter;

    public static final String ID = "tcl";
    public static final String MIME_TYPE = "application/x-tcl";
    private static final Source BUILTIN_SOURCE = Source.newBuilder(TclLanguage.ID, "", "tcl builtin").build();

    private final Assumption singleContext = Truffle.getRuntime().createAssumption("Single tcl context.");

    private final Map<NodeFactory<? extends TclBuiltinNode>, RootCallTarget> builtinTargets = new ConcurrentHashMap<>();
    private final Map<String, RootCallTarget> undefinedFunctions = new ConcurrentHashMap<>();

    private final Shape rootShape;

    public TclLanguage() {
        counter++;
        this.rootShape = Shape.newBuilder().layout(TclObject.class).build();
    }

    @Override
    protected TclContext createContext(Env env) {
        return new TclContext(this, env, new ArrayList<>(EXTERNAL_BUILTINS));
    }

    public RootCallTarget getOrCreateUndefinedFunction(String name) {
        RootCallTarget target = undefinedFunctions.get(name);
        if (target == null) {
            target = Truffle.getRuntime().createCallTarget(new TclUndefinedFunctionRootNode(this, name));
            RootCallTarget other = undefinedFunctions.putIfAbsent(name, target);
            if (other != null) {
                target = other;
            }
        }
        return target;
    }

    public RootCallTarget lookupBuiltin(NodeFactory<? extends TclBuiltinNode> factory) {
        RootCallTarget target = builtinTargets.get(factory);
        if (target != null) {
            return target;
        }

        /*
         * The builtin node factory is a class that is automatically generated by the
         * Truffle DSL. The signature returned by the factory reflects the signature of
         * the @Specialization
         *
         * methods in the builtin classes.
         */
        int argumentCount = factory.getExecutionSignature().size();
        TclExpressionNode[] argumentNodes = new TclExpressionNode[argumentCount];
        /*
         * Builtin functions are like normal functions, i.e., the arguments are passed
         * in as an Object[] array encapsulated in TclArguments. A TclReadArgumentNode
         * extracts a parameter from this array.
         */
        for (int i = 0; i < argumentCount; i++) {
            argumentNodes[i] = new TclReadArgumentNode(i);
        }
        /* Instantiate the builtin node. This node performs the actual functionality. */
        TclBuiltinNode builtinBodyNode = factory.createNode((Object) argumentNodes);
        builtinBodyNode.addRootTag();
        /*
         * The name of the builtin function is specified via an annotation on the node
         * class.
         */
        String name = lookupNodeInfo(builtinBodyNode.getClass()).shortName();
        builtinBodyNode.setUnavailableSourceSection();

        /*
         * Wrap the builtin in a RootNode. Truffle requires all AST to start with a
         * RootNode.
         */
        TclRootNode rootNode = new TclRootNode(this, new FrameDescriptor(), builtinBodyNode,
                BUILTIN_SOURCE.createUnavailableSection(), name);

        /*
         * Register the builtin function in the builtin registry. Call targets for
         * builtins may be reused across multiple contexts.
         */
        RootCallTarget newTarget = Truffle.getRuntime().createCallTarget(rootNode);
        RootCallTarget oldTarget = builtinTargets.put(factory, newTarget);
        if (oldTarget != null) {
            return oldTarget;
        }
        return newTarget;
    }

    public static NodeInfo lookupNodeInfo(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        NodeInfo info = clazz.getAnnotation(NodeInfo.class);
        if (info != null) {
            return info;
        } else {
            return lookupNodeInfo(clazz.getSuperclass());
        }
    }

    @Override
    protected CallTarget parse(ParsingRequest request) throws Exception {
        Source source = request.getSource();
        Map<String, RootCallTarget> functions;
        /*
         * Parse the provided source. At this point, we do not have a TclContext yet.
         * Registration of the functions with the TclContext happens lazily in
         * TclEvalRootNode.
         */
        if (request.getArgumentNames().isEmpty()) {
            functions = TclParser.parseTcl(this, source);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("set argc ").append(request.getArgumentNames().size()).append("\n");
            sb.append("set argv [list");
            for (String argumentName : request.getArgumentNames()) {
                sb.append(" \"").append(argumentName).append('"');
            }
            sb.append("]\n");
            String language = source.getLanguage() == null ? ID : source.getLanguage();
            Source decoratedSource = Source.newBuilder(language, sb.toString(), source.getName()).build();
            functions = TclParser.parseTcl(this, decoratedSource);
        }

        RootCallTarget main = functions.get(source.getName() + "...");
        RootNode evalMain;
        if (main != null) {
            /*
             * We have a main function, so "evaluating" the parsed source means invoking
             * that main function. However, we need to lazily register functions into the
             * TclContext first, so we cannot use the original TclRootNode for the main
             * function. Instead, we create a new TclEvalRootNode that does everything we
             * need.
             */
            evalMain = new TclEvalRootNode(this, main, functions);
        } else {
            /*
             * Even without a main function, "evaluating" the parsed source needs to
             * register the functions into the TclContext.
             */
            evalMain = new TclEvalRootNode(this, null, functions);
        }
        return Truffle.getRuntime().createCallTarget(evalMain);
    }

    /**
     * TclLanguage specifies the {@link ContextPolicy#SHARED} in
     * {@link Registration#contextPolicy()}. This means that a single
     * {@link TruffleLanguage} instance can be reused for multiple language
     * contexts. Before this happens the Truffle framework notifies the language by
     * invoking {@link #initializeMultipleContexts()}. This allows the language to
     * invalidate certain assumptions taken for the single context case. One
     * assumption tcl takes for single context case is located in
     * {@link TclEvalRootNode}. There functions are only tried to be registered once
     * in the single context case, but produce a boundary call in the multi context
     * case, as function registration is expected to happen more than once.
     *
     * Value identity caches should be avoided and invalidated for the multiple
     * contexts case as no value will be the same. Instead, in multi context case, a
     * language should only use types, shapes and code to speculate.
     *
     * For a new language it is recommended to start with
     * {@link ContextPolicy#EXCLUSIVE} and as the language gets more mature switch
     * to {@link ContextPolicy#SHARED}.
     */
    @Override
    protected void initializeMultipleContexts() {
        singleContext.invalidate();
    }

    public boolean isSingleContext() {
        return singleContext.isValid();
    }

    @Override
    protected Object getLanguageView(TclContext context, Object value) {
        return TclLanguageView.create(value);
    }

    /*
     * Still necessary for the old tcl TCK to pass. We should remove with the old
     * TCK. New language should not override this.
     */
    @Override
    protected Object findExportedSymbol(TclContext context, String globalName, boolean onlyExplicit) {
        return context.getFunctionRegistry().lookup(globalName, false);
    }

    @Override
    protected boolean isVisible(TclContext context, Object value) {
        return !InteropLibrary.getFactory().getUncached(value).isNull(value);
    }

    @Override
    protected Object getScope(TclContext context) {
        return context.getFunctionRegistry().getFunctionsObject();
    }

    public Shape getRootShape() {
        return rootShape;
    }

    /**
     * Allocate an empty object. All new objects initially have no properties.
     * Properties are added when they are first stored, i.e., the store triggers a
     * shape change of the object.
     */
    public TclObject createObject(AllocationReporter reporter) {
        reporter.onEnter(null, 0, AllocationReporter.SIZE_UNKNOWN);
        TclObject object = new TclObject(rootShape);
        reporter.onReturnValue(object, 0, AllocationReporter.SIZE_UNKNOWN);
        return object;
    }

    public static TclContext getCurrentContext() {
        return getCurrentContext(TclLanguage.class);
    }

    private static final List<NodeFactory<? extends TclBuiltinNode>> EXTERNAL_BUILTINS = Collections
            .synchronizedList(new ArrayList<>());

    public static void installBuiltin(NodeFactory<? extends TclBuiltinNode> builtin) {
        EXTERNAL_BUILTINS.add(builtin);
    }

}
