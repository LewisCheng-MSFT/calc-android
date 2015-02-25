package com.crix.lewiscalc;

import java.util.Stack;

public class Avg implements Function {

	@Override
	public void evaluate(Stack<Double> argStack) {
		if (argStack.isEmpty())
			return;
		double sum = argStack.pop();
		int count = 1;
		while (!argStack.isEmpty()) {
			double x = argStack.pop();
			sum += x;
			++count;
		}
		argStack.push(sum / count);
	}

}
