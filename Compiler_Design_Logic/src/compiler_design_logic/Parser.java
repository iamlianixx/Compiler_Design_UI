package compiler_design_logic;

import java.util.*;


public class Parser {
	private Stack<Token> inputStack;
	private Stack<String> prodStack;
	private String production;
	private String[] prodTokens;
	private StringTokenizer token;
	private LookupTable lookUp;
	private boolean isError = false;

	public Parser(ArrayList<Token> input) {
                this.inputStack = new Stack<Token>();
                this.prodStack = new Stack<String>();
                this.prodTokens = new String[10];
		this.inputStack.addAll(input);
                Collections.reverse(this.inputStack);
		prodStack.push("<program>");
		this.lookUp = new LookupTable();
	}
	
	public boolean LLParser(){
            
            while(!isError && !inputStack.isEmpty()){
                try{
                    if(!inputStack.peek().getToken().equals(prodStack.peek())){
                        if(prodStack.peek().equals("EPSILON")){
                            prodStack.pop();
                        } else {
                            if(lookUp.isTerminal(prodStack.peek()))
                                isError = true;
                            else ;
                                
                        }
                    }
                }catch(EmptyStackException e){
                
                }
            }
            
            return isError;
	/*	while(!isError && !inputStack.isEmpty()){
                        System.out.println(inputStack.peek().getToken());
			production = lookUp.retrieveProduction(inputStack.peek().getToken(), prodStack.peek()); //retrieves the corresponding production
			System.out.println(production);
                        if(production == null)
				isError = true;
			else {
				token = new StringTokenizer(production);
				if(token.countTokens() > 1){												//if retrieved production has more than 1 token
					for(int i = 0; token.hasMoreTokens(); i++)
						prodTokens[i] = token.nextToken();
					prodStack.pop();
					for(int i = 0; i < prodTokens.length; i++)
						prodStack.push(prodTokens[i]);
				}else {
					prodStack.pop();
					prodStack.push(production);
				}
			}
			
                        System.err.println("CHECK!");
                        this.displayStacks();
                        
			if(inputStack.peek().equals(prodStack.peek())){
				inputStack.pop();
				prodStack.pop();
			} else {
				if(lookUp.isTerminal(prodStack.peek()))								//if input and production are both terminals but does not match
					isError = true;
				else if(prodStack.peek().equals("EPSILON"))
					prodStack.pop();
			}
                   this.displayStacks();     
		}
		
		if(!inputStack.isEmpty())
			isError = true;
		
		return isError; */
	}

    public void displayStacks(){
        System.out.println("INPUT\t\t\tPRODUCTION");
        System.out.println();
        Stack<Token> tempInp = new Stack<Token>();
        Stack<String> tempProd = new Stack<String>();
        tempInp.addAll(this.inputStack);
        tempProd.addAll(this.prodStack);
        for(; !tempInp.isEmpty() || !tempProd.isEmpty(); tempInp.pop(),tempProd.pop()){
            String token = "empty", prod = "empty";
            if(!tempInp.isEmpty()) 
                token = tempInp.peek().getToken();
            if(!tempProd.isEmpty()) 
                token = tempProd.peek();
            System.out.println(token + "\t\t\t" + prod);
        }
    }    
        
}
