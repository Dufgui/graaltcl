// Generated from language/src/main/java/com/oracle/truffle/tcl/parser/Tcl.g4 by ANTLR 4.7.1
package com.oracle.truffle.tcl.parser;

// DO NOT MODIFY - generated from Tcl.g4 using "mx create-tcl-parser"
// DO NOT MODIFY - generated from Tcl.g4 using "mx create-tcl-parser"
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;

import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.source.Source;
import com.oracle.truffle.tcl.TclLanguage;
import com.oracle.truffle.tcl.nodes.TclExpressionNode;
import com.oracle.truffle.tcl.nodes.TclStatementNode;

@SuppressWarnings({
        "all",
        "warnings",
        "unchecked",
        "unused",
        "cast" })
public class TclParser
        extends
        Parser {

    static {
        RuntimeMetaData
                .checkVersion(
                        "4.7.1",
                        RuntimeMetaData.VERSION);
    }

    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache = new PredictionContextCache();
    public static final int T__0 = 1,
            T__1 = 2,
            T__2 = 3,
            T__3 = 4,
            T__4 = 5,
            T__5 = 6,
            T__6 = 7,
            T__7 = 8,
            T__8 = 9,
            T__9 = 10,
            T__10 = 11,
            T__11 = 12,
            T__12 = 13,
            T__13 = 14,
            T__14 = 15,
            T__15 = 16,
            T__16 = 17,
            T__17 = 18,
            T__18 = 19,
            T__19 = 20,
            T__20 = 21,
            T__21 = 22,
            T__22 = 23,
            T__23 = 24,
            T__24 = 25,
            T__25 = 26,
            T__26 = 27,
            T__27 = 28,
            T__28 = 29,
            T__29 = 30,
            T__30 = 31,
            T__31 = 32,
            T__32 = 33,
            T__33 = 34,
            COMMENT = 35,
            WS = 36,
            IDENTIFIER = 37,
            STRING_LITERAL = 38,
            INTEGER_LITERAL = 39,
            DOUBLE_LITERAL = 40;
    public static final int RULE_tcl = 0,
            RULE_function = 1,
            RULE_block = 2,
            RULE_module = 3,
            RULE_statement = 4,
            RULE_while_statement = 5,
            RULE_if_statement = 6,
            RULE_return_statement = 7,
            RULE_expression = 8,
            RULE_logic_term = 9,
            RULE_logic_factor = 10,
            RULE_arithmetic = 11,
            RULE_term_add = 12,
            RULE_term_pot = 13,
            RULE_term = 14,
            RULE_member_expression = 15;
    public static final String[] ruleNames = {
            "tcl",
            "function",
            "block",
            "module",
            "statement",
            "while_statement",
            "if_statement",
            "return_statement",
            "expression",
            "logic_term",
            "logic_factor",
            "arithmetic",
            "term_add",
            "term_pot",
            "term",
            "member_expression" };

    private static final String[] _LITERAL_NAMES = {
            null,
            "'proc'",
            "'{'",
            "','",
            "'}'",
            "'break'",
            "';'",
            "'continue'",
            "'while'",
            "'if'",
            "'then'",
            "'else'",
            "'return'",
            "'||'",
            "'&&'",
            "'<'",
            "'<='",
            "'>'",
            "'>='",
            "'=='",
            "'!='",
            "'eq'",
            "'ne'",
            "'+'",
            "'-'",
            "'*'",
            "'/'",
            "'%'",
            "'**'",
            "'('",
            "')'",
            "'='",
            "'.'",
            "'['",
            "']'" };
    private static final String[] _SYMBOLIC_NAMES = {
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            "COMMENT",
            "WS",
            "IDENTIFIER",
            "STRING_LITERAL",
            "INTEGER_LITERAL",
            "DOUBLE_LITERAL" };
    public static final Vocabulary VOCABULARY = new VocabularyImpl(
            _LITERAL_NAMES,
            _SYMBOLIC_NAMES);

    /**
     * @deprecated Use {@link #VOCABULARY} instead.
     */
    @Deprecated
    public static final String[] tokenNames;
    static {
        tokenNames = new String[_SYMBOLIC_NAMES.length];
        for (int i = 0; i < tokenNames.length; i++) {
            tokenNames[i] = VOCABULARY
                    .getLiteralName(
                            i);
            if (tokenNames[i] == null) {
                tokenNames[i] = VOCABULARY
                        .getSymbolicName(
                                i);
            }

            if (tokenNames[i] == null) {
                tokenNames[i] = "<INVALID>";
            }
        }
    }

    @Override
    @Deprecated
    public String[] getTokenNames() {
        return tokenNames;
    }

    @Override

    public Vocabulary getVocabulary() {
        return VOCABULARY;
    }

    @Override
    public String getGrammarFileName() {
        return "Tcl.g4";
    }

    @Override
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override
    public String getSerializedATN() {
        return _serializedATN;
    }

    @Override
    public ATN getATN() {
        return _ATN;
    }

    private TclNodeFactory factory;
    private Source source;

    private static final class BailoutErrorListener
            extends
            BaseErrorListener {

        private final Source source;

        BailoutErrorListener(
                Source source) {
            this.source = source;
        }

        @Override
        public void syntaxError(
                Recognizer<?, ?> recognizer,
                Object offendingSymbol,
                int line,
                int charPositionInLine,
                String msg,
                RecognitionException e) {
            throwParseError(
                    source,
                    line,
                    charPositionInLine,
                    (Token) offendingSymbol,
                    msg);
        }
    }

    public void SemErr(
            Token token,
            String message) {
        assert token != null;
        throwParseError(
                source,
                token.getLine(),
                token.getCharPositionInLine(),
                token,
                message);
    }

    private static void throwParseError(
            Source source,
            int line,
            int charPositionInLine,
            Token token,
            String message) {
        int col = charPositionInLine
                + 1;
        String location = "-- line "
                + line
                + " col "
                + col
                + ": ";
        int length = token == null
                ? 1
                : Math.max(
                        token.getStopIndex()
                                - token.getStartIndex(),
                        0);
        throw new TclParseError(
                source,
                line,
                col,
                length,
                String.format(
                        "Error(s) parsing script:%n"
                                + location
                                + message));
    }

    public static Map<String, RootCallTarget> parseTcl(
            TclLanguage language,
            Source source) {
        TclLexer lexer = new TclLexer(
                CharStreams
                        .fromString(
                                source.getCharacters()
                                        .toString()));
        TclParser parser = new TclParser(
                new CommonTokenStream(
                        lexer));
        lexer.removeErrorListeners();
        parser.removeErrorListeners();
        BailoutErrorListener listener = new BailoutErrorListener(
                source);
        lexer.addErrorListener(
                listener);
        parser.addErrorListener(
                listener);
        parser.factory = new TclNodeFactory(
                language,
                source);
        parser.source = source;
        parser.tcl();
        return parser.factory
                .getAllFunctions();
    }

    public TclParser(
            TokenStream input) {
        super(input);
        _interp = new ParserATNSimulator(
                this,
                _ATN,
                _decisionToDFA,
                _sharedContextCache);
    }

    public static class TclContext
            extends
            ParserRuleContext {

        public List<FunctionContext> function() {
            return getRuleContexts(
                    FunctionContext.class);
        }

        public FunctionContext function(
                int i) {
            return getRuleContext(
                    FunctionContext.class,
                    i);
        }

        public ModuleContext module() {
            return getRuleContext(
                    ModuleContext.class,
                    0);
        }

        public TerminalNode EOF() {
            return getToken(
                    TclParser.EOF,
                    0);
        }

        public TclContext(
                ParserRuleContext parent,
                int invokingState) {
            super(parent,
                    invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_tcl;
        }
    }

    public final TclContext tcl()
            throws RecognitionException {
        TclContext _localctx = new TclContext(
                _ctx,
                getState());
        enterRule(
                _localctx,
                0,
                RULE_tcl);
        int _la;
        try {
            enterOuterAlt(
                    _localctx,
                    1);
            {
                setState(
                        32);
                function();
                setState(
                        36);
                _errHandler
                        .sync(this);
                _la = _input
                        .LA(1);
                while (_la == T__0) {
                    {
                        {
                            setState(
                                    33);
                            function();
                        }
                    }
                    setState(
                            38);
                    _errHandler
                            .sync(this);
                    _la = _input
                            .LA(1);
                }
                setState(
                        39);
                module();
                setState(
                        40);
                match(EOF);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler
                    .reportError(
                            this,
                            re);
            _errHandler
                    .recover(
                            this,
                            re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class FunctionContext
            extends
            ParserRuleContext {

        public Token IDENTIFIER;
        public Token s;
        public BlockContext body;

        public List<TerminalNode> IDENTIFIER() {
            return getTokens(
                    TclParser.IDENTIFIER);
        }

        public TerminalNode IDENTIFIER(
                int i) {
            return getToken(
                    TclParser.IDENTIFIER,
                    i);
        }

        public BlockContext block() {
            return getRuleContext(
                    BlockContext.class,
                    0);
        }

        public FunctionContext(
                ParserRuleContext parent,
                int invokingState) {
            super(parent,
                    invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_function;
        }
    }

    public final FunctionContext function()
            throws RecognitionException {
        FunctionContext _localctx = new FunctionContext(
                _ctx,
                getState());
        enterRule(
                _localctx,
                2,
                RULE_function);
        int _la;
        try {
            enterOuterAlt(
                    _localctx,
                    1);
            {
                setState(
                        42);
                match(T__0);
                setState(
                        43);
                ((FunctionContext) _localctx).IDENTIFIER = match(
                        IDENTIFIER);
                setState(
                        44);
                ((FunctionContext) _localctx).s = match(
                        T__1);
                factory.startFunction(
                        ((FunctionContext) _localctx).IDENTIFIER,
                        ((FunctionContext) _localctx).s);
                setState(
                        56);
                _errHandler
                        .sync(this);
                _la = _input
                        .LA(1);
                if (_la == IDENTIFIER) {
                    {
                        setState(
                                46);
                        ((FunctionContext) _localctx).IDENTIFIER = match(
                                IDENTIFIER);
                        factory.addFormalParameter(
                                ((FunctionContext) _localctx).IDENTIFIER);
                        setState(
                                53);
                        _errHandler
                                .sync(this);
                        _la = _input
                                .LA(1);
                        while (_la == T__2) {
                            {
                                {
                                    setState(
                                            48);
                                    match(T__2);
                                    setState(
                                            49);
                                    ((FunctionContext) _localctx).IDENTIFIER = match(
                                            IDENTIFIER);
                                    factory.addFormalParameter(
                                            ((FunctionContext) _localctx).IDENTIFIER);
                                }
                            }
                            setState(
                                    55);
                            _errHandler
                                    .sync(this);
                            _la = _input
                                    .LA(1);
                        }
                    }
                }

                setState(
                        58);
                match(T__3);
                setState(
                        59);
                ((FunctionContext) _localctx).body = block(
                        false);
                factory.finishFunction(
                        ((FunctionContext) _localctx).body.result);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler
                    .reportError(
                            this,
                            re);
            _errHandler
                    .recover(
                            this,
                            re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class BlockContext
            extends
            ParserRuleContext {

        public boolean inLoop;
        public TclStatementNode result;
        public Token s;
        public StatementContext statement;
        public Token e;

        public List<StatementContext> statement() {
            return getRuleContexts(
                    StatementContext.class);
        }

        public StatementContext statement(
                int i) {
            return getRuleContext(
                    StatementContext.class,
                    i);
        }

        public BlockContext(
                ParserRuleContext parent,
                int invokingState) {
            super(parent,
                    invokingState);
        }

        public BlockContext(
                ParserRuleContext parent,
                int invokingState,
                boolean inLoop) {
            super(parent,
                    invokingState);
            this.inLoop = inLoop;
        }

        @Override
        public int getRuleIndex() {
            return RULE_block;
        }
    }

    public final BlockContext block(
            boolean inLoop)
            throws RecognitionException {
        BlockContext _localctx = new BlockContext(
                _ctx,
                getState(),
                inLoop);
        enterRule(
                _localctx,
                4,
                RULE_block);
        int _la;
        try {
            enterOuterAlt(
                    _localctx,
                    1);
            {
                factory.startBlock();
                List<TclStatementNode> body = new ArrayList<>();
                setState(
                        63);
                ((BlockContext) _localctx).s = match(
                        T__1);
                setState(
                        69);
                _errHandler
                        .sync(this);
                _la = _input
                        .LA(1);
                while ((((_la)
                        & ~0x3f) == 0
                        && ((1L << _la)
                                & ((1L << T__4)
                                        | (1L << T__6)
                                        | (1L << T__7)
                                        | (1L << T__8)
                                        | (1L << T__11)
                                        | (1L << T__28)
                                        | (1L << IDENTIFIER)
                                        | (1L << STRING_LITERAL)
                                        | (1L << INTEGER_LITERAL)
                                        | (1L << DOUBLE_LITERAL))) != 0)) {
                    {
                        {
                            setState(
                                    64);
                            ((BlockContext) _localctx).statement = statement(
                                    inLoop);
                            body.add(
                                    ((BlockContext) _localctx).statement.result);
                        }
                    }
                    setState(
                            71);
                    _errHandler
                            .sync(this);
                    _la = _input
                            .LA(1);
                }
                setState(
                        72);
                ((BlockContext) _localctx).e = match(
                        T__3);
                ((BlockContext) _localctx).result = factory
                        .finishBlock(
                                body,
                                ((BlockContext) _localctx).s
                                        .getStartIndex(),
                                ((BlockContext) _localctx).e
                                        .getStopIndex()
                                        - ((BlockContext) _localctx).s
                                                .getStartIndex()
                                        + 1);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler
                    .reportError(
                            this,
                            re);
            _errHandler
                    .recover(
                            this,
                            re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ModuleContext
            extends
            ParserRuleContext {

        public StatementContext statement;

        public List<StatementContext> statement() {
            return getRuleContexts(
                    StatementContext.class);
        }

        public StatementContext statement(
                int i) {
            return getRuleContext(
                    StatementContext.class,
                    i);
        }

        public ModuleContext(
                ParserRuleContext parent,
                int invokingState) {
            super(parent,
                    invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_module;
        }
    }

    public final ModuleContext module()
            throws RecognitionException {
        ModuleContext _localctx = new ModuleContext(
                _ctx,
                getState());
        enterRule(
                _localctx,
                6,
                RULE_module);
        int _la;
        try {
            enterOuterAlt(
                    _localctx,
                    1);
            {
                setState(
                        80);
                _errHandler
                        .sync(this);
                _la = _input
                        .LA(1);
                while ((((_la)
                        & ~0x3f) == 0
                        && ((1L << _la)
                                & ((1L << T__4)
                                        | (1L << T__6)
                                        | (1L << T__7)
                                        | (1L << T__8)
                                        | (1L << T__11)
                                        | (1L << T__28)
                                        | (1L << IDENTIFIER)
                                        | (1L << STRING_LITERAL)
                                        | (1L << INTEGER_LITERAL)
                                        | (1L << DOUBLE_LITERAL))) != 0)) {
                    {
                        {
                            setState(
                                    75);
                            ((ModuleContext) _localctx).statement = statement(
                                    false);
                            factory.addModuleStatement(
                                    ((ModuleContext) _localctx).statement.result);
                        }
                    }
                    setState(
                            82);
                    _errHandler
                            .sync(this);
                    _la = _input
                            .LA(1);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler
                    .reportError(
                            this,
                            re);
            _errHandler
                    .recover(
                            this,
                            re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class StatementContext
            extends
            ParserRuleContext {

        public boolean inLoop;
        public TclStatementNode result;
        public While_statementContext while_statement;
        public Token b;
        public Token c;
        public If_statementContext if_statement;
        public Return_statementContext return_statement;
        public ExpressionContext expression;

        public While_statementContext while_statement() {
            return getRuleContext(
                    While_statementContext.class,
                    0);
        }

        public If_statementContext if_statement() {
            return getRuleContext(
                    If_statementContext.class,
                    0);
        }

        public Return_statementContext return_statement() {
            return getRuleContext(
                    Return_statementContext.class,
                    0);
        }

        public ExpressionContext expression() {
            return getRuleContext(
                    ExpressionContext.class,
                    0);
        }

        public StatementContext(
                ParserRuleContext parent,
                int invokingState) {
            super(parent,
                    invokingState);
        }

        public StatementContext(
                ParserRuleContext parent,
                int invokingState,
                boolean inLoop) {
            super(parent,
                    invokingState);
            this.inLoop = inLoop;
        }

        @Override
        public int getRuleIndex() {
            return RULE_statement;
        }
    }

    public final StatementContext statement(
            boolean inLoop)
            throws RecognitionException {
        StatementContext _localctx = new StatementContext(
                _ctx,
                getState(),
                inLoop);
        enterRule(
                _localctx,
                8,
                RULE_statement);
        try {
            enterOuterAlt(
                    _localctx,
                    1);
            {
                setState(
                        102);
                _errHandler
                        .sync(this);
                switch (_input
                        .LA(1)) {
                    case T__7: {
                        setState(
                                83);
                        ((StatementContext) _localctx).while_statement = while_statement();
                        ((StatementContext) _localctx).result = ((StatementContext) _localctx).while_statement.result;
                    }
                        break;
                    case T__4: {
                        setState(
                                86);
                        ((StatementContext) _localctx).b = match(
                                T__4);
                        if (inLoop) {
                            ((StatementContext) _localctx).result = factory
                                    .createBreak(
                                            ((StatementContext) _localctx).b);
                        } else {
                            SemErr(((StatementContext) _localctx).b,
                                    "break used outside of loop");
                        }
                        setState(
                                88);
                        match(T__5);
                    }
                        break;
                    case T__6: {
                        setState(
                                89);
                        ((StatementContext) _localctx).c = match(
                                T__6);
                        if (inLoop) {
                            ((StatementContext) _localctx).result = factory
                                    .createContinue(
                                            ((StatementContext) _localctx).c);
                        } else {
                            SemErr(((StatementContext) _localctx).c,
                                    "continue used outside of loop");
                        }
                        setState(
                                91);
                        match(T__5);
                    }
                        break;
                    case T__8: {
                        setState(
                                92);
                        ((StatementContext) _localctx).if_statement = if_statement(
                                inLoop);
                        ((StatementContext) _localctx).result = ((StatementContext) _localctx).if_statement.result;
                    }
                        break;
                    case T__11: {
                        setState(
                                95);
                        ((StatementContext) _localctx).return_statement = return_statement();
                        ((StatementContext) _localctx).result = ((StatementContext) _localctx).return_statement.result;
                    }
                        break;
                    case T__28:
                    case IDENTIFIER:
                    case STRING_LITERAL:
                    case INTEGER_LITERAL:
                    case DOUBLE_LITERAL: {
                        setState(
                                98);
                        ((StatementContext) _localctx).expression = expression();
                        setState(
                                99);
                        match(T__5);
                        ((StatementContext) _localctx).result = ((StatementContext) _localctx).expression.result;
                    }
                        break;
                    default:
                        throw new NoViableAltException(
                                this);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler
                    .reportError(
                            this,
                            re);
            _errHandler
                    .recover(
                            this,
                            re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class While_statementContext
            extends
            ParserRuleContext {

        public TclStatementNode result;
        public Token w;
        public ExpressionContext condition;
        public BlockContext body;

        public ExpressionContext expression() {
            return getRuleContext(
                    ExpressionContext.class,
                    0);
        }

        public BlockContext block() {
            return getRuleContext(
                    BlockContext.class,
                    0);
        }

        public While_statementContext(
                ParserRuleContext parent,
                int invokingState) {
            super(parent,
                    invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_while_statement;
        }
    }

    public final While_statementContext while_statement()
            throws RecognitionException {
        While_statementContext _localctx = new While_statementContext(
                _ctx,
                getState());
        enterRule(
                _localctx,
                10,
                RULE_while_statement);
        try {
            enterOuterAlt(
                    _localctx,
                    1);
            {
                setState(
                        104);
                ((While_statementContext) _localctx).w = match(
                        T__7);
                setState(
                        105);
                match(T__1);
                setState(
                        106);
                ((While_statementContext) _localctx).condition = expression();
                setState(
                        107);
                match(T__3);
                setState(
                        108);
                ((While_statementContext) _localctx).body = block(
                        true);
                ((While_statementContext) _localctx).result = factory
                        .createWhile(
                                ((While_statementContext) _localctx).w,
                                ((While_statementContext) _localctx).condition.result,
                                ((While_statementContext) _localctx).body.result);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler
                    .reportError(
                            this,
                            re);
            _errHandler
                    .recover(
                            this,
                            re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class If_statementContext
            extends
            ParserRuleContext {

        public boolean inLoop;
        public TclStatementNode result;
        public Token i;
        public ExpressionContext condition;
        public BlockContext then;
        public BlockContext block;

        public ExpressionContext expression() {
            return getRuleContext(
                    ExpressionContext.class,
                    0);
        }

        public List<BlockContext> block() {
            return getRuleContexts(
                    BlockContext.class);
        }

        public BlockContext block(
                int i) {
            return getRuleContext(
                    BlockContext.class,
                    i);
        }

        public If_statementContext(
                ParserRuleContext parent,
                int invokingState) {
            super(parent,
                    invokingState);
        }

        public If_statementContext(
                ParserRuleContext parent,
                int invokingState,
                boolean inLoop) {
            super(parent,
                    invokingState);
            this.inLoop = inLoop;
        }

        @Override
        public int getRuleIndex() {
            return RULE_if_statement;
        }
    }

    public final If_statementContext if_statement(
            boolean inLoop)
            throws RecognitionException {
        If_statementContext _localctx = new If_statementContext(
                _ctx,
                getState(),
                inLoop);
        enterRule(
                _localctx,
                12,
                RULE_if_statement);
        int _la;
        try {
            enterOuterAlt(
                    _localctx,
                    1);
            {
                setState(
                        111);
                ((If_statementContext) _localctx).i = match(
                        T__8);
                setState(
                        112);
                match(T__1);
                setState(
                        113);
                ((If_statementContext) _localctx).condition = expression();
                setState(
                        114);
                match(T__3);
                setState(
                        115);
                match(T__9);
                setState(
                        116);
                ((If_statementContext) _localctx).then = ((If_statementContext) _localctx).block = block(
                        inLoop);
                TclStatementNode elsePart = null;
                setState(
                        122);
                _errHandler
                        .sync(this);
                _la = _input
                        .LA(1);
                if (_la == T__10) {
                    {
                        setState(
                                118);
                        match(T__10);
                        setState(
                                119);
                        ((If_statementContext) _localctx).block = block(
                                inLoop);
                        elsePart = ((If_statementContext) _localctx).block.result;
                    }
                }

                ((If_statementContext) _localctx).result = factory
                        .createIf(
                                ((If_statementContext) _localctx).i,
                                ((If_statementContext) _localctx).condition.result,
                                ((If_statementContext) _localctx).then.result,
                                elsePart);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler
                    .reportError(
                            this,
                            re);
            _errHandler
                    .recover(
                            this,
                            re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class Return_statementContext
            extends
            ParserRuleContext {

        public TclStatementNode result;
        public Token r;
        public ExpressionContext expression;

        public ExpressionContext expression() {
            return getRuleContext(
                    ExpressionContext.class,
                    0);
        }

        public Return_statementContext(
                ParserRuleContext parent,
                int invokingState) {
            super(parent,
                    invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_return_statement;
        }
    }

    public final Return_statementContext return_statement()
            throws RecognitionException {
        Return_statementContext _localctx = new Return_statementContext(
                _ctx,
                getState());
        enterRule(
                _localctx,
                14,
                RULE_return_statement);
        int _la;
        try {
            enterOuterAlt(
                    _localctx,
                    1);
            {
                setState(
                        126);
                ((Return_statementContext) _localctx).r = match(
                        T__11);
                TclExpressionNode value = null;
                setState(
                        131);
                _errHandler
                        .sync(this);
                _la = _input
                        .LA(1);
                if ((((_la)
                        & ~0x3f) == 0
                        && ((1L << _la)
                                & ((1L << T__28)
                                        | (1L << IDENTIFIER)
                                        | (1L << STRING_LITERAL)
                                        | (1L << INTEGER_LITERAL)
                                        | (1L << DOUBLE_LITERAL))) != 0)) {
                    {
                        setState(
                                128);
                        ((Return_statementContext) _localctx).expression = expression();
                        value = ((Return_statementContext) _localctx).expression.result;
                    }
                }

                ((Return_statementContext) _localctx).result = factory
                        .createReturn(
                                ((Return_statementContext) _localctx).r,
                                value);
                setState(
                        134);
                match(T__5);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler
                    .reportError(
                            this,
                            re);
            _errHandler
                    .recover(
                            this,
                            re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ExpressionContext
            extends
            ParserRuleContext {

        public TclExpressionNode result;
        public Logic_termContext logic_term;
        public Token op;

        public List<Logic_termContext> logic_term() {
            return getRuleContexts(
                    Logic_termContext.class);
        }

        public Logic_termContext logic_term(
                int i) {
            return getRuleContext(
                    Logic_termContext.class,
                    i);
        }

        public ExpressionContext(
                ParserRuleContext parent,
                int invokingState) {
            super(parent,
                    invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_expression;
        }
    }

    public final ExpressionContext expression()
            throws RecognitionException {
        ExpressionContext _localctx = new ExpressionContext(
                _ctx,
                getState());
        enterRule(
                _localctx,
                16,
                RULE_expression);
        try {
            int _alt;
            enterOuterAlt(
                    _localctx,
                    1);
            {
                setState(
                        136);
                ((ExpressionContext) _localctx).logic_term = logic_term();
                ((ExpressionContext) _localctx).result = ((ExpressionContext) _localctx).logic_term.result;
                setState(
                        144);
                _errHandler
                        .sync(this);
                _alt = getInterpreter()
                        .adaptivePredict(
                                _input,
                                8,
                                _ctx);
                while (_alt != 2
                        && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        {
                            {
                                setState(
                                        138);
                                ((ExpressionContext) _localctx).op = match(
                                        T__12);
                                setState(
                                        139);
                                ((ExpressionContext) _localctx).logic_term = logic_term();
                                ((ExpressionContext) _localctx).result = factory
                                        .createBinary(
                                                ((ExpressionContext) _localctx).op,
                                                _localctx.result,
                                                ((ExpressionContext) _localctx).logic_term.result);
                            }
                        }
                    }
                    setState(
                            146);
                    _errHandler
                            .sync(this);
                    _alt = getInterpreter()
                            .adaptivePredict(
                                    _input,
                                    8,
                                    _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler
                    .reportError(
                            this,
                            re);
            _errHandler
                    .recover(
                            this,
                            re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class Logic_termContext
            extends
            ParserRuleContext {

        public TclExpressionNode result;
        public Logic_factorContext logic_factor;
        public Token op;

        public List<Logic_factorContext> logic_factor() {
            return getRuleContexts(
                    Logic_factorContext.class);
        }

        public Logic_factorContext logic_factor(
                int i) {
            return getRuleContext(
                    Logic_factorContext.class,
                    i);
        }

        public Logic_termContext(
                ParserRuleContext parent,
                int invokingState) {
            super(parent,
                    invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_logic_term;
        }
    }

    public final Logic_termContext logic_term()
            throws RecognitionException {
        Logic_termContext _localctx = new Logic_termContext(
                _ctx,
                getState());
        enterRule(
                _localctx,
                18,
                RULE_logic_term);
        try {
            int _alt;
            enterOuterAlt(
                    _localctx,
                    1);
            {
                setState(
                        147);
                ((Logic_termContext) _localctx).logic_factor = logic_factor();
                ((Logic_termContext) _localctx).result = ((Logic_termContext) _localctx).logic_factor.result;
                setState(
                        155);
                _errHandler
                        .sync(this);
                _alt = getInterpreter()
                        .adaptivePredict(
                                _input,
                                9,
                                _ctx);
                while (_alt != 2
                        && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        {
                            {
                                setState(
                                        149);
                                ((Logic_termContext) _localctx).op = match(
                                        T__13);
                                setState(
                                        150);
                                ((Logic_termContext) _localctx).logic_factor = logic_factor();
                                ((Logic_termContext) _localctx).result = factory
                                        .createBinary(
                                                ((Logic_termContext) _localctx).op,
                                                _localctx.result,
                                                ((Logic_termContext) _localctx).logic_factor.result);
                            }
                        }
                    }
                    setState(
                            157);
                    _errHandler
                            .sync(this);
                    _alt = getInterpreter()
                            .adaptivePredict(
                                    _input,
                                    9,
                                    _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler
                    .reportError(
                            this,
                            re);
            _errHandler
                    .recover(
                            this,
                            re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class Logic_factorContext
            extends
            ParserRuleContext {

        public TclExpressionNode result;
        public ArithmeticContext arithmetic;
        public Token op;

        public List<ArithmeticContext> arithmetic() {
            return getRuleContexts(
                    ArithmeticContext.class);
        }

        public ArithmeticContext arithmetic(
                int i) {
            return getRuleContext(
                    ArithmeticContext.class,
                    i);
        }

        public Logic_factorContext(
                ParserRuleContext parent,
                int invokingState) {
            super(parent,
                    invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_logic_factor;
        }
    }

    public final Logic_factorContext logic_factor()
            throws RecognitionException {
        Logic_factorContext _localctx = new Logic_factorContext(
                _ctx,
                getState());
        enterRule(
                _localctx,
                20,
                RULE_logic_factor);
        int _la;
        try {
            enterOuterAlt(
                    _localctx,
                    1);
            {
                setState(
                        158);
                ((Logic_factorContext) _localctx).arithmetic = arithmetic();
                ((Logic_factorContext) _localctx).result = ((Logic_factorContext) _localctx).arithmetic.result;
                setState(
                        164);
                _errHandler
                        .sync(this);
                switch (getInterpreter()
                        .adaptivePredict(
                                _input,
                                10,
                                _ctx)) {
                    case 1: {
                        setState(
                                160);
                        ((Logic_factorContext) _localctx).op = _input
                                .LT(1);
                        _la = _input
                                .LA(1);
                        if (!((((_la)
                                & ~0x3f) == 0
                                && ((1L << _la)
                                        & ((1L << T__14)
                                                | (1L << T__15)
                                                | (1L << T__16)
                                                | (1L << T__17)
                                                | (1L << T__18)
                                                | (1L << T__19)
                                                | (1L << T__20)
                                                | (1L << T__21))) != 0))) {
                            ((Logic_factorContext) _localctx).op = (Token) _errHandler
                                    .recoverInline(
                                            this);
                        } else {
                            if (_input
                                    .LA(1) == Token.EOF)
                                matchedEOF = true;
                            _errHandler
                                    .reportMatch(
                                            this);
                            consume();
                        }
                        setState(
                                161);
                        ((Logic_factorContext) _localctx).arithmetic = arithmetic();
                        ((Logic_factorContext) _localctx).result = factory
                                .createBinary(
                                        ((Logic_factorContext) _localctx).op,
                                        _localctx.result,
                                        ((Logic_factorContext) _localctx).arithmetic.result);
                    }
                        break;
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler
                    .reportError(
                            this,
                            re);
            _errHandler
                    .recover(
                            this,
                            re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ArithmeticContext
            extends
            ParserRuleContext {

        public TclExpressionNode result;
        public Term_addContext term_add;
        public Token op;

        public List<Term_addContext> term_add() {
            return getRuleContexts(
                    Term_addContext.class);
        }

        public Term_addContext term_add(
                int i) {
            return getRuleContext(
                    Term_addContext.class,
                    i);
        }

        public ArithmeticContext(
                ParserRuleContext parent,
                int invokingState) {
            super(parent,
                    invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_arithmetic;
        }
    }

    public final ArithmeticContext arithmetic()
            throws RecognitionException {
        ArithmeticContext _localctx = new ArithmeticContext(
                _ctx,
                getState());
        enterRule(
                _localctx,
                22,
                RULE_arithmetic);
        int _la;
        try {
            int _alt;
            enterOuterAlt(
                    _localctx,
                    1);
            {
                setState(
                        166);
                ((ArithmeticContext) _localctx).term_add = term_add();
                ((ArithmeticContext) _localctx).result = ((ArithmeticContext) _localctx).term_add.result;
                setState(
                        174);
                _errHandler
                        .sync(this);
                _alt = getInterpreter()
                        .adaptivePredict(
                                _input,
                                11,
                                _ctx);
                while (_alt != 2
                        && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        {
                            {
                                setState(
                                        168);
                                ((ArithmeticContext) _localctx).op = _input
                                        .LT(1);
                                _la = _input
                                        .LA(1);
                                if (!(_la == T__22
                                        || _la == T__23)) {
                                    ((ArithmeticContext) _localctx).op = (Token) _errHandler
                                            .recoverInline(
                                                    this);
                                } else {
                                    if (_input
                                            .LA(1) == Token.EOF)
                                        matchedEOF = true;
                                    _errHandler
                                            .reportMatch(
                                                    this);
                                    consume();
                                }
                                setState(
                                        169);
                                ((ArithmeticContext) _localctx).term_add = term_add();
                                ((ArithmeticContext) _localctx).result = factory
                                        .createBinary(
                                                ((ArithmeticContext) _localctx).op,
                                                _localctx.result,
                                                ((ArithmeticContext) _localctx).term_add.result);
                            }
                        }
                    }
                    setState(
                            176);
                    _errHandler
                            .sync(this);
                    _alt = getInterpreter()
                            .adaptivePredict(
                                    _input,
                                    11,
                                    _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler
                    .reportError(
                            this,
                            re);
            _errHandler
                    .recover(
                            this,
                            re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class Term_addContext
            extends
            ParserRuleContext {

        public TclExpressionNode result;
        public Term_potContext term_pot;
        public Token op;

        public List<Term_potContext> term_pot() {
            return getRuleContexts(
                    Term_potContext.class);
        }

        public Term_potContext term_pot(
                int i) {
            return getRuleContext(
                    Term_potContext.class,
                    i);
        }

        public Term_addContext(
                ParserRuleContext parent,
                int invokingState) {
            super(parent,
                    invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_term_add;
        }
    }

    public final Term_addContext term_add()
            throws RecognitionException {
        Term_addContext _localctx = new Term_addContext(
                _ctx,
                getState());
        enterRule(
                _localctx,
                24,
                RULE_term_add);
        int _la;
        try {
            int _alt;
            enterOuterAlt(
                    _localctx,
                    1);
            {
                setState(
                        177);
                ((Term_addContext) _localctx).term_pot = term_pot();
                ((Term_addContext) _localctx).result = ((Term_addContext) _localctx).term_pot.result;
                setState(
                        185);
                _errHandler
                        .sync(this);
                _alt = getInterpreter()
                        .adaptivePredict(
                                _input,
                                12,
                                _ctx);
                while (_alt != 2
                        && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        {
                            {
                                setState(
                                        179);
                                ((Term_addContext) _localctx).op = _input
                                        .LT(1);
                                _la = _input
                                        .LA(1);
                                if (!((((_la)
                                        & ~0x3f) == 0
                                        && ((1L << _la)
                                                & ((1L << T__24)
                                                        | (1L << T__25)
                                                        | (1L << T__26))) != 0))) {
                                    ((Term_addContext) _localctx).op = (Token) _errHandler
                                            .recoverInline(
                                                    this);
                                } else {
                                    if (_input
                                            .LA(1) == Token.EOF)
                                        matchedEOF = true;
                                    _errHandler
                                            .reportMatch(
                                                    this);
                                    consume();
                                }
                                setState(
                                        180);
                                ((Term_addContext) _localctx).term_pot = term_pot();
                                ((Term_addContext) _localctx).result = factory
                                        .createBinary(
                                                ((Term_addContext) _localctx).op,
                                                _localctx.result,
                                                ((Term_addContext) _localctx).term_pot.result);
                            }
                        }
                    }
                    setState(
                            187);
                    _errHandler
                            .sync(this);
                    _alt = getInterpreter()
                            .adaptivePredict(
                                    _input,
                                    12,
                                    _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler
                    .reportError(
                            this,
                            re);
            _errHandler
                    .recover(
                            this,
                            re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class Term_potContext
            extends
            ParserRuleContext {

        public TclExpressionNode result;
        public TermContext term;
        public Token op;

        public List<TermContext> term() {
            return getRuleContexts(
                    TermContext.class);
        }

        public TermContext term(
                int i) {
            return getRuleContext(
                    TermContext.class,
                    i);
        }

        public Term_potContext(
                ParserRuleContext parent,
                int invokingState) {
            super(parent,
                    invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_term_pot;
        }
    }

    public final Term_potContext term_pot()
            throws RecognitionException {
        Term_potContext _localctx = new Term_potContext(
                _ctx,
                getState());
        enterRule(
                _localctx,
                26,
                RULE_term_pot);
        try {
            int _alt;
            enterOuterAlt(
                    _localctx,
                    1);
            {
                setState(
                        188);
                ((Term_potContext) _localctx).term = term();
                ((Term_potContext) _localctx).result = ((Term_potContext) _localctx).term.result;
                setState(
                        196);
                _errHandler
                        .sync(this);
                _alt = getInterpreter()
                        .adaptivePredict(
                                _input,
                                13,
                                _ctx);
                while (_alt != 2
                        && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        {
                            {
                                setState(
                                        190);
                                ((Term_potContext) _localctx).op = match(
                                        T__27);
                                setState(
                                        191);
                                ((Term_potContext) _localctx).term = term();
                                ((Term_potContext) _localctx).result = factory
                                        .createBinary(
                                                ((Term_potContext) _localctx).op,
                                                _localctx.result,
                                                ((Term_potContext) _localctx).term.result);
                            }
                        }
                    }
                    setState(
                            198);
                    _errHandler
                            .sync(this);
                    _alt = getInterpreter()
                            .adaptivePredict(
                                    _input,
                                    13,
                                    _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler
                    .reportError(
                            this,
                            re);
            _errHandler
                    .recover(
                            this,
                            re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class TermContext
            extends
            ParserRuleContext {

        public TclExpressionNode result;
        public Token IDENTIFIER;
        public Member_expressionContext member_expression;
        public Token STRING_LITERAL;
        public Token INTEGER_LITERAL;
        public Token DOUBLE_LITERAL;
        public Token s;
        public ExpressionContext expr;
        public Token e;

        public TerminalNode IDENTIFIER() {
            return getToken(
                    TclParser.IDENTIFIER,
                    0);
        }

        public TerminalNode STRING_LITERAL() {
            return getToken(
                    TclParser.STRING_LITERAL,
                    0);
        }

        public TerminalNode INTEGER_LITERAL() {
            return getToken(
                    TclParser.INTEGER_LITERAL,
                    0);
        }

        public TerminalNode DOUBLE_LITERAL() {
            return getToken(
                    TclParser.DOUBLE_LITERAL,
                    0);
        }

        public ExpressionContext expression() {
            return getRuleContext(
                    ExpressionContext.class,
                    0);
        }

        public Member_expressionContext member_expression() {
            return getRuleContext(
                    Member_expressionContext.class,
                    0);
        }

        public TermContext(
                ParserRuleContext parent,
                int invokingState) {
            super(parent,
                    invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_term;
        }
    }

    public final TermContext term()
            throws RecognitionException {
        TermContext _localctx = new TermContext(
                _ctx,
                getState());
        enterRule(
                _localctx,
                28,
                RULE_term);
        try {
            enterOuterAlt(
                    _localctx,
                    1);
            {
                setState(
                        218);
                _errHandler
                        .sync(this);
                switch (_input
                        .LA(1)) {
                    case IDENTIFIER: {
                        setState(
                                199);
                        ((TermContext) _localctx).IDENTIFIER = match(
                                IDENTIFIER);
                        TclExpressionNode assignmentName = factory
                                .createStringLiteral(
                                        ((TermContext) _localctx).IDENTIFIER,
                                        false);
                        setState(
                                205);
                        _errHandler
                                .sync(this);
                        switch (getInterpreter()
                                .adaptivePredict(
                                        _input,
                                        14,
                                        _ctx)) {
                            case 1: {
                                setState(
                                        201);
                                ((TermContext) _localctx).member_expression = member_expression(
                                        null,
                                        null,
                                        assignmentName);
                                ((TermContext) _localctx).result = ((TermContext) _localctx).member_expression.result;
                            }
                                break;
                            case 2: {
                                ((TermContext) _localctx).result = factory
                                        .createRead(
                                                assignmentName);
                            }
                                break;
                        }
                    }
                        break;
                    case STRING_LITERAL: {
                        setState(
                                207);
                        ((TermContext) _localctx).STRING_LITERAL = match(
                                STRING_LITERAL);
                        ((TermContext) _localctx).result = factory
                                .createStringLiteral(
                                        ((TermContext) _localctx).STRING_LITERAL,
                                        true);
                    }
                        break;
                    case INTEGER_LITERAL: {
                        setState(
                                209);
                        ((TermContext) _localctx).INTEGER_LITERAL = match(
                                INTEGER_LITERAL);
                        ((TermContext) _localctx).result = factory
                                .createIntegerLiteral(
                                        ((TermContext) _localctx).INTEGER_LITERAL);
                    }
                        break;
                    case DOUBLE_LITERAL: {
                        setState(
                                211);
                        ((TermContext) _localctx).DOUBLE_LITERAL = match(
                                DOUBLE_LITERAL);
                        ((TermContext) _localctx).result = factory
                                .createDoubleLiteral(
                                        ((TermContext) _localctx).DOUBLE_LITERAL);
                    }
                        break;
                    case T__28: {
                        setState(
                                213);
                        ((TermContext) _localctx).s = match(
                                T__28);
                        setState(
                                214);
                        ((TermContext) _localctx).expr = expression();
                        setState(
                                215);
                        ((TermContext) _localctx).e = match(
                                T__29);
                        ((TermContext) _localctx).result = factory
                                .createParenExpression(
                                        ((TermContext) _localctx).expr.result,
                                        ((TermContext) _localctx).s
                                                .getStartIndex(),
                                        ((TermContext) _localctx).e
                                                .getStopIndex()
                                                - ((TermContext) _localctx).s
                                                        .getStartIndex()
                                                + 1);
                    }
                        break;
                    default:
                        throw new NoViableAltException(
                                this);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler
                    .reportError(
                            this,
                            re);
            _errHandler
                    .recover(
                            this,
                            re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class Member_expressionContext
            extends
            ParserRuleContext {

        public TclExpressionNode r;
        public TclExpressionNode assignmentReceiver;
        public TclExpressionNode assignmentName;
        public TclExpressionNode result;
        public ExpressionContext expression;
        public Token e;
        public Token IDENTIFIER;
        public Member_expressionContext member_expression;

        public List<ExpressionContext> expression() {
            return getRuleContexts(
                    ExpressionContext.class);
        }

        public ExpressionContext expression(
                int i) {
            return getRuleContext(
                    ExpressionContext.class,
                    i);
        }

        public TerminalNode IDENTIFIER() {
            return getToken(
                    TclParser.IDENTIFIER,
                    0);
        }

        public Member_expressionContext member_expression() {
            return getRuleContext(
                    Member_expressionContext.class,
                    0);
        }

        public Member_expressionContext(
                ParserRuleContext parent,
                int invokingState) {
            super(parent,
                    invokingState);
        }

        public Member_expressionContext(
                ParserRuleContext parent,
                int invokingState,
                TclExpressionNode r,
                TclExpressionNode assignmentReceiver,
                TclExpressionNode assignmentName) {
            super(parent,
                    invokingState);
            this.r = r;
            this.assignmentReceiver = assignmentReceiver;
            this.assignmentName = assignmentName;
        }

        @Override
        public int getRuleIndex() {
            return RULE_member_expression;
        }
    }

    public final Member_expressionContext member_expression(
            TclExpressionNode r,
            TclExpressionNode assignmentReceiver,
            TclExpressionNode assignmentName)
            throws RecognitionException {
        Member_expressionContext _localctx = new Member_expressionContext(
                _ctx,
                getState(),
                r,
                assignmentReceiver,
                assignmentName);
        enterRule(
                _localctx,
                30,
                RULE_member_expression);
        int _la;
        try {
            enterOuterAlt(
                    _localctx,
                    1);
            {
                TclExpressionNode receiver = r;
                TclExpressionNode nestedAssignmentName = null;
                setState(
                        252);
                _errHandler
                        .sync(this);
                switch (_input
                        .LA(1)) {
                    case T__28: {
                        setState(
                                221);
                        match(T__28);
                        List<TclExpressionNode> parameters = new ArrayList<>();
                        if (receiver == null) {
                            receiver = factory
                                    .createRead(
                                            assignmentName);
                        }
                        setState(
                                234);
                        _errHandler
                                .sync(this);
                        _la = _input
                                .LA(1);
                        if ((((_la)
                                & ~0x3f) == 0
                                && ((1L << _la)
                                        & ((1L << T__28)
                                                | (1L << IDENTIFIER)
                                                | (1L << STRING_LITERAL)
                                                | (1L << INTEGER_LITERAL)
                                                | (1L << DOUBLE_LITERAL))) != 0)) {
                            {
                                setState(
                                        223);
                                ((Member_expressionContext) _localctx).expression = expression();
                                parameters
                                        .add(((Member_expressionContext) _localctx).expression.result);
                                setState(
                                        231);
                                _errHandler
                                        .sync(this);
                                _la = _input
                                        .LA(1);
                                while (_la == T__2) {
                                    {
                                        {
                                            setState(
                                                    225);
                                            match(T__2);
                                            setState(
                                                    226);
                                            ((Member_expressionContext) _localctx).expression = expression();
                                            parameters
                                                    .add(((Member_expressionContext) _localctx).expression.result);
                                        }
                                    }
                                    setState(
                                            233);
                                    _errHandler
                                            .sync(this);
                                    _la = _input
                                            .LA(1);
                                }
                            }
                        }

                        setState(
                                236);
                        ((Member_expressionContext) _localctx).e = match(
                                T__29);
                        ((Member_expressionContext) _localctx).result = factory
                                .createCall(
                                        receiver,
                                        parameters,
                                        ((Member_expressionContext) _localctx).e);
                    }
                        break;
                    case T__30: {
                        setState(
                                238);
                        match(T__30);
                        setState(
                                239);
                        ((Member_expressionContext) _localctx).expression = expression();
                        if (assignmentName == null) {
                            SemErr((((Member_expressionContext) _localctx).expression != null
                                    ? (((Member_expressionContext) _localctx).expression.start)
                                    : null),
                                    "invalid assignment target");
                        } else
                            if (assignmentReceiver == null) {
                                ((Member_expressionContext) _localctx).result = factory
                                        .createAssignment(
                                                assignmentName,
                                                ((Member_expressionContext) _localctx).expression.result);
                            } else {
                                ((Member_expressionContext) _localctx).result = factory
                                        .createWriteProperty(
                                                assignmentReceiver,
                                                assignmentName,
                                                ((Member_expressionContext) _localctx).expression.result);
                            }
                    }
                        break;
                    case T__31: {
                        setState(
                                242);
                        match(T__31);
                        if (receiver == null) {
                            receiver = factory
                                    .createRead(
                                            assignmentName);
                        }
                        setState(
                                244);
                        ((Member_expressionContext) _localctx).IDENTIFIER = match(
                                IDENTIFIER);
                        nestedAssignmentName = factory
                                .createStringLiteral(
                                        ((Member_expressionContext) _localctx).IDENTIFIER,
                                        false);
                        ((Member_expressionContext) _localctx).result = factory
                                .createReadProperty(
                                        receiver,
                                        nestedAssignmentName);
                    }
                        break;
                    case T__32: {
                        setState(
                                246);
                        match(T__32);
                        if (receiver == null) {
                            receiver = factory
                                    .createRead(
                                            assignmentName);
                        }
                        setState(
                                248);
                        ((Member_expressionContext) _localctx).expression = expression();
                        nestedAssignmentName = ((Member_expressionContext) _localctx).expression.result;
                        ((Member_expressionContext) _localctx).result = factory
                                .createReadProperty(
                                        receiver,
                                        nestedAssignmentName);
                        setState(
                                250);
                        match(T__33);
                    }
                        break;
                    default:
                        throw new NoViableAltException(
                                this);
                }
                setState(
                        257);
                _errHandler
                        .sync(this);
                switch (getInterpreter()
                        .adaptivePredict(
                                _input,
                                19,
                                _ctx)) {
                    case 1: {
                        setState(
                                254);
                        ((Member_expressionContext) _localctx).member_expression = member_expression(
                                _localctx.result,
                                receiver,
                                nestedAssignmentName);
                        ((Member_expressionContext) _localctx).result = ((Member_expressionContext) _localctx).member_expression.result;
                    }
                        break;
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler
                    .reportError(
                            this,
                            re);
            _errHandler
                    .recover(
                            this,
                            re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static final String _serializedATN = "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3*\u0106\4\2\t\2\4"
            + "\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"
            + "\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2\7"
            + "\2%\n\2\f\2\16\2(\13\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"
            + "\7\3\66\n\3\f\3\16\39\13\3\5\3;\n\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3"
            + "\4\7\4F\n\4\f\4\16\4I\13\4\3\4\3\4\3\4\3\5\3\5\3\5\7\5Q\n\5\f\5\16\5T"
            + "\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"
            + "\3\6\3\6\3\6\5\6i\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b"
            + "\3\b\3\b\3\b\3\b\3\b\3\b\5\b}\n\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\5\t\u0086"
            + "\n\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u0091\n\n\f\n\16\n\u0094"
            + "\13\n\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u009c\n\13\f\13\16\13\u009f\13"
            + "\13\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00a7\n\f\3\r\3\r\3\r\3\r\3\r\3\r\7\r"
            + "\u00af\n\r\f\r\16\r\u00b2\13\r\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u00ba"
            + "\n\16\f\16\16\16\u00bd\13\16\3\17\3\17\3\17\3\17\3\17\3\17\7\17\u00c5"
            + "\n\17\f\17\16\17\u00c8\13\17\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00d0"
            + "\n\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00dd"
            + "\n\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\7\21\u00e8\n\21\f\21"
            + "\16\21\u00eb\13\21\5\21\u00ed\n\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"
            + "\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00ff\n\21\3\21\3\21"
            + "\3\21\5\21\u0104\n\21\3\21\2\2\22\2\4\6\b\n\f\16\20\22\24\26\30\32\34"
            + "\36 \2\5\3\2\21\30\3\2\31\32\3\2\33\35\2\u0112\2\"\3\2\2\2\4,\3\2\2\2"
            + "\6@\3\2\2\2\bR\3\2\2\2\nh\3\2\2\2\fj\3\2\2\2\16q\3\2\2\2\20\u0080\3\2"
            + "\2\2\22\u008a\3\2\2\2\24\u0095\3\2\2\2\26\u00a0\3\2\2\2\30\u00a8\3\2\2"
            + "\2\32\u00b3\3\2\2\2\34\u00be\3\2\2\2\36\u00dc\3\2\2\2 \u00de\3\2\2\2\""
            + "&\5\4\3\2#%\5\4\3\2$#\3\2\2\2%(\3\2\2\2&$\3\2\2\2&\'\3\2\2\2\')\3\2\2"
            + "\2(&\3\2\2\2)*\5\b\5\2*+\7\2\2\3+\3\3\2\2\2,-\7\3\2\2-.\7\'\2\2./\7\4"
            + "\2\2/:\b\3\1\2\60\61\7\'\2\2\61\67\b\3\1\2\62\63\7\5\2\2\63\64\7\'\2\2"
            + "\64\66\b\3\1\2\65\62\3\2\2\2\669\3\2\2\2\67\65\3\2\2\2\678\3\2\2\28;\3"
            + "\2\2\29\67\3\2\2\2:\60\3\2\2\2:;\3\2\2\2;<\3\2\2\2<=\7\6\2\2=>\5\6\4\2"
            + ">?\b\3\1\2?\5\3\2\2\2@A\b\4\1\2AG\7\4\2\2BC\5\n\6\2CD\b\4\1\2DF\3\2\2"
            + "\2EB\3\2\2\2FI\3\2\2\2GE\3\2\2\2GH\3\2\2\2HJ\3\2\2\2IG\3\2\2\2JK\7\6\2"
            + "\2KL\b\4\1\2L\7\3\2\2\2MN\5\n\6\2NO\b\5\1\2OQ\3\2\2\2PM\3\2\2\2QT\3\2"
            + "\2\2RP\3\2\2\2RS\3\2\2\2S\t\3\2\2\2TR\3\2\2\2UV\5\f\7\2VW\b\6\1\2Wi\3"
            + "\2\2\2XY\7\7\2\2YZ\b\6\1\2Zi\7\b\2\2[\\\7\t\2\2\\]\b\6\1\2]i\7\b\2\2^"
            + "_\5\16\b\2_`\b\6\1\2`i\3\2\2\2ab\5\20\t\2bc\b\6\1\2ci\3\2\2\2de\5\22\n"
            + "\2ef\7\b\2\2fg\b\6\1\2gi\3\2\2\2hU\3\2\2\2hX\3\2\2\2h[\3\2\2\2h^\3\2\2"
            + "\2ha\3\2\2\2hd\3\2\2\2i\13\3\2\2\2jk\7\n\2\2kl\7\4\2\2lm\5\22\n\2mn\7"
            + "\6\2\2no\5\6\4\2op\b\7\1\2p\r\3\2\2\2qr\7\13\2\2rs\7\4\2\2st\5\22\n\2"
            + "tu\7\6\2\2uv\7\f\2\2vw\5\6\4\2w|\b\b\1\2xy\7\r\2\2yz\5\6\4\2z{\b\b\1\2"
            + "{}\3\2\2\2|x\3\2\2\2|}\3\2\2\2}~\3\2\2\2~\177\b\b\1\2\177\17\3\2\2\2\u0080"
            + "\u0081\7\16\2\2\u0081\u0085\b\t\1\2\u0082\u0083\5\22\n\2\u0083\u0084\b"
            + "\t\1\2\u0084\u0086\3\2\2\2\u0085\u0082\3\2\2\2\u0085\u0086\3\2\2\2\u0086"
            + "\u0087\3\2\2\2\u0087\u0088\b\t\1\2\u0088\u0089\7\b\2\2\u0089\21\3\2\2"
            + "\2\u008a\u008b\5\24\13\2\u008b\u0092\b\n\1\2\u008c\u008d\7\17\2\2\u008d"
            + "\u008e\5\24\13\2\u008e\u008f\b\n\1\2\u008f\u0091\3\2\2\2\u0090\u008c\3"
            + "\2\2\2\u0091\u0094\3\2\2\2\u0092\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093"
            + "\23\3\2\2\2\u0094\u0092\3\2\2\2\u0095\u0096\5\26\f\2\u0096\u009d\b\13"
            + "\1\2\u0097\u0098\7\20\2\2\u0098\u0099\5\26\f\2\u0099\u009a\b\13\1\2\u009a"
            + "\u009c\3\2\2\2\u009b\u0097\3\2\2\2\u009c\u009f\3\2\2\2\u009d\u009b\3\2"
            + "\2\2\u009d\u009e\3\2\2\2\u009e\25\3\2\2\2\u009f\u009d\3\2\2\2\u00a0\u00a1"
            + "\5\30\r\2\u00a1\u00a6\b\f\1\2\u00a2\u00a3\t\2\2\2\u00a3\u00a4\5\30\r\2"
            + "\u00a4\u00a5\b\f\1\2\u00a5\u00a7\3\2\2\2\u00a6\u00a2\3\2\2\2\u00a6\u00a7"
            + "\3\2\2\2\u00a7\27\3\2\2\2\u00a8\u00a9\5\32\16\2\u00a9\u00b0\b\r\1\2\u00aa"
            + "\u00ab\t\3\2\2\u00ab\u00ac\5\32\16\2\u00ac\u00ad\b\r\1\2\u00ad\u00af\3"
            + "\2\2\2\u00ae\u00aa\3\2\2\2\u00af\u00b2\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b0"
            + "\u00b1\3\2\2\2\u00b1\31\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b3\u00b4\5\34\17"
            + "\2\u00b4\u00bb\b\16\1\2\u00b5\u00b6\t\4\2\2\u00b6\u00b7\5\34\17\2\u00b7"
            + "\u00b8\b\16\1\2\u00b8\u00ba\3\2\2\2\u00b9\u00b5\3\2\2\2\u00ba\u00bd\3"
            + "\2\2\2\u00bb\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\33\3\2\2\2\u00bd"
            + "\u00bb\3\2\2\2\u00be\u00bf\5\36\20\2\u00bf\u00c6\b\17\1\2\u00c0\u00c1"
            + "\7\36\2\2\u00c1\u00c2\5\36\20\2\u00c2\u00c3\b\17\1\2\u00c3\u00c5\3\2\2"
            + "\2\u00c4\u00c0\3\2\2\2\u00c5\u00c8\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c7"
            + "\3\2\2\2\u00c7\35\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c9\u00ca\7\'\2\2\u00ca"
            + "\u00cf\b\20\1\2\u00cb\u00cc\5 \21\2\u00cc\u00cd\b\20\1\2\u00cd\u00d0\3"
            + "\2\2\2\u00ce\u00d0\b\20\1\2\u00cf\u00cb\3\2\2\2\u00cf\u00ce\3\2\2\2\u00d0"
            + "\u00dd\3\2\2\2\u00d1\u00d2\7(\2\2\u00d2\u00dd\b\20\1\2\u00d3\u00d4\7)"
            + "\2\2\u00d4\u00dd\b\20\1\2\u00d5\u00d6\7*\2\2\u00d6\u00dd\b\20\1\2\u00d7"
            + "\u00d8\7\37\2\2\u00d8\u00d9\5\22\n\2\u00d9\u00da\7 \2\2\u00da\u00db\b"
            + "\20\1\2\u00db\u00dd\3\2\2\2\u00dc\u00c9\3\2\2\2\u00dc\u00d1\3\2\2\2\u00dc"
            + "\u00d3\3\2\2\2\u00dc\u00d5\3\2\2\2\u00dc\u00d7\3\2\2\2\u00dd\37\3\2\2"
            + "\2\u00de\u00fe\b\21\1\2\u00df\u00e0\7\37\2\2\u00e0\u00ec\b\21\1\2\u00e1"
            + "\u00e2\5\22\n\2\u00e2\u00e9\b\21\1\2\u00e3\u00e4\7\5\2\2\u00e4\u00e5\5"
            + "\22\n\2\u00e5\u00e6\b\21\1\2\u00e6\u00e8\3\2\2\2\u00e7\u00e3\3\2\2\2\u00e8"
            + "\u00eb\3\2\2\2\u00e9\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00ed\3\2"
            + "\2\2\u00eb\u00e9\3\2\2\2\u00ec\u00e1\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed"
            + "\u00ee\3\2\2\2\u00ee\u00ef\7 \2\2\u00ef\u00ff\b\21\1\2\u00f0\u00f1\7!"
            + "\2\2\u00f1\u00f2\5\22\n\2\u00f2\u00f3\b\21\1\2\u00f3\u00ff\3\2\2\2\u00f4"
            + "\u00f5\7\"\2\2\u00f5\u00f6\b\21\1\2\u00f6\u00f7\7\'\2\2\u00f7\u00ff\b"
            + "\21\1\2\u00f8\u00f9\7#\2\2\u00f9\u00fa\b\21\1\2\u00fa\u00fb\5\22\n\2\u00fb"
            + "\u00fc\b\21\1\2\u00fc\u00fd\7$\2\2\u00fd\u00ff\3\2\2\2\u00fe\u00df\3\2"
            + "\2\2\u00fe\u00f0\3\2\2\2\u00fe\u00f4\3\2\2\2\u00fe\u00f8\3\2\2\2\u00ff"
            + "\u0103\3\2\2\2\u0100\u0101\5 \21\2\u0101\u0102\b\21\1\2\u0102\u0104\3"
            + "\2\2\2\u0103\u0100\3\2\2\2\u0103\u0104\3\2\2\2\u0104!\3\2\2\2\26&\67:"
            + "GRh|\u0085\u0092\u009d\u00a6\u00b0\u00bb\u00c6\u00cf\u00dc\u00e9\u00ec"
            + "\u00fe\u0103";
    public static final ATN _ATN = new ATNDeserializer()
            .deserialize(
                    _serializedATN
                            .toCharArray());
    static {
        _decisionToDFA = new DFA[_ATN
                .getNumberOfDecisions()];
        for (int i = 0; i < _ATN
                .getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(
                    _ATN.getDecisionState(
                            i),
                    i);
        }
    }
}
