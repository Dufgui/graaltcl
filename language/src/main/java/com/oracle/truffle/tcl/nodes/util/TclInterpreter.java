package com.oracle.truffle.tcl.nodes.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

public class TclInterpreter {

  public static String interpret(Path file) throws IOException {
    ProcessBuilder pb = new ProcessBuilder("tclsh", file.toString());
    Process process = pb.start();
    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

    StringBuilder builder = new StringBuilder();
    String line = null;
    while ((line = reader.readLine()) != null) {
      builder.append(line);
      builder.append(System.getProperty("line.separator"));
    }

    return builder.toString();
  }
}
