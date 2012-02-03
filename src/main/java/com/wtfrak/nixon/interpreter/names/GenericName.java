package com.wtfrak.nixon.interpreter.names;

import com.wtfrak.nixon.interpreter.Context;
import com.wtfrak.nixon.parser.expressions.CommandExpression;
import com.wtfrak.nixon.parser.expressions.ListExpression;
import com.wtfrak.nixon.scanner.tokens.Token;
import com.wtfrak.nixon.scanner.tokens.TokenType;

public class GenericName implements Name {
	private ListExpression list;

	public GenericName(ListExpression list) {
		this.list = list;
	}

	public void evaluate(Context context) {
		context.getStack().push(this.list);
		context.getStack().push(new CommandExpression(new Token(TokenType.COMMAND)));
	}
}
