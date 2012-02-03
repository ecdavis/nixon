package com.wtfrak.nixon.parser.expressions;

import java.util.List;

import com.wtfrak.nixon.scanner.tokens.Token;

public class NumberExpression implements Expression {
	private Integer value;
	private Token token;

	public NumberExpression(Token token) {
		this.value = Integer.parseInt(token.getValue());
		this.token = token;
	}

	@Override
	public ExpressionType getExpressionType() {
		return ExpressionType.NUMBER;
	}

	@Override
	public Integer getValue() {
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
