package com.wtfrak.nixon.parser.expressions;

import java.util.List;

import com.wtfrak.nixon.scanner.tokens.Token;

public class StringExpression implements Expression {
	private String value;
	private Token token;

	public StringExpression(Token token) {
		this.value = token.getValue();
		this.token = token;
	}

	@Override
	public ExpressionType getExpressionType() {
		return ExpressionType.STRING;
	}

	@Override
	public String getValue() {
		return this.value;
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
