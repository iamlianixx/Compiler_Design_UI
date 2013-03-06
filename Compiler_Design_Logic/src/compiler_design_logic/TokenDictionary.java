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
        
        //symbols
        dictionary.add(new DictionaryEntry(",","<symbol>"));
        dictionary.add(new DictionaryEntry("'","<quotation>"));
        dictionary.add(new DictionaryEntry("\"","<quotation>"));
        dictionary.add(new DictionaryEntry(",","<symbol>"));
        dictionary.add(new DictionaryEntry("","<symbol>"));
        dictionary.add(new DictionaryEntry(",","<symbol>"));
        dictionary.add(new DictionaryEntry("(","<symbol>"));
        dictionary.add(new DictionaryEntry(")","<symbol>"));
        dictionary.add(new DictionaryEntry("{","<symbol>"));
        dictionary.add(new DictionaryEntry("}","<symbol>"));
        dictionary.add(new DictionaryEntry(";","<symbol>"));
        
        //datatypes
        dictionary.add(new DictionaryEntry("INT","datatype"));
        dictionary.add(new DictionaryEntry("CHR","datatype"));
        dictionary.add(new DictionaryEntry("FLT","datatype"));
        dictionary.add(new DictionaryEntry("STR","datatype"));
        
        //keywords
        dictionary.add(new DictionaryEntry("FUNC","keyword"));
        dictionary.add(new DictionaryEntry("IF","keyword"));
        dictionary.add(new DictionaryEntry("THEN","keyword"));
        dictionary.add(new DictionaryEntry("MAIN","keyword"));
        dictionary.add(new DictionaryEntry("ELSE","keyword"));
        dictionary.add(new DictionaryEntry("WHILE","keyword"));
        dictionary.add(new DictionaryEntry("VAR","keyword"));
        dictionary.add(new DictionaryEntry("RETURN","keyword"));
        
        //logical and relational keywords
        dictionary.add(new DictionaryEntry("AND","<logical>"));
        dictionary.add(new DictionaryEntry("OR","<logical>"));
        dictionary.add(new DictionaryEntry("==","<relational>"));
        dictionary.add(new DictionaryEntry("NOT=","<relational>"));
        dictionary.add(new DictionaryEntry(">","<relational>"));
        dictionary.add(new DictionaryEntry("<","<relational>"));
        
        //arithmetic operators
        dictionary.add(new DictionaryEntry("+","<arithmetic>"));
        dictionary.add(new DictionaryEntry("-","<arithmetic>"));
        dictionary.add(new DictionaryEntry("*","<arithmetic>"));
        dictionary.add(new DictionaryEntry("/","<arithmetic>"));
        
        //assignment
        dictionary.add(new DictionaryEntry("=","<variableDeclaration'>"));
        
    }
    
    public String extractType(String val){
       String result = null;
       int i;
        for(i=0; i<dictionary.size() && !dictionary.get(i).getToken().equals(val); i++);
        if(i<dictionary.size()){
            result = dictionary.get(i).getTokentype();
        }
       return result;
    }
}
