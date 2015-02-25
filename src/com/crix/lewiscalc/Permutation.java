package com.crix.lewiscalc;

import java.util.Stack;

public class Permutation implements Function {

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
		double q = n - m;
		double val = 1;
		while (q > 0) {
			val = val * p / q;
			--p;
			--q;
		}
		while (p > 0) {
			val *= p;
			--p;
		}
		argStack.push(val);
	}

}
