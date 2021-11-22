/*
 * The parser and lexer need to be generated using "mx create-tcl-parser".
 */
parser grammar TclParser;

options { tokenVocab=TclLexer; }

@parser::header {
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

@lexer::header {
// DO NOT MODIFY - generated from Tcl.g4 using "mx create-tcl-parser"
}

@parser::members {
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
    NL*
    (
        function
        | command [false]
        { factory.addModuleStatement($command.result); }
        (NL+ | EOC)
    )* NL* EOF
    { factory.finishModule(); }

;

function
:
    PROC IDENTIFIER s = OPEN_BRACE
    { factory.startFunction($IDENTIFIER, $s); }

    (
        IDENTIFIER
        { factory.addFormalParameter($IDENTIFIER); }

        (
            IDENTIFIER
            { factory.addFormalParameter($IDENTIFIER); }

        )*
    )? CLOSE_BRACE body = block [false]
    { factory.finishFunction($body.result); }

;

block [boolean inLoop] returns [TclExpressionNode result]
:
{ factory.startBlock();
                                                  List<TclExpressionNode> body = new ArrayList<>(); }

    s = OPEN_BRACE NL*
    (
        command [inLoop]
        { body.add($command.result); }

    )?
    (
        NL+ command [inLoop]
        { body.add($command.result); }

    )* NL* e = CLOSE_BRACE
    { $result = factory.finishBlock(body, $s.getStartIndex(), $e.getStopIndex() - $s.getStartIndex() + 1); }

    NL*
;

command [boolean inLoop] returns [TclExpressionNode result]
:
    (
        while_command
        { $result = $while_command.result; }

        | b = BREAK
        { if (inLoop) { $result = factory.createBreak($b); } else { SemErr($b, "break used outside of loop"); } }

        | c = CONTINUE
        { if (inLoop) { $result = factory.createContinue($c); } else { SemErr($c, "continue used outside of loop"); } }

        | if_command [inLoop]
        { $result = $if_command.result; }

        | return_command
        { $result = $return_command.result; }

        | set_command
        { $result = $set_command.result; }

        | DOLLAR var =
        (
            IDENTIFIER
            | INTEGER_LITERAL
        )
        { TclExpressionNode assignmentName = factory.createStringLiteral($var, false); }
        command_parameters [$var, assignmentName]
        { $result = $command_parameters.result; }

        | IDENTIFIER
        { TclExpressionNode assignmentName = factory.createStringLiteral($IDENTIFIER, false); }

        (
            member_expression [null, null, assignmentName]
            { $result = $member_expression.result; }

            |
            { $result = factory.createIdentifier(assignmentName); }

        )
        (
            command_parameters [$IDENTIFIER, assignmentName]
            { $result = $command_parameters.result; }

        )

    ) EOC?
;

while_command returns [TclExpressionNode result]
:
    w = WHILE condition = expression body = block [true]
    { $result = factory.createWhile($w, $condition.result, $body.result); }

;

if_command [boolean inLoop] returns [TclExpressionNode result]
:
    i = IF condition = expression THEN? then = block [inLoop]
    { TclExpressionNode elsePart = null; }

    (
        ELSEIF condition2 = expression //TODO manage elseif
        THEN? then2 = block [inLoop]
    )*
    (
        ELSE? block [inLoop]
        { elsePart = $block.result; }

    )?
    { $result = factory.createIf($i, $condition.result, $then.result, elsePart); }

;

return_command returns [TclExpressionNode result]
:
    r = RETURN
    { TclExpressionNode value = null; }

    (
        expression
        { value = $expression.result; }

    )?
    { $result = factory.createReturn($r, value); }
;

set_command returns [TclExpressionNode result]
:
    SET
    name = expression
    { TclExpressionNode name = $name.result; }
    value = expression
    { TclExpressionNode value = $value.result; }
    { $result = factory.createAssignment(name, value); }
;

expression returns [TclExpressionNode result]
:
    OPEN_BRACE NL* expression
    { $result = $expression.result; }
NL*
    CLOSE_BRACE
    | OPEN_PARENTHESIS NL* expression
    { $result = $expression.result; }
NL*
    CLOSE_PARENTHESIS
    | left = expression NL* op = OR NL* right = expression
    { $result = factory.createBinary($op, $left.result, $right.result); }

    | left = expression NL* op = AND NL* right = expression
    { $result = factory.createBinary($op, $left.result, $right.result); }

    | left = expression NL* op =
    (
        LT
        | LE
        | GT
        | GE
        | EQUAL
        | NOTEQUAL
        | EQ
        | NE
    ) NL* right = expression
    { $result = factory.createBinary($op, $left.result, $right.result); }

    | left = expression NL* op =
    (
        MUL
        | DIV
        | MOD
    ) NL* right = expression
    { $result = factory.createBinary($op, $left.result, $right.result); }

    | left = expression NL* op =
    (
        ADD
        | SUB
    ) NL* right = expression
    { $result = factory.createBinary($op, $left.result, $right.result); }

    | left = expression NL* op = POW NL* right = expression
    { $result = factory.createBinary($op, $left.result, $right.result); }

    | term
    { $result = $term.result; }

;

term returns [TclExpressionNode result]
:
    (
        DOLLAR var =
        (
            IDENTIFIER
            | INTEGER_LITERAL
        )
        { TclExpressionNode assignmentName = factory.createStringLiteral($var, false);
                                                    $result = factory.createRead(assignmentName);
                                                }
        | subExpression
        { $result = $subExpression.result; }
        | word
        { $result = $word.result; }

        | string
        { $result = $string.result; }

    )
;

subExpression returns [TclExpressionNode result]
:
    s = (OPEN_BRACKET | OPEN_BRACKET_IN_STRING) NL*
    exp = command [false] NL*
    e = CLOSE_BRACKET
    { $result = factory.createParentExpression($exp.result, $s.getStartIndex(), $e.getStopIndex() - $s.getStartIndex() + 1); }
;

word returns [TclExpressionNode result]
:
    (
        INTEGER_LITERAL
        { $result = factory.createIntegerLiteral($INTEGER_LITERAL); }

        | DOUBLE_LITERAL
        { $result = factory.createDoubleLiteral($DOUBLE_LITERAL); }

        | BOOLEAN_LITERAL
        { $result = factory.createBooleanLiteral($BOOLEAN_LITERAL); }

        | IDENTIFIER
        { $result = factory.createStringLiteral($IDENTIFIER, false); }

    )
;

string returns [TclExpressionNode result]
:
    OPEN_STRING
    (
        stringContent
        { $result = $stringContent.result; }
        CLOSE_STRING
    |
        CLOSE_STRING
        { $result = factory.createEmptyStringLiteral($OPEN_STRING, $CLOSE_STRING); }
    )
;

stringContent returns [TclExpressionNode result]
:
    (
        subExpression
        { TclExpressionNode first = $subExpression.result; }
        stringContent
        { TclExpressionNode second = $stringContent.result; }
        { $result = factory.createConcat(first, second); }
    |
        STRING_VALUE
        { TclExpressionNode first = factory.createStringLiteral($STRING_VALUE, false); }
        stringContent
        { TclExpressionNode second = $stringContent.result; }
        { $result = factory.createConcat(first, second); }
    |
        STRING_VALUE
        { TclExpressionNode first = factory.createStringLiteral($STRING_VALUE, false); }
        subExpression
        { TclExpressionNode second = $subExpression.result; }
        { $result = factory.createConcat(first, second); }
    |
        subExpression
        { TclExpressionNode first = $subExpression.result; }
        STRING_VALUE
        { TclExpressionNode second = factory.createStringLiteral($STRING_VALUE, false); }
        { $result = factory.createConcat(first, second); }
    |
        STRING_VALUE
        { $result = factory.createStringLiteral($STRING_VALUE, false); }
    |
        subExpression
        { $result = $subExpression.result; }
    )
;

member_expression
[TclExpressionNode r, TclExpressionNode assignmentReceiver, TclExpressionNode assignmentName]
returns[TclExpressionNode result]
:
{ TclExpressionNode receiver = r;
                                                  TclExpressionNode nestedAssignmentName = null; }

    (
        NS
        { if (receiver == null) {
                                                       receiver = factory.createRead(assignmentName);
                                                  } }

        IDENTIFIER
        { nestedAssignmentName = factory.createStringLiteral($IDENTIFIER, false);
                                                  $result = factory.createReadProperty(receiver, nestedAssignmentName); }

        (
            member_expression [$result, receiver, nestedAssignmentName]
            { $result = $member_expression.result; }

        )?
        | OPEN_PARENTHESIS
        { if (receiver == null) {
                                                      receiver = factory.createRead(assignmentName);
                                                  } }

        expression
        { nestedAssignmentName = $expression.result;
                                                  $result = factory.createReadProperty(receiver, nestedAssignmentName); }

        CLOSE_PARENTHESIS
    )
;

command_parameters [Token start, TclExpressionNode assignmentName] returns
[TclExpressionNode result]
:
    { TclExpressionNode nestedAssignmentName = null; }

    { List<TclExpressionNode> parameters = new ArrayList<>();
                                                  Token end = start;
                                                  TclExpressionNode receiver = factory.createCommand(assignmentName);
                                             }

    (
        end = expression
        { parameters.add($expression.result); }

    )*
    { $result = factory.createCall(receiver, parameters, end); }
;
