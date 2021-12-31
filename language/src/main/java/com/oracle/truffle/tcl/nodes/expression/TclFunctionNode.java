package com.oracle.truffle.tcl.nodes.expression;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.tcl.nodes.TclExpressionNode;
import com.oracle.truffle.tcl.runtime.TclFunction;

public abstract class TclFunctionNode extends TclExpressionNode {
    public abstract TclFunction executeGeneric(VirtualFrame frame);
}
