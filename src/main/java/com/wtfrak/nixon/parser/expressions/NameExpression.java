package com.wtfrak.nixon.parser.expressions;

import java.util.List;

import com.wtfrak.nixon.scanner.tokens.Token;

public class NameExpression implements Expression {
	private String value;
	private Token token;

	public NameExpression(Token token) {
		this.value = token.getValue();
		this.token = token;
	}

	@Override
	public ExpressionType getExpressionType() {
		return ExpressionType.NAME;
	}

	@Override
	public Object getValue() {
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
