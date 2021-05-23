/*
 * Copyright (c) 2012, 2020, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * The Universal Permissive License (UPL), Version 1.0
 *
 * Subject to the condition set forth below, permission is hereby granted to any
 * person obtaining a copy of this software, associated documentation and/or
 * data (collectively the "Software"), free of charge and under any and all
 * copyright rights in the Software, and any and all patent rights owned or
 * freely licensable by each licensor hereunder covering either (i) the
 * unmodified Software as contributed to or provided by such licensor, or (ii)
 * the Larger Works (as defined below), to deal in both
 *
 * (a) the Software, and
 *
 * (b) any piece of software and/or hardware listed in the lrgrwrks.txt file if
 * one is included with the Software each a "Larger Work" to which the Software
 * is contributed by such licensors),
 *
 * without restriction, including without limitation the rights to copy, create
 * derivative works of, display, perform, and distribute the Software and make,
 * use, sell, offer for sale, import, export, have made, and have sold the
 * Software and the Larger Work(s), and to sublicense the foregoing rights on
 * either these or other terms.
 *
 * This license is subject to the following condition:
 *
 * The above copyright notice and either this complete permission notice or at a
 * minimum a reference to the UPL must be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.oracle.truffle.tcl.parser;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oracle.truffle.tcl.nodes.expression.TclBooleanLiteralNode;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.Token;

import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.source.Source;
import com.oracle.truffle.api.source.SourceSection;
import com.oracle.truffle.tcl.TclLanguage;
import com.oracle.truffle.tcl.nodes.TclExpressionNode;
import com.oracle.truffle.tcl.nodes.TclRootNode;
import com.oracle.truffle.tcl.nodes.TclStatementNode;
import com.oracle.truffle.tcl.nodes.controlflow.TclBlockNode;
import com.oracle.truffle.tcl.nodes.controlflow.TclBreakNode;
import com.oracle.truffle.tcl.nodes.controlflow.TclContinueNode;
import com.oracle.truffle.tcl.nodes.controlflow.TclFunctionBodyNode;
import com.oracle.truffle.tcl.nodes.controlflow.TclIfNode;
import com.oracle.truffle.tcl.nodes.controlflow.TclReturnNode;
import com.oracle.truffle.tcl.nodes.controlflow.TclWhileNode;
import com.oracle.truffle.tcl.nodes.expression.TclAddNodeGen;
import com.oracle.truffle.tcl.nodes.expression.TclBigIntegerLiteralNode;
import com.oracle.truffle.tcl.nodes.expression.TclDivNodeGen;
import com.oracle.truffle.tcl.nodes.expression.TclDoubleLiteralNode;
import com.oracle.truffle.tcl.nodes.expression.TclEqualNodeGen;
import com.oracle.truffle.tcl.nodes.expression.TclFunctionLiteralNode;
import com.oracle.truffle.tcl.nodes.expression.TclInvokeNode;
import com.oracle.truffle.tcl.nodes.expression.TclLessOrEqualNodeGen;
import com.oracle.truffle.tcl.nodes.expression.TclLessThanNodeGen;
import com.oracle.truffle.tcl.nodes.expression.TclLogicalAndNode;
import com.oracle.truffle.tcl.nodes.expression.TclLogicalNotNodeGen;
import com.oracle.truffle.tcl.nodes.expression.TclLogicalOrNode;
import com.oracle.truffle.tcl.nodes.expression.TclLongLiteralNode;
import com.oracle.truffle.tcl.nodes.expression.TclMulNodeGen;
import com.oracle.truffle.tcl.nodes.expression.TclParenExpressionNode;
import com.oracle.truffle.tcl.nodes.expression.TclReadPropertyNode;
import com.oracle.truffle.tcl.nodes.expression.TclReadPropertyNodeGen;
import com.oracle.truffle.tcl.nodes.expression.TclStringLiteralNode;
import com.oracle.truffle.tcl.nodes.expression.TclSubNodeGen;
import com.oracle.truffle.tcl.nodes.expression.TclWritePropertyNode;
import com.oracle.truffle.tcl.nodes.expression.TclWritePropertyNodeGen;
import com.oracle.truffle.tcl.nodes.local.TclReadArgumentNode;
import com.oracle.truffle.tcl.nodes.local.TclReadLocalVariableNode;
import com.oracle.truffle.tcl.nodes.local.TclReadLocalVariableNodeGen;
import com.oracle.truffle.tcl.nodes.local.TclWriteLocalVariableNode;
import com.oracle.truffle.tcl.nodes.local.TclWriteLocalVariableNodeGen;
import com.oracle.truffle.tcl.nodes.util.TclUnboxNodeGen;

/**
 * Helper class used by the tcl {@link Parser} to create nodes. The code is factored out of the automatically generated
 * parser to keep the attributed grammar of tcl small.
 */
public class TclNodeFactory
{

    /**
     * Local variable names that are visible in the current block. Variables are not visible outside of their defining
     * block, to prevent the usage of undefined variables. Because of that, we can decide during parsing if a name
     * references a local variable or is a function name.
     */
    static class LexicalScope
    {
        protected final LexicalScope outer;
        protected final Map<String, FrameSlot> locals;

        LexicalScope( LexicalScope outer )
        {
            this.outer = outer;
            this.locals = new HashMap<>();
            if ( outer != null )
            {
                locals.putAll( outer.locals );
            }
        }
    }

    /* State while parsing a source unit. */
    private final Source source;
    private final Map<String, RootCallTarget> allFunctions;
    private final FrameDescriptor moduleFrameDescriptor;
    private final List<TclStatementNode> moduleNodes;

    /* State while parsing a module. */
    private int moduleStartPos = 0;

    /* State while parsing a function. */
    private int functionStartPos;
    private String functionName;
    private int functionBodyStartPos; // includes parameter list
    private int parameterCount;
    private FrameDescriptor frameDescriptor;
    private List<TclStatementNode> methodNodes;

    /* State while parsing a block. */
    private LexicalScope lexicalScope;
    private final TclLanguage language;

    public TclNodeFactory( TclLanguage language, Source source )
    {
        this.language = language;
        this.source = source;
        this.allFunctions = new HashMap<>();
        this.moduleFrameDescriptor = new FrameDescriptor();
        this.moduleNodes = new ArrayList<>();
        this.lexicalScope = new LexicalScope( null );
    }

    public Map<String, RootCallTarget> getAllFunctions()
    {
        return allFunctions;
    }

    public void startFunction( Token nameToken, Token bodyStartToken )
    {
        assert functionStartPos == 0;
        assert functionName == null;
        assert functionBodyStartPos == 0;
        assert parameterCount == 0;
        assert frameDescriptor == null;

        functionStartPos = nameToken.getStartIndex();
        functionName = nameToken.getText();
        functionBodyStartPos = bodyStartToken.getStartIndex();
        frameDescriptor = new FrameDescriptor();
        methodNodes = new ArrayList<>();
        startBlock();
    }

    public void addFormalParameter( Token nameToken )
    {
        /*
         * Method parameters are assigned to local variables at the beginning of the
         * method. This ensures that accesses to parameters are specialized the same way
         * as local variables are specialized.
         */
        final TclReadArgumentNode readArg = new TclReadArgumentNode( parameterCount );
        readArg.setSourceSection( nameToken.getStartIndex(), nameToken.getText().length() );
        TclExpressionNode assignment = createAssignment( createStringLiteral( nameToken, false ), readArg,
                parameterCount );
        methodNodes.add( assignment );
        parameterCount++;
    }

    public void finishFunction( TclStatementNode bodyNode )
    {
        if ( bodyNode == null )
        {
            // a state update that would otherwise be performed by finishBlock
            lexicalScope = lexicalScope.outer;
        }
        else
        {
            methodNodes.add( bodyNode );
            final int bodyEndPos = bodyNode.getSourceEndIndex();
            final SourceSection functionSrc = source.createSection( functionStartPos, bodyEndPos - functionStartPos );
            final TclStatementNode methodBlock = finishBlock( methodNodes, parameterCount, functionBodyStartPos,
                    bodyEndPos - functionBodyStartPos );

            final TclFunctionBodyNode functionBodyNode = new TclFunctionBodyNode( methodBlock );
            functionBodyNode.setSourceSection( functionSrc.getCharIndex(), functionSrc.getCharLength() );

            final TclRootNode rootNode = new TclRootNode( language, frameDescriptor, functionBodyNode, functionSrc,
                    functionName );
            allFunctions.put( functionName, Truffle.getRuntime().createCallTarget( rootNode ) );
        }

        functionStartPos = 0;
        functionName = null;
        functionBodyStartPos = 0;
        parameterCount = 0;
        frameDescriptor = null;
    }

    public void startBlock()
    {
        lexicalScope = new LexicalScope( lexicalScope );
    }

    public TclStatementNode finishBlock( List<TclStatementNode> bodyNodes, int startPos, int length )
    {
        return finishBlock( bodyNodes, 0, startPos, length );
    }

    public TclStatementNode finishBlock( List<TclStatementNode> bodyNodes, int skipCount, int startPos, int length )
    {
        lexicalScope = lexicalScope.outer;

        if ( containsNull( bodyNodes ) )
        {
            return null;
        }

        List<TclStatementNode> flattenedNodes = new ArrayList<>( bodyNodes.size() );
        flattenBlocks( bodyNodes, flattenedNodes );
        int n = flattenedNodes.size();
        for ( int i = skipCount; i < n; i++ )
        {
            TclStatementNode statement = flattenedNodes.get( i );
            if ( statement.hasSource() && !isHaltInCondition( statement ) )
            {
                statement.addStatementTag();
            }
        }
        TclBlockNode blockNode = new TclBlockNode(
                flattenedNodes.toArray( new TclStatementNode[flattenedNodes.size()] ) );
        blockNode.setSourceSection( startPos, length );
        return blockNode;
    }

    private static boolean isHaltInCondition( TclStatementNode statement )
    {
        return ( statement instanceof TclIfNode ) || ( statement instanceof TclWhileNode );
    }

    private void flattenBlocks( Iterable<? extends TclStatementNode> bodyNodes, List<TclStatementNode> flattenedNodes )
    {
        for ( TclStatementNode n : bodyNodes )
        {
            if ( n instanceof TclBlockNode )
            {
                flattenBlocks( ( (TclBlockNode) n ).getStatements(), flattenedNodes );
            }
            else
            {
                flattenedNodes.add( n );
            }
        }
    }

    /**
     * Returns an {@link TclBreakNode} for the given token.
     *
     * @param breakToken The token containing the break node's info.
     * @return A TclBreakNode for the given token.
     */
    public TclStatementNode createBreak( Token breakToken )
    {
        final TclBreakNode breakNode = new TclBreakNode();
        srcFromToken( breakNode, breakToken );
        return breakNode;
    }

    /**
     * Returns an {@link TclContinueNode} for the given token.
     *
     * @param continueToken The token containing the continue node's info.
     * @return A TclContinueNode built using the given token.
     */
    public TclStatementNode createContinue( Token continueToken )
    {
        final TclContinueNode continueNode = new TclContinueNode();
        srcFromToken( continueNode, continueToken );
        return continueNode;
    }

    /**
     * Returns an {@link TclWhileNode} for the given parameters.
     *
     * @param whileToken    The token containing the while node's info
     * @param conditionNode The conditional node for this while loop
     * @param bodyNode      The body of the while loop
     * @return A TclWhileNode built using the given parameters. null if either conditionNode or bodyNode is null.
     */
    public TclStatementNode createWhile( Token whileToken, TclExpressionNode conditionNode, TclStatementNode bodyNode )
    {
        if ( conditionNode == null || bodyNode == null )
        {
            return null;
        }

        conditionNode.addStatementTag();
        final int start = whileToken.getStartIndex();
        final int end = bodyNode.getSourceEndIndex();
        final TclWhileNode whileNode = new TclWhileNode( conditionNode, bodyNode );
        whileNode.setSourceSection( start, end - start );
        return whileNode;
    }

    /**
     * Returns an {@link TclIfNode} for the given parameters.
     *
     * @param ifToken       The token containing the if node's info
     * @param conditionNode The condition node of this if statement
     * @param thenPartNode  The then part of the if
     * @param elsePartNode  The else part of the if (null if no else part)
     * @return An TclIfNode for the given parameters. null if either conditionNode or thenPartNode is null.
     */
    public TclStatementNode createIf( Token ifToken, TclExpressionNode conditionNode, TclStatementNode thenPartNode, TclStatementNode elsePartNode )
    {
        if ( conditionNode == null || thenPartNode == null )
        {
            return null;
        }

        conditionNode.addStatementTag();
        final int start = ifToken.getStartIndex();
        final int end = elsePartNode == null ? thenPartNode.getSourceEndIndex() : elsePartNode.getSourceEndIndex();
        final TclIfNode ifNode = new TclIfNode( conditionNode, thenPartNode, elsePartNode );
        ifNode.setSourceSection( start, end - start );
        return ifNode;
    }

    /**
     * Returns an {@link TclReturnNode} for the given parameters.
     *
     * @param t         The token containing the return node's info
     * @param valueNode The value of the return (null if not returning a value)
     * @return An TclReturnNode for the given parameters.
     */
    public TclStatementNode createReturn( Token t, TclExpressionNode valueNode )
    {
        final int start = t.getStartIndex();
        final int length = valueNode == null ? t.getText().length() : valueNode.getSourceEndIndex() - start;
        final TclReturnNode returnNode = new TclReturnNode( valueNode );
        returnNode.setSourceSection( start, length );
        return returnNode;
    }

    /**
     * Returns the corresponding subclass of {@link TclExpressionNode} for binary expressions. </br> These nodes are
     * currently not instrumented.
     *
     * @param opToken   The operator of the binary expression
     * @param leftNode  The left node of the expression
     * @param rightNode The right node of the expression
     * @return A subclass of TclExpressionNode using the given parameters based on the given opToken. null if either
     * leftNode or rightNode is null.
     */
    public TclExpressionNode createBinary( Token opToken, TclExpressionNode leftNode, TclExpressionNode rightNode )
    {
        if ( leftNode == null || rightNode == null )
        {
            return null;
        }
        final TclExpressionNode leftUnboxed = TclUnboxNodeGen.create( leftNode );
        final TclExpressionNode rightUnboxed = TclUnboxNodeGen.create( rightNode );

        final TclExpressionNode result;
        switch ( opToken.getText() )
        {
            case "+":
                result = TclAddNodeGen.create( leftUnboxed, rightUnboxed );
                break;
            case "*":
                result = TclMulNodeGen.create( leftUnboxed, rightUnboxed );
                break;
            case "/":
                result = TclDivNodeGen.create( leftUnboxed, rightUnboxed );
                break;
            case "-":
                result = TclSubNodeGen.create( leftUnboxed, rightUnboxed );
                break;
            case "<":
                result = TclLessThanNodeGen.create( leftUnboxed, rightUnboxed );
                break;
            case "<=":
                result = TclLessOrEqualNodeGen.create( leftUnboxed, rightUnboxed );
                break;
            case ">":
                result = TclLogicalNotNodeGen.create( TclLessOrEqualNodeGen.create( leftUnboxed, rightUnboxed ) );
                break;
            case ">=":
                result = TclLogicalNotNodeGen.create( TclLessThanNodeGen.create( leftUnboxed, rightUnboxed ) );
                break;
            case "==":
                result = TclEqualNodeGen.create( leftUnboxed, rightUnboxed );
                break;
            case "!=":
                result = TclLogicalNotNodeGen.create( TclEqualNodeGen.create( leftUnboxed, rightUnboxed ) );
                break;
            case "&&":
                result = new TclLogicalAndNode( leftUnboxed, rightUnboxed );
                break;
            case "||":
                result = new TclLogicalOrNode( leftUnboxed, rightUnboxed );
                break;
            default:
                throw new RuntimeException( "unexpected operation: " + opToken.getText() );
        }

        int start = leftNode.getSourceCharIndex();
        int length = rightNode.getSourceEndIndex() - start;
        result.setSourceSection( start, length );
        result.addExpressionTag();

        return result;
    }

    public void finishModule()
    {
        if(moduleNodes.isEmpty()) {
            return;
        }
        final int moduleEndPos = moduleNodes.get( moduleNodes.size() - 1 ).getSourceEndIndex();
        final SourceSection functionSrc = source.createSection( moduleStartPos, moduleEndPos - moduleStartPos );
        final TclStatementNode methodBlock = finishBlock( moduleNodes, 0, moduleStartPos,
                moduleEndPos - moduleStartPos );

        final TclFunctionBodyNode functionBodyNode = new TclFunctionBodyNode( methodBlock );
        functionBodyNode.setSourceSection( functionSrc.getCharIndex(), functionSrc.getCharLength() );

        String moduleFunctionName = source.getName() + "...";
        final TclRootNode rootNode = new TclRootNode( language, moduleFrameDescriptor, functionBodyNode, functionSrc,
                moduleFunctionName );
        allFunctions.put( moduleFunctionName, Truffle.getRuntime().createCallTarget( rootNode ));
    }

    public void addModuleStatement( TclStatementNode node )
    {
        if ( moduleStartPos != 0 )
        {
            moduleStartPos = node.getSourceCharIndex();
        }
        moduleNodes.add( node );
    }

    /**
     * Returns an {@link TclInvokeNode} for the given parameters.
     *
     * @param functionNode   The function being called
     * @param parameterNodes The parameters of the function call
     * @param finalToken     A token used to determine the end of the sourceSelection for this call
     * @return An TclInvokeNode for the given parameters. null if functionNode or any of the parameterNodes are null.
     */
    public TclExpressionNode createCall( TclExpressionNode functionNode, List<TclExpressionNode> parameterNodes, Token finalToken )
    {
        if ( functionNode == null || containsNull( parameterNodes ) )
        {
            return null;
        }

        final TclExpressionNode result = new TclInvokeNode( functionNode,
                parameterNodes.toArray( new TclExpressionNode[parameterNodes.size()] ) );

        final int startPos = functionNode.getSourceCharIndex();
        final int endPos = finalToken.getStartIndex() + finalToken.getText().length();
        result.setSourceSection( startPos, endPos - startPos );
        result.addExpressionTag();

        return result;
    }

    /**
     * Returns an {@link TclWriteLocalVariableNode} for the given parameters.
     *
     * @param nameNode  The name of the variable being assigned
     * @param valueNode The value to be assigned
     * @return An TclExpressionNode for the given parameters. null if nameNode or valueNode is null.
     */
    public TclExpressionNode createAssignment( TclExpressionNode nameNode, TclExpressionNode valueNode )
    {
        return createAssignment( nameNode, valueNode, null );
    }

    /**
     * Returns an {@link TclWriteLocalVariableNode} for the given parameters.
     *
     * @param nameNode      The name of the variable being assigned
     * @param valueNode     The value to be assigned
     * @param argumentIndex null or index of the argument the assignment is assigning
     * @return An TclExpressionNode for the given parameters. null if nameNode or valueNode is null.
     */
    public TclExpressionNode createAssignment( TclExpressionNode nameNode, TclExpressionNode valueNode, Integer argumentIndex )
    {
        if ( nameNode == null || valueNode == null )
        {
            return null;
        }

        String name = ( (TclStringLiteralNode) nameNode ).executeGeneric( null );
        FrameSlot frameSlot = frameDescriptor.findOrAddFrameSlot( name, argumentIndex, FrameSlotKind.Illegal );
        FrameSlot existingSlot = lexicalScope.locals.put( name, frameSlot );
        boolean newVariable = existingSlot == null;
        final TclExpressionNode result = TclWriteLocalVariableNodeGen.create( valueNode, frameSlot, nameNode,
                newVariable );

        if ( valueNode.hasSource() )
        {
            final int start = nameNode.getSourceCharIndex();
            final int length = valueNode.getSourceEndIndex() - start;
            result.setSourceSection( start, length );
        }
        if ( argumentIndex == null )
        {
            result.addExpressionTag();
        }

        return result;
    }

    /**
     * Returns a {@link TclReadLocalVariableNode} if this read is a local variable or a {@link TclFunctionLiteralNode}
     * if this read is global. In tcl, the only global names are functions.
     *
     * @param nameNode The name of the variable/function being read
     * @return either:
     * <ul>
     * <li>A TclReadLocalVariableNode representing the local variable being
     * read.</li>
     * <li>A TclFunctionLiteralNode representing the function
     * definition.</li>
     * <li>null if nameNode is null.</li>
     * </ul>
     */
    public TclExpressionNode createRead( TclExpressionNode nameNode )
    {
        if ( nameNode == null )
        {
            return null;
        }

        String name = ( (TclStringLiteralNode) nameNode ).executeGeneric( null );
        final TclExpressionNode result;
        final FrameSlot frameSlot = lexicalScope.locals.get( name );
        if ( frameSlot != null )
        {
            /* Read of a local variable. */
            result = TclReadLocalVariableNodeGen.create( frameSlot );
        }
        else
        {
            /*
             * Read of a global name. In our language, the only global names are functions.
             */
            result = new TclFunctionLiteralNode( name );
        }
        result.setSourceSection( nameNode.getSourceCharIndex(), nameNode.getSourceLength() );
        result.addExpressionTag();
        return result;
    }

    /**
     * Returns a {@link TclReadLocalVariableNode} if this read is a local variable or a {@link TclFunctionLiteralNode}
     * if this read is global. In tcl, the only global names are functions.
     *
     * @param nameNode The name of the variable/function being read
     * @return either:
     * <ul>
     * <li>A TclReadLocalVariableNode representing the local variable being
     * read.</li>
     * <li>A TclFunctionLiteralNode representing the function
     * definition.</li>
     * <li>null if nameNode is null.</li>
     * </ul>
     */
    public TclExpressionNode createIdentifier( TclExpressionNode nameNode )
    {
        if ( nameNode == null )
        {
            return null;
        }

        String name = ( (TclStringLiteralNode) nameNode ).executeGeneric( null );
        final TclExpressionNode result;
        final FrameSlot frameSlot = lexicalScope.locals.get( name );
        if ( frameSlot != null )
        {
            /* Read of a local variable. */
            result = TclReadLocalVariableNodeGen.create( frameSlot );
        }
        else
        {
            /*
             * Read of a global name. In our language, the only global names are functions.
             */
            result = new TclFunctionLiteralNode( name );
        }
        result.setSourceSection( nameNode.getSourceCharIndex(), nameNode.getSourceLength() );
        result.addExpressionTag();
        return result;
    }

    public TclExpressionNode createStringLiteral( Token literalToken, boolean removeQuotes )
    {
        /* Remove the trailing and ending " */
        String literal = literalToken.getText();
        if ( removeQuotes )
        {
            assert literal.length() >= 2 && literal.startsWith( "\"" ) && literal.endsWith( "\"" );
            literal = literal.substring( 1, literal.length() - 1 );
        }

        final TclStringLiteralNode result = new TclStringLiteralNode( literal.intern() );
        srcFromToken( result, literalToken );
        result.addExpressionTag();
        return result;
    }

    public TclExpressionNode createIntegerLiteral( Token literalToken )
    {
        TclExpressionNode result;
        try
        {
            /* Try if the literal is small enough to fit into a long value. */
            result = new TclLongLiteralNode( Long.parseLong( literalToken.getText() ) );
        }
        catch ( NumberFormatException ex )
        {
            /* Overflow of long value, so fall back to BigInteger. */
            result = new TclBigIntegerLiteralNode( new BigInteger( literalToken.getText() ) );
        }
        srcFromToken( result, literalToken );
        result.addExpressionTag();
        return result;
    }

    public TclExpressionNode createDoubleLiteral( Token literalToken )
    {
        TclExpressionNode result;
        try
        {
            /* Try if the literal is small enough to fit into a long value. */
            result = new TclDoubleLiteralNode( Double.parseDouble( literalToken.getText() ) );
        }
        catch ( NumberFormatException ex )
        {
            /* Overflow of long value, so fall back to BigInteger. */
            result = new TclBigIntegerLiteralNode( new BigInteger( literalToken.getText() ) );
        }
        srcFromToken( result, literalToken );
        result.addExpressionTag();
        return result;
    }

    public TclExpressionNode createBooleanLiteral( Token literalToken )
    {
        TclExpressionNode result;
        try
        {
            /* Try if the literal is small enough to fit into a long value. */
            result = new TclBooleanLiteralNode( parseBoolean( literalToken.getText() ) );
        }
        catch ( IllegalArgumentException ex )
        {
            /* Overflow of long value, so fall back to BigInteger. */
            result = new TclStringLiteralNode( literalToken.getText() );
        }
        srcFromToken( result, literalToken );
        result.addExpressionTag();
        return result;
    }

    private boolean parseBoolean( String text )
    {
        if ( "0".equals( text ) || "false".equals( text ) || "no".equals( text ) || "n".equals( text ) || "off".equals(
                text ) )
        {
            return false;
        }
        if ( "1".equals( text ) || "true".equals( text ) || "yes".equals( text ) || "y".equals( text ) || "on".equals(
                text ) )
        {
            return false;
        }
        throw new IllegalArgumentException( text + " is not a boolean." );
    }


    public TclExpressionNode createParentExpression( TclExpressionNode expressionNode, int start, int length )
    {
        if ( expressionNode == null )
        {
            return null;
        }

        final TclParenExpressionNode result = new TclParenExpressionNode( expressionNode );
        result.setSourceSection( start, length );
        return result;
    }

    /**
     * Returns an {@link TclReadPropertyNode} for the given parameters.
     *
     * @param receiverNode The receiver of the property access
     * @param nameNode     The name of the property being accessed
     * @return An TclExpressionNode for the given parameters. null if receiverNode or nameNode is null.
     */
    public TclExpressionNode createReadProperty( TclExpressionNode receiverNode, TclExpressionNode nameNode )
    {
        if ( receiverNode == null || nameNode == null )
        {
            return null;
        }

        final TclExpressionNode result = TclReadPropertyNodeGen.create( receiverNode, nameNode );

        final int startPos = receiverNode.getSourceCharIndex();
        final int endPos = nameNode.getSourceEndIndex();
        result.setSourceSection( startPos, endPos - startPos );
        result.addExpressionTag();

        return result;
    }

    /**
     * Returns an {@link TclWritePropertyNode} for the given parameters.
     *
     * @param receiverNode The receiver object of the property assignment
     * @param nameNode     The name of the property being assigned
     * @param valueNode    The value to be assigned
     * @return An TclExpressionNode for the given parameters. null if receiverNode, nameNode or valueNode is null.
     */
    public TclExpressionNode createWriteProperty( TclExpressionNode receiverNode, TclExpressionNode nameNode, TclExpressionNode valueNode )
    {
        if ( receiverNode == null || nameNode == null || valueNode == null )
        {
            return null;
        }

        final TclExpressionNode result = TclWritePropertyNodeGen.create( receiverNode, nameNode, valueNode );

        final int start = receiverNode.getSourceCharIndex();
        final int length = valueNode.getSourceEndIndex() - start;
        result.setSourceSection( start, length );
        result.addExpressionTag();

        return result;
    }

    /**
     * Creates source description of a single token.
     */
    private static void srcFromToken( TclStatementNode node, Token token )
    {
        node.setSourceSection( token.getStartIndex(), token.getText().length() );
    }

    /**
     * Checks whether a list contains a null.
     */
    private static boolean containsNull( List<?> list )
    {
        for ( Object e : list )
        {
            if ( e == null )
            {
                return true;
            }
        }
        return false;
    }

}
