package com.crix.lewiscalc;

public class Tokenizer {
	
	
	private String exp;
	private int pos;
	private StringBuffer textBuffer;
	private Token curr;
	
	public Tokenizer(String exp) {
		// Add grammar terminator.
		if (exp.length() == 0 || exp.charAt(exp.length() - 1) != '#')
			this.exp = exp + '#';
		else
			this.exp = exp;
	}

	public void eat(TokenType type) throws TokenMissingException {
		if (curr.getType() == type)
			nextToken();
		else
			throw new TokenMissingException("Token " + type + " expected\nbut " + curr.getType() + " encountered");
	}
	
	public Token currToken() {
		return curr;
	}
	
	// FA states.
	private enum FAState {
		START,
		READ_INTEGER,
		READ_DECIMAL,
		READ_EXP_SIGN,
		READ_EXP_INTEGER,
		READ_IDENTIFIER,
		END
	}
		
	private FAState state;

	public void nextToken() {
		if (curr != null && curr.getType() == TokenType.TERMINATOR)
			return;
		
		state = FAState.START;
		while (state != FAState.END) {
			char input = exp.charAt(pos++);
			if (state == FAState.START) {
				if (Character.isLetter(input)) {
					textBuffer = new StringBuffer();
					textBuffer.append(input);
					state = FAState.READ_IDENTIFIER;
				} else if (Character.isDigit(input)) {
					textBuffer = new StringBuffer();
					textBuffer.append(input);
					state = FAState.READ_INTEGER;
				} else if (input == '.') {
					textBuffer = new StringBuffer();
					textBuffer.append("0.");
					state = FAState.READ_DECIMAL;
				} else if (input == '+') {
					curr = new Token(TokenType.OP_ADD);
					state = FAState.END;
				} else if (input == '-') {
					curr = new Token(TokenType.OP_SUB);
					state = FAState.END;
				} else if (input == '*') {
					curr = new Token(TokenType.OP_MUL);
					state = FAState.END;
				} else if (input == '/') {
					curr = new Token(TokenType.OP_DIV);
					state = FAState.END;
				} else if (input == '(') {
					curr = new Token(TokenType.LPAREN);
					state = FAState.END;
				} else if (input == ')') {
					curr = new Token(TokenType.RPAREN);
					state = FAState.END;
				} else if (input == '^') {
					curr = new Token(TokenType.OP_POW);
					state = FAState.END;
				} else if (input == '!') {
					curr = new Token(TokenType.OP_FACT);
					state = FAState.END;
				} else if (input == '%') {
					curr = new Token(TokenType.OP_MOD);
					state = FAState.END;
				} else if (input == ',') { 
					curr = new Token(TokenType.COMMA);
					state = FAState.END;
				} else if (input == '#') {
					curr = new Token(TokenType.TERMINATOR);
					state = FAState.END;
				} else {
					// No possible to reach here.
					state = FAState.END;
				}
			} else if (state == FAState.READ_IDENTIFIER) {
				if (Character.isLetter(input) || Character.isDigit(input)) {
					textBuffer.append(input);
				} else {
					--pos;
					curr = new Token(TokenType.IDENTIFIER, textBuffer.toString());
					state = FAState.END;
				}
			} else if (state == FAState.READ_INTEGER) {
				if (Character.isDigit(input)) {
					textBuffer.append(input);
				} else if (input == '.') {
					textBuffer.append('.');
					state = FAState.READ_DECIMAL;
				} else if (input == 'e' || input == 'E') {
					textBuffer.append('e');
					state = FAState.READ_EXP_SIGN;
				} else {
					--pos;
					curr = new Token(TokenType.LITERAL, textBuffer.toString());
					state = FAState.END;
				}
			} else if (state == FAState.READ_DECIMAL) {
				if (Character.isDigit(input)) {
					textBuffer.append(input);
				} else if (input == 'e') {
					textBuffer.append('e');
					state = FAState.READ_EXP_SIGN;
				} else {
					--pos;
					curr = new Token(TokenType.LITERAL, textBuffer.toString());
					state = FAState.END;
				}
			} else if (state == FAState.READ_EXP_SIGN){
				if (input == '+' || input == '-') {
					textBuffer.append(input);
				} else if (Character.isDigit(input)) {
					--pos;
					state = FAState.READ_EXP_INTEGER;
				}
			} else if (state == FAState.READ_EXP_INTEGER) {
				if (Character.isDigit(input)) {
					textBuffer.append(input);
				} else {
					--pos;
					curr = new Token(TokenType.LITERAL, textBuffer.toString());
					state = FAState.END;
				}
			}
		}
		
		System.out.println("read token: " + curr.toString() + "[" + curr.getType() + "]");
	}
}
