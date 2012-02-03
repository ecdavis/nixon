package com.wtfrak.nixon.interpreter.names;

import com.wtfrak.nixon.interpreter.Context;
import com.wtfrak.nixon.interpreter.InterpreterException;
import com.wtfrak.nixon.parser.expressions.Expression;
import com.wtfrak.nixon.parser.expressions.ExpressionType;

public class WriteName implements Name {
	public void evaluate(Context context) throws InterpreterException {
		if (context.getStack().size() < 1) {
			throw new InterpreterException();
		}

		Expression string = context.getStack().pop();

		if (string.getExpressionType() != ExpressionType.STRING) {
			throw new InterpreterException();
		}

		System.out.println(string.getToken().getValue());
	}
}
