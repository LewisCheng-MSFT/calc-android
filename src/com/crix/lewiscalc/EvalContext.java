package com.crix.lewiscalc;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EvalContext {
	private Map<String, Double> varList = new HashMap<String, Double>();
	private Map<String, Function> funList = new HashMap<String, Function>();
	
	private List<String> varNameList = new LinkedList<String>();
	private List<String> funNameList = new LinkedList<String>();
	
	private static EvalContext singleton = new EvalContext();
	
	public static EvalContext getSingleton() {
		return singleton;
	}
	
	public EvalContext() {
    	addFunction("comb", new Combination());
    	addFunction("perm", new Permutation());
    	addFunction("sin", new Sine());
    	addFunction("cos", new Cosine());
    	addFunction("tan", new Tangent());
    	addFunction("cot", new Cotangent());
    	addFunction("asin", new Arcsine());
    	addFunction("acos", new Arccosine());
    	addFunction("atan", new Arctangent());
    	addFunction("acot", new Arccotangent());
    	addFunction("sinh", new Hypersine());
    	addFunction("cosh", new Hypercosine());
    	addFunction("tanh", new Hypertangent());
    	addFunction("coth", new Hypercotangent());
    	addFunction("avg", new Avg());
    	addFunction("max", new Max());
    	addFunction("min", new Min());
    	addFunction("log", new Log());
    	addFunction("ln", new Ln());
    	addFunction("sqrt", new Sqrt());
    	addFunction("cbrt", new Cbrt());
    	addFunction("exp", new Exp());
    	addFunction("root", new Root());
    	
    	addVariable("ANS");
    	addVariable("PI", Math.PI);
    	addVariable("E", Math.E);
	}
	
	private void addFunction(String name, Function fun) {
		if (funList.get(name) == null)
			funNameList.add(name);
		funList.put(name, fun);
	}
	
	public Function getFunction(String name) throws UndefinedFunctionException {
		Function fun = funList.get(name);
		if (fun == null)
			throw new UndefinedFunctionException("Function undefined: " + name);
		return fun;
	}
	
	public List<String> getFunctionList() {
		return funNameList;
	}
	
	public void addVariable(String name) {
		addVariable(name, 0.0);
	}
	
	public void addVariable(String name, double initVal) {
		if (varList.get(name) == null)
			varNameList.add(name);
		varList.put(name, initVal);
		
	}
	
	public double getVariable(String name) throws UndefinedVariableException {
		Double var = varList.get(name);
		if (var == null)
			throw new UndefinedVariableException("Variable undefined: " + name);
		return var;
	}
	
	public void setVariable(String name, double value) throws UndefinedVariableException {
		Double var = varList.remove(name);
		if (var == null)
			throw new UndefinedVariableException("Variable undefined: " + name);
		varList.put(name, value);
	}
	
	public void removeVariable(String name) throws UndefinedVariableException {
		Double var = varList.remove(name);
		if (var == null)
			throw new UndefinedVariableException("Variable not exist: " + name);
		varNameList.remove(name);
	}
	
	public List<String> getVariableList() {
		return varNameList;
	}
}
