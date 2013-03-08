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
public class ParseTree {
    private ParseNode root;
    
    public ParseTree(){
        root = new ParseNode("<program>",null);
    }
    
    public ParseNode getRoot() {
        return root;
    }
    
    public void displayTree(ParseNode n){
        System.out.println("Parent: " + n.getNodeData());
        ArrayList<ParseNode> children = n.getChildren();
        for(ParseNode node : children){
            displayTree(node);
        }
    }
}
