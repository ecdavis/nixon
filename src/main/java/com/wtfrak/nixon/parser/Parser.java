package com.wtfrak.nixon.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.wtfrak.nixon.parser.expressions.CommandExpression;
import com.wtfrak.nixon.parser.expressions.Expression;
import com.wtfrak.nixon.parser.expressions.ExpressionType;
import com.wtfrak.nixon.parser.expressions.ListExpression;
import com.wtfrak.nixon.parser.expressions.NameExpression;
import com.wtfrak.nixon.parser.expressions.NumberExpression;
import com.wtfrak.nixon.parser.expressions.StringExpression;
import com.wtfrak.nixon.parser.expressions.TaskExpression;
import com.wtfrak.nixon.scanner.tokens.Token;

public class Parser {
	public List<Expression> parse(List<Token> tokens) throws ParserException {
		List<Expression> expressions = new ArrayList<Expression>();
		Stack<Expression> expressionStack = new Stack<Expression>();

		for (Token token : tokens) {
			switch (token.getTokenType()) {

			case LEFT_BRACKET:
				expressionStack.push(new TaskExpression());
				break;

			case RIGHT_BRACKET:
				if (!expressionStack.empty() && expressionStack.peek().getExpressionType() == ExpressionType.TASK) {
					Parser.addExpression(expressions, expressionStack, expressionStack.pop());
				} else {
					throw new ParserException();
				}
				break;

			case LEFT_PAREN:
				expressionStack.push(new ListExpression());
				break;

			case RIGHT_PAREN:
				if (!expressionStack.empty() && expressionStack.peek().getExpressionType() == ExpressionType.LIST) {
					Parser.addExpression(expressions, expressionStack, expressionStack.pop());
				} else {
					throw new ParserException();
				}
				break;

			case STRING:
				Parser.addExpression(expressions, expressionStack, new StringExpression(token));
				break;

			case NUMBER:
				Parser.addExpression(expressions, expressionStack, new NumberExpression(token));
				break;

			case NAME:
				Parser.addExpression(expressions, expressionStack, new NameExpression(token));
				break;

			case COMMAND:
				Parser.addExpression(expressions, expressionStack, new CommandExpression(token));
				break;

			default:
				continue;

			}
		}

		return expressions;
	}

	private static void addExpression(List<Expression> expressions, Stack<Expression> expressionStack, Expression expr) {
		if (expressionStack.empty()) {
			expressions.add(expr);
		} else {
			expressionStack.peek().add(expr);
		}
	}
}
