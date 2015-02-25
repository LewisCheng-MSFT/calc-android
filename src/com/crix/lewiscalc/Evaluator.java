package com.crix.lewiscalc;

import java.util.Stack;

public class Evaluator {
	private Tokenizer tkzr;
	private EvalContext con;
	private Stack<Double> argStack = new Stack<Double>();
	
	public Evaluator(Tokenizer tkzr, EvalContext con) {
		this.tkzr = tkzr;
		this.con = con;
	}
	
	public double evaluate() throws TokenMissingException, UnexpectedTokenException, UndefinedFunctionException, UndefinedVariableException, NoReturnValueException {
		nextToken();
		double val = expression();

		eat(TokenType.TERMINATOR);
		
		System.out.println("result: " + val);
		return val;
	}
	
	private double expression() throws TokenMissingException, UnexpectedTokenException, UndefinedFunctionException, UndefinedVariableException, NoReturnValueException {
		double val = term();
		
		while (true) {
			if (currToken().match(TokenType.OP_ADD)) {
				nextToken();
				val += term();
			} else if (currToken().match(TokenType.OP_SUB)) {
				nextToken();
				val -= term();
			} else {
				break;
			}
		}
		
		System.out.println("expression: " + val);
		return val;
	}
	
	private double term() throws TokenMissingException, UnexpectedTokenException, UndefinedFunctionException, UndefinedVariableException, NoReturnValueException {
		double val = factor();
		
		while (true) {
			if (currToken().match(TokenType.OP_MUL)) {
				nextToken();
				val *= factor();
			} else if (currToken().match(TokenType.OP_DIV)) {
				nextToken();
				val /= factor();
			} else if (currToken().match(TokenType.OP_MOD)) {
				nextToken();
				val %= factor();
			} else {
				break;
			}
		}
		
		System.out.println("term: " + val);
		return val;
	}
	
	private double factor() throws TokenMissingException, UnexpectedTokenException, UndefinedFunctionException, UndefinedVariableException, NoReturnValueException {
		double val = atom();
		
		while (currToken().match(TokenType.OP_POW)) {
			nextToken();
			val = Math.pow(val, atom());
		}
		
		System.out.println("factor: " + val);
		return val;
	}
	
	private double atom() throws TokenMissingException, UnexpectedTokenException, UndefinedFunctionException, UndefinedVariableException, NoReturnValueException {
		double val = 0;
		
		// Deal with sign.
		boolean neg = false;
		if (currToken().match(TokenType.OP_ADD)) {
			nextToken();
		} else if (currToken().match(TokenType.OP_SUB)) {
			neg = true;
			nextToken();
		}
		
		if (currToken().match(TokenType.LPAREN)) {
			nextToken();
			val = expression();
			eat(TokenType.RPAREN);
		} else if (currToken().match(TokenType.IDENTIFIER)) {
			String text = currToken().toString();
			nextToken();
			if (currToken().match(TokenType.LPAREN)) {
				// It's a function.
				nextToken();
				Function fun = con.getFunction(text);
				argList();
				eat(TokenType.RPAREN);
				fun.evaluate(argStack);
				if (argStack.isEmpty())
					throw new NoReturnValueException("Function " + text + " didn't return a value due to some error");
				val = argStack.pop();
				System.out.println("function called: " + text + "[retval=" + val + "]");
			} else {
				// It's a variable.
				val = con.getVariable(text);
			}
		} else if (currToken().match(TokenType.LITERAL)) {
			val = Double.valueOf(currToken().toString());
			nextToken();
		} else {
			throw new TokenMissingException("Operand expected");
		}
		
		if (currToken().match(TokenType.OP_FACT)) {
			nextToken();
			if (val < 0) {
				val = Double.NaN;
			} else {
				double fval = 1;
				while (val > 0)
					fval *= val--;
				val = fval;
			}
		}
		
		if (neg) val = -val;
		System.out.println("atom: " + val);
		return val;
	}
	
	private void argList() throws TokenMissingException, UnexpectedTokenException, UndefinedFunctionException, UndefinedVariableException, NoReturnValueException {
		if (!currToken().match(TokenType.RPAREN)) {
			while (true) {
				double arg = expression();
				argStack.push(arg);
				System.out.println("push arg: " + arg);
				if (currToken().match(TokenType.COMMA))
					nextToken();
				else
					break;
			}
		}
	}
	
	private Token currToken() {
		return tkzr.currToken();
	}
	
	private void nextToken() {
		tkzr.nextToken();
	}
	
	private void eat(TokenType type) throws TokenMissingException {
		tkzr.eat(type);
	}
}
