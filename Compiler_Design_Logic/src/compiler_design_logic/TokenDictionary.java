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
class DictionaryEntry{
    private String token;
    private String tokentype;

    public DictionaryEntry(String token, String tokentype){
        this.token = token;
        this.tokentype = tokentype;
    }
    
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokentype() {
        return tokentype;
    }

    public void setTokentype(String tokentype) {
        this.tokentype = tokentype;
    }
}

public class TokenDictionary {
    ArrayList<DictionaryEntry> dictionary;
    
    public TokenDictionary(){
        dictionary = new ArrayList<>();
        
        dictionary.add(new DictionaryEntry("FUNC","<function"));
    }
    
    
}
