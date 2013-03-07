package compilerui;

import java.util.*;


public class Parser {
	private Stack<Token> inputStack;
	private Stack<String> prodStack;
	private String production;
	private StringTokenizer token;
	private LookupTable lookUp;
	private boolean isError = false;

	public Parser(ArrayList<Token> input) {
                this.inputStack = new Stack<Token>();
                this.prodStack = new Stack<String>();
		this.inputStack.addAll(input);
                Collections.reverse(this.inputStack);
		prodStack.push("<program>");
		this.lookUp = new LookupTable();
	}
	
	public boolean LLParser(){
            
            while(!isError && !inputStack.isEmpty()){
                try{
                   if(prodStack.peek().equals(inputStack.peek().getToken())){
                       inputStack.pop();
                       prodStack.pop();
                   }else {
                       String prod = lookUp.retrieveProduction(inputStack.peek().getToken(), prodStack.peek());
                       if(prod == null) isError = true;
                       else{
                           token = new StringTokenizer(prod);
                           if(token.countTokens()>1){
                               isError = this.separateProds(prod);
                           } else {
                               prodStack.pop();
                               if(!prod.equals("EPSILON"))
                                    prodStack.push(prod);
                           }
                           
                       }
                   }
                }catch(EmptyStackException e){
                    isError = true;
                } 
            }
            
            if(inputStack.empty() && !prodStack.empty())
                isError = true;
            
            return isError;
	}
        
    public boolean separateProds(String given){
        boolean error = false;
        if(given == null)
            error = true;
        else {
            prodStack.pop();
            String[] prodArr = given.split(" ");
            for(int i=prodArr.length-1; i>=0; i--)
                prodStack.push(prodArr[i]);
        }
        return error;
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
