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
package com.oracle.truffle.tcl.runtime;

import static com.oracle.truffle.api.CompilerDirectives.shouldNotReachHere;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.TruffleLanguage.Env;
import com.oracle.truffle.api.dsl.NodeFactory;
import com.oracle.truffle.api.instrumentation.AllocationReporter;
import com.oracle.truffle.api.interop.TruffleObject;
import com.oracle.truffle.api.source.Source;
import com.oracle.truffle.tcl.TclLanguage;
import com.oracle.truffle.tcl.builtins.TclBuiltinNode;
import com.oracle.truffle.tcl.builtins.TclDefineFunctionBuiltinFactory;
import com.oracle.truffle.tcl.builtins.TclEvalBuiltinFactory;
import com.oracle.truffle.tcl.builtins.TclGetSizeBuiltinFactory;
import com.oracle.truffle.tcl.builtins.TclHasSizeBuiltinFactory;
import com.oracle.truffle.tcl.builtins.TclHelloEqualsWorldBuiltinFactory;
import com.oracle.truffle.tcl.builtins.TclImportBuiltinFactory;
import com.oracle.truffle.tcl.builtins.TclIsExecutableBuiltinFactory;
import com.oracle.truffle.tcl.builtins.TclIsInstanceBuiltinFactory;
import com.oracle.truffle.tcl.builtins.TclIsNullBuiltinFactory;
import com.oracle.truffle.tcl.builtins.TclNanoTimeBuiltinFactory;
import com.oracle.truffle.tcl.builtins.TclNewObjectBuiltinFactory;
import com.oracle.truffle.tcl.builtins.TclPrintlnBuiltin;
import com.oracle.truffle.tcl.builtins.TclPrintlnBuiltinFactory;
import com.oracle.truffle.tcl.builtins.TclReadlnBuiltin;
import com.oracle.truffle.tcl.builtins.TclReadlnBuiltinFactory;
import com.oracle.truffle.tcl.builtins.TclStackTraceBuiltinFactory;
import com.oracle.truffle.tcl.builtins.TclTypeOfBuiltinFactory;
import com.oracle.truffle.tcl.builtins.TclWrapPrimitiveBuiltinFactory;

/**
 * The run-time state of tcl during execution. The context is created by the
 * {@link TclLanguage}. It is used, for example, by
 * {@link TclBuiltinNode#getContext() builtin functions}.
 * <p>
 * It would be an error to have two different context instances during the
 * execution of one script. However, if two separate scripts run in one Java VM
 * at the same time, they have a different context. Therefore, the context is
 * not a singleton.
 */
public final class TclContext {

	private final TclLanguage language;
	private final Env env;
	private final BufferedReader input;
	private final PrintWriter output;
	private final TclFunctionRegistry functionRegistry;
	private final AllocationReporter allocationReporter;

	public TclContext(TclLanguage language, TruffleLanguage.Env env,
			List<NodeFactory<? extends TclBuiltinNode>> externalBuiltins) {
		this.env = env;
		this.input = new BufferedReader(new InputStreamReader(env.in()));
		this.output = new PrintWriter(env.out(), true);
		this.language = language;
		this.allocationReporter = env.lookup(AllocationReporter.class);
		this.functionRegistry = new TclFunctionRegistry(language);
		installBuiltins();
		for (NodeFactory<? extends TclBuiltinNode> builtin : externalBuiltins) {
			installBuiltin(builtin);
		}
	}

	/**
	 * Return the current Truffle environment.
	 */
	public Env getEnv() {
		return env;
	}

	/**
	 * Returns the default input, i.e., the source for the {@link TclReadlnBuiltin}.
	 * To allow unit testing, we do not use {@link System#in} directly.
	 */
	public BufferedReader getInput() {
		return input;
	}

	/**
	 * The default default, i.e., the output for the {@link TclPrintlnBuiltin}. To
	 * allow unit testing, we do not use {@link System#out} directly.
	 */
	public PrintWriter getOutput() {
		return output;
	}

	/**
	 * Returns the registry of all functions that are currently defined.
	 */
	public TclFunctionRegistry getFunctionRegistry() {
		return functionRegistry;
	}

	/**
	 * Adds all builtin functions to the {@link TclFunctionRegistry}. This method
	 * lists all {@link TclBuiltinNode builtin implementation classes}.
	 */
	private void installBuiltins() {
		installBuiltin(TclReadlnBuiltinFactory.getInstance());
		installBuiltin(TclPrintlnBuiltinFactory.getInstance());
		installBuiltin(TclNanoTimeBuiltinFactory.getInstance());
		installBuiltin(TclDefineFunctionBuiltinFactory.getInstance());
		installBuiltin(TclStackTraceBuiltinFactory.getInstance());
		installBuiltin(TclHelloEqualsWorldBuiltinFactory.getInstance());
		installBuiltin(TclNewObjectBuiltinFactory.getInstance());
		installBuiltin(TclEvalBuiltinFactory.getInstance());
		installBuiltin(TclImportBuiltinFactory.getInstance());
		installBuiltin(TclGetSizeBuiltinFactory.getInstance());
		installBuiltin(TclHasSizeBuiltinFactory.getInstance());
		installBuiltin(TclIsExecutableBuiltinFactory.getInstance());
		installBuiltin(TclIsNullBuiltinFactory.getInstance());
		installBuiltin(TclWrapPrimitiveBuiltinFactory.getInstance());
		installBuiltin(TclTypeOfBuiltinFactory.getInstance());
		installBuiltin(TclIsInstanceBuiltinFactory.getInstance());
	}

	public void installBuiltin(NodeFactory<? extends TclBuiltinNode> factory) {
		/* Register the builtin function in our function registry. */
		RootCallTarget target = language.lookupBuiltin(factory);
		String rootName = target.getRootNode().getName();
		getFunctionRegistry().register(rootName, target);
	}

	/*
	 * Methods for object creation / object property access.
	 */
	public AllocationReporter getAllocationReporter() {
		return allocationReporter;
	}

	/*
	 * Methods for language interoperability.
	 */
	public static Object fromForeignValue(Object a) {
		if (a instanceof Long || a instanceof TclBigNumber || a instanceof String || a instanceof Boolean) {
			return a;
		} else if (a instanceof Character) {
			return fromForeignCharacter((Character) a);
		} else if (a instanceof Number) {
			return fromForeignNumber(a);
		} else if (a instanceof TruffleObject) {
			return a;
		} else if (a instanceof TclContext) {
			return a;
		}
		throw shouldNotReachHere("Value is not a truffle value.");
	}

	@TruffleBoundary
	private static long fromForeignNumber(Object a) {
		return ((Number) a).longValue();
	}

	@TruffleBoundary
	private static String fromForeignCharacter(char c) {
		return String.valueOf(c);
	}

	public CallTarget parse(Source source) {
		return env.parsePublic(source);
	}

	/**
	 * Returns an object that contains bindings that were exported across all used
	 * languages. To read or write from this object the {@link TruffleObject
	 * interop} API can be used.
	 */
	public TruffleObject getPolyglotBindings() {
		return (TruffleObject) env.getPolyglotBindings();
	}

	public static TclContext getCurrent() {
		return TclLanguage.getCurrentContext();
	}

}
