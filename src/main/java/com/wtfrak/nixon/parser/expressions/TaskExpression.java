package com.wtfrak.nixon.parser.expressions;

import java.util.ArrayList;
import java.util.List;

import com.wtfrak.nixon.scanner.tokens.Token;

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
public class TaskExpression implements Expression {
	private List<Expression> expressions;

	public TaskExpression() {
		this.expressions = new ArrayList<Expression>();
	}

	@Override
	public ExpressionType getExpressionType() {
		return ExpressionType.TASK;
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
