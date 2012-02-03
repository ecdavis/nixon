package com.wtfrak.nixon.parser.expressions;

import java.util.List;

import com.wtfrak.nixon.scanner.tokens.Token;

public class CommandExpression implements Expression {
	private Token token;

	public CommandExpression(Token token) {
		this.token = token;
	}

	@Override
	public ExpressionType getExpressionType() {
		return ExpressionType.COMMAND;
	}

	@Override
	public Object getValue() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Token getToken() {
		return this.token;
	}

	@Override
	public void add(Expression expr) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Expression> getExpressions() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
}
