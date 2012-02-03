package com.wtfrak.nixon.parser.expressions;

import java.util.ArrayList;
import java.util.List;

import com.wtfrak.nixon.scanner.tokens.Token;

public class ListExpression implements Expression {
	private List<Expression> expressions;

	public ListExpression() {
		this.expressions = new ArrayList<Expression>();
	}

	@Override
	public ExpressionType getExpressionType() {
		return ExpressionType.LIST;
	}

	@Override
	public Object getValue() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Token getToken() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void add(Expression expr) {
		this.expressions.add(expr);
	}

	@Override
	public List<Expression> getExpressions() {
		return this.expressions;
	}
}
