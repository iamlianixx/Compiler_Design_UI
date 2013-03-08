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
public class TestTree {
    
    private ParseTree tree;
    private ParseNode nodePtr;
    
    public TestTree(){
        tree = new ParseTree();
    }
    
    public void addChild(String given, ParseNode parent){
        ParseNode temp = new ParseNode(given, parent);
        tree.getRoot().getChildren().add(temp);
    }
    
    public void display(ParseNode n){
        System.out.println(n.getNodeData());
        ArrayList<ParseNode> children = n.getChildren();
        for(ParseNode newptr : children){
            System.out.println("Children:");
            display(newptr);
        }
    }
    public static void main(String[] args){
        TestTree t = new TestTree();
        t.addChild("a", t.tree.getRoot());
        t.display(t.tree.getRoot());
    }

}
