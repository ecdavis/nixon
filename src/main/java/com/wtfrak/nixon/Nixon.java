package com.wtfrak.nixon;

import java.util.List;

import com.wtfrak.nixon.interpreter.Task;
import com.wtfrak.nixon.parser.Parser;
import com.wtfrak.nixon.parser.ParserException;
import com.wtfrak.nixon.parser.expressions.Expression;
import com.wtfrak.nixon.scanner.Scanner;
import com.wtfrak.nixon.scanner.ScannerException;
import com.wtfrak.nixon.scanner.tokens.Token;

/**
 *    Copyright 2012 Chris Davis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
