package compiler_design_logic;

import java.util.*;


public class Parser {
	private Stack<Token> inputStack;
	private Stack<String> prodStack;
	private String production;
	private StringTokenizer token;
	private LookupTable lookUp;
        private ParseTree tree;
        private ParseNode nodePtr;
	private boolean isError = false;

	public Parser(ArrayList<Token> input) {
                this.inputStack = new Stack<Token>();
                this.prodStack = new Stack<String>();
		this.inputStack.addAll(input);
                Collections.reverse(this.inputStack);
		prodStack.push("<program>");
		this.lookUp = new LookupTable();
                this.initializeParseTree();
	}
	
	public boolean LLParser(){
            
            while(!isError && !inputStack.isEmpty()){
                try{
                   if(prodStack.peek().equals(inputStack.peek().getToken())){
                       moveTreePtr();
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
                                   if(!prodStack.peek().equals(inputStack.peek().getToken())){
                                       String[] temp = new String[10];
                                       temp[0] = prod;
                                       this.nodePtr.setChildren(this.gatherChildren(nodePtr, temp));
                                       int index = this.nodePtr.fetchChildIndex(prod);
                                       this.nodePtr = this.nodePtr.getChildren().get(index);
                                   }
                                       
                                    moveTreePtr();
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
        
    public void initializeParseTree(){
        this.tree = new ParseTree();
        this.nodePtr = this.tree.getRoot();
    }    
    
    private ArrayList<ParseNode> gatherChildren(ParseNode parent, String[] prodlist){
        ArrayList childSet = new ArrayList<ParseNode>();
        for(int i=0; i<prodlist.length; i++)
            childSet.add(new ParseNode(prodlist[i], parent));
        return childSet;
    }
    
    public boolean separateProds(String given){
        boolean error = false;
        if(given == null)
            error = true;
        else {
            prodStack.pop();
            String[] prodArr = given.split(" ");
            nodePtr.setChildren(this.gatherChildren(nodePtr, prodArr));
            for(int i=prodArr.length-1; i>=0; i--){
                prodStack.push(prodArr[i]);
            }
        }
        return error;
    }
    
    public void moveTreePtr(){
        boolean valid = false;
        while(valid == false && !nodePtr.getNodeData().equals("<program>")){
            String current = this.nodePtr.getNodeData();
            if(current.equals("num_id") || current.equals("string_id") || 
                    current.equals("char_id") || current.equals("var_id")){
                    nodePtr.setNodeData(inputStack.peek().getInfo());
                    current = nodePtr.getNodeData();
            }
            nodePtr = nodePtr.getParent();
            int currentNdx = nodePtr.fetchChildIndex(current);
            
            if(currentNdx+1 < nodePtr.fetchChildrenCtr()){
                nodePtr = nodePtr.getChildren().get(currentNdx+1);
                valid = true;
            }
        }
    }
        
    public ParseTree getTree(){
        return this.tree;
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
