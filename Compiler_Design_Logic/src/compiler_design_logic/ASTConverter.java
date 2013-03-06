/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler_design_logic;

import java.util.ArrayList;

/**
 *
 * @author Jullian
 */
public class ASTConverter {
    private LookupTable look;
    
    public ASTConverter(){
        this.look = new LookupTable();
    }
    
    public void convertTree(ParseNode n){
        ArrayList<ParseNode> temp = n.getChildren();
        if(n.getNodeData().equals("EPSILON"))
            n.setNodeData("^");
        
        int i;
        for(i=0; i<look.nonterminals.size() 
                && n.getNodeData().equals(look.nonterminals.get(i)); i++);
          if(i<look.nonterminals.size())
              n.setNodeData("^");
          
          for(ParseNode child : temp){
              convertTree(child);
          }
        
    }
    
    public void displayAST(ParseNode p){
        System.out.println("Parent: " + p.getNodeData());
        ArrayList<ParseNode> temp = p.getChildren();
        
        for(ParseNode child : temp)
            displayAST(child);
    }
}
