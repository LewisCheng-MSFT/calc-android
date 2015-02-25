package com.crix.lewiscalc;

import java.util.Stack;

public class Root implements Function {

	@Override
	public void evaluate(Stack<Double> argStack) {
		if (argStack.size() != 2) {
			argStack.clear();
			return;
		}
		double y = argStack.pop();
		double x = argStack.pop();
		argStack.push(Math.pow(x, 1 / y));
	}

}
