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
public class ParseNode {
    private String nodeData;
    private ParseNode parent;
    private ArrayList<ParseNode> children;

    public ParseNode(String nodeData, ParseNode parent) {
        this.nodeData = nodeData;
        this.parent = parent;
        this.children = new ArrayList<ParseNode>();
    }

    public ArrayList<ParseNode> getChildren() {
        return this.children;
    }

    public void setChildren(ArrayList<ParseNode> children) {
        this.children = children;
    }

    public String getNodeData() {
        return this.nodeData;
    }

    public void setNodeData(String nodeData) {
        this.nodeData = nodeData;
    }

    public ParseNode getParent() {
        return this.parent;
    }

    public int fetchChildrenCtr(){
        return this.children.size();
    }

    public int fetchChildIndex(String given){
        int index = -1; boolean check = false;
        for(int i=0; check == false && i<children.size(); i++){
            if(this.children.get(i).getNodeData().equals(given)){
                check = true;
                index = i;
            }
        }
        return index;
    }




}
