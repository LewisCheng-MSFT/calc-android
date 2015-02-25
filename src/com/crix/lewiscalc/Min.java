package com.crix.lewiscalc;

import java.util.Stack;

public class Min implements Function {

	@Override
	public void evaluate(Stack<Double> argStack) {
		if (argStack.isEmpty())
			return;
		double min = argStack.pop();
		while (!argStack.isEmpty()) {
			double x = argStack.pop();
			if (x < min)
				min = x;
		}
		argStack.push(min);
	}

}
