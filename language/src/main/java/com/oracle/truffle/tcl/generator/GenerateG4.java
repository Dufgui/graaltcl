package com.oracle.truffle.tcl.generator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.regex.Pattern;

public class GenerateG4 {

    public static void main(String[] args) throws IOException {
        String parser = args[0];
        String lexer = args[1];
        String testPrefix = args[2];

        String g4ParserFile = Files.readString(Paths.get("src/main/antlr4/", parser + ".g4"));
        String g4LexerFile = Files.readString(Paths.get("src/main/antlr4/", lexer + ".g4"));

        g4ParserFile = g4ParserFile.replace("grammar " + parser + ";", "grammar " + testPrefix + parser + ";");
        g4ParserFile = g4ParserFile.replace("options { tokenVocab=" + lexer + "; }",
                "options { tokenVocab=" + testPrefix + lexer + "; }");

        g4ParserFile = Pattern.compile("@parser.*// parser", Pattern.DOTALL)//
                .matcher(g4ParserFile).replaceFirst("// parser");

        String[] g4FileSplit = g4ParserFile.split("// parser");
        g4ParserFile = g4FileSplit[0] + "// parser" + Pattern.compile("\\s+\\{(?:(?:\\{.*?\\})|.)*?\\}", Pattern.DOTALL)//
                .matcher(g4FileSplit[1]).replaceAll("");

        g4ParserFile = Pattern.compile("returns.[^:]*+:", Pattern.DOTALL)//
                .matcher(g4ParserFile).replaceAll("\n:");

        g4ParserFile = Pattern.compile("\\[\\$result,", Pattern.DOTALL)//
                .matcher(g4ParserFile).replaceAll("[null,");

        g4ParserFile = Pattern.compile("\\s+\\[.*?\\]", Pattern.DOTALL)//
                .matcher(g4ParserFile).replaceAll("");

        g4LexerFile = g4LexerFile.replace("grammar " + lexer + ";", "grammar " + testPrefix + lexer + ";");

        Path outputParser = Paths.get("target/generated-test-sources/antlr4/", testPrefix + parser + ".g4");
        Path outputLexer = Paths.get("target/generated-test-sources/antlr4/", testPrefix + lexer + ".g4");
        Files.createDirectories(outputParser.getParent());
        Files.writeString(outputParser, g4ParserFile, StandardOpenOption.CREATE);
        Files.writeString(outputLexer, g4LexerFile, StandardOpenOption.CREATE);
    }
}
