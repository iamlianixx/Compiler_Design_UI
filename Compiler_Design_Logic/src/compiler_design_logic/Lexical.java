/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler_design_logic;

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
            if(lexType != null){ 
                placeholder.setToken(current);
            //    System.out.println("Current: " + placeholder.getToken());
             //   System.err.println(current);
            } else if(tokens.size()>0 && isInteger(current) && (!tokens.get(tokens.size()-1).equals("'"))){
                System.out.println(current);
                placeholder.setToken(current);
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

    public void display(){
        System.out.println("LIST OF LEXEMES");
        for(int i=0; i<lexemes.size(); i++)
            System.out.println(lexemes.get(i));
    }

    public static void main(String[] args){
        Lexical lex = new Lexical("INT FUNC samplefunc(INT given){ STR string = \"Apple\";"
                + " INT sum = 1; CHR char = '3';  sum = sum + given; RETURN sum; }MAIN{ INT "
                + "VAR x = 0; IF(x >= 0) THEN { x = x + 1; } FUNC samplefunc(5);}",new SymbolTable());
        lex.generateLexemes();
        lex.fillSymbolTable();
        //lex.display();
  
    }
}
