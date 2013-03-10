/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package compilerui;

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
        boolean check = false;
        for(int i=0; check == false && i< look.nonterminals.size(); i++){
            if(n.getNodeData().equals(look.nonterminals.get(i))){
                n.setNodeData("^");
                check = true;
            }
        }
          
          for(ParseNode child : temp){
              convertTree(child);
          }
        
    }
    
    public void displayAST(ParseNode p){
        System.out.println("Parent: " + p.getNodeData());
        ArrayList<ParseNode> temp = p.getChildren();
        
        for(ParseNode child : temp){
            System.out.println("CHILDREN:");
            displayAST(child);
        }
    }
}
