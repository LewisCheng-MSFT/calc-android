package com.crix.lewiscalc;

import java.util.Stack;

public class Combination implements Function {

	@Override
	public void evaluate(Stack<Double> argStack) {
		if (argStack.size() != 2) {
			argStack.clear();
			return;
		}
			
		double m = argStack.pop();
		double n = argStack.pop();
		if (n < m)
			return;
		
		double p = n;
		double q = m;
		double val = 1;
		while (q > 0) {
			val = val * p / q;
			--p;
			--q;
		}
		q = p;
		while (p > 0) {
			val = val * p / q;
			--p;
			--q;
		}
		argStack.push(val);
	}

}
