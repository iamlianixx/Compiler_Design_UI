/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler_design_logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Jullian
 */
class LookupMapRow{
    int symbolKey, nonterminalKey, prodKey;
    
    public LookupMapRow(int sym, int nonterm, int prod){
        symbolKey = sym;
        nonterminalKey = nonterm;
        prodKey = prod;
    }
    
}

public class LookupTable {
    public ArrayList<String> symbols, nonterminals, productionList;
    static ArrayList<LookupMapRow> map;
    
    public LookupTable(){
        String[] symbolList = {"char_id","num_id","string_id","var_id", ";", 
        "_",".", "=", ",", "{","}", "(", ")","+", "-", "/", "*", "<", ">", "'",
        "\"","AND", "OR", "NOT=", "INT", "CHR","FLT", "STR", "VAR", "FUNC", "MAIN", 
        "IF", "THEN", "WHILE", "ELSE", "$", "RETURN", "PRINT", "VOID",">=","<="};
        String[] nonterminalsList = {
        "<character>",
        "<number>",
        "<string>",
        "<varname>", 
        "<symbol>", 
        "<quotation>", 
        "<arithmetic>", 
        "<relational>",
        "<relational'>",
        "<logical>", 
        "<datatype>", 
        "<value>", 
        "<inputNumber>",
        "<inputNumber'>", 
        "<variableDeclaration>", 
        "<variableDeclaration'>",
        "<functionDeclaration>", 
        "<functionParameters>", 
        "<functionParameters'>",
        "<MainFunction>", 
        "<statement>", 
        "<functionCall>", 
        "<parameter>",
        "<parameter'>", 
        "<ifStatement>", 
        "<ifStatement'>", 
        "<elseStatement>",
        "<whileStatement>", 
        "<conditionLogical>",
        "<expression>", 
        "<expression'>", 
        "<inputCharacter>", 
        "<inputString>",
        "<assignmentStatement>", 
        "<assignment>", 
        "<condition>", 
        "<condition'>",
        "<functionSet>",
        "<program>",
        "<returnStatement>",
        "<returnStatement'>",
        "<printStatement>",
        "<printStatement'>",
        "<assignment'>",
        "<assignmentStatement'>"};
        String[] productions = {
        "char_id", 
        "num_id", 
        "string_id", 
        "var_id", 
        "'",
        "\"", 
        ";", 
        "_",
        ".", 
        "=", 
        ",", 
        "{",
        "}", 
        "(", 
        ")", 
        "<arithmetic>",
        ">",
        "<",
        "+", 
        "-",
        "*", 
        "/", 
        ">", 
        "<", 
        "==", 
        "NOT=", 
        ">=", 
        "<=",
        "AND", 
        "OR", 
        "INT",
        "CHR", 
        "FLT", 
        "STR",
        "' <inputCharacter>", 
        "<inputNumber>",
        "\" <inputString>", 
        "<character> '",
        "<string> \"",
        "( <inputNumber> )",
        "<number> <inputNumber'>", 
        "<arithmetic> <inputNumber>", 
        "EPSILON",
        "= <assignmentStatement'>", 
        "<arithmetic> <assignment'>",
        "<varname>", 
        "VAR <varname> <variableDeclaration'>",
        "= <value> <variableDeclaration'>",
        ", <varname> <variableDeclaration'>",
        ";", 
        "FUNC <varname> ( <functionParameters> ) { <statement> }",
        "<datatype> <varname> <functionParameters'>",
        ", <functionParameters>",
        "EPSILON", 
        "MAIN { <statement> }",
        "<datatype> <variableDeclaration> <statement>",
        "<ifStatement> <statement>", 
        "<whileStatement> <statement>", 
        "<functionCall> ; <statement>",
        "EPSILON", 
        "FUNC <varname> ( <parameter> )",
        "<varname> <parameter'>", 
        "<value> <parameter'>",
        ", <parameter>", 
        "EPSILON", 
        "IF ( <condition> ) THEN { <statement> } <ifStatement'>",
        "ELSE <elseStatement>", 
        "EPSILON",
        "{ <statement> } <ifStatement'>", 
        "<ifStatement>",
        "WHILE ( <condition> ) { <statement> }",
        "<condition'> <conditionLogical>",
        "<logical> <condition'> <conditionLogical>",
        "EPSILON", 
        "<expression> <relational> <expression>", 
        "<varname> <expression'>", 
        "<number> <expression'>",
        "( <expression> ) <expression'>", 
        "<arithmetic> <expression>",
        "EPSILON",
        "<datatype> <functionDeclaration> <functionSet>",
        "<functionSet> <MainFunction>",
        "<functionCall>",
        "EPSILON",
        "RETURN <returnStatement'> ;",
        "<varname>",
        "<value>",
        "<returnStatement> <statement>",
        "PRINT ( <printStatement'> ) ;",
        "<value>",
        "<varname>",
        "<printStatement> <statement>",
        "VOID",
        "<varname> <assignmentStatement> ; <statement>",
        "<number>",
        "<varname> <assignment>",
        "<value>",
        "EPSILON",
        "EPSILON"};
        
        LookupMapRow[] mapArray = {new LookupMapRow(0,0,0), new LookupMapRow(1,1,1),
    new LookupMapRow(2,2,2), new LookupMapRow(3,3,3), new LookupMapRow(4,4,6),
    new LookupMapRow(5,4,7), new LookupMapRow(6,4,8), new LookupMapRow(7,4,9),
    new LookupMapRow(8,4,10), new LookupMapRow(9,4,11), new LookupMapRow(10,4,12),
    new LookupMapRow(11,4,13), new LookupMapRow(12,4,14), new LookupMapRow(13,4,15),
    new LookupMapRow(14,4,15), new LookupMapRow(15,4,15), new LookupMapRow(16,4,15),
    new LookupMapRow(17,4,17), new LookupMapRow(18,4,16), new LookupMapRow(19,5,4),
    new LookupMapRow(20,5,5), new LookupMapRow(13,6,18), new LookupMapRow(14,6,19),
    new LookupMapRow(15,6,20), new LookupMapRow(16,6,21), new LookupMapRow(17,7,23),
    new LookupMapRow(18,7,22), new LookupMapRow(23,7,25), new LookupMapRow(7,7,24),
    new LookupMapRow(39,7,26), new LookupMapRow(40,7,27), 
    new LookupMapRow(11,8,27), new LookupMapRow(21,9,28), new LookupMapRow(22,9,29),
    new LookupMapRow(24,10,30), new LookupMapRow(25,10,31), new LookupMapRow(26,10,32),
    new LookupMapRow(27,10,33), new LookupMapRow(1,11,35), new LookupMapRow(11,11,35),
    new LookupMapRow(19,11,34), new LookupMapRow(20,11,36), new LookupMapRow(1,12,40),
    new LookupMapRow(11,12,39), new LookupMapRow(4,13,42), new LookupMapRow(7,13,42),
    new LookupMapRow(8,13,42), new LookupMapRow(12,13,42), new LookupMapRow(13,13,41),
    new LookupMapRow(14,13,41), new LookupMapRow(15,13,41), new LookupMapRow(16,13,41),
    new LookupMapRow(28,14,46), new LookupMapRow(4,15,49), new LookupMapRow(7,15,47),
    new LookupMapRow(8,15,48), new LookupMapRow(29,16,50), new LookupMapRow(24,17,51),
    new LookupMapRow(25,17,51), new LookupMapRow(26,17,51), new LookupMapRow(27,17,51),
    new LookupMapRow(8,18,52), new LookupMapRow(12,18,53), new LookupMapRow(35,18,53),
    new LookupMapRow(30,19,54), new LookupMapRow(10,20,59), new LookupMapRow(24,20,55),
    new LookupMapRow(25,20,55), new LookupMapRow(26,20,55), new LookupMapRow(27,20,55),
    new LookupMapRow(29,20,58), new LookupMapRow(31,20,56), new LookupMapRow(33,20,57),
    new LookupMapRow(35,20,59), new LookupMapRow(29,21,60), new LookupMapRow(1,22,62),
    new LookupMapRow(3,22,61), new LookupMapRow(11,22,62), new LookupMapRow(19,22,62),
    new LookupMapRow(20,22,62), new LookupMapRow(8,23,63), new LookupMapRow(12,23,64),
    new LookupMapRow(35,23,64), new LookupMapRow(31,24,65), new LookupMapRow(10,25,67),
    new LookupMapRow(24,25,67), new LookupMapRow(25,25,67), new LookupMapRow(26,25,67),
    new LookupMapRow(27,25,67), new LookupMapRow(29,25,67), new LookupMapRow(31,25,67),
    new LookupMapRow(33,25,67), new LookupMapRow(34,25,66), new LookupMapRow(35,25,67),
    new LookupMapRow(9,26,68), new LookupMapRow(31,26,69), new LookupMapRow(33,27,70),
    new LookupMapRow(12,28,73), new LookupMapRow(21,28,72), new LookupMapRow(22,28,72),
    new LookupMapRow(35,28,73), new LookupMapRow(1,29,76), new LookupMapRow(3,29,75),
    new LookupMapRow(11,29,77), new LookupMapRow(7,30,79), new LookupMapRow(12,30,79),
    new LookupMapRow(13,30,78), new LookupMapRow(14,30,78), new LookupMapRow(15,30,78),
    new LookupMapRow(16,30,78), new LookupMapRow(17,30,79), new LookupMapRow(18,30,79),
    new LookupMapRow(21,30,79), new LookupMapRow(22,30,79), new LookupMapRow(23,30,79),
    new LookupMapRow(35,30,79), new LookupMapRow(0,31,37), new LookupMapRow(2,32,38),
    new LookupMapRow(1,35,71), new LookupMapRow(3,35,71), new LookupMapRow(11,35,71),
    new LookupMapRow(1,36,74), new LookupMapRow(3,36,74), new LookupMapRow(11,36,74),
    new LookupMapRow(29,11,82), new LookupMapRow(30,37,83), new LookupMapRow(36,20,87), 
    new LookupMapRow(36,39,84), new LookupMapRow(24,37,80), new LookupMapRow(25,37,80),
    new LookupMapRow(26,37,80), new LookupMapRow(27,37,80), new LookupMapRow(24,38,81),
    new LookupMapRow(25,38,81), new LookupMapRow(26,38,81), new LookupMapRow(27,38,81),
    new LookupMapRow(1,40,86), new LookupMapRow(3,40,85), new LookupMapRow(11,40,86),
    new LookupMapRow(19,40,86), new LookupMapRow(20,40,86), new LookupMapRow(37,20,91),
    new LookupMapRow(37,41,88), new LookupMapRow(1,42,89), new LookupMapRow(3,42,90),
    new LookupMapRow(11,42,89), new LookupMapRow(19,42,89), new LookupMapRow(20,42,89),
    new LookupMapRow(38,10,92), new LookupMapRow(38,37,80), new LookupMapRow(38,38,81),
    new LookupMapRow(7,33,43), new LookupMapRow(13,34,44), new LookupMapRow(14,34,44),
    new LookupMapRow(15,34,44), new LookupMapRow(16,34,44), new LookupMapRow(3,43,45),
    new LookupMapRow(1,43,94), new LookupMapRow(3,20,93), new LookupMapRow(30,38,81),
    new LookupMapRow(39,30,79), new LookupMapRow(40,30,79), new LookupMapRow(1,44,96),
    new LookupMapRow(3,44,95), new LookupMapRow(11,44,96), new LookupMapRow(19,44,96),
    new LookupMapRow(20,44,96), new LookupMapRow(29,44,96), new LookupMapRow(12,17,97),
    new LookupMapRow(12,22,98), new LookupMapRow(37,25,67), new LookupMapRow(36,25,67)};
       
        symbols = new ArrayList(Arrays.asList(symbolList));
        nonterminals = new ArrayList(Arrays.asList(nonterminalsList));
        productionList = new ArrayList(Arrays.asList(productions));
        map = new ArrayList(Arrays.asList(mapArray));

    }
    
        public String retrieveProduction(String symbol, String nonterminal){
        int i,sym,nterm;
        String res = null;
        LookupMapRow temp;
        for(i=0; i<symbols.size() && !symbols.get(i).equals(symbol); i++);
        if(i<symbols.size()){
            sym = i;
            for(i=0; i<nonterminals.size() && !nonterminals.get(i).equals(nonterminal); i++);
            if(i<nonterminals.size()){
                nterm = i;
                for(i=0; i<map.size() && 
                        (map.get(i).symbolKey != sym || map.get(i).nonterminalKey != nterm); i++);
                if(i<map.size()){
                    res = productionList.get(map.get(i).prodKey).toString();
                }
                else System.err.println("There is no production.");
            }
            else System.err.println("Nonterminal does not exist.");
        }
        else System.err.println("Symbol does not exist.");
        return res;
    }
        
    public boolean isTerminal(String prod){
        boolean check = false;
        for(int i=0; check == false && i<this.symbols.size(); i++){
            if(prod.equals(this.symbols.get(i))){
                check = true;
            }
        }
        return check;
    }
        
}
