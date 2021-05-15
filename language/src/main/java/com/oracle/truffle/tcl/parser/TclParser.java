// Generated from language/src/main/java/com/oracle/truffle/tcl/parser/Tcl.g4 by ANTLR 4.7.1
package com.oracle.truffle.tcl.parser;

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

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TclParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, COMMENT=36, WS=37, CR=38, IDENTIFIER=39, 
		STRING_LITERAL=40, INTEGER_LITERAL=41, DOUBLE_LITERAL=42, BOOLEAN_LITERAL=43;
	public static final int
		RULE_tcl = 0, RULE_function = 1, RULE_block = 2, RULE_module = 3, RULE_command = 4, 
		RULE_while_command = 5, RULE_if_command = 6, RULE_return_command = 7, 
		RULE_expression = 8, RULE_logic_term = 9, RULE_logic_factor = 10, RULE_arithmetic = 11, 
		RULE_term_add = 12, RULE_term_pot = 13, RULE_term = 14, RULE_word = 15, 
		RULE_member_expression = 16, RULE_command_parameters = 17;
	public static final String[] ruleNames = {
		"tcl", "function", "block", "module", "command", "while_command", "if_command", 
		"return_command", "expression", "logic_term", "logic_factor", "arithmetic", 
		"term_add", "term_pot", "term", "word", "member_expression", "command_parameters"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'proc'", "'{'", "','", "'}'", "'break'", "'continue'", "';'", "'while'", 
		"'if'", "'then'", "'elseif'", "'else'", "'return'", "'||'", "'&&'", "'<'", 
		"'<='", "'>'", "'>='", "'=='", "'!='", "'eq'", "'ne'", "'+'", "'-'", "'*'", 
		"'/'", "'%'", "'**'", "'$'", "'['", "']'", "'::'", "'('", "')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		"COMMENT", "WS", "CR", "IDENTIFIER", "STRING_LITERAL", "INTEGER_LITERAL", 
		"DOUBLE_LITERAL", "BOOLEAN_LITERAL"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
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
	public String getGrammarFileName() { return "Tcl.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


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

	public TclParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class TclContext extends ParserRuleContext {
		public ModuleContext module() {
			return getRuleContext(ModuleContext.class,0);
		}
		public TerminalNode EOF() { return getToken(TclParser.EOF, 0); }
		public List<TerminalNode> CR() { return getTokens(TclParser.CR); }
		public TerminalNode CR(int i) {
			return getToken(TclParser.CR, i);
		}
		public List<FunctionContext> function() {
			return getRuleContexts(FunctionContext.class);
		}
		public FunctionContext function(int i) {
			return getRuleContext(FunctionContext.class,i);
		}
		public TclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tcl; }
	}

	public final TclContext tcl() throws RecognitionException {
		TclContext _localctx = new TclContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_tcl);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(36);
					match(CR);
					}
					} 
				}
				setState(41);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(42);
				function();
				}
				}
				setState(47);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(48);
			module();
			setState(52);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CR) {
				{
				{
				setState(49);
				match(CR);
				}
				}
				setState(54);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(55);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionContext extends ParserRuleContext {
		public Token IDENTIFIER;
		public Token s;
		public BlockContext body;
		public List<TerminalNode> IDENTIFIER() { return getTokens(TclParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(TclParser.IDENTIFIER, i);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			match(T__0);
			setState(58);
			((FunctionContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			setState(59);
			((FunctionContext)_localctx).s = match(T__1);
			 factory.startFunction(((FunctionContext)_localctx).IDENTIFIER, ((FunctionContext)_localctx).s); 
			setState(71);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(61);
				((FunctionContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				 factory.addFormalParameter(((FunctionContext)_localctx).IDENTIFIER); 
				setState(68);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(63);
					match(T__2);
					setState(64);
					((FunctionContext)_localctx).IDENTIFIER = match(IDENTIFIER);
					 factory.addFormalParameter(((FunctionContext)_localctx).IDENTIFIER); 
					}
					}
					setState(70);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(73);
			match(T__3);
			setState(74);
			((FunctionContext)_localctx).body = block(false);
			 factory.finishFunction(((FunctionContext)_localctx).body.result); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public boolean inLoop;
		public TclStatementNode result;
		public Token s;
		public CommandContext command;
		public Token e;
		public List<TerminalNode> CR() { return getTokens(TclParser.CR); }
		public TerminalNode CR(int i) {
			return getToken(TclParser.CR, i);
		}
		public List<CommandContext> command() {
			return getRuleContexts(CommandContext.class);
		}
		public CommandContext command(int i) {
			return getRuleContext(CommandContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public BlockContext(ParserRuleContext parent, int invokingState, boolean inLoop) {
			super(parent, invokingState);
			this.inLoop = inLoop;
		}
		@Override public int getRuleIndex() { return RULE_block; }
	}

	public final BlockContext block(boolean inLoop) throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState(), inLoop);
		enterRule(_localctx, 4, RULE_block);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			 factory.startBlock();
			                                                  List<TclStatementNode> body = new ArrayList<>(); 
			setState(78);
			((BlockContext)_localctx).s = match(T__1);
			setState(82);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(79);
					match(CR);
					}
					} 
				}
				setState(84);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__4) | (1L << T__5) | (1L << T__7) | (1L << T__8) | (1L << T__12) | (1L << T__29) | (1L << T__30) | (1L << IDENTIFIER) | (1L << STRING_LITERAL) | (1L << INTEGER_LITERAL) | (1L << DOUBLE_LITERAL) | (1L << BOOLEAN_LITERAL))) != 0)) {
				{
				setState(85);
				((BlockContext)_localctx).command = command(inLoop);
				 body.add(((BlockContext)_localctx).command.result); 
				}
			}

			setState(100);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(91); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(90);
						match(CR);
						}
						}
						setState(93); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==CR );
					setState(95);
					((BlockContext)_localctx).command = command(inLoop);
					 body.add(((BlockContext)_localctx).command.result); 
					}
					} 
				}
				setState(102);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			setState(106);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CR) {
				{
				{
				setState(103);
				match(CR);
				}
				}
				setState(108);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(109);
			((BlockContext)_localctx).e = match(T__3);
			 ((BlockContext)_localctx).result =  factory.finishBlock(body, ((BlockContext)_localctx).s.getStartIndex(), ((BlockContext)_localctx).e.getStopIndex() - ((BlockContext)_localctx).s.getStartIndex() + 1); 
			setState(114);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(111);
					match(CR);
					}
					} 
				}
				setState(116);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ModuleContext extends ParserRuleContext {
		public CommandContext command;
		public List<CommandContext> command() {
			return getRuleContexts(CommandContext.class);
		}
		public CommandContext command(int i) {
			return getRuleContext(CommandContext.class,i);
		}
		public List<TerminalNode> CR() { return getTokens(TclParser.CR); }
		public TerminalNode CR(int i) {
			return getToken(TclParser.CR, i);
		}
		public ModuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_module; }
	}

	public final ModuleContext module() throws RecognitionException {
		ModuleContext _localctx = new ModuleContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_module);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__4) | (1L << T__5) | (1L << T__7) | (1L << T__8) | (1L << T__12) | (1L << T__29) | (1L << T__30) | (1L << IDENTIFIER) | (1L << STRING_LITERAL) | (1L << INTEGER_LITERAL) | (1L << DOUBLE_LITERAL) | (1L << BOOLEAN_LITERAL))) != 0)) {
				{
				{
				setState(117);
				((ModuleContext)_localctx).command = command(false);
				 factory.addModuleStatement(((ModuleContext)_localctx).command.result); 
				setState(120); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(119);
						match(CR);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(122); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				}
				setState(128);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CommandContext extends ParserRuleContext {
		public boolean inLoop;
		public TclStatementNode result;
		public While_commandContext while_command;
		public Token b;
		public Token c;
		public If_commandContext if_command;
		public Return_commandContext return_command;
		public ExpressionContext expression;
		public While_commandContext while_command() {
			return getRuleContext(While_commandContext.class,0);
		}
		public If_commandContext if_command() {
			return getRuleContext(If_commandContext.class,0);
		}
		public Return_commandContext return_command() {
			return getRuleContext(Return_commandContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public CommandContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public CommandContext(ParserRuleContext parent, int invokingState, boolean inLoop) {
			super(parent, invokingState);
			this.inLoop = inLoop;
		}
		@Override public int getRuleIndex() { return RULE_command; }
	}

	public final CommandContext command(boolean inLoop) throws RecognitionException {
		CommandContext _localctx = new CommandContext(_ctx, getState(), inLoop);
		enterRule(_localctx, 8, RULE_command);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__7:
				{
				setState(129);
				((CommandContext)_localctx).while_command = while_command();
				 ((CommandContext)_localctx).result =  ((CommandContext)_localctx).while_command.result; 
				}
				break;
			case T__4:
				{
				setState(132);
				((CommandContext)_localctx).b = match(T__4);
				 if (inLoop) { ((CommandContext)_localctx).result =  factory.createBreak(((CommandContext)_localctx).b); } else { SemErr(((CommandContext)_localctx).b, "break used outside of loop"); } 
				}
				break;
			case T__5:
				{
				setState(134);
				((CommandContext)_localctx).c = match(T__5);
				 if (inLoop) { ((CommandContext)_localctx).result =  factory.createContinue(((CommandContext)_localctx).c); } else { SemErr(((CommandContext)_localctx).c, "continue used outside of loop"); } 
				}
				break;
			case T__8:
				{
				setState(136);
				((CommandContext)_localctx).if_command = if_command(inLoop);
				 ((CommandContext)_localctx).result =  ((CommandContext)_localctx).if_command.result; 
				}
				break;
			case T__12:
				{
				setState(139);
				((CommandContext)_localctx).return_command = return_command();
				 ((CommandContext)_localctx).result =  ((CommandContext)_localctx).return_command.result; 
				}
				break;
			case T__1:
			case T__29:
			case T__30:
			case IDENTIFIER:
			case STRING_LITERAL:
			case INTEGER_LITERAL:
			case DOUBLE_LITERAL:
			case BOOLEAN_LITERAL:
				{
				setState(142);
				((CommandContext)_localctx).expression = expression();
				 ((CommandContext)_localctx).result =  ((CommandContext)_localctx).expression.result; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(148);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(147);
				match(T__6);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class While_commandContext extends ParserRuleContext {
		public TclStatementNode result;
		public Token w;
		public ExpressionContext condition;
		public BlockContext body;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public While_commandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_while_command; }
	}

	public final While_commandContext while_command() throws RecognitionException {
		While_commandContext _localctx = new While_commandContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_while_command);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			((While_commandContext)_localctx).w = match(T__7);
			setState(151);
			((While_commandContext)_localctx).condition = expression();
			setState(152);
			((While_commandContext)_localctx).body = block(true);
			 ((While_commandContext)_localctx).result =  factory.createWhile(((While_commandContext)_localctx).w, ((While_commandContext)_localctx).condition.result, ((While_commandContext)_localctx).body.result); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class If_commandContext extends ParserRuleContext {
		public boolean inLoop;
		public TclStatementNode result;
		public Token i;
		public ExpressionContext condition;
		public BlockContext then;
		public BlockContext block;
		public ExpressionContext condition2;
		public BlockContext then2;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public If_commandContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public If_commandContext(ParserRuleContext parent, int invokingState, boolean inLoop) {
			super(parent, invokingState);
			this.inLoop = inLoop;
		}
		@Override public int getRuleIndex() { return RULE_if_command; }
	}

	public final If_commandContext if_command(boolean inLoop) throws RecognitionException {
		If_commandContext _localctx = new If_commandContext(_ctx, getState(), inLoop);
		enterRule(_localctx, 12, RULE_if_command);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			((If_commandContext)_localctx).i = match(T__8);
			setState(156);
			((If_commandContext)_localctx).condition = expression();
			setState(158);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(157);
				match(T__9);
				}
			}

			setState(160);
			((If_commandContext)_localctx).then = ((If_commandContext)_localctx).block = block(inLoop);
			 TclStatementNode elsePart = null; 
			setState(171);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(162);
				match(T__10);
				setState(163);
				((If_commandContext)_localctx).condition2 = expression();
				setState(165);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__9) {
					{
					setState(164);
					match(T__9);
					}
				}

				setState(167);
				((If_commandContext)_localctx).then2 = ((If_commandContext)_localctx).block = block(inLoop);
				}
				}
				setState(173);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1 || _la==T__11) {
				{
				setState(175);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__11) {
					{
					setState(174);
					match(T__11);
					}
				}

				setState(177);
				((If_commandContext)_localctx).block = block(inLoop);
				 elsePart = ((If_commandContext)_localctx).block.result; 
				}
			}

			 ((If_commandContext)_localctx).result =  factory.createIf(((If_commandContext)_localctx).i, ((If_commandContext)_localctx).condition.result, ((If_commandContext)_localctx).then.result, elsePart); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Return_commandContext extends ParserRuleContext {
		public TclStatementNode result;
		public Token r;
		public ExpressionContext expression;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Return_commandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_command; }
	}

	public final Return_commandContext return_command() throws RecognitionException {
		Return_commandContext _localctx = new Return_commandContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_return_command);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			((Return_commandContext)_localctx).r = match(T__12);
			 TclExpressionNode value = null; 
			setState(189);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__29) | (1L << T__30) | (1L << IDENTIFIER) | (1L << STRING_LITERAL) | (1L << INTEGER_LITERAL) | (1L << DOUBLE_LITERAL) | (1L << BOOLEAN_LITERAL))) != 0)) {
				{
				setState(186);
				((Return_commandContext)_localctx).expression = expression();
				 value = ((Return_commandContext)_localctx).expression.result; 
				}
			}

			 ((Return_commandContext)_localctx).result =  factory.createReturn(((Return_commandContext)_localctx).r, value); 
			setState(193);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(192);
				match(T__6);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public TclExpressionNode result;
		public Logic_termContext logic_term;
		public Token op;
		public List<Logic_termContext> logic_term() {
			return getRuleContexts(Logic_termContext.class);
		}
		public Logic_termContext logic_term(int i) {
			return getRuleContext(Logic_termContext.class,i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_expression);
		int _la;
		try {
			int _alt;
			setState(220);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				setState(195);
				match(T__1);
				setState(196);
				((ExpressionContext)_localctx).logic_term = logic_term();
				 ((ExpressionContext)_localctx).result =  ((ExpressionContext)_localctx).logic_term.result; 
				setState(204);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__13) {
					{
					{
					setState(198);
					((ExpressionContext)_localctx).op = match(T__13);
					setState(199);
					((ExpressionContext)_localctx).logic_term = logic_term();
					 ((ExpressionContext)_localctx).result =  factory.createBinary(((ExpressionContext)_localctx).op, _localctx.result, ((ExpressionContext)_localctx).logic_term.result); 
					}
					}
					setState(206);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(207);
				match(T__3);
				}
				break;
			case T__29:
			case T__30:
			case IDENTIFIER:
			case STRING_LITERAL:
			case INTEGER_LITERAL:
			case DOUBLE_LITERAL:
			case BOOLEAN_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(209);
				((ExpressionContext)_localctx).logic_term = logic_term();
				 ((ExpressionContext)_localctx).result =  ((ExpressionContext)_localctx).logic_term.result; 
				setState(217);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(211);
						((ExpressionContext)_localctx).op = match(T__13);
						setState(212);
						((ExpressionContext)_localctx).logic_term = logic_term();
						 ((ExpressionContext)_localctx).result =  factory.createBinary(((ExpressionContext)_localctx).op, _localctx.result, ((ExpressionContext)_localctx).logic_term.result); 
						}
						} 
					}
					setState(219);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Logic_termContext extends ParserRuleContext {
		public TclExpressionNode result;
		public Logic_factorContext logic_factor;
		public Token op;
		public List<Logic_factorContext> logic_factor() {
			return getRuleContexts(Logic_factorContext.class);
		}
		public Logic_factorContext logic_factor(int i) {
			return getRuleContext(Logic_factorContext.class,i);
		}
		public Logic_termContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logic_term; }
	}

	public final Logic_termContext logic_term() throws RecognitionException {
		Logic_termContext _localctx = new Logic_termContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_logic_term);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			((Logic_termContext)_localctx).logic_factor = logic_factor();
			 ((Logic_termContext)_localctx).result =  ((Logic_termContext)_localctx).logic_factor.result; 
			setState(230);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(224);
					((Logic_termContext)_localctx).op = match(T__14);
					setState(225);
					((Logic_termContext)_localctx).logic_factor = logic_factor();
					 ((Logic_termContext)_localctx).result =  factory.createBinary(((Logic_termContext)_localctx).op, _localctx.result, ((Logic_termContext)_localctx).logic_factor.result); 
					}
					} 
				}
				setState(232);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Logic_factorContext extends ParserRuleContext {
		public TclExpressionNode result;
		public ArithmeticContext arithmetic;
		public Token op;
		public List<ArithmeticContext> arithmetic() {
			return getRuleContexts(ArithmeticContext.class);
		}
		public ArithmeticContext arithmetic(int i) {
			return getRuleContext(ArithmeticContext.class,i);
		}
		public Logic_factorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logic_factor; }
	}

	public final Logic_factorContext logic_factor() throws RecognitionException {
		Logic_factorContext _localctx = new Logic_factorContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_logic_factor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			((Logic_factorContext)_localctx).arithmetic = arithmetic();
			 ((Logic_factorContext)_localctx).result =  ((Logic_factorContext)_localctx).arithmetic.result; 
			setState(239);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				{
				setState(235);
				((Logic_factorContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22))) != 0)) ) {
					((Logic_factorContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(236);
				((Logic_factorContext)_localctx).arithmetic = arithmetic();
				 ((Logic_factorContext)_localctx).result =  factory.createBinary(((Logic_factorContext)_localctx).op, _localctx.result, ((Logic_factorContext)_localctx).arithmetic.result); 
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArithmeticContext extends ParserRuleContext {
		public TclExpressionNode result;
		public Term_addContext term_add;
		public Token op;
		public List<Term_addContext> term_add() {
			return getRuleContexts(Term_addContext.class);
		}
		public Term_addContext term_add(int i) {
			return getRuleContext(Term_addContext.class,i);
		}
		public ArithmeticContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmetic; }
	}

	public final ArithmeticContext arithmetic() throws RecognitionException {
		ArithmeticContext _localctx = new ArithmeticContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_arithmetic);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(241);
			((ArithmeticContext)_localctx).term_add = term_add();
			 ((ArithmeticContext)_localctx).result =  ((ArithmeticContext)_localctx).term_add.result; 
			setState(249);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(243);
					((ArithmeticContext)_localctx).op = _input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==T__23 || _la==T__24) ) {
						((ArithmeticContext)_localctx).op = (Token)_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(244);
					((ArithmeticContext)_localctx).term_add = term_add();
					 ((ArithmeticContext)_localctx).result =  factory.createBinary(((ArithmeticContext)_localctx).op, _localctx.result, ((ArithmeticContext)_localctx).term_add.result); 
					}
					} 
				}
				setState(251);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Term_addContext extends ParserRuleContext {
		public TclExpressionNode result;
		public Term_potContext term_pot;
		public Token op;
		public List<Term_potContext> term_pot() {
			return getRuleContexts(Term_potContext.class);
		}
		public Term_potContext term_pot(int i) {
			return getRuleContext(Term_potContext.class,i);
		}
		public Term_addContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term_add; }
	}

	public final Term_addContext term_add() throws RecognitionException {
		Term_addContext _localctx = new Term_addContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_term_add);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
			((Term_addContext)_localctx).term_pot = term_pot();
			 ((Term_addContext)_localctx).result =  ((Term_addContext)_localctx).term_pot.result; 
			setState(260);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(254);
					((Term_addContext)_localctx).op = _input.LT(1);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__25) | (1L << T__26) | (1L << T__27))) != 0)) ) {
						((Term_addContext)_localctx).op = (Token)_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(255);
					((Term_addContext)_localctx).term_pot = term_pot();
					 ((Term_addContext)_localctx).result =  factory.createBinary(((Term_addContext)_localctx).op, _localctx.result, ((Term_addContext)_localctx).term_pot.result); 
					}
					} 
				}
				setState(262);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Term_potContext extends ParserRuleContext {
		public TclExpressionNode result;
		public TermContext term;
		public Token op;
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public Term_potContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term_pot; }
	}

	public final Term_potContext term_pot() throws RecognitionException {
		Term_potContext _localctx = new Term_potContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_term_pot);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(263);
			((Term_potContext)_localctx).term = term();
			 ((Term_potContext)_localctx).result =  ((Term_potContext)_localctx).term.result; 
			setState(271);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(265);
					((Term_potContext)_localctx).op = match(T__28);
					setState(266);
					((Term_potContext)_localctx).term = term();
					 ((Term_potContext)_localctx).result =  factory.createBinary(((Term_potContext)_localctx).op, _localctx.result, ((Term_potContext)_localctx).term.result); 
					}
					} 
				}
				setState(273);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public TclExpressionNode result;
		public Token IDENTIFIER;
		public Member_expressionContext member_expression;
		public Token s;
		public ExpressionContext exp;
		public Token e;
		public TerminalNode IDENTIFIER() { return getToken(TclParser.IDENTIFIER, 0); }
		public Member_expressionContext member_expression() {
			return getRuleContext(Member_expressionContext.class,0);
		}
		public Command_parametersContext command_parameters() {
			return getRuleContext(Command_parametersContext.class,0);
		}
		public WordContext word() {
			return getRuleContext(WordContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_term);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				{
				setState(274);
				match(T__29);
				setState(275);
				((TermContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				 TclExpressionNode assignmentName = factory.createStringLiteral(((TermContext)_localctx).IDENTIFIER, false); 
				}
				break;
			case 2:
				{
				setState(277);
				((TermContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				 TclExpressionNode assignmentName = factory.createStringLiteral(((TermContext)_localctx).IDENTIFIER, false); 
				setState(279);
				((TermContext)_localctx).member_expression = member_expression(null, null, assignmentName);
				 ((TermContext)_localctx).result =  ((TermContext)_localctx).member_expression.result; 
				}
				break;
			case 3:
				{
				setState(282);
				((TermContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				 TclExpressionNode assignmentName = factory.createStringLiteral(((TermContext)_localctx).IDENTIFIER, false); 
				setState(284);
				command_parameters(((TermContext)_localctx).IDENTIFIER, assignmentName);
				 ((TermContext)_localctx).result =  ((TermContext)_localctx).member_expression.result; 
				}
				break;
			case 4:
				{
				setState(287);
				word();
				}
				break;
			case 5:
				{
				setState(288);
				((TermContext)_localctx).s = match(T__30);
				setState(289);
				((TermContext)_localctx).exp = expression();
				setState(290);
				((TermContext)_localctx).e = match(T__31);
				 ((TermContext)_localctx).result =  factory.createParentExpression(((TermContext)_localctx).exp.result, ((TermContext)_localctx).s.getStartIndex(), ((TermContext)_localctx).e.getStopIndex() - ((TermContext)_localctx).s.getStartIndex() + 1); 
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WordContext extends ParserRuleContext {
		public TclExpressionNode result;
		public Token STRING_LITERAL;
		public Token INTEGER_LITERAL;
		public Token DOUBLE_LITERAL;
		public Token BOOLEAN_LITERAL;
		public TerminalNode STRING_LITERAL() { return getToken(TclParser.STRING_LITERAL, 0); }
		public TerminalNode INTEGER_LITERAL() { return getToken(TclParser.INTEGER_LITERAL, 0); }
		public TerminalNode DOUBLE_LITERAL() { return getToken(TclParser.DOUBLE_LITERAL, 0); }
		public TerminalNode BOOLEAN_LITERAL() { return getToken(TclParser.BOOLEAN_LITERAL, 0); }
		public WordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_word; }
	}

	public final WordContext word() throws RecognitionException {
		WordContext _localctx = new WordContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_word);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(303);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING_LITERAL:
				{
				setState(295);
				((WordContext)_localctx).STRING_LITERAL = match(STRING_LITERAL);
				 ((WordContext)_localctx).result =  factory.createStringLiteral(((WordContext)_localctx).STRING_LITERAL, true); 
				}
				break;
			case INTEGER_LITERAL:
				{
				setState(297);
				((WordContext)_localctx).INTEGER_LITERAL = match(INTEGER_LITERAL);
				 ((WordContext)_localctx).result =  factory.createIntegerLiteral(((WordContext)_localctx).INTEGER_LITERAL); 
				}
				break;
			case DOUBLE_LITERAL:
				{
				setState(299);
				((WordContext)_localctx).DOUBLE_LITERAL = match(DOUBLE_LITERAL);
				 ((WordContext)_localctx).result =  factory.createDoubleLiteral(((WordContext)_localctx).DOUBLE_LITERAL); 
				}
				break;
			case BOOLEAN_LITERAL:
				{
				setState(301);
				((WordContext)_localctx).BOOLEAN_LITERAL = match(BOOLEAN_LITERAL);
				 ((WordContext)_localctx).result =  factory.createBooleanLiteral(((WordContext)_localctx).BOOLEAN_LITERAL); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Member_expressionContext extends ParserRuleContext {
		public TclExpressionNode r;
		public TclExpressionNode assignmentReceiver;
		public TclExpressionNode assignmentName;
		public TclExpressionNode result;
		public Token IDENTIFIER;
		public ExpressionContext expression;
		public Member_expressionContext member_expression;
		public TerminalNode IDENTIFIER() { return getToken(TclParser.IDENTIFIER, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Member_expressionContext member_expression() {
			return getRuleContext(Member_expressionContext.class,0);
		}
		public Member_expressionContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public Member_expressionContext(ParserRuleContext parent, int invokingState, TclExpressionNode r, TclExpressionNode assignmentReceiver, TclExpressionNode assignmentName) {
			super(parent, invokingState);
			this.r = r;
			this.assignmentReceiver = assignmentReceiver;
			this.assignmentName = assignmentName;
		}
		@Override public int getRuleIndex() { return RULE_member_expression; }
	}

	public final Member_expressionContext member_expression(TclExpressionNode r,TclExpressionNode assignmentReceiver,TclExpressionNode assignmentName) throws RecognitionException {
		Member_expressionContext _localctx = new Member_expressionContext(_ctx, getState(), r, assignmentReceiver, assignmentName);
		enterRule(_localctx, 32, RULE_member_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 TclExpressionNode receiver = r;
			                                                  TclExpressionNode nestedAssignmentName = null; 
			setState(316);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__32:
				{
				setState(306);
				match(T__32);
				 if (receiver == null) {
				                                                       receiver = factory.createRead(assignmentName);
				                                                  } 
				setState(308);
				((Member_expressionContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				 nestedAssignmentName = factory.createStringLiteral(((Member_expressionContext)_localctx).IDENTIFIER, false);
				                                                  ((Member_expressionContext)_localctx).result =  factory.createReadProperty(receiver, nestedAssignmentName); 
				}
				break;
			case T__33:
				{
				setState(310);
				match(T__33);
				 if (receiver == null) {
				                                                      receiver = factory.createRead(assignmentName);
				                                                  } 
				setState(312);
				((Member_expressionContext)_localctx).expression = expression();
				 nestedAssignmentName = ((Member_expressionContext)_localctx).expression.result;
				                                                  ((Member_expressionContext)_localctx).result =  factory.createReadProperty(receiver, nestedAssignmentName); 
				setState(314);
				match(T__34);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(321);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__32 || _la==T__33) {
				{
				setState(318);
				((Member_expressionContext)_localctx).member_expression = member_expression(_localctx.result, receiver, nestedAssignmentName);
				 ((Member_expressionContext)_localctx).result =  ((Member_expressionContext)_localctx).member_expression.result; 
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Command_parametersContext extends ParserRuleContext {
		public Token start;
		public TclExpressionNode assignmentName;
		public TclExpressionNode result;
		public ExpressionContext end;
		public ExpressionContext expression;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public Command_parametersContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public Command_parametersContext(ParserRuleContext parent, int invokingState, Token start, TclExpressionNode assignmentName) {
			super(parent, invokingState);
			this.start = start;
			this.assignmentName = assignmentName;
		}
		@Override public int getRuleIndex() { return RULE_command_parameters; }
	}

	public final Command_parametersContext command_parameters(Token start,TclExpressionNode assignmentName) throws RecognitionException {
		Command_parametersContext _localctx = new Command_parametersContext(_ctx, getState(), start, assignmentName);
		enterRule(_localctx, 34, RULE_command_parameters);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			 TclExpressionNode nestedAssignmentName = null; 
			 List<TclExpressionNode> parameters = new ArrayList<>();
			                                                  Token end = start;
			                                                  TclExpressionNode receiver = factory.createRead(assignmentName);
			                                             
			setState(330);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(325);
					((Command_parametersContext)_localctx).end = ((Command_parametersContext)_localctx).expression = expression();
					 parameters.add(((Command_parametersContext)_localctx).expression.result); 
					}
					} 
				}
				setState(332);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			}
			 ((Command_parametersContext)_localctx).result =  factory.createCall(null, parameters, end); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3-\u0152\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\7\2(\n\2\f\2\16\2+\13\2\3\2\7\2.\n\2\f\2\16\2\61\13\2\3"+
		"\2\3\2\7\2\65\n\2\f\2\16\28\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\7\3E\n\3\f\3\16\3H\13\3\5\3J\n\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\7"+
		"\4S\n\4\f\4\16\4V\13\4\3\4\3\4\3\4\5\4[\n\4\3\4\6\4^\n\4\r\4\16\4_\3\4"+
		"\3\4\3\4\7\4e\n\4\f\4\16\4h\13\4\3\4\7\4k\n\4\f\4\16\4n\13\4\3\4\3\4\3"+
		"\4\7\4s\n\4\f\4\16\4v\13\4\3\5\3\5\3\5\6\5{\n\5\r\5\16\5|\7\5\177\n\5"+
		"\f\5\16\5\u0082\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\5\6\u0094\n\6\3\6\5\6\u0097\n\6\3\7\3\7\3\7\3\7\3\7\3\b"+
		"\3\b\3\b\5\b\u00a1\n\b\3\b\3\b\3\b\3\b\3\b\5\b\u00a8\n\b\3\b\3\b\7\b\u00ac"+
		"\n\b\f\b\16\b\u00af\13\b\3\b\5\b\u00b2\n\b\3\b\3\b\3\b\5\b\u00b7\n\b\3"+
		"\b\3\b\3\t\3\t\3\t\3\t\3\t\5\t\u00c0\n\t\3\t\3\t\5\t\u00c4\n\t\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\7\n\u00cd\n\n\f\n\16\n\u00d0\13\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\7\n\u00da\n\n\f\n\16\n\u00dd\13\n\5\n\u00df\n\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\7\13\u00e7\n\13\f\13\16\13\u00ea\13\13\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\5\f\u00f2\n\f\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u00fa\n\r"+
		"\f\r\16\r\u00fd\13\r\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u0105\n\16\f\16"+
		"\16\16\u0108\13\16\3\17\3\17\3\17\3\17\3\17\3\17\7\17\u0110\n\17\f\17"+
		"\16\17\u0113\13\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u0128\n\20\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u0132\n\21\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u013f\n\22\3\22\3\22\3\22\5\22\u0144"+
		"\n\22\3\23\3\23\3\23\3\23\3\23\7\23\u014b\n\23\f\23\16\23\u014e\13\23"+
		"\3\23\3\23\3\23\2\2\24\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$\2\5"+
		"\3\2\22\31\3\2\32\33\3\2\34\36\2\u016b\2)\3\2\2\2\4;\3\2\2\2\6O\3\2\2"+
		"\2\b\u0080\3\2\2\2\n\u0093\3\2\2\2\f\u0098\3\2\2\2\16\u009d\3\2\2\2\20"+
		"\u00ba\3\2\2\2\22\u00de\3\2\2\2\24\u00e0\3\2\2\2\26\u00eb\3\2\2\2\30\u00f3"+
		"\3\2\2\2\32\u00fe\3\2\2\2\34\u0109\3\2\2\2\36\u0127\3\2\2\2 \u0131\3\2"+
		"\2\2\"\u0133\3\2\2\2$\u0145\3\2\2\2&(\7(\2\2\'&\3\2\2\2(+\3\2\2\2)\'\3"+
		"\2\2\2)*\3\2\2\2*/\3\2\2\2+)\3\2\2\2,.\5\4\3\2-,\3\2\2\2.\61\3\2\2\2/"+
		"-\3\2\2\2/\60\3\2\2\2\60\62\3\2\2\2\61/\3\2\2\2\62\66\5\b\5\2\63\65\7"+
		"(\2\2\64\63\3\2\2\2\658\3\2\2\2\66\64\3\2\2\2\66\67\3\2\2\2\679\3\2\2"+
		"\28\66\3\2\2\29:\7\2\2\3:\3\3\2\2\2;<\7\3\2\2<=\7)\2\2=>\7\4\2\2>I\b\3"+
		"\1\2?@\7)\2\2@F\b\3\1\2AB\7\5\2\2BC\7)\2\2CE\b\3\1\2DA\3\2\2\2EH\3\2\2"+
		"\2FD\3\2\2\2FG\3\2\2\2GJ\3\2\2\2HF\3\2\2\2I?\3\2\2\2IJ\3\2\2\2JK\3\2\2"+
		"\2KL\7\6\2\2LM\5\6\4\2MN\b\3\1\2N\5\3\2\2\2OP\b\4\1\2PT\7\4\2\2QS\7(\2"+
		"\2RQ\3\2\2\2SV\3\2\2\2TR\3\2\2\2TU\3\2\2\2UZ\3\2\2\2VT\3\2\2\2WX\5\n\6"+
		"\2XY\b\4\1\2Y[\3\2\2\2ZW\3\2\2\2Z[\3\2\2\2[f\3\2\2\2\\^\7(\2\2]\\\3\2"+
		"\2\2^_\3\2\2\2_]\3\2\2\2_`\3\2\2\2`a\3\2\2\2ab\5\n\6\2bc\b\4\1\2ce\3\2"+
		"\2\2d]\3\2\2\2eh\3\2\2\2fd\3\2\2\2fg\3\2\2\2gl\3\2\2\2hf\3\2\2\2ik\7("+
		"\2\2ji\3\2\2\2kn\3\2\2\2lj\3\2\2\2lm\3\2\2\2mo\3\2\2\2nl\3\2\2\2op\7\6"+
		"\2\2pt\b\4\1\2qs\7(\2\2rq\3\2\2\2sv\3\2\2\2tr\3\2\2\2tu\3\2\2\2u\7\3\2"+
		"\2\2vt\3\2\2\2wx\5\n\6\2xz\b\5\1\2y{\7(\2\2zy\3\2\2\2{|\3\2\2\2|z\3\2"+
		"\2\2|}\3\2\2\2}\177\3\2\2\2~w\3\2\2\2\177\u0082\3\2\2\2\u0080~\3\2\2\2"+
		"\u0080\u0081\3\2\2\2\u0081\t\3\2\2\2\u0082\u0080\3\2\2\2\u0083\u0084\5"+
		"\f\7\2\u0084\u0085\b\6\1\2\u0085\u0094\3\2\2\2\u0086\u0087\7\7\2\2\u0087"+
		"\u0094\b\6\1\2\u0088\u0089\7\b\2\2\u0089\u0094\b\6\1\2\u008a\u008b\5\16"+
		"\b\2\u008b\u008c\b\6\1\2\u008c\u0094\3\2\2\2\u008d\u008e\5\20\t\2\u008e"+
		"\u008f\b\6\1\2\u008f\u0094\3\2\2\2\u0090\u0091\5\22\n\2\u0091\u0092\b"+
		"\6\1\2\u0092\u0094\3\2\2\2\u0093\u0083\3\2\2\2\u0093\u0086\3\2\2\2\u0093"+
		"\u0088\3\2\2\2\u0093\u008a\3\2\2\2\u0093\u008d\3\2\2\2\u0093\u0090\3\2"+
		"\2\2\u0094\u0096\3\2\2\2\u0095\u0097\7\t\2\2\u0096\u0095\3\2\2\2\u0096"+
		"\u0097\3\2\2\2\u0097\13\3\2\2\2\u0098\u0099\7\n\2\2\u0099\u009a\5\22\n"+
		"\2\u009a\u009b\5\6\4\2\u009b\u009c\b\7\1\2\u009c\r\3\2\2\2\u009d\u009e"+
		"\7\13\2\2\u009e\u00a0\5\22\n\2\u009f\u00a1\7\f\2\2\u00a0\u009f\3\2\2\2"+
		"\u00a0\u00a1\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a3\5\6\4\2\u00a3\u00ad"+
		"\b\b\1\2\u00a4\u00a5\7\r\2\2\u00a5\u00a7\5\22\n\2\u00a6\u00a8\7\f\2\2"+
		"\u00a7\u00a6\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00aa"+
		"\5\6\4\2\u00aa\u00ac\3\2\2\2\u00ab\u00a4\3\2\2\2\u00ac\u00af\3\2\2\2\u00ad"+
		"\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00b6\3\2\2\2\u00af\u00ad\3\2"+
		"\2\2\u00b0\u00b2\7\16\2\2\u00b1\u00b0\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2"+
		"\u00b3\3\2\2\2\u00b3\u00b4\5\6\4\2\u00b4\u00b5\b\b\1\2\u00b5\u00b7\3\2"+
		"\2\2\u00b6\u00b1\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8"+
		"\u00b9\b\b\1\2\u00b9\17\3\2\2\2\u00ba\u00bb\7\17\2\2\u00bb\u00bf\b\t\1"+
		"\2\u00bc\u00bd\5\22\n\2\u00bd\u00be\b\t\1\2\u00be\u00c0\3\2\2\2\u00bf"+
		"\u00bc\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c3\b\t"+
		"\1\2\u00c2\u00c4\7\t\2\2\u00c3\u00c2\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4"+
		"\21\3\2\2\2\u00c5\u00c6\7\4\2\2\u00c6\u00c7\5\24\13\2\u00c7\u00ce\b\n"+
		"\1\2\u00c8\u00c9\7\20\2\2\u00c9\u00ca\5\24\13\2\u00ca\u00cb\b\n\1\2\u00cb"+
		"\u00cd\3\2\2\2\u00cc\u00c8\3\2\2\2\u00cd\u00d0\3\2\2\2\u00ce\u00cc\3\2"+
		"\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d1\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d1"+
		"\u00d2\7\6\2\2\u00d2\u00df\3\2\2\2\u00d3\u00d4\5\24\13\2\u00d4\u00db\b"+
		"\n\1\2\u00d5\u00d6\7\20\2\2\u00d6\u00d7\5\24\13\2\u00d7\u00d8\b\n\1\2"+
		"\u00d8\u00da\3\2\2\2\u00d9\u00d5\3\2\2\2\u00da\u00dd\3\2\2\2\u00db\u00d9"+
		"\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00df\3\2\2\2\u00dd\u00db\3\2\2\2\u00de"+
		"\u00c5\3\2\2\2\u00de\u00d3\3\2\2\2\u00df\23\3\2\2\2\u00e0\u00e1\5\26\f"+
		"\2\u00e1\u00e8\b\13\1\2\u00e2\u00e3\7\21\2\2\u00e3\u00e4\5\26\f\2\u00e4"+
		"\u00e5\b\13\1\2\u00e5\u00e7\3\2\2\2\u00e6\u00e2\3\2\2\2\u00e7\u00ea\3"+
		"\2\2\2\u00e8\u00e6\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\25\3\2\2\2\u00ea"+
		"\u00e8\3\2\2\2\u00eb\u00ec\5\30\r\2\u00ec\u00f1\b\f\1\2\u00ed\u00ee\t"+
		"\2\2\2\u00ee\u00ef\5\30\r\2\u00ef\u00f0\b\f\1\2\u00f0\u00f2\3\2\2\2\u00f1"+
		"\u00ed\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2\27\3\2\2\2\u00f3\u00f4\5\32\16"+
		"\2\u00f4\u00fb\b\r\1\2\u00f5\u00f6\t\3\2\2\u00f6\u00f7\5\32\16\2\u00f7"+
		"\u00f8\b\r\1\2\u00f8\u00fa\3\2\2\2\u00f9\u00f5\3\2\2\2\u00fa\u00fd\3\2"+
		"\2\2\u00fb\u00f9\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc\31\3\2\2\2\u00fd\u00fb"+
		"\3\2\2\2\u00fe\u00ff\5\34\17\2\u00ff\u0106\b\16\1\2\u0100\u0101\t\4\2"+
		"\2\u0101\u0102\5\34\17\2\u0102\u0103\b\16\1\2\u0103\u0105\3\2\2\2\u0104"+
		"\u0100\3\2\2\2\u0105\u0108\3\2\2\2\u0106\u0104\3\2\2\2\u0106\u0107\3\2"+
		"\2\2\u0107\33\3\2\2\2\u0108\u0106\3\2\2\2\u0109\u010a\5\36\20\2\u010a"+
		"\u0111\b\17\1\2\u010b\u010c\7\37\2\2\u010c\u010d\5\36\20\2\u010d\u010e"+
		"\b\17\1\2\u010e\u0110\3\2\2\2\u010f\u010b\3\2\2\2\u0110\u0113\3\2\2\2"+
		"\u0111\u010f\3\2\2\2\u0111\u0112\3\2\2\2\u0112\35\3\2\2\2\u0113\u0111"+
		"\3\2\2\2\u0114\u0115\7 \2\2\u0115\u0116\7)\2\2\u0116\u0128\b\20\1\2\u0117"+
		"\u0118\7)\2\2\u0118\u0119\b\20\1\2\u0119\u011a\5\"\22\2\u011a\u011b\b"+
		"\20\1\2\u011b\u0128\3\2\2\2\u011c\u011d\7)\2\2\u011d\u011e\b\20\1\2\u011e"+
		"\u011f\5$\23\2\u011f\u0120\b\20\1\2\u0120\u0128\3\2\2\2\u0121\u0128\5"+
		" \21\2\u0122\u0123\7!\2\2\u0123\u0124\5\22\n\2\u0124\u0125\7\"\2\2\u0125"+
		"\u0126\b\20\1\2\u0126\u0128\3\2\2\2\u0127\u0114\3\2\2\2\u0127\u0117\3"+
		"\2\2\2\u0127\u011c\3\2\2\2\u0127\u0121\3\2\2\2\u0127\u0122\3\2\2\2\u0128"+
		"\37\3\2\2\2\u0129\u012a\7*\2\2\u012a\u0132\b\21\1\2\u012b\u012c\7+\2\2"+
		"\u012c\u0132\b\21\1\2\u012d\u012e\7,\2\2\u012e\u0132\b\21\1\2\u012f\u0130"+
		"\7-\2\2\u0130\u0132\b\21\1\2\u0131\u0129\3\2\2\2\u0131\u012b\3\2\2\2\u0131"+
		"\u012d\3\2\2\2\u0131\u012f\3\2\2\2\u0132!\3\2\2\2\u0133\u013e\b\22\1\2"+
		"\u0134\u0135\7#\2\2\u0135\u0136\b\22\1\2\u0136\u0137\7)\2\2\u0137\u013f"+
		"\b\22\1\2\u0138\u0139\7$\2\2\u0139\u013a\b\22\1\2\u013a\u013b\5\22\n\2"+
		"\u013b\u013c\b\22\1\2\u013c\u013d\7%\2\2\u013d\u013f\3\2\2\2\u013e\u0134"+
		"\3\2\2\2\u013e\u0138\3\2\2\2\u013f\u0143\3\2\2\2\u0140\u0141\5\"\22\2"+
		"\u0141\u0142\b\22\1\2\u0142\u0144\3\2\2\2\u0143\u0140\3\2\2\2\u0143\u0144"+
		"\3\2\2\2\u0144#\3\2\2\2\u0145\u0146\b\23\1\2\u0146\u014c\b\23\1\2\u0147"+
		"\u0148\5\22\n\2\u0148\u0149\b\23\1\2\u0149\u014b\3\2\2\2\u014a\u0147\3"+
		"\2\2\2\u014b\u014e\3\2\2\2\u014c\u014a\3\2\2\2\u014c\u014d\3\2\2\2\u014d"+
		"\u014f\3\2\2\2\u014e\u014c\3\2\2\2\u014f\u0150\b\23\1\2\u0150%\3\2\2\2"+
		"%)/\66FITZ_flt|\u0080\u0093\u0096\u00a0\u00a7\u00ad\u00b1\u00b6\u00bf"+
		"\u00c3\u00ce\u00db\u00de\u00e8\u00f1\u00fb\u0106\u0111\u0127\u0131\u013e"+
		"\u0143\u014c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}