package com.wtfrak.nixon.interpreter.names;

import com.wtfrak.nixon.interpreter.Context;
import com.wtfrak.nixon.interpreter.InterpreterException;
import com.wtfrak.nixon.parser.expressions.ListExpression;
import com.wtfrak.nixon.parser.expressions.Expression;
import com.wtfrak.nixon.parser.expressions.ExpressionType;

public class DefineName implements Name {
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
