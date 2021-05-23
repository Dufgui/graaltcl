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

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TclParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TclVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TclParser#tcl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTcl(TclParser.TclContext ctx);
	/**
	 * Visit a parse tree produced by {@link TclParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(TclParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TclParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(TclParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link TclParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommand(TclParser.CommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link TclParser#while_command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_command(TclParser.While_commandContext ctx);
	/**
	 * Visit a parse tree produced by {@link TclParser#if_command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_command(TclParser.If_commandContext ctx);
	/**
	 * Visit a parse tree produced by {@link TclParser#return_command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_command(TclParser.Return_commandContext ctx);
	/**
	 * Visit a parse tree produced by {@link TclParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(TclParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TclParser#logic_term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogic_term(TclParser.Logic_termContext ctx);
	/**
	 * Visit a parse tree produced by {@link TclParser#logic_factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogic_factor(TclParser.Logic_factorContext ctx);
	/**
	 * Visit a parse tree produced by {@link TclParser#arithmetic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmetic(TclParser.ArithmeticContext ctx);
	/**
	 * Visit a parse tree produced by {@link TclParser#term_add}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm_add(TclParser.Term_addContext ctx);
	/**
	 * Visit a parse tree produced by {@link TclParser#term_pot}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm_pot(TclParser.Term_potContext ctx);
	/**
	 * Visit a parse tree produced by {@link TclParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(TclParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link TclParser#word}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWord(TclParser.WordContext ctx);
	/**
	 * Visit a parse tree produced by {@link TclParser#member_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMember_expression(TclParser.Member_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TclParser#command_parameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommand_parameters(TclParser.Command_parametersContext ctx);
}