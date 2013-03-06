package compiler_design_logic;

import java.util.Stack;
import java.util.StringTokenizer;


public class Parser {
	private Stack<String> inputStack;
	private Stack<String> prodStack;
	private String production;
	private String[] prodTokens;
	private StringTokenizer tokens;
	private LookupTable lookUp;
	private boolean isError = false;

	public Parser(String[] input) {
		for(int i = 0; i <= input.length; i++)
			inputStack.push(input[i]);
		prodStack.push("<program>");
		this.lookUp = new LookupTable();
	}
	
	public boolean LLParser(){
		while(!isError && !inputStack.isEmpty()){
			production = lookUp.retrieveProduction(inputStack.peek(), prodStack.peek()); //retrieves the corresponding production
			if(production.equals("null"))
				isError = true;
			else {
				tokens = new StringTokenizer(production);
				if(tokens.countTokens() > 1){												//if retrieved production has more than 1 token
					for(int i = 0; tokens.hasMoreTokens(); i++)
						prodTokens[i] = tokens.nextToken();
					prodStack.pop();
					for(int i = 0; i < prodTokens.length; i++)
						prodStack.push(prodTokens[i]);
				}else {
					prodStack.pop();
					prodStack.push(production);
				}
			}
					
			if(inputStack.peek().equals(prodStack.peek())){
				inputStack.pop();
				prodStack.pop();
			} else {
				if(lookUp.isTerminal(prodStack.peek()))								//if input and production are both terminals but does not match
					isError = true;
				else if(prodStack.peek().equals("EPSILON"))
					prodStack.pop();
			}
		}
		
		if(!inputStack.isEmpty())
			isError = true;
		
		return isError;
	}

}
