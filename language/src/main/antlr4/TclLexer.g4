lexer grammar TclLexer;
// lexer

COMMENT
:
    '#' ~[\r\n]* -> skip
;

WS
:
    [ \t\u000C]+ -> skip
;

NL
:
    [\r\n]+
;

fragment
LETTER
:
    [A-Z]
    | [a-z]
    | '_'
;

fragment
NON_ZERO_DIGIT
:
    [1-9]
;

fragment
DIGIT
:
    [0-9]
;

fragment
HEX_DIGIT
:
    [0-9]
    | [a-f]
    | [A-F]
;

fragment
OCT_DIGIT
:
    [0-7]
;

fragment
BINARY_DIGIT
:
    '0'
    | '1'
;

fragment
TAB
:
    '\t'
;

fragment
STRING_CHAR
:
    ~( '"' | '\\' | '\r' | '\n' )
;

fragment EscapeSequence
    : '\\' ('"')
    ;

// Commands

PROC: 'proc';
BREAK: 'break';
CONTINUE: 'continue';
WHILE: 'while';
IF: 'if';
THEN: 'then';
ELSEIF: 'elseif';
ELSE: 'else';
RETURN: 'return';
SET: 'set';
LIST: 'list';

// Operators

OR:                       '||';
AND:                      '&&';

GT:                       '>';
LT:                       '<';
LE:                       '<=';
GE:                       '>=';
EQUAL:                    '==';
NOTEQUAL:                 '!=';
EQ:                       'eq';
NE:                       'ne';

MUL:                      '*';
DIV:                      '/';
ADD:                      '+';
SUB:                      '-';
MOD:                      '%';
POW:                      '**';
NS:                       '::';

DOLLAR: '$';
EOC: ';';
OPEN_BRACE: '{';
CLOSE_BRACE: '}';
OPEN_PARENTHESIS: '(';
CLOSE_PARENTHESIS: ')';
OPEN_BRACKET: '[' -> pushMode(DEFAULT_MODE);
CLOSE_BRACKET: ']' -> popMode;

OPEN_STRING: '"' -> pushMode(STRING); // Switch context

IDENTIFIER
:
    (
        LETTER+ DIGIT+
        | DIGIT+ LETTER+
        | LETTER+
    )+
;

INTEGER_LITERAL
:
    DIGIT+
;

DOUBLE_LITERAL
:
    DIGIT+ '.' DIGIT+
;

BOOLEAN_LITERAL
:
    'false'
    | 'no'
    | 'n'
    | 'off'
    | 'true'
    | 'yes'
    | 'y'
    | 'on'
;

mode STRING;

STRING_NEWLINE:   '\\' '\r'? '\n'         ;
STRING_ESCAPE:    EscapeSequence          -> type(STRING_VALUE);
CLOSE_STRING:     '"'                     -> popMode;
OPEN_BRACKET_IN_STRING: '['               -> pushMode(DEFAULT_MODE);
STRING_VALUE:     ~["[]+                 ;
