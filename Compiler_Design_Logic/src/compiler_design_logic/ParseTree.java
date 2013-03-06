/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler_design_logic;

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
    
    
}
