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
		T__31=32, T__32=33, T__33=34, COMMENT=35, WS=36, NL=37, STRING_LITERAL=38, 
		IDENTIFIER=39, INTEGER_LITERAL=40, DOUBLE_LITERAL=41, BOOLEAN_LITERAL=42;
	public static final int
		RULE_tcl = 0, RULE_function = 1, RULE_block = 2, RULE_command = 3, RULE_while_command = 4, 
		RULE_if_command = 5, RULE_return_command = 6, RULE_expression = 7, RULE_term = 8, 
		RULE_word = 9, RULE_member_expression = 10, RULE_command_parameters = 11;
	public static final String[] ruleNames = {
		"tcl", "function", "block", "command", "while_command", "if_command", 
		"return_command", "expression", "term", "word", "member_expression", "command_parameters"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'proc'", "'{'", "'}'", "'break'", "'continue'", "';'", "'while'", 
		"'if'", "'then'", "'elseif'", "'else'", "'return'", "'('", "')'", "'||'", 
		"'&&'", "'<'", "'<='", "'>'", "'>='", "'=='", "'!='", "'eq'", "'ne'", 
		"'*'", "'/'", "'%'", "'+'", "'-'", "'**'", "'$'", "'['", "']'", "'::'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, "COMMENT", 
		"WS", "NL", "STRING_LITERAL", "IDENTIFIER", "INTEGER_LITERAL", "DOUBLE_LITERAL", 
		"BOOLEAN_LITERAL"
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
		public CommandContext command;
		public TerminalNode EOF() { return getToken(TclParser.EOF, 0); }
		public List<TerminalNode> NL() { return getTokens(TclParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(TclParser.NL, i);
		}
		public List<FunctionContext> function() {
			return getRuleContexts(FunctionContext.class);
		}
		public FunctionContext function(int i) {
			return getRuleContext(FunctionContext.class,i);
		}
		public List<CommandContext> command() {
			return getRuleContexts(CommandContext.class);
		}
		public CommandContext command(int i) {
			return getRuleContext(CommandContext.class,i);
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
			setState(27);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(24);
					match(NL);
					}
					} 
				}
				setState(29);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(40);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__3) | (1L << T__4) | (1L << T__6) | (1L << T__7) | (1L << T__11) | (1L << T__12) | (1L << T__30) | (1L << T__31) | (1L << STRING_LITERAL) | (1L << IDENTIFIER) | (1L << INTEGER_LITERAL) | (1L << DOUBLE_LITERAL) | (1L << BOOLEAN_LITERAL))) != 0)) {
				{
				setState(38);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__0:
					{
					setState(30);
					function();
					}
					break;
				case T__1:
				case T__3:
				case T__4:
				case T__6:
				case T__7:
				case T__11:
				case T__12:
				case T__30:
				case T__31:
				case STRING_LITERAL:
				case IDENTIFIER:
				case INTEGER_LITERAL:
				case DOUBLE_LITERAL:
				case BOOLEAN_LITERAL:
					{
					setState(31);
					((TclContext)_localctx).command = command(false);
					 factory.addModuleStatement(((TclContext)_localctx).command.result); 
					setState(34); 
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
						case 1:
							{
							{
							setState(33);
							match(NL);
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(36); 
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
					} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(42);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(46);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(43);
				match(NL);
				}
				}
				setState(48);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(49);
			match(EOF);
			 factory.finishModule(); 
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
			setState(52);
			match(T__0);
			setState(53);
			((FunctionContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			setState(54);
			((FunctionContext)_localctx).s = match(T__1);
			 factory.startFunction(((FunctionContext)_localctx).IDENTIFIER, ((FunctionContext)_localctx).s); 
			setState(65);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(56);
				((FunctionContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				 factory.addFormalParameter(((FunctionContext)_localctx).IDENTIFIER); 
				setState(62);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==IDENTIFIER) {
					{
					{
					setState(58);
					((FunctionContext)_localctx).IDENTIFIER = match(IDENTIFIER);
					 factory.addFormalParameter(((FunctionContext)_localctx).IDENTIFIER); 
					}
					}
					setState(64);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(67);
			match(T__2);
			setState(68);
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
		public List<TerminalNode> NL() { return getTokens(TclParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(TclParser.NL, i);
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
			setState(72);
			((BlockContext)_localctx).s = match(T__1);
			setState(76);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(73);
					match(NL);
					}
					} 
				}
				setState(78);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			setState(82);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__3) | (1L << T__4) | (1L << T__6) | (1L << T__7) | (1L << T__11) | (1L << T__12) | (1L << T__30) | (1L << T__31) | (1L << STRING_LITERAL) | (1L << IDENTIFIER) | (1L << INTEGER_LITERAL) | (1L << DOUBLE_LITERAL) | (1L << BOOLEAN_LITERAL))) != 0)) {
				{
				setState(79);
				((BlockContext)_localctx).command = command(inLoop);
				 body.add(((BlockContext)_localctx).command.result); 
				}
			}

			setState(94);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(85); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(84);
						match(NL);
						}
						}
						setState(87); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==NL );
					setState(89);
					((BlockContext)_localctx).command = command(inLoop);
					 body.add(((BlockContext)_localctx).command.result); 
					}
					} 
				}
				setState(96);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			setState(100);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(97);
				match(NL);
				}
				}
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(103);
			((BlockContext)_localctx).e = match(T__2);
			 ((BlockContext)_localctx).result =  factory.finishBlock(body, ((BlockContext)_localctx).s.getStartIndex(), ((BlockContext)_localctx).e.getStopIndex() - ((BlockContext)_localctx).s.getStartIndex() + 1); 
			setState(108);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(105);
					match(NL);
					}
					} 
				}
				setState(110);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
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
		enterRule(_localctx, 6, RULE_command);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
				{
				setState(111);
				((CommandContext)_localctx).while_command = while_command();
				 ((CommandContext)_localctx).result =  ((CommandContext)_localctx).while_command.result; 
				}
				break;
			case T__3:
				{
				setState(114);
				((CommandContext)_localctx).b = match(T__3);
				 if (inLoop) { ((CommandContext)_localctx).result =  factory.createBreak(((CommandContext)_localctx).b); } else { SemErr(((CommandContext)_localctx).b, "break used outside of loop"); } 
				}
				break;
			case T__4:
				{
				setState(116);
				((CommandContext)_localctx).c = match(T__4);
				 if (inLoop) { ((CommandContext)_localctx).result =  factory.createContinue(((CommandContext)_localctx).c); } else { SemErr(((CommandContext)_localctx).c, "continue used outside of loop"); } 
				}
				break;
			case T__7:
				{
				setState(118);
				((CommandContext)_localctx).if_command = if_command(inLoop);
				 ((CommandContext)_localctx).result =  ((CommandContext)_localctx).if_command.result; 
				}
				break;
			case T__11:
				{
				setState(121);
				((CommandContext)_localctx).return_command = return_command();
				 ((CommandContext)_localctx).result =  ((CommandContext)_localctx).return_command.result; 
				}
				break;
			case T__1:
			case T__12:
			case T__30:
			case T__31:
			case STRING_LITERAL:
			case IDENTIFIER:
			case INTEGER_LITERAL:
			case DOUBLE_LITERAL:
			case BOOLEAN_LITERAL:
				{
				setState(124);
				((CommandContext)_localctx).expression = expression(0);
				 ((CommandContext)_localctx).result =  ((CommandContext)_localctx).expression.result; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(130);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(129);
				match(T__5);
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
		enterRule(_localctx, 8, RULE_while_command);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			((While_commandContext)_localctx).w = match(T__6);
			setState(133);
			((While_commandContext)_localctx).condition = expression(0);
			setState(134);
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
		enterRule(_localctx, 10, RULE_if_command);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			((If_commandContext)_localctx).i = match(T__7);
			setState(138);
			((If_commandContext)_localctx).condition = expression(0);
			setState(140);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(139);
				match(T__8);
				}
			}

			setState(142);
			((If_commandContext)_localctx).then = ((If_commandContext)_localctx).block = block(inLoop);
			 TclStatementNode elsePart = null; 
			setState(153);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(144);
				match(T__9);
				setState(145);
				((If_commandContext)_localctx).condition2 = expression(0);
				setState(147);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__8) {
					{
					setState(146);
					match(T__8);
					}
				}

				setState(149);
				((If_commandContext)_localctx).then2 = ((If_commandContext)_localctx).block = block(inLoop);
				}
				}
				setState(155);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(162);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1 || _la==T__10) {
				{
				setState(157);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__10) {
					{
					setState(156);
					match(T__10);
					}
				}

				setState(159);
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
		enterRule(_localctx, 12, RULE_return_command);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			((Return_commandContext)_localctx).r = match(T__11);
			 TclExpressionNode value = null; 
			setState(171);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__12) | (1L << T__30) | (1L << T__31) | (1L << STRING_LITERAL) | (1L << IDENTIFIER) | (1L << INTEGER_LITERAL) | (1L << DOUBLE_LITERAL) | (1L << BOOLEAN_LITERAL))) != 0)) {
				{
				setState(168);
				((Return_commandContext)_localctx).expression = expression(0);
				 value = ((Return_commandContext)_localctx).expression.result; 
				}
			}

			 ((Return_commandContext)_localctx).result =  factory.createReturn(((Return_commandContext)_localctx).r, value); 
			setState(175);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(174);
				match(T__5);
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
		public ExpressionContext left;
		public ExpressionContext expression;
		public TermContext term;
		public Token op;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				{
				setState(178);
				match(T__1);
				setState(179);
				((ExpressionContext)_localctx).expression = expression(0);
				 ((ExpressionContext)_localctx).result =  ((ExpressionContext)_localctx).expression.result; 
				setState(181);
				match(T__2);
				}
				break;
			case T__12:
				{
				setState(183);
				match(T__12);
				setState(184);
				((ExpressionContext)_localctx).expression = expression(0);
				 ((ExpressionContext)_localctx).result =  ((ExpressionContext)_localctx).expression.result; 
				setState(186);
				match(T__13);
				}
				break;
			case T__30:
			case T__31:
			case STRING_LITERAL:
			case IDENTIFIER:
			case INTEGER_LITERAL:
			case DOUBLE_LITERAL:
			case BOOLEAN_LITERAL:
				{
				setState(188);
				((ExpressionContext)_localctx).term = term();
				 ((ExpressionContext)_localctx).result =  ((ExpressionContext)_localctx).term.result; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(225);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(223);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(193);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(194);
						((ExpressionContext)_localctx).op = match(T__14);
						setState(195);
						((ExpressionContext)_localctx).right = ((ExpressionContext)_localctx).expression = expression(8);
						 ((ExpressionContext)_localctx).result =  factory.createBinary(((ExpressionContext)_localctx).op, ((ExpressionContext)_localctx).left.result, ((ExpressionContext)_localctx).right.result); 
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(198);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(199);
						((ExpressionContext)_localctx).op = match(T__15);
						setState(200);
						((ExpressionContext)_localctx).right = ((ExpressionContext)_localctx).expression = expression(7);
						 ((ExpressionContext)_localctx).result =  factory.createBinary(((ExpressionContext)_localctx).op, ((ExpressionContext)_localctx).left.result, ((ExpressionContext)_localctx).right.result); 
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(203);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(204);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23))) != 0)) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(205);
						((ExpressionContext)_localctx).right = ((ExpressionContext)_localctx).expression = expression(6);
						 ((ExpressionContext)_localctx).result =  factory.createBinary(((ExpressionContext)_localctx).op, ((ExpressionContext)_localctx).left.result, ((ExpressionContext)_localctx).right.result); 
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(208);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(209);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__24) | (1L << T__25) | (1L << T__26))) != 0)) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(210);
						((ExpressionContext)_localctx).right = ((ExpressionContext)_localctx).expression = expression(5);
						 ((ExpressionContext)_localctx).result =  factory.createBinary(((ExpressionContext)_localctx).op, ((ExpressionContext)_localctx).left.result, ((ExpressionContext)_localctx).right.result); 
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(213);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(214);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__27 || _la==T__28) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(215);
						((ExpressionContext)_localctx).right = ((ExpressionContext)_localctx).expression = expression(4);
						 ((ExpressionContext)_localctx).result =  factory.createBinary(((ExpressionContext)_localctx).op, ((ExpressionContext)_localctx).left.result, ((ExpressionContext)_localctx).right.result); 
						}
						break;
					case 6:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(218);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(219);
						((ExpressionContext)_localctx).op = match(T__29);
						setState(220);
						((ExpressionContext)_localctx).right = ((ExpressionContext)_localctx).expression = expression(3);
						 ((ExpressionContext)_localctx).result =  factory.createBinary(((ExpressionContext)_localctx).op, ((ExpressionContext)_localctx).left.result, ((ExpressionContext)_localctx).right.result); 
						}
						break;
					}
					} 
				}
				setState(227);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public TclExpressionNode result;
		public Token var;
		public Token IDENTIFIER;
		public Member_expressionContext member_expression;
		public Command_parametersContext command_parameters;
		public Token s;
		public ExpressionContext exp;
		public Token e;
		public WordContext word;
		public TerminalNode IDENTIFIER() { return getToken(TclParser.IDENTIFIER, 0); }
		public WordContext word() {
			return getRuleContext(WordContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode INTEGER_LITERAL() { return getToken(TclParser.INTEGER_LITERAL, 0); }
		public Member_expressionContext member_expression() {
			return getRuleContext(Member_expressionContext.class,0);
		}
		public Command_parametersContext command_parameters() {
			return getRuleContext(Command_parametersContext.class,0);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(250);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				{
				setState(228);
				match(T__30);
				setState(229);
				((TermContext)_localctx).var = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==IDENTIFIER || _la==INTEGER_LITERAL) ) {
					((TermContext)_localctx).var = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				 TclExpressionNode assignmentName = factory.createStringLiteral(((TermContext)_localctx).var, false);
				                                                    ((TermContext)_localctx).result =  factory.createRead(assignmentName);
				                                                
				}
				break;
			case 2:
				{
				setState(231);
				((TermContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				 TclExpressionNode assignmentName = factory.createStringLiteral(((TermContext)_localctx).IDENTIFIER, false); 
				setState(237);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
				case 1:
					{
					setState(233);
					((TermContext)_localctx).member_expression = member_expression(null, null, assignmentName);
					 ((TermContext)_localctx).result =  ((TermContext)_localctx).member_expression.result; 
					}
					break;
				case 2:
					{
					 ((TermContext)_localctx).result =  factory.createIdentifier(assignmentName); 
					}
					break;
				}
				{
				setState(239);
				((TermContext)_localctx).command_parameters = command_parameters(((TermContext)_localctx).IDENTIFIER, assignmentName);
				 ((TermContext)_localctx).result =  ((TermContext)_localctx).command_parameters.result; 
				}
				}
				break;
			case 3:
				{
				setState(242);
				((TermContext)_localctx).s = match(T__31);
				setState(243);
				((TermContext)_localctx).exp = expression(0);
				setState(244);
				((TermContext)_localctx).e = match(T__32);
				 ((TermContext)_localctx).result =  factory.createParentExpression(((TermContext)_localctx).exp.result, ((TermContext)_localctx).s.getStartIndex(), ((TermContext)_localctx).e.getStopIndex() - ((TermContext)_localctx).s.getStartIndex() + 1); 
				}
				break;
			case 4:
				{
				setState(247);
				((TermContext)_localctx).word = word();
				 ((TermContext)_localctx).result =  ((TermContext)_localctx).word.result; 
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
		public Token IDENTIFIER;
		public TerminalNode STRING_LITERAL() { return getToken(TclParser.STRING_LITERAL, 0); }
		public TerminalNode INTEGER_LITERAL() { return getToken(TclParser.INTEGER_LITERAL, 0); }
		public TerminalNode DOUBLE_LITERAL() { return getToken(TclParser.DOUBLE_LITERAL, 0); }
		public TerminalNode BOOLEAN_LITERAL() { return getToken(TclParser.BOOLEAN_LITERAL, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TclParser.IDENTIFIER, 0); }
		public WordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_word; }
	}

	public final WordContext word() throws RecognitionException {
		WordContext _localctx = new WordContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_word);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING_LITERAL:
				{
				setState(252);
				((WordContext)_localctx).STRING_LITERAL = match(STRING_LITERAL);
				 ((WordContext)_localctx).result =  factory.createStringLiteral(((WordContext)_localctx).STRING_LITERAL, true); 
				}
				break;
			case INTEGER_LITERAL:
				{
				setState(254);
				((WordContext)_localctx).INTEGER_LITERAL = match(INTEGER_LITERAL);
				 ((WordContext)_localctx).result =  factory.createIntegerLiteral(((WordContext)_localctx).INTEGER_LITERAL); 
				}
				break;
			case DOUBLE_LITERAL:
				{
				setState(256);
				((WordContext)_localctx).DOUBLE_LITERAL = match(DOUBLE_LITERAL);
				 ((WordContext)_localctx).result =  factory.createDoubleLiteral(((WordContext)_localctx).DOUBLE_LITERAL); 
				}
				break;
			case BOOLEAN_LITERAL:
				{
				setState(258);
				((WordContext)_localctx).BOOLEAN_LITERAL = match(BOOLEAN_LITERAL);
				 ((WordContext)_localctx).result =  factory.createBooleanLiteral(((WordContext)_localctx).BOOLEAN_LITERAL); 
				}
				break;
			case IDENTIFIER:
				{
				setState(260);
				((WordContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				 ((WordContext)_localctx).result =  factory.createStringLiteral(((WordContext)_localctx).IDENTIFIER, false); 
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
		public Member_expressionContext member_expression;
		public ExpressionContext expression;
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
		enterRule(_localctx, 20, RULE_member_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			 TclExpressionNode receiver = r;
			                                                  TclExpressionNode nestedAssignmentName = null; 
			setState(280);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__33:
				{
				setState(265);
				match(T__33);
				 if (receiver == null) {
				                                                       receiver = factory.createRead(assignmentName);
				                                                  } 
				setState(267);
				((Member_expressionContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				 nestedAssignmentName = factory.createStringLiteral(((Member_expressionContext)_localctx).IDENTIFIER, false);
				                                                  ((Member_expressionContext)_localctx).result =  factory.createReadProperty(receiver, nestedAssignmentName); 
				setState(272);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
				case 1:
					{
					setState(269);
					((Member_expressionContext)_localctx).member_expression = member_expression(_localctx.result, receiver, nestedAssignmentName);
					 ((Member_expressionContext)_localctx).result =  ((Member_expressionContext)_localctx).member_expression.result; 
					}
					break;
				}
				}
				break;
			case T__12:
				{
				setState(274);
				match(T__12);
				 if (receiver == null) {
				                                                      receiver = factory.createRead(assignmentName);
				                                                  } 
				setState(276);
				((Member_expressionContext)_localctx).expression = expression(0);
				 nestedAssignmentName = ((Member_expressionContext)_localctx).expression.result;
				                                                  ((Member_expressionContext)_localctx).result =  factory.createReadProperty(receiver, nestedAssignmentName); 
				setState(278);
				match(T__13);
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
		enterRule(_localctx, 22, RULE_command_parameters);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			 TclExpressionNode nestedAssignmentName = null; 
			 List<TclExpressionNode> parameters = new ArrayList<>();
			                                                  Token end = start;
			                                                  TclExpressionNode receiver = factory.createRead(assignmentName);
			                                             
			setState(287); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(284);
					((Command_parametersContext)_localctx).end = ((Command_parametersContext)_localctx).expression = expression(0);
					 parameters.add(((Command_parametersContext)_localctx).expression.result); 
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(289); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			 ((Command_parametersContext)_localctx).result =  factory.createCall(receiver, parameters, end); 
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 7:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 7);
		case 1:
			return precpred(_ctx, 6);
		case 2:
			return precpred(_ctx, 5);
		case 3:
			return precpred(_ctx, 4);
		case 4:
			return precpred(_ctx, 3);
		case 5:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3,\u0128\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\3\2\7\2\34\n\2\f\2\16\2\37\13\2\3\2\3\2\3\2\3\2\6"+
		"\2%\n\2\r\2\16\2&\7\2)\n\2\f\2\16\2,\13\2\3\2\7\2/\n\2\f\2\16\2\62\13"+
		"\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3?\n\3\f\3\16\3B\13\3"+
		"\5\3D\n\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\7\4M\n\4\f\4\16\4P\13\4\3\4\3\4"+
		"\3\4\5\4U\n\4\3\4\6\4X\n\4\r\4\16\4Y\3\4\3\4\3\4\7\4_\n\4\f\4\16\4b\13"+
		"\4\3\4\7\4e\n\4\f\4\16\4h\13\4\3\4\3\4\3\4\7\4m\n\4\f\4\16\4p\13\4\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u0082"+
		"\n\5\3\5\5\5\u0085\n\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\5\7\u008f\n\7\3"+
		"\7\3\7\3\7\3\7\3\7\5\7\u0096\n\7\3\7\3\7\7\7\u009a\n\7\f\7\16\7\u009d"+
		"\13\7\3\7\5\7\u00a0\n\7\3\7\3\7\3\7\5\7\u00a5\n\7\3\7\3\7\3\b\3\b\3\b"+
		"\3\b\3\b\5\b\u00ae\n\b\3\b\3\b\5\b\u00b2\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00c2\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u00e2\n\t\f\t\16\t\u00e5\13\t\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00f0\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\5\n\u00fd\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\5\13\u0109\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u0113\n\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\5\f\u011b\n\f\3\r\3\r\3\r\3\r\3\r\6\r\u0122\n\r"+
		"\r\r\16\r\u0123\3\r\3\r\3\r\2\3\20\16\2\4\6\b\n\f\16\20\22\24\26\30\2"+
		"\6\3\2\23\32\3\2\33\35\3\2\36\37\3\2)*\2\u0148\2\35\3\2\2\2\4\66\3\2\2"+
		"\2\6I\3\2\2\2\b\u0081\3\2\2\2\n\u0086\3\2\2\2\f\u008b\3\2\2\2\16\u00a8"+
		"\3\2\2\2\20\u00c1\3\2\2\2\22\u00fc\3\2\2\2\24\u0108\3\2\2\2\26\u010a\3"+
		"\2\2\2\30\u011c\3\2\2\2\32\34\7\'\2\2\33\32\3\2\2\2\34\37\3\2\2\2\35\33"+
		"\3\2\2\2\35\36\3\2\2\2\36*\3\2\2\2\37\35\3\2\2\2 )\5\4\3\2!\"\5\b\5\2"+
		"\"$\b\2\1\2#%\7\'\2\2$#\3\2\2\2%&\3\2\2\2&$\3\2\2\2&\'\3\2\2\2\')\3\2"+
		"\2\2( \3\2\2\2(!\3\2\2\2),\3\2\2\2*(\3\2\2\2*+\3\2\2\2+\60\3\2\2\2,*\3"+
		"\2\2\2-/\7\'\2\2.-\3\2\2\2/\62\3\2\2\2\60.\3\2\2\2\60\61\3\2\2\2\61\63"+
		"\3\2\2\2\62\60\3\2\2\2\63\64\7\2\2\3\64\65\b\2\1\2\65\3\3\2\2\2\66\67"+
		"\7\3\2\2\678\7)\2\289\7\4\2\29C\b\3\1\2:;\7)\2\2;@\b\3\1\2<=\7)\2\2=?"+
		"\b\3\1\2><\3\2\2\2?B\3\2\2\2@>\3\2\2\2@A\3\2\2\2AD\3\2\2\2B@\3\2\2\2C"+
		":\3\2\2\2CD\3\2\2\2DE\3\2\2\2EF\7\5\2\2FG\5\6\4\2GH\b\3\1\2H\5\3\2\2\2"+
		"IJ\b\4\1\2JN\7\4\2\2KM\7\'\2\2LK\3\2\2\2MP\3\2\2\2NL\3\2\2\2NO\3\2\2\2"+
		"OT\3\2\2\2PN\3\2\2\2QR\5\b\5\2RS\b\4\1\2SU\3\2\2\2TQ\3\2\2\2TU\3\2\2\2"+
		"U`\3\2\2\2VX\7\'\2\2WV\3\2\2\2XY\3\2\2\2YW\3\2\2\2YZ\3\2\2\2Z[\3\2\2\2"+
		"[\\\5\b\5\2\\]\b\4\1\2]_\3\2\2\2^W\3\2\2\2_b\3\2\2\2`^\3\2\2\2`a\3\2\2"+
		"\2af\3\2\2\2b`\3\2\2\2ce\7\'\2\2dc\3\2\2\2eh\3\2\2\2fd\3\2\2\2fg\3\2\2"+
		"\2gi\3\2\2\2hf\3\2\2\2ij\7\5\2\2jn\b\4\1\2km\7\'\2\2lk\3\2\2\2mp\3\2\2"+
		"\2nl\3\2\2\2no\3\2\2\2o\7\3\2\2\2pn\3\2\2\2qr\5\n\6\2rs\b\5\1\2s\u0082"+
		"\3\2\2\2tu\7\6\2\2u\u0082\b\5\1\2vw\7\7\2\2w\u0082\b\5\1\2xy\5\f\7\2y"+
		"z\b\5\1\2z\u0082\3\2\2\2{|\5\16\b\2|}\b\5\1\2}\u0082\3\2\2\2~\177\5\20"+
		"\t\2\177\u0080\b\5\1\2\u0080\u0082\3\2\2\2\u0081q\3\2\2\2\u0081t\3\2\2"+
		"\2\u0081v\3\2\2\2\u0081x\3\2\2\2\u0081{\3\2\2\2\u0081~\3\2\2\2\u0082\u0084"+
		"\3\2\2\2\u0083\u0085\7\b\2\2\u0084\u0083\3\2\2\2\u0084\u0085\3\2\2\2\u0085"+
		"\t\3\2\2\2\u0086\u0087\7\t\2\2\u0087\u0088\5\20\t\2\u0088\u0089\5\6\4"+
		"\2\u0089\u008a\b\6\1\2\u008a\13\3\2\2\2\u008b\u008c\7\n\2\2\u008c\u008e"+
		"\5\20\t\2\u008d\u008f\7\13\2\2\u008e\u008d\3\2\2\2\u008e\u008f\3\2\2\2"+
		"\u008f\u0090\3\2\2\2\u0090\u0091\5\6\4\2\u0091\u009b\b\7\1\2\u0092\u0093"+
		"\7\f\2\2\u0093\u0095\5\20\t\2\u0094\u0096\7\13\2\2\u0095\u0094\3\2\2\2"+
		"\u0095\u0096\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0098\5\6\4\2\u0098\u009a"+
		"\3\2\2\2\u0099\u0092\3\2\2\2\u009a\u009d\3\2\2\2\u009b\u0099\3\2\2\2\u009b"+
		"\u009c\3\2\2\2\u009c\u00a4\3\2\2\2\u009d\u009b\3\2\2\2\u009e\u00a0\7\r"+
		"\2\2\u009f\u009e\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1"+
		"\u00a2\5\6\4\2\u00a2\u00a3\b\7\1\2\u00a3\u00a5\3\2\2\2\u00a4\u009f\3\2"+
		"\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a7\b\7\1\2\u00a7"+
		"\r\3\2\2\2\u00a8\u00a9\7\16\2\2\u00a9\u00ad\b\b\1\2\u00aa\u00ab\5\20\t"+
		"\2\u00ab\u00ac\b\b\1\2\u00ac\u00ae\3\2\2\2\u00ad\u00aa\3\2\2\2\u00ad\u00ae"+
		"\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00b1\b\b\1\2\u00b0\u00b2\7\b\2\2\u00b1"+
		"\u00b0\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\17\3\2\2\2\u00b3\u00b4\b\t\1"+
		"\2\u00b4\u00b5\7\4\2\2\u00b5\u00b6\5\20\t\2\u00b6\u00b7\b\t\1\2\u00b7"+
		"\u00b8\7\5\2\2\u00b8\u00c2\3\2\2\2\u00b9\u00ba\7\17\2\2\u00ba\u00bb\5"+
		"\20\t\2\u00bb\u00bc\b\t\1\2\u00bc\u00bd\7\20\2\2\u00bd\u00c2\3\2\2\2\u00be"+
		"\u00bf\5\22\n\2\u00bf\u00c0\b\t\1\2\u00c0\u00c2\3\2\2\2\u00c1\u00b3\3"+
		"\2\2\2\u00c1\u00b9\3\2\2\2\u00c1\u00be\3\2\2\2\u00c2\u00e3\3\2\2\2\u00c3"+
		"\u00c4\f\t\2\2\u00c4\u00c5\7\21\2\2\u00c5\u00c6\5\20\t\n\u00c6\u00c7\b"+
		"\t\1\2\u00c7\u00e2\3\2\2\2\u00c8\u00c9\f\b\2\2\u00c9\u00ca\7\22\2\2\u00ca"+
		"\u00cb\5\20\t\t\u00cb\u00cc\b\t\1\2\u00cc\u00e2\3\2\2\2\u00cd\u00ce\f"+
		"\7\2\2\u00ce\u00cf\t\2\2\2\u00cf\u00d0\5\20\t\b\u00d0\u00d1\b\t\1\2\u00d1"+
		"\u00e2\3\2\2\2\u00d2\u00d3\f\6\2\2\u00d3\u00d4\t\3\2\2\u00d4\u00d5\5\20"+
		"\t\7\u00d5\u00d6\b\t\1\2\u00d6\u00e2\3\2\2\2\u00d7\u00d8\f\5\2\2\u00d8"+
		"\u00d9\t\4\2\2\u00d9\u00da\5\20\t\6\u00da\u00db\b\t\1\2\u00db\u00e2\3"+
		"\2\2\2\u00dc\u00dd\f\4\2\2\u00dd\u00de\7 \2\2\u00de\u00df\5\20\t\5\u00df"+
		"\u00e0\b\t\1\2\u00e0\u00e2\3\2\2\2\u00e1\u00c3\3\2\2\2\u00e1\u00c8\3\2"+
		"\2\2\u00e1\u00cd\3\2\2\2\u00e1\u00d2\3\2\2\2\u00e1\u00d7\3\2\2\2\u00e1"+
		"\u00dc\3\2\2\2\u00e2\u00e5\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2"+
		"\2\2\u00e4\21\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e6\u00e7\7!\2\2\u00e7\u00e8"+
		"\t\5\2\2\u00e8\u00fd\b\n\1\2\u00e9\u00ea\7)\2\2\u00ea\u00ef\b\n\1\2\u00eb"+
		"\u00ec\5\26\f\2\u00ec\u00ed\b\n\1\2\u00ed\u00f0\3\2\2\2\u00ee\u00f0\b"+
		"\n\1\2\u00ef\u00eb\3\2\2\2\u00ef\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1"+
		"\u00f2\5\30\r\2\u00f2\u00f3\b\n\1\2\u00f3\u00fd\3\2\2\2\u00f4\u00f5\7"+
		"\"\2\2\u00f5\u00f6\5\20\t\2\u00f6\u00f7\7#\2\2\u00f7\u00f8\b\n\1\2\u00f8"+
		"\u00fd\3\2\2\2\u00f9\u00fa\5\24\13\2\u00fa\u00fb\b\n\1\2\u00fb\u00fd\3"+
		"\2\2\2\u00fc\u00e6\3\2\2\2\u00fc\u00e9\3\2\2\2\u00fc\u00f4\3\2\2\2\u00fc"+
		"\u00f9\3\2\2\2\u00fd\23\3\2\2\2\u00fe\u00ff\7(\2\2\u00ff\u0109\b\13\1"+
		"\2\u0100\u0101\7*\2\2\u0101\u0109\b\13\1\2\u0102\u0103\7+\2\2\u0103\u0109"+
		"\b\13\1\2\u0104\u0105\7,\2\2\u0105\u0109\b\13\1\2\u0106\u0107\7)\2\2\u0107"+
		"\u0109\b\13\1\2\u0108\u00fe\3\2\2\2\u0108\u0100\3\2\2\2\u0108\u0102\3"+
		"\2\2\2\u0108\u0104\3\2\2\2\u0108\u0106\3\2\2\2\u0109\25\3\2\2\2\u010a"+
		"\u011a\b\f\1\2\u010b\u010c\7$\2\2\u010c\u010d\b\f\1\2\u010d\u010e\7)\2"+
		"\2\u010e\u0112\b\f\1\2\u010f\u0110\5\26\f\2\u0110\u0111\b\f\1\2\u0111"+
		"\u0113\3\2\2\2\u0112\u010f\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u011b\3\2"+
		"\2\2\u0114\u0115\7\17\2\2\u0115\u0116\b\f\1\2\u0116\u0117\5\20\t\2\u0117"+
		"\u0118\b\f\1\2\u0118\u0119\7\20\2\2\u0119\u011b\3\2\2\2\u011a\u010b\3"+
		"\2\2\2\u011a\u0114\3\2\2\2\u011b\27\3\2\2\2\u011c\u011d\b\r\1\2\u011d"+
		"\u0121\b\r\1\2\u011e\u011f\5\20\t\2\u011f\u0120\b\r\1\2\u0120\u0122\3"+
		"\2\2\2\u0121\u011e\3\2\2\2\u0122\u0123\3\2\2\2\u0123\u0121\3\2\2\2\u0123"+
		"\u0124\3\2\2\2\u0124\u0125\3\2\2\2\u0125\u0126\b\r\1\2\u0126\31\3\2\2"+
		"\2!\35&(*\60@CNTY`fn\u0081\u0084\u008e\u0095\u009b\u009f\u00a4\u00ad\u00b1"+
		"\u00c1\u00e1\u00e3\u00ef\u00fc\u0108\u0112\u011a\u0123";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}