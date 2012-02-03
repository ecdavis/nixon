package com.wtfrak.nixon.parser.expressions;

import java.util.List;

import com.wtfrak.nixon.scanner.tokens.Token;

public interface Expression {
	public ExpressionType getExpressionType();

	public Object getValue() throws UnsupportedOperationException;

	public Token getToken() throws UnsupportedOperationException;

	public void add(Expression expr) throws UnsupportedOperationException;

	public List<Expression> getExpressions() throws UnsupportedOperationException;
}
