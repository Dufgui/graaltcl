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
package com.oracle.truffle.tcl.nodes.local;

import java.util.Objects;

import com.oracle.truffle.api.dsl.Fallback;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.instrumentation.StandardTags.WriteVariableTag;
import com.oracle.truffle.api.instrumentation.Tag;
import com.oracle.truffle.tcl.nodes.TclExpressionNode;
import com.oracle.truffle.tcl.parser.TclNodeFactory;

/**
 * Node to write a local variable to a function's {@link VirtualFrame frame}.
 * The Truffle frame API allows to store primitive values of all Java primitive
 * types, and Object values.
 */
@NodeChild("nameNode")
@NodeChild("valueNode")
@NodeField(name = "frameDescriptor", type = FrameDescriptor.class)
@NodeField(name = "lexicalScope", type = TclNodeFactory.LexicalScope.class)
@NodeField(name = "argumentIndex", type = Integer.class)
public abstract class TclWriteDynamicVariableNode extends TclExpressionNode {

    /**
     * Returns the frame descriptor. The goal is to dynamically set variable
     */
    protected abstract FrameDescriptor getFrameDescriptor();

    /**
     * @return
     */
    protected abstract TclNodeFactory.LexicalScope getLexicalScope();

    protected abstract Integer getArgumentIndex();

    private boolean isDeclaration = false;

    public boolean isDeclaration() {
        return isDeclaration;
    }

    private FrameSlot createFrameSlot(VirtualFrame frame, Object name) {
        String nameString = Objects.toString(name);
        FrameSlot frameSlot = getFrameDescriptor().findOrAddFrameSlot(name, getArgumentIndex(), FrameSlotKind.Illegal);
        FrameSlot existingSlot = getLexicalScope().putLocal(nameString, frameSlot);
        isDeclaration = existingSlot == null;
        return frameSlot;
    }

    /**
     * Generic write method that works for all possible types.
     * <p>
     * Why is this method annotated with {@link Specialization} and not
     * {@link Fallback}? For a {@link Fallback} method, the Truffle DSL generated
     * code would try all other specializations first before calling this method. We
     * know that all these specializations would fail their guards, so there is no
     * point in calling them. Since this method takes a value of type
     * {@link Object}, it is guaranteed to never fail, i.e., once we are in this
     * specialization the node will never be re-specialized.
     */
    @Specialization
    protected Object write(VirtualFrame frame, Object name, Object value) {
        FrameSlot frameSlot = createFrameSlot(frame, name);
        /*
         * Regardless of the type before, the new and final type of the local variable
         * is Object. Changing the slot kind also discards compiled code, because the
         * variable type is important when the compiler optimizes a method.
         *
         * No-op if kind is already Object.
         */
        frame.getFrameDescriptor().setFrameSlotKind(frameSlot, FrameSlotKind.Object);

        frame.setObject(frameSlot, value);
        return value;
    }

    public abstract void executeWrite(VirtualFrame frame, Object name, Object value);

    @Override
    public boolean hasTag(Class<? extends Tag> tag) {
        return tag == WriteVariableTag.class || super.hasTag(tag);
    }

}
