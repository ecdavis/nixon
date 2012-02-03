package com.wtfrak.nixon.interpreter.names;

import com.wtfrak.nixon.interpreter.Context;
import com.wtfrak.nixon.interpreter.InterpreterException;

public interface Name {
	public void evaluate(Context context) throws InterpreterException;
}
