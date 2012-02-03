package com.wtfrak.nixon.scanner.tokens;

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
