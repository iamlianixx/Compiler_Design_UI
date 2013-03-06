/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler_design_logic;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class SemanticAnalyzer {
    private ArrayList<String> program;
    private ArrayList<ArrayList<String>> list;
    private SymbolTable symTab;

    public SemanticAnalyzer(SymbolTable symTab) {
        this.symTab = symTab;
    }
    
    //STORE ALL ASSIGMENT STATEMENTS
    public void takeAssignments(ArrayList<String> program){
        int i;
        list = new ArrayList<>();
        ArrayList<String> assign;
        for(i=0; i<program.size(); i++){
            if(program.get(i).equals("=")){ //encounters an equal sign
                if(program.get(i+1).equals("'")||program.get(i+1).equals("\"")){ //checks if a CHR or STR comes after =
                    assign = new ArrayList<>();
                    assign.add(program.get(i-1));
                    assign.add(program.get(i+1)+program.get(i+2));
                    assign.add(symTab.table.get(symTab.searchTable(assign.get(0)));
                    assign.add(symTab.getTokenValue(symTab.searchTable(assign.get(0))));
                }
                else if(program.get(i+2).equals(";")){ //checks if it's an assignment statement, not an operation
                    assignments.add(new AssignmentListNode(program.get(i-1),program.get(i+1),,);
                }   
            }
        }
        
    }
    
    public void checkAssignments(ArrayList<ArrayList<String>> assignments){ //type checking
        
    }
    
    //STORE ALL FUNCTION NAMES (FROM FUNCTION DECLARATIONS)
    public void takeFunctions(ArrayList<String> program){
        int i, commactr, noOfParams = 0; 
        String retType;
        functions = new ArrayList<>();
        for(i=0; i<program.size(); i++){
            commactr = 0;
            if(program.get(i).equals("FUNC")){ //encounters FUNC 
                if(program.get(i-1).equals("VOID")||program.get(i-1).equals("INT")|| //checks if return type comes before keyword FUNC
                        program.get(i-1).equals("FLT")||program.get(i-1).equals("CHR")||program.get(i-1).equals("STR")){
                    retType=program.get(i-1);
                    for(;!program.get(i).equals(")");i++){ //loop to count number of commas
                        if(program.get(i).equals(",")){
                            commactr++;
                        }
                    }
                    if(!program.get(i-1).equals("(")){
                        noOfParams = commactr + 1;
                    }
                    functions.add(new ArrayList<String>(program.get(i+1),retType, noOfParams));
                }
            }
        }
    }
    
    public void checkFunctionCalls(ArrayList<ArrayList<String>> functions){ //checks if function calls are valid
    
    }
} 