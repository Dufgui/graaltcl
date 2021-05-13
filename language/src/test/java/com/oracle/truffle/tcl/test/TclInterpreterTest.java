package com.oracle.truffle.tcl.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.oracle.truffle.tcl.nodes.util.TclInterpreter;

public class TclInterpreterTest {

  @Test
  public void interpretHelloWorld() throws IOException {
    assertEquals("Hello World!\n", TclInterpreter.interpret(new File("tests/HelloWorld.tcl").toPath()));
  }
}
