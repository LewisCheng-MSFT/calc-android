package com.crix.lewiscalc;

import java.util.Stack;

public class Max implements Function {

	@Override
	public void evaluate(Stack<Double> argStack) {
		if (argStack.isEmpty())
			return;
		double max = argStack.pop();
		while (!argStack.isEmpty()) {
			double x = argStack.pop();
			if (x > max)
				max = x;
		}
		argStack.push(max);
	}

}
