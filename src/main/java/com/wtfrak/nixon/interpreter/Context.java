package com.wtfrak.nixon.interpreter;

import java.util.HashMap;
import java.util.Stack;

import com.wtfrak.nixon.interpreter.names.Name;
import com.wtfrak.nixon.parser.expressions.Expression;

public interface Context {
	public Stack<Expression> getStack();

	public HashMap<String, Name> getScope();
}
