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
class SymbolTableEntry{
    private String token, tokenVal, dataType, scope;
    
    SymbolTableEntry(String token, String value, String datatype, String scope){
        this.token = token;
        this.tokenVal = tokenVal;
        this.dataType = datatype;
        this.scope = scope;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenVal() {
        return tokenVal;
    }

    public void setTokenVal(String tokenVal) {
        this.tokenVal = tokenVal;
    }

    
}

public class SymbolTable {
    ArrayList<SymbolTableEntry> table;
    
    public SymbolTable(){
        table = new ArrayList<SymbolTableEntry>();
    }
    
    public int searchTable(String tokenValue){
        int resultIndex = -1,i;
        for(i=0; i<this.table.size() && !this.table.get(i).getTokenVal().equals(tokenValue); i++);
        if(i<this.table.size())
            resultIndex = i;
        return resultIndex;
    }
    
    public void insert(String token, String tokenvalue, String datatype, String scope){
        SymbolTableEntry entry = new SymbolTableEntry(token,tokenvalue,datatype,scope);
        this.table.add(entry);
    }
    
    public int lastIndex(){
        return this.table.size()-1;
    }
    
    public String getTokenValue(int index){
        return this.table.get(index).getTokenVal();
    }
}