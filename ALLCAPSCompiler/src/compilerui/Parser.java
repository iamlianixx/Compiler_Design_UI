package compilerui;

import java.util.*;


public class Parser {
	private Stack<Token> inputStack;
	private Stack<String> prodStack;
	private String production;
        private ParseTree pTree;
        private ParseNode pNode;
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
                this.initializePTree();
	}
        
        private void initializePTree(){
            this.pTree = new ParseTree();
            this.pNode = this.pTree.getRoot();
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
                                  this.addChild(prod, pNode);
                               if(prod.equals("EPSILON"))
                                   moveTreePtr();
                               else if(!lookUp.isTerminal(prod)) 
                                   pNode = pNode.getChildren().get(pNode.fetchChildIndex(prod));
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
        
    private void addChild(String given, ParseNode parent){
        if(parent.getChildren() != null){
            parent.setChildren(new ArrayList<ParseNode>());
        }
        parent.getChildren().add(new ParseNode(given, parent));
            
    }    
    
    public ParseTree grabTree(){
        return this.pTree;
    }
    
    public boolean separateProds(String given){
        boolean error = false;
        if(given == null)
            error = true;
        else {
            prodStack.pop();
            String[] prodArr = given.split(" ");
            pNode.setChildren(this.gatherChildren(prodArr, pNode));
            pNode = pNode.getChildren().get(0);
            for(int i=prodArr.length-1; i>=0; i--)
                prodStack.push(prodArr[i]);
        }
        return error;
    }
    
    
    private void moveTreePtr(){
               boolean validPosition = false;
        while( validPosition == false && !pNode.getNodeData().equals("<program>")){
            String curProd = pNode.getNodeData();
            // Check for int, char, string, id exception
            if(curProd.equals("int_id") || curProd.equals("char_id") || curProd.equals("string_id") || curProd.equals("var_id")){
                pNode.setNodeData(inputStack.peek().getInfo());
                curProd = pNode.getNodeData();
            }
            // Go up to parent
            pNode = pNode.getParent();
            // Get current index
            int curNdx = pNode.fetchChildIndex(curProd);
            // Find next position
            if(curNdx+1 < pNode.fetchChildrenCtr()){
               pNode = pNode.getChildren().get(curNdx+1);
                    validPosition = true;
            } 
        }
    }
    
    private ArrayList<ParseNode> gatherChildren(String[] prodlist, ParseNode parent){
        ArrayList<ParseNode> temp = new ArrayList<>();
        for(int i=0; i<prodlist.length; i++)
            temp.add(new ParseNode(prodlist[i], parent));
        return temp;
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
