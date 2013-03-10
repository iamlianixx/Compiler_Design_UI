/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler_design_logic;

import java.util.ArrayList;

/*
 * @author Satchel Samantha A. Magsambol
 *         Zita Mary D. Enriquez
 */
public class SemanticAnalyzer {
    private ParseTree parseTree;
    private ArrayList<String> program = new ArrayList<>();
    private ArrayList<ArrayList<String>> assignList;
    private ArrayList<ArrayList<String>> funcList;
    private SymbolTable symTab;
    private String semanticErrorMessage = "Success!";
    private int flag = 0;


    public SemanticAnalyzer(ParseTree parseTree, SymbolTable symTab) {
        this.parseTree = parseTree;
        this.symTab = symTab;
        
        storeTokens(parseTree.getRoot());
        checkDuplicates();
        takeAssignments();
        checkAssignments();
        takeFunctions();
        checkFunctionCalls();
        this.checkPrintCalls();
        
        
    }
    
    
    private void storeTokens(ParseNode node) {
        ArrayList <ParseNode> children = node.getChildren();
        
        if(!children.isEmpty()){
            for(int i=0; i<children.size();  i++){
                storeTokens(children.get(i));
            }
        }
        else{
            if(node.getNodeData().equals("^"))
                program.add(node.getNodeData());
        }
    }
    
    //STORE ALL ASSIGMENT STATEMENTS                
    private void takeAssignments(){
        int i;
        assignList = new ArrayList<>();
        ArrayList<String> assign, rightVal = new ArrayList<>();
        for(i=0; flag == 0 && i<program.size(); i++){
            if(program.get(i).equals("=")){ //encounters an equal sign
                System.out.println("Variable: " + program.get(i-1));
                System.out.println(symTab.searchTable(program.get(i-1)));
                if(symTab.searchTable(program.get(i-1)) != -1){ //checks if the left value has been declared
                    assign = new ArrayList();
                    assign.add(program.get(i-1));
                    assign.add(symTab.table.get(symTab.searchTable(assign.get(0))).getDataType());
                    assign.add(symTab.table.get(symTab.searchTable(assign.get(0))).getScope());
                    
                    if(program.get(i+1).equals("'")||program.get(i+1).equals("\"")){ //checks if a CHR or STR comes after =
                        rightVal.add(program.get(i+1) + program.get(i+2));
                    }
                    else if(program.get(i+2).equals(";")){ 
                        rightVal.add(program.get(i+1));
                    }
                    else if(!program.get(i+1).equals("=")&&!program.get(i+1).equals("FUNC")) {
                        for(i=i+2; !program.get(i).equals(";"); i+=2){
                            rightVal.add(program.get(i-1));
                        }
                        rightVal.add(program.get(i-1));
                    }
                    for(int j=0; j<rightVal.size(); j++){
                        assign.add(rightVal.get(j));
                    }
                    assignList.add(assign);
                }
                else{
                    this.setUndeclaredVariableMessage();
                    this.setFlag();
                }
            }
        }
        
    }
    //checks whether the right value has the same data type as the left value
    private boolean checkDatatype(String val, String datatype){ 
        boolean result = false;
        switch (datatype) {
            case "INT":
                try {
                    Integer.parseInt(val);
                    Float.parseFloat(val);
                    result = true;
                } catch (NumberFormatException ex) {
                    result = checkVarType(val, datatype);
                }
                break;
            case "FLT":
                try {
                    Float.parseFloat(val);
                    result = true;
                } catch (NumberFormatException ex) {
                    result = checkVarType(val, datatype);
                }
                break;
            case "CHR":
                if (val.charAt(0) == '\'') {
                    result = true;
                } else {
                    result = checkVarType(val, datatype);
                }
                break;
            case "STR": 
                if (val.charAt(0) == '\"') { 
                    result = true;
                } else {
                    result = checkVarType(val, datatype);
                }
                break;
        }
        return result;
    }
    
    // checks the datatype of the variable found at the right side of the assignment statement
    // used if the rvalue is a variable
    private boolean checkVarType(String var, String datatype){
        if(symTab.searchTable(var) != -1) {
            if(symTab.table.get(symTab.searchTable(var)).getDataType().equals(datatype)){
                return true;
            }
            else{
                return false;
            }
        }
        else return false;
    }
        
    private void checkAssignments(){ //type checking
        int i;
        for(i=0; flag == 0 && i<assignList.size(); i++){
            for(int j=3; flag == 0 && j<assignList.get(i).size(); j++){
                if(!checkDatatype(assignList.get(i).get(j), assignList.get(i).get(1))){ 
                    if(assignList.get(i).get(j).charAt(0) >= 97 &&
                       assignList.get(i).get(j).charAt(0) <= 122) {
                        this.setUndeclaredVariableMessage();
                    } else {
                        this.setTypeMismatchMessage();
                    }
                    this.setFlag();
                }
            }
        }
    }
    
    //STORE ALL FUNCTION NAMES (FROM FUNCTION DECLARATIONS)
    private void takeFunctions(){
        int i, commactr, noOfParams = 0; 
        String retVal;
        ArrayList<String> func;
        funcList = new ArrayList<>();
        ArrayList<String> params = new ArrayList<>();
        
        for(i=0; flag == 0 && i<program.size(); i++){
            commactr = 0;
            retVal = "";
            if(program.get(i).equals("FUNC")){ //encounters FUNC 
                if(program.get(i-1).equals("VOID")||program.get(i-1).equals("INT")|| //checks if return type comes before keyword FUNC
                        program.get(i-1).equals("FLT")||program.get(i-1).equals("CHR")||program.get(i-1).equals("STR")){
                    if(symTab.searchTable(program.get(i+1)) != -1){
                        func = new ArrayList<>();
                        func.add(program.get(i+1));
                        func.add(symTab.table.get(symTab.searchTable(func.get(0))).getDataType());
                        
                        for(;!program.get(i).equals(")");i++){ //loop to count number of commas
                            if(program.get(i).equals(",")){
                                params.add(program.get(i-1));
                                commactr++;
                            }
                        }
                        if(!program.get(i-1).equals("(")){
                            noOfParams = commactr + 1;
                        }
                        
                        func.add(Integer.toString(noOfParams));
                        
                        if(!params.isEmpty()){
                            for(int j=0; j<params.size(); j++){
                                func.add(symTab.table.get(symTab.searchTable(program.get(i-1))).getParameterValues().get(j).getDatatype());
                            }
                        }
                        funcList.add(func);
                        //check if RETURN statement is valid
                        System.out.println("func.get(1) "+func.get(1));
                        if(!func.get(1).equals("VOID")){  //non VOID functions should have RETURN statements
                            for(; !program.get(i).equals("RETURN"); i++);  
                            
                            if(program.get(i).equals("RETURN")){    //RETURN is found
                                retVal = program.get(i+1);
                                if(checkDatatype(retVal, func.get(1))==false){  //checks if return value's datatype  
                                    this.setInvalidReturnValueMessage();        //corresponds to return type
                                    this.setFlag();
                                }
                            }
                            if(retVal.equals("")){                      //checks if the retVal has value 
                                this.setNoReturnStatement();
                                this.setFlag();
                            }
                        }    
                    }
                }
            }
        }
    }
    
    
    private void checkDuplicates() {
        for(int i = symTab.table.size()-1; flag == 0 && i > 0; i--) {
            for(int j = i-1; flag == 0 && j > 0; j--) {
                if (symTab.table.get(i).getToken().equals(symTab.table.get(j).getToken())) {
                    if (symTab.table.get(i).getScope().equals(symTab.table.get(j).getScope()) || 
                        symTab.table.get(i).getParameterValues() == symTab.table.get(j).getParameterValues()) {
                        this.setDuplicateMessage(symTab.table.get(i).getTokenVal());
                        this.setFlag();
                    }
                }
            }
        }
    }
    
    private void checkFunctionCalls(){ //checks if function calls are valid
        int i, j, commactr, noOfParams = 0; 
        ArrayList<String> func;
        funcList = new ArrayList<>();
        ArrayList<String> params = new ArrayList<>();
        
        for(i=0; flag == 0 && i<program.size(); i++){
            commactr = 0;
            if(program.get(i).equals("FUNC")){ //encounters FUNC 
                if(!program.get(i-1).equals("VOID") && !program.get(i-1).equals("INT") && //checks if return type comes before keyword FUNC
                   !program.get(i-1).equals("FLT")  && !program.get(i-1).equals("CHR") && 
                   !program.get(i-1).equals("STR")){
                    if(symTab.searchTable(program.get(i+1)) != -1){
                        func = new ArrayList<>();
                        func.add(program.get(i+1));
                        func.add(symTab.table.get(symTab.searchTable(func.get(0))).getDataType());

                        for(j=i;!program.get(j).equals(")");j++){ //loop to count number of commas
                            if(program.get(j).equals(",")){
                                params.add(program.get(j-1));
                                commactr++;
                            }
                        }
                        if(!program.get(j-1).equals("(")){
                            noOfParams = commactr + 1;
                        }
                        
                        func.add(Integer.toString(noOfParams));
                        
                        if(!params.isEmpty()){
                            for(j=0; j<params.size(); j++){
                                func.add(symTab.table.get(symTab.searchTable(program.get(i-1))).getParameterValues().get(j).getDatatype());
                            }
                        }
                        //the following for-loop checks if function call has the right number of parameters and if parameters have the right datatypes
                        for(j=0; flag == 0 && j<funcList.size(); j++){
                            if(funcList.get(j).get(0).equals(func.get(0))){
                                for(int k=1; k<func.size()&&funcList.get(j).get(j).equals(func.get(j)); k++) {}
                                if(!funcList.get(j).get(j).equals(func.get(j))){
                                    this.setInvalidFuncCallMessage();
                                    this.setFlag();
                                }
                            }
                        }
                        System.out.println(program.get(i-1));
                        if(program.get(i-1).equals("=")){
                            //the following if-statement checks if left value's datatype is the function's return type:
                            if(!symTab.table.get(symTab.searchTable(program.get(i-2))).getDataType().equals(func.get(1))){
                                this.setTypeMismatchMessage();
                                this.setFlag();
                            }
                        }
                        else { //token before FUNC is not =
                            if(!func.get(1).equals("VOID")){
                                this.setNoLValueMessage();
                                this.setFlag();
                            }
                        }
                   }else{
                        this.setUndeclaredFunctionMessage();
                        this.setFlag();
                    }
                }
              }
         }
   }
    
    private void checkPrintCalls(){
        int i;
        for(i=0; flag == 0 && i<program.size(); i++){
            if(program.get(i).equals("PRINT")){
                System.out.println(program.get(i+2));
                if(program.get(i+2).equals("FUNC")
                   && symTab.table.get(symTab.searchTable(program.get(i+3))).getDataType().equals("VOID")){
                    this.setInvalidArgumentMessage();
                    this.setFlag();
                }
                else if(program.get(i+2).charAt(0) >= 97 &&program.get(i+2).charAt(0) <= 122){
                    if(symTab.searchTable(program.get(i+2)) == -1){
                        this.setInvalidArgumentMessage();
                        this.setFlag();
                    }
                }
            }
        }
    }
    
    public String getSemanticErrorMessage(){
        return semanticErrorMessage;
    }
    
    private void setFlag() {
        this.flag = 1;
    }
    
    private void setTypeMismatchMessage(){
        this.semanticErrorMessage = "Type mismatch.";
    }
    
    private void setDuplicateMessage(String token) {
        this.semanticErrorMessage = token + " already exists.";
    }
    
    private void setInvalidFuncCallMessage() {
        this.semanticErrorMessage = "Invalid function call.";
    }
    
    private void setUndeclaredVariableMessage(){
        this.semanticErrorMessage = "Variable has not been declared.";
    }
    
    private void setUndeclaredFunctionMessage(){
        this.semanticErrorMessage = "Function has not been declared.";
    }
    
    private void setNoLValueMessage(){
        this.semanticErrorMessage = "Function call requires left value.";
    }
    
    private void setInvalidArgumentMessage(){
        this.semanticErrorMessage = "Invalid argument.";
    }
    
    private void setInvalidReturnValueMessage(){
        this.semanticErrorMessage = "Return value is invalid.";
    }
    
    private void setNoReturnStatement(){
        this.semanticErrorMessage = "Function must have a return statement.";
    }
} 