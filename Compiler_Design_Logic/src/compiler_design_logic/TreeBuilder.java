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
public class TreeBuilder {
    private ParseTree pTree;
    private ParseArray pArray;
    private ParseEntry entryPtr;
    private ParseNode nodePtr;
    private LookupTable look;
    
    public TreeBuilder(ParseArray given){
        pArray = given;
        pTree = new ParseTree();
        nodePtr = pTree.getRoot();
        look = new LookupTable();
    }
    
    public ParseTree grabTree(){
        return this.pTree;
    }
    
    public void buildTree(){
        ParseNode tempPtr;
        if(nodePtr!=null){
            boolean check = false; int index=-1;
            for(int i=0; check == false && i<=pArray.getLastIndex(); i++){
                if(pArray.retrieveEntryWithIndex(i).getToken().equals(nodePtr.getNodeData())){
                    check = true;
                    index = i;
                }
            }
            ArrayList<ParseNode> temp = new ArrayList<>();
            for(int i=0; i<pArray.retrieveEntryWithIndex(index).getTokenChildren().size(); i++)
                temp.add(new ParseNode(pArray.retrieveEntryWithIndex(index).getTokenChildren().get(i),nodePtr));
            nodePtr.setChildren(temp);
            tempPtr = nodePtr;
            for(int i=0; i<tempPtr.getChildren().size(); i++){
               if(look.isNonTerminal(nodePtr.getChildren().get(i).getNodeData())){
                   nodePtr = nodePtr.getChildren().get(i);
                   this.buildTree();
               } else nodePtr = nodePtr.getParent();
               
            }
        }
    }
}
