package com.wtfrak.nixon;

import java.util.Arrays;
import java.util.List;

import com.wtfrak.nixon.interpreter.Task;
import com.wtfrak.nixon.parser.Parser;
import com.wtfrak.nixon.parser.ParserException;
import com.wtfrak.nixon.parser.expressions.Expression;
import com.wtfrak.nixon.scanner.Scanner;
import com.wtfrak.nixon.scanner.ScannerException;
import com.wtfrak.nixon.scanner.tokens.Token;

public class Nixon {
	private Scanner scanner;
	private Parser parser;
	private Task task;

	public Nixon() {
		this.scanner = new Scanner();
		this.parser = new Parser();
		this.task = Task.getRootTask();
	}

	public void run(String code) {
		try {
			List<Token> tokens = this.scanner.scan(code);
			List<Expression> expressions = this.parser.parse(tokens);
			this.task.addExpressions(expressions);

			(new Thread(this.task)).start();
		} catch (ScannerException e) {
			e.printStackTrace();
		} catch (ParserException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Nixon nixon = new Nixon();
		nixon.run("\"Sock it to me!\" write!");
	}
}
