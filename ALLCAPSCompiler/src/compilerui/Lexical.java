/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package compilerui;

import java.util.ArrayList;
import java.util.StringTokenizer;


/**
 *
 * @author Jullian
 */
class Token{
    private String token, info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}

public class Lexical {
    private TokenDictionary td;
    SymbolTable symbolTable;
    ArrayList<Token> tokens;
    ArrayList<String> lexemes;
    String splitDelim = "[ \t\n]+";
    String removeSpacesDelim = "[(),;+-*/%&|!<>=;'\"]+{}";
    String source;

    public Lexical(String source, SymbolTable table){
        this.td = new TokenDictionary();
        this.source = source;
        this.symbolTable = table;
        tokens = new ArrayList<Token>();
        lexemes = new ArrayList<String>();
    }



    public void generateLexemes(){
       String[] codeArray = this.source.split(splitDelim);
       for(int i=0; i<codeArray.length; i++){
           StringTokenizer tk = new StringTokenizer(codeArray[i],removeSpacesDelim, true);
           while(tk.hasMoreTokens()){
               String st = tk.nextToken();
               if(!lexemes.isEmpty() && notRelationalAndDoubleDelimChecker(st)){
                   lexemes.set(lexemes.size()-1, lexemes.get(lexemes.size()-1)+st);
               }
               else lexemes.add(st);
           }
       }
    }

    public ArrayList<Token> fillSymbolTable(){
        String locationScope = "";
        
        for(int i=0; i<lexemes.size(); i++){
            String current = lexemes.get(i);
            if(current.equals("MAIN")){
                locationScope = "MAIN";
            }
            else if(i!=lexemes.size()-1 && current.equals("FUNC")){
                locationScope = lexemes.get(i+1);
            }
            
            Token placeholder = new Token();
            String lexType = td.extractType(current);
            if(lexType != null){ //extraction of keywords
                placeholder.setToken(current);
                if(current.equals("\"") && tokens.size()>0 && 
                      tokens.get(tokens.size()-1).getToken().equals("\"")){
                  placeholder.setToken("string_id");
                  placeholder.setInfo("");
                  this.tokens.add(placeholder);
                  placeholder = new Token();
                  placeholder.setToken(current);
                
                }
               //extraction of integer and float 
            } else if(tokens.size()>0 && (isInteger(current) || isFloat(current)) && 
                    (!tokens.get(tokens.size()-1).getToken().equals("'") && 
                    !tokens.get(tokens.size()-1).getToken().equals("\""))){
                placeholder.setToken("num_id");
                placeholder.setInfo(current);
                //extraction of character
            } else if(tokens.size()>0 && tokens.get(tokens.size()-1).getToken().equals("'")){
                placeholder.setToken("char_id");
                placeholder.setInfo(current);
                //extraction of string
            } else if(tokens.size()>0 && tokens.get(tokens.size()-1).getToken().equals("\"")){
                String str = "";
                for(; i<lexemes.size() && !lexemes.get(i).equals("\"") ;i++){
                    str += lexemes.get(i) + " ";
                }
                i--;
                placeholder.setToken("string_id");
                placeholder.setInfo(str);
            } else {

                if(i!=0 && (lexemes.get(i-1).equals("VAR") || (lexemes.get(i-1).equals("FUNC") && 
                    (lexemes.get(i-2).equals("INT") || lexemes.get(i-2).equals("CHR")
                     || lexemes.get(i-2).equals("FLT") || lexemes.get(i-2).equals("STR")) || 
                        lexemes.get(i-2).equals("VOID")))){
                    String dtype = lexemes.get(i-2);
                    String scope = (lexemes.get(i-1).equals("FUNC"))?"null":locationScope;
                    this.symbolTable.insert("var_id", current, dtype, scope);
                } 
                placeholder.setToken("var_id");
                placeholder.setInfo(current);
                
            }
            this.tokens.add(placeholder);
        }
        return this.tokens;
    }
    
    private boolean isInteger(String given){
        try{
            Integer.parseInt(given);
        }catch(NumberFormatException ex){
            return false;
        }
        return true;
    }
    
    private boolean isFloat(String given){
        try{
            Float.parseFloat(given);
        }catch(NumberFormatException ex){
            return false;
        }
        return true;
    }

    
    public boolean notRelationalAndDoubleDelimChecker(String delim){
        boolean res=false;
        String lastLex = lexemes.get(lexemes.size()-1);
        if(delim.equals("=") && 
           (lastLex.equals("NOT") || lastLex.equals("=") || lastLex.equals(">") 
                || lastLex.equals("<"))) res = true;
        return res;
    }
}
