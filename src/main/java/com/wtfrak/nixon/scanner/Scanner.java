package com.wtfrak.nixon.scanner;

import java.util.ArrayList;
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
public class Scanner {
	private String text;
	private Integer position;

	public List<Token> scan(String text) throws ScannerException {
		this.text = text;
		this.position = 0;

		ArrayList<Token> tokens = new ArrayList<Token>();

		Token token = readToken();
		while (token.getTokenType() != TokenType.EOF) {
			tokens.add(token);
			token = readToken();
		}

		return tokens;
	}

	private Token readToken() throws ScannerException {
		char c = readChar();

		switch (c) {

		case '#':
			return new Token(TokenType.COMMENT, readComment(c));

		case ' ':
		case '\t':
		case '\r':
		case '\n':
			return new Token(TokenType.WHITESPACE, c);

		case '!':
			return new Token(TokenType.COMMAND, c);

		case '(':
			return new Token(TokenType.LEFT_PAREN, c);

		case ')':
			return new Token(TokenType.RIGHT_PAREN, c);

		case '[':
			return new Token(TokenType.LEFT_BRACKET, c);

		case ']':
			return new Token(TokenType.RIGHT_BRACKET, c);

		case '\0':
			return new Token(TokenType.EOF, c);

		case '"':
			return new Token(TokenType.STRING, readString(c));

		default:
			String word = readWord(c);
			if (isNumber(word)) {
				return new Token(TokenType.NUMBER, word);
			} else {
				return new Token(TokenType.NAME, word);
			}
		}
	}

	private String readComment(char c) {
		String value = Character.toString(c);

		while (peekAtChar() != '\r' && peekAtChar() != '\n' && peekAtChar() != '\0') {
			value += readChar();
		}

		return value;
	}

	private String readString(char c) throws ScannerException {
		String value = Character.toString(c);

		while (peekAtChar() != '"' && peekAtChar() != '\0') {
			value += readChar();
		}

		if (peekAtChar() == '\0') {
			throw new ScannerException();
		}

		value += readChar();

		return value;
	}

	private String readWord(char c) throws ScannerException {
		String value = Character.toString(c);

		while (isAllowedInWord(peekAtChar())) {
			value += readChar();
		}

		return value;
	}

	private char peekAtChar() {
		if (this.position < this.text.length()) {
			return this.text.charAt(this.position);
		} else {
			return '\0';
		}
	}

	private char readChar() {
		char ret = peekAtChar();

		if (this.position < this.text.length()) {
			this.position += 1;
		}

		return ret;
	}

	private boolean isNumber(String word) {
		try {
			Integer.parseInt(word);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	private boolean isAllowedInWord(char c) {
		return !(c < 36 || c > 126 || c == '(' || c == ')' || c == '[' || c == ']' || c == '\0');
	}
}
