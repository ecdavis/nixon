package com.wtfrak.nixon.parser.expressions;

import java.util.List;

import com.wtfrak.nixon.scanner.tokens.Token;
import com.wtfrak.nixon.scanner.tokens.TokenType;

/**
 * Copyright 2012 Chris Davis
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
public class NumberExpression implements Expression {

	public static NumberExpression fromLiteral(Integer number) {
		Token numberToken = new Token(TokenType.NUMBER, number.toString());
		return new NumberExpression(numberToken);
	}

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
