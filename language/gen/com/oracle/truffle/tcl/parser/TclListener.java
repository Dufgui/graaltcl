// Generated from /Users/guillaumedufour/git/graalvm/graaltcl/language/src/main/java/com/oracle/truffle/tcl/parser/Tcl.g4 by ANTLR 4.9.1
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

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TclParser}.
 */
public interface TclListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TclParser#tcl}.
	 * @param ctx the parse tree
	 */
	void enterTcl(TclParser.TclContext ctx);
	/**
	 * Exit a parse tree produced by {@link TclParser#tcl}.
	 * @param ctx the parse tree
	 */
	void exitTcl(TclParser.TclContext ctx);
	/**
	 * Enter a parse tree produced by {@link TclParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(TclParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TclParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(TclParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TclParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(TclParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link TclParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(TclParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link TclParser#command}.
	 * @param ctx the parse tree
	 */
	void enterCommand(TclParser.CommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link TclParser#command}.
	 * @param ctx the parse tree
	 */
	void exitCommand(TclParser.CommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link TclParser#while_command}.
	 * @param ctx the parse tree
	 */
	void enterWhile_command(TclParser.While_commandContext ctx);
	/**
	 * Exit a parse tree produced by {@link TclParser#while_command}.
	 * @param ctx the parse tree
	 */
	void exitWhile_command(TclParser.While_commandContext ctx);
	/**
	 * Enter a parse tree produced by {@link TclParser#if_command}.
	 * @param ctx the parse tree
	 */
	void enterIf_command(TclParser.If_commandContext ctx);
	/**
	 * Exit a parse tree produced by {@link TclParser#if_command}.
	 * @param ctx the parse tree
	 */
	void exitIf_command(TclParser.If_commandContext ctx);
	/**
	 * Enter a parse tree produced by {@link TclParser#return_command}.
	 * @param ctx the parse tree
	 */
	void enterReturn_command(TclParser.Return_commandContext ctx);
	/**
	 * Exit a parse tree produced by {@link TclParser#return_command}.
	 * @param ctx the parse tree
	 */
	void exitReturn_command(TclParser.Return_commandContext ctx);
	/**
	 * Enter a parse tree produced by {@link TclParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(TclParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TclParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(TclParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TclParser#logic_term}.
	 * @param ctx the parse tree
	 */
	void enterLogic_term(TclParser.Logic_termContext ctx);
	/**
	 * Exit a parse tree produced by {@link TclParser#logic_term}.
	 * @param ctx the parse tree
	 */
	void exitLogic_term(TclParser.Logic_termContext ctx);
	/**
	 * Enter a parse tree produced by {@link TclParser#logic_factor}.
	 * @param ctx the parse tree
	 */
	void enterLogic_factor(TclParser.Logic_factorContext ctx);
	/**
	 * Exit a parse tree produced by {@link TclParser#logic_factor}.
	 * @param ctx the parse tree
	 */
	void exitLogic_factor(TclParser.Logic_factorContext ctx);
	/**
	 * Enter a parse tree produced by {@link TclParser#arithmetic}.
	 * @param ctx the parse tree
	 */
	void enterArithmetic(TclParser.ArithmeticContext ctx);
	/**
	 * Exit a parse tree produced by {@link TclParser#arithmetic}.
	 * @param ctx the parse tree
	 */
	void exitArithmetic(TclParser.ArithmeticContext ctx);
	/**
	 * Enter a parse tree produced by {@link TclParser#term_add}.
	 * @param ctx the parse tree
	 */
	void enterTerm_add(TclParser.Term_addContext ctx);
	/**
	 * Exit a parse tree produced by {@link TclParser#term_add}.
	 * @param ctx the parse tree
	 */
	void exitTerm_add(TclParser.Term_addContext ctx);
	/**
	 * Enter a parse tree produced by {@link TclParser#term_pot}.
	 * @param ctx the parse tree
	 */
	void enterTerm_pot(TclParser.Term_potContext ctx);
	/**
	 * Exit a parse tree produced by {@link TclParser#term_pot}.
	 * @param ctx the parse tree
	 */
	void exitTerm_pot(TclParser.Term_potContext ctx);
	/**
	 * Enter a parse tree produced by {@link TclParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(TclParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link TclParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(TclParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link TclParser#word}.
	 * @param ctx the parse tree
	 */
	void enterWord(TclParser.WordContext ctx);
	/**
	 * Exit a parse tree produced by {@link TclParser#word}.
	 * @param ctx the parse tree
	 */
	void exitWord(TclParser.WordContext ctx);
	/**
	 * Enter a parse tree produced by {@link TclParser#member_expression}.
	 * @param ctx the parse tree
	 */
	void enterMember_expression(TclParser.Member_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TclParser#member_expression}.
	 * @param ctx the parse tree
	 */
	void exitMember_expression(TclParser.Member_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TclParser#command_parameters}.
	 * @param ctx the parse tree
	 */
	void enterCommand_parameters(TclParser.Command_parametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link TclParser#command_parameters}.
	 * @param ctx the parse tree
	 */
	void exitCommand_parameters(TclParser.Command_parametersContext ctx);
}