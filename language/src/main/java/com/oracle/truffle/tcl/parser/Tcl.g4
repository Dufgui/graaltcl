/*
 * The parser and lexer need to be generated using "mx create-tcl-parser".
 */
grammar Tcl;

@parser::header
{
// DO NOT MODIFY - generated from Tcl.g4 using "mx create-tcl-parser"
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.oracle.truffle.api.source.Source;
import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.tcl.TclLanguage;
import com.oracle.truffle.tcl.nodes.TclExpressionNode;
import com.oracle.truffle.tcl.nodes.TclRootNode;
import com.oracle.truffle.tcl.nodes.TclStatementNode;
import com.oracle.truffle.tcl.parser.TclParseError;
}

@lexer::header
{
// DO NOT MODIFY - generated from SimpleLanguage.g4 using "mx create-tcl-parser"
}

@parser::members
{
private TclNodeFactory factory;
private Source source;
private static final class BailoutErrorListener extends BaseErrorListener {
    private final Source source;
    BailoutErrorListener(Source source) {
        this.source = source;
    }
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        throwParseError(source, line, charPositionInLine, (Token) offendingSymbol, msg);
    }
}

public void SemErr(Token token, String message) {
    assert token != null;
    throwParseError(source, token.getLine(), token.getCharPositionInLine(), token, message);
}

private static void throwParseError(Source source, int line, int charPositionInLine, Token token, String message) {
    int col = charPositionInLine + 1;
    String location = "-- line " + line + " col " + col + ": ";
    int length = token == null ? 1 : Math.max(token.getStopIndex() - token.getStartIndex(), 0);
    throw new TclParseError(source, line, col, length, String.format("Error(s) parsing script:%n" + location + message));
}

public static Map<String, RootCallTarget> parseTcl( TclLanguage language, Source source) {
    TclLexer lexer = new TclLexer(CharStreams.fromString(source.getCharacters().toString()));
    TclParser parser = new TclParser(new CommonTokenStream(lexer));
    lexer.removeErrorListeners();
    parser.removeErrorListeners();
    BailoutErrorListener listener = new BailoutErrorListener(source);
    lexer.addErrorListener(listener);
    parser.addErrorListener(listener);
    parser.factory = new TclNodeFactory(language, source);
    parser.source = source;
    parser.tcl();
    return parser.factory.getAllFunctions();
}
}

// parser

tcl
:
CR*
function* module
CR*
EOF
;


function
:
'proc'
IDENTIFIER
s='{'
                                                { factory.startFunction($IDENTIFIER, $s); }
(
    IDENTIFIER                                  { factory.addFormalParameter($IDENTIFIER); }
    (
        ','
        IDENTIFIER                              { factory.addFormalParameter($IDENTIFIER); }
    )*
)?
'}'
body=block[false]                               { factory.finishFunction($body.result); }
;



block [boolean inLoop] returns [TclStatementNode result]
:                                               { factory.startBlock();
                                                  List<TclStatementNode> body = new ArrayList<>(); }
s='{' CR*
(
    statement[inLoop]                           { body.add($statement.result); }
)?
(
    CR+
    statement[inLoop]                           { body.add($statement.result); }
)*
CR*
e='}'
                                                { $result = factory.finishBlock(body, $s.getStartIndex(), $e.getStopIndex() - $s.getStartIndex() + 1); }
CR*
;

module
:
(
    statement[false]                           { factory.addModuleStatement($statement.result); }
    CR+
)*
;

//it's a TCL command
statement [boolean inLoop] returns [TclStatementNode result]
:
(
    while_statement                             { $result = $while_statement.result; }
|
    b='break'                                   { if (inLoop) { $result = factory.createBreak($b); } else { SemErr($b, "break used outside of loop"); } }
|
    c='continue'                                { if (inLoop) { $result = factory.createContinue($c); } else { SemErr($c, "continue used outside of loop"); } }
|
    if_statement[inLoop]                        { $result = $if_statement.result; }
|
    return_statement                            { $result = $return_statement.result; }
|
    expression                                  { $result = $expression.result; }
)
';'?
;


while_statement returns [TclStatementNode result]
:
w='while'
condition=expression
body=block[true]                                { $result = factory.createWhile($w, $condition.result, $body.result); }
;


if_statement [boolean inLoop] returns [TclStatementNode result]
:
i='if'
condition=expression
'then'?
then=block[inLoop]                              { TclStatementNode elsePart = null; }
(
'elseif'
condition2=expression                           //TODO manage elseif
'then'?
then2=block[inLoop]
)*
(
    'else'?
    block[inLoop]                               { elsePart = $block.result; }
)?                                              { $result = factory.createIf($i, $condition.result, $then.result, elsePart); }
;


return_statement returns [TclStatementNode result]
:
r='return'                                      { TclExpressionNode value = null; }
(
    expression                                  { value = $expression.result; }
)?                                              { $result = factory.createReturn($r, value); }
';'?
;

expression returns [TclExpressionNode result]
:
'{'
logic_term                                      { $result = $logic_term.result; }
(
    op='||'
    logic_term                                  { $result = factory.createBinary($op, $result, $logic_term.result); }
)*
'}'
|
logic_term                                      { $result = $logic_term.result; }
(
    op='||'
    logic_term                                  { $result = factory.createBinary($op, $result, $logic_term.result); }
)*
;


logic_term returns [TclExpressionNode result]
:
logic_factor                                    { $result = $logic_factor.result; }
(
    op='&&'
    logic_factor                                { $result = factory.createBinary($op, $result, $logic_factor.result); }
)*
;


logic_factor returns [TclExpressionNode result]
:
arithmetic                                      { $result = $arithmetic.result; }
(
    op=('<' | '<=' | '>' | '>=' | '==' | '!=' | 'eq' | 'ne' )
    arithmetic                                  { $result = factory.createBinary($op, $result, $arithmetic.result); }
)?
;


arithmetic returns [TclExpressionNode result]
:
term_add                                        { $result = $term_add.result; }
(
    op=('+' | '-')
    term_add                                    { $result = factory.createBinary($op, $result, $term_add.result); }
)*
;


term_add returns [TclExpressionNode result]
:
term_pot                                        { $result = $term_pot.result; }
(
    op=('*' | '/' | '%')
    term_pot                                    { $result = factory.createBinary($op, $result, $term_pot.result); }
)*
;

term_pot returns [TclExpressionNode result]
:
term                                            { $result = $term.result; }
(
    op='**'
    term                                        { $result = factory.createBinary($op, $result, $term.result); }
)*
;


term returns [TclExpressionNode result]
:
(
    '$' IDENTIFIER                              { TclExpressionNode assignmentName = factory.createStringLiteral($IDENTIFIER, false); }
|
    IDENTIFIER                                  { TclExpressionNode assignmentName = factory.createStringLiteral($IDENTIFIER, false); }
    member_expression[$IDENTIFIER, assignmentName] { $result = $member_expression.result; }
|
    word
|
    s='['
    expr=statement[false]
    e=']'                                       { $result = factory.createParenExpression($expr.result, $s.getStartIndex(), $e.getStopIndex() - $s.getStartIndex() + 1); }
)
;

word returns [TclExpressionNode result]
:
(
    STRING_LITERAL                              { $result = factory.createStringLiteral($STRING_LITERAL, true); }
|
    INTEGER_LITERAL                             { $result = factory.createIntegerLiteral($INTEGER_LITERAL); }
|
    DOUBLE_LITERAL                              { $result = factory.createDoubleLiteral($DOUBLE_LITERAL); }
|
    BOOLEAN_LITERAL                             { $result = factory.createBooleanLiteral($BOOLEAN_LITERAL); }
)
;


member_expression [Token start, TclExpressionNode assignmentName] returns [TclExpressionNode result]
:                                               { TclExpressionNode receiver = r;
                                                  TclExpressionNode nestedAssignmentName = null; }
(
    '::'                                         { if (receiver == null) {
                                                       receiver = factory.createRead(assignmentName);
                                                  } }
    IDENTIFIER
                                                { nestedAssignmentName = factory.createStringLiteral($IDENTIFIER, false);
                                                  $result = factory.createReadProperty(receiver, nestedAssignmentName); }
|
    '('                                         { if (receiver == null) {
                                                      receiver = factory.createRead(assignmentName);
                                                  } }
    expression
                                                { nestedAssignmentName = $expression.result;
                                                  $result = factory.createReadProperty(receiver, nestedAssignmentName); }
    ')'
|
                                             { List<TclExpressionNode> parameters = new ArrayList<>();
                                                  Token end = start;
                                                  if (receiver == null) {
                                                      receiver = factory.createRead(assignmentName);
                                                  } }
    (
        end=expression                              { parameters.add($expression.result); }
    )*

                                                { $result = factory.createCall(receiver, parameters, end); }
)
;

// lexer

COMMENT	:	'#' ~[\r\n]*	->	skip  ;
WS : [ \t\u000C]+ -> skip;
CR : [\r\n]+;

fragment LETTER : [A-Z] | [a-z] | '_';
fragment NON_ZERO_DIGIT : [1-9];
fragment DIGIT : [0-9];
fragment HEX_DIGIT : [0-9] | [a-f] | [A-F];
fragment OCT_DIGIT : [0-7];
fragment BINARY_DIGIT : '0' | '1';
fragment TAB : '\t';
fragment STRING_CHAR : ~('"' | '\\' | '\r' | '\n');

IDENTIFIER : LETTER (LETTER | DIGIT)*;
STRING_LITERAL : '"' STRING_CHAR* '"';
INTEGER_LITERAL	:	DIGIT+  ;
DOUBLE_LITERAL	:	DIGIT+ '.' DIGIT+ ;
BOOLEAN_LITERAL	:	'false' | 'no' | 'n' | 'off' | 'true' | 'yes' | 'y' | 'on';


