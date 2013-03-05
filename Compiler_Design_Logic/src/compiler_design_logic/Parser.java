/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler_design_logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Jullian
 */
public class Parser {
    private LookupTable x;
    private Stack<LinkList> tokenList;
    
    public Parser(LinkList[] tokens){
        ArrayList<LinkList> temp;
        temp = new ArrayList(Arrays.asList(tokens));
        x = new LookupTable();
        Collections.reverse(temp);
        temp.get(0).printList();
    }

}
