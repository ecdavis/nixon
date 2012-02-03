package com.wtfrak.nixon.scanner.tokens;

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
public class Token {
	private TokenType tokenType;
	private String value;

	public Token(TokenType tokenType) {
		this.tokenType = tokenType;
		this.value = null;
	}

	public Token(TokenType tokenType, String value) {
		this.tokenType = tokenType;
		this.value = value;
	}

	public Token(TokenType tokenType, Character value) {
		this.tokenType = tokenType;
		this.value = Character.toString(value);
	}

	public TokenType getTokenType() {
		return this.tokenType;
	}

	public String getValue() {
		return this.value;
	}
}
