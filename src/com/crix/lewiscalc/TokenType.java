package com.crix.lewiscalc;

public enum TokenType {
	OP_ADD,			// +
	OP_SUB,			// -
	OP_MUL,			// *
	OP_DIV,			// /
	OP_POW,			// ^
	OP_FACT,		// !
	OP_MOD,			// %
	LPAREN,			// (
	RPAREN,			// )
	COMMA,			// ,
	TERMINATOR,		// #
	IDENTIFIER,
	LITERAL
}
