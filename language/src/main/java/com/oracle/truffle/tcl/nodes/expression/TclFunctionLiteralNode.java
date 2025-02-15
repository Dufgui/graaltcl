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
package com.oracle.truffle.tcl.nodes.expression;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.TruffleLanguage.ContextPolicy;
import com.oracle.truffle.api.TruffleLanguage.ContextReference;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.tcl.TclLanguage;
import com.oracle.truffle.tcl.runtime.TclContext;
import com.oracle.truffle.tcl.runtime.TclFunction;
import com.oracle.truffle.tcl.runtime.TclFunctionRegistry;

/**
 * Constant literal for a {@link TclFunction function} value, created when a
 * function name occurs as a literal in tcl source code. Note that function
 * redefinition can change the {@link CallTarget call target} that is executed
 * when calling the function, but the {@link TclFunction} for a name never
 * changes. This is guaranteed by the {@link TclFunctionRegistry}.
 */
@NodeInfo(shortName = "func")
public final class TclFunctionLiteralNode extends TclFunctionNode {

    /** The name of the function. */
    private final String functionName;

    /**
     * The resolved function. During parsing (in the constructor of this node), we
     * do not have the {@link TclContext} available yet, so the lookup can only be
     * done at {@link #executeGeneric first execution}. The {@link CompilationFinal}
     * annotation ensures that the function can still be constant folded during
     * compilation.
     */
    @CompilationFinal
    private TclFunction cachedFunction;

    /**
     * The stored context reference. Caching the context reference in a field like
     * this always ensures the most efficient context lookup. The {@link TclContext}
     * must not be stored in the AST in the multi-context case.
     */
    @CompilationFinal
    private ContextReference<TclContext> contextRef;

    /**
     * It is always safe to store the language in the AST if the language supports
     * {@link ContextPolicy#SHARED shared}.
     */
    @CompilationFinal
    private TclLanguage language;

    public TclFunctionLiteralNode(String functionName) {
        this.functionName = functionName;
    }

    @Override
    public TclFunction executeGeneric(VirtualFrame frame) {
        ContextReference<TclContext> contextReference = contextRef;
        if (contextReference == null) {
            CompilerDirectives.transferToInterpreterAndInvalidate();
            contextReference = contextRef = lookupContextReference(TclLanguage.class);
        }
        TclLanguage l = language;
        if (l == null) {
            CompilerDirectives.transferToInterpreterAndInvalidate();
            l = language = lookupLanguageReference(TclLanguage.class).get();
        }
        CompilerAsserts.partialEvaluationConstant(l);

        TclFunction function;
        if (l.isSingleContext()) {
            function = this.cachedFunction;
            if (function == null) {
                /* We are about to change a @CompilationFinal field. */
                CompilerDirectives.transferToInterpreterAndInvalidate();
                /* First execution of the node: lookup the function in the function registry. */
                this.cachedFunction = function = contextReference.get().getFunctionRegistry().lookup(functionName,
                        true);
            }
        } else {
            /*
             * We need to rest the cached function otherwise it might cause a memory leak.
             */
            if (this.cachedFunction != null) {
                CompilerDirectives.transferToInterpreterAndInvalidate();
                this.cachedFunction = null;
            }
            // in the multi-context case we are not allowed to store
            // TclFunction objects in the AST. Instead we always perform the lookup in the
            // hash map.
            function = contextReference.get().getFunctionRegistry().lookup(functionName, true);
        }
        return function;
    }

}
