package com.wtfrak.nixon.interpreter.names;

import com.wtfrak.nixon.interpreter.Context;
import com.wtfrak.nixon.interpreter.InterpreterException;
import com.wtfrak.nixon.parser.expressions.ListExpression;
import com.wtfrak.nixon.parser.expressions.Expression;
import com.wtfrak.nixon.parser.expressions.ExpressionType;

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
public class DefineName implements Name {

	@Override
	public void evaluate(Context context) throws InterpreterException {
		if (context.getStack().size() < 2) {
			throw new InterpreterException();
		}

		Expression name = context.getStack().pop();
		Expression list = context.getStack().pop();

		if (name.getExpressionType() != ExpressionType.NAME) {
			throw new InterpreterException();
		}
		if (list.getExpressionType() != ExpressionType.LIST) {
			throw new InterpreterException();
		}

		context.getScope().put(name.getToken().getValue(), new GenericName((ListExpression) list));
	}
}
