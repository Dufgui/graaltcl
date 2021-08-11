package com.oracle.truffle.tcl.generator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.regex.Pattern;

public class GenerateG4 {

    public static void main(String[] args) throws IOException {
        String g4File = Files.readString(Paths.get("src/main/antlr4/", args[0] + ".g4"));

        g4File = g4File.replace("grammar " + args[0] + ";", "grammar " + args[1] + ";");

        g4File = Pattern.compile("@parser.*// parser", Pattern.DOTALL)//
                .matcher(g4File).replaceFirst("// parser");

        String[] g4FileSplit = g4File.split("// parser");
        g4File = g4FileSplit[0] + "// parser" + Pattern.compile("\\s+\\{(?:(?:\\{.*?\\})|.)*?\\}", Pattern.DOTALL)//
                .matcher(g4FileSplit[1]).replaceAll("");

        g4File = Pattern.compile("returns.[^:]*+:", Pattern.DOTALL)//
                .matcher(g4File).replaceAll("\n:");

        g4FileSplit = g4File.split("// lexer");
        g4File = Pattern.compile("\\s+\\[.*?\\]", Pattern.DOTALL)//
                .matcher(g4FileSplit[0]).replaceAll("") + "// lexer" + g4FileSplit[1];

        Path output = Paths.get("target/generated-test-sources/antlr4/", args[1] + ".g4");
        Files.createDirectories(output.getParent());
        Files.writeString(output, g4File, StandardOpenOption.CREATE);
    }
}
