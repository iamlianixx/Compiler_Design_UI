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

public class ParseEntry{
    private String token;
    private ArrayList<String> tokenChildren;
    private int lastIndex;
    
    public ParseEntry(String given){
        super();
        this.token = given;
        this.tokenChildren = new ArrayList<>();
        this.lastIndex = -1;
    }
    
    public void addChildToken(String given){
        this.tokenChildren.add(given);
        lastIndex++;
           
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ArrayList<String> getTokenChildren() {
        return tokenChildren;
    }
    

}
