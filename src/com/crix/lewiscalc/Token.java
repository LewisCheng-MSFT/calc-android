package com.crix.lewiscalc;

public class Token {
	private TokenType type;
	private String text;
	
	public Token(TokenType type) {
		this.type = type;
		this.text = type.toString();
	}
	
	public Token(TokenType type, String text) {
		this.type = type;
		this.text = text;
	}
	
	public TokenType getType() {
		return type;
	}
	
	public boolean match(TokenType type) {
		return this.type == type;
	}
	
	public String toString() {
		return text;
	}
}
