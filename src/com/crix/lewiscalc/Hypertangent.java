package com.crix.lewiscalc;

import java.util.Stack;

public class Hypertangent implements Function {

	@Override
	public void evaluate(Stack<Double> argStack) {
		if (argStack.size() != 1) {
			argStack.clear();
			return;
		}
		double x = argStack.pop();
		argStack.push(Math.tanh(x));
	}

}
