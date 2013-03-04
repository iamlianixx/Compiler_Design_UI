/** Lexical Tokenizer
 * @author Jonie Francis R. Tan
 * 
 * Goal: create tokens input String and determine the following:
 *  1) Value
 *  2) Data Type
 *  3) Statement Type (Statement or Assignment)
 *  4) Scope
 *      -   Scope has been addressed by counting its nesting depth
 *          and assigning a unique section ID. Integer, Starts at 0.
 *   
 *  Output:
 *      a Linked List that contains the previous information of the input line.
 */

package lex;

import java.util.Scanner;


class Link {
    public String value;
    public String datatype;
    public String statement_type;
    public int scope_depth = 0;
    public Link prevLink;
    public Link nextLink;
    public int index;

    //Link constructor
    public Link(String val, String dtype, String statement) {
	    value = val;
	    datatype = dtype;
            statement_type = statement;
    }

    //Print Link data
    public void printLink() {
	    System.out.print("Index: " + index + " Value: \"" + value + "\"\t Data Type: " + datatype + "\t Statement Type: " + statement_type + "\t Scope Depth: " + scope_depth + "\n");
    }
}

class LinkList {
    private Link first;
    private Link last;
    private int index_list;

    //LinkList constructor
    public LinkList() {
	    first = null;
            index_list = 0;
    }

    //Returns true if list is empty
    public Link getList() {
	    return first;
    }
    
    //Returns true if list is empty
    public boolean isEmpty() {
	    return first == null;
    }

    public int length() {
	    return index_list;
    }
    
   // This function searches for a specific member of the linked list and updates its value/datatype information.
    public void UpdateData(int update_index, String val, String dtype){        
       Link link = first;
       
       while(link.index != update_index){
           link = link.nextLink;
       }
       if (update_index == link.index)
       {    link.value = val;
            link.datatype = dtype;
       }
    }
    
public void UpdateStatement(int update_index, String state){        
       Link link = first;
       
       while(link.index != update_index){
           link = link.nextLink;
       }
       if (update_index == link.index)
       {    link.statement_type = state;
       }
    }
    
   // This function searches for a specific member of the linked list and updates its scope information. 
   public void UpdateScope(int update_index, int s_depth){        
       Link link = first;
       
       while(link.index != update_index){
           link = link.nextLink;
       }
       if (update_index == link.index)
       {    link.scope_depth = s_depth;
       }
    }
   
    //Inserts a new Link at the first of the list
    public void insert(String val, String dtype, String statement) {
	    Link link = new Link(val,dtype, statement);
	    link.index = index_list++;
            
            link.nextLink = null;

            if (first == null){
                first = link;
                last = link;
                link.prevLink = null;
            }
            else {
                last.nextLink = link;
                link.prevLink = last;
                last = link;
            }
                
            /* for insert first
            link.nextLink = first;
	    first = link;*/
    }

    //Deletes the link at the first of the list
    public Link delete() {
	    Link temp = first;
	    first = first.nextLink;
	    return temp;
    }

    //Prints list data
    public void printList() {
	    Link currentLink = first;
	    System.out.print("List: \n");
	    while(currentLink != null) {
		    currentLink.printLink();
		    currentLink = currentLink.nextLink;
	    }
	    System.out.println("");
    }
}  

class LexList {
    public static LinkList main(String input) {
        
            String[] catcher = stringToken(input);
            String[] datatype = new String[catcher.length];
            String statement = "Statement";
            
            //makes an array that lists the data types
            for(int i=0; i<catcher.length; i++){
                datatype[i] = tokenFilter(catcher[i]);
                if (datatype[i].equals("Assignment")){
                    statement = "Assignment"; // defaults as statement, changes if "assignment" data type detected
                } 
            }
            
	    LinkList list = new LinkList();
            
            for(int i=0; i < catcher.length; i++){
                list.insert(catcher[i], datatype[i], statement);
	    }            
            
            // enable to check if list successfully generated
            // list.printList();
            
            return list;

            
 //           list.UpdateScope(2, 77, 8);
//            list.UpdateData(3, "Cars", "Chucky");
           
/*
	    while(!list.isEmpty()) {
		    Link deletedLink = list.delete();
		    System.out.print("deleted: ");
		    deletedLink.printLink();
	    }
	    list.printList();
            */
    }
    
    //function used to creat an array of tokens from input.
    public static String[] stringToken(String input) {
     Scanner s = new Scanner(input).useDelimiter("\\b");
     String output = "";
     
     String temp;
     while (s.hasNext())
     {   temp = s.next();
        String out ="";
     
        
        // Formats the incoming token
        if (temp.equals("==")){
            output = output.concat(temp+ " ");
        }
       else if (temp.equals("){ ")){
            output = output.concat(") { ");                
        }
       else if (temp.equals(")) ")){
            output = output.concat(") ) ");                
        }
       else if (temp.equals(")} ")){
            output = output.concat(") } ");                
        }
              else if (temp.equals("({ ")){
            output = output.concat("( { ");                
        }
        else if (temp.equals("(( ")){
            output = output.concat("( ( ");
        }
        else if (temp.equals("({ ")){
            output = output.concat("( { ");
        }
        else if (temp.matches("\\p{Punct}+")){
                for (int i = 0; i < temp.length(); i++)
                {   out = out.concat(temp.charAt(i) + " ");
//                    System.out.println("trying to split" + temp.charAt(i));
                }
            output = output.concat(out);                
        }
        
        else if (temp.matches("\\s") == false) {
             output = output.concat(temp + " ");
         }
     }
     
     output = output.trim();
       
     // enable to see temp formatting actions
     //System.out.println(output);
     
     String[] catcher = new String[output.split("\\s").length];
     catcher = output.split("\\s");
     
     catcher = DataTyper(catcher);
     
     return catcher;
    }
    
    public static String[] DataTyper(String[] tokens)
    {   String[] datatype = new String[tokens.length];
        String dtype;
        
        for(int i=0; i<tokens.length; i++){
            datatype[i] = tokenFilter(tokens[i]);
            // enable to show extracted tokens
            //System.out.println("hm " + tokens[i]);
        }
        /* Enable to show variablesto show 
        for(int i=0; i < datatype.length; i++){
               System.out.println(datatype[i]) ;
	}*/
       
    

        String new_tokens = "";
        
        // Captures cases like "+-3.2" a negative number with a decimal point.
        for(int i=0; i < (datatype.length); i++){
            if(i < datatype.length-4){
            if (!datatype[i].equals("Number") && datatype[i+1].equals("Arithmetic") && (tokens[i+1]).equals("-") && datatype[i+2].equals("Number") && (datatype[i+3]).equals("period") && (datatype[i+4]).equals("Number")){
                    new_tokens = new_tokens.concat(tokens[i]+ " " + tokens[i+1]+tokens[i+2]+tokens[i+3]+tokens[i+4]+" ");
                    // Enable to show consolidation actions
                    // System.out.println("Phase 1 -- Consolidated:" +tokens[i+1]+tokens[i+2]+tokens[i+3]+tokens[i+4]+" ");
                    i+=4;
                }
                else {
                    new_tokens = new_tokens.concat(tokens[i] + " ");
            }
            }
            else {
                    new_tokens = new_tokens.concat(tokens[i] + " ");
                }
        }
        // Enable to show consolidation results
        // System.out.println("Phase 1 - Tokens ("+ new_tokens.length() + "): " + new_tokens);
        tokens = new_tokens.split(" ");
        String[] datatype2 = new String[tokens.length];
                
        for(int i=0; i<tokens.length; i++){
            datatype2[i] = tokenFilter(tokens[i]);
        }
        new_tokens ="";
        
        
         // Captures cases like "3.5" a number with a decimal point.
        for(int i=0; i < (datatype2.length); i++){
            if(i < datatype2.length-2){
            if (datatype2[i].equals("Number") && (datatype2[i+1]).equals("period") && (datatype2[i+2]).equals("Number")){
                    new_tokens = new_tokens.concat(tokens[i]+ tokens[i+1] + tokens[i+2] + " ");
                    // Enable to show consolidation actions
                    // System.out.println("Phase 2 -- Consolidated:" +tokens[i]+tokens[i+1]+tokens[i+2]+" ");
                    i+=2;
                }
                else {
                    new_tokens = new_tokens.concat(tokens[i] + " ");
            }
            }
            else {
                    new_tokens = new_tokens.concat(tokens[i] + " ");
                }
        }
        // Enable to show consolidation results
        // System.out.println("Phase 2 - Tokens ("+ new_tokens.length() + "): " + new_tokens);
        
        tokens = new_tokens.split(" ");
        String[] datatype3 = new String[tokens.length];
                
        for(int i=0; i<tokens.length; i++){
            datatype3[i] = tokenFilter(tokens[i]);
        }
        new_tokens ="";

         // Captures cases like "-5" a negative number .
        for(int i=0; i < (datatype3.length); i++){
            if(i < datatype3.length-2){
                if (!datatype3[i].equals("Number") && (datatype3[i+1]).equals("Arithmetic") && (tokens[i+1]).equals("-") && (datatype3[i+2]).equals("Number")){
                    new_tokens = new_tokens.concat(tokens[i]+ " " + tokens[i+1] + tokens[i+2] + " ");
                    // Enable to show consolidation actions
                    // System.out.println("Phase 3 -- Consolidated:" + tokens[i]+" " + tokens[i+1]+tokens[i+2]+" ");
                    i+=2;
                }
                else {
                    new_tokens = new_tokens.concat(tokens[i] + " ");
            }
            }
            else {
                    new_tokens = new_tokens.concat(tokens[i] + " ");
                }
        }
        // Enable to show consolidation results
        // System.out.println("Phase 3 - Tokens ("+ new_tokens.length() + "): " + new_tokens);

        tokens = new_tokens.split(" ");
        String[] datatype4 = new String[tokens.length];
                
        for(int i=0; i<tokens.length; i++){
            datatype4[i] = tokenFilter(tokens[i]);
        }
        new_tokens ="";
        
        // Captures cases like ".5" a negative number .
        for(int i=0; i < (datatype4.length); i++){
            if(i < datatype4.length-2){
                if (!datatype4[i].equals("Number") && (datatype4[i+1]).equals("period") && (datatype4[i+2]).equals("Number")){
                    new_tokens = new_tokens.concat(tokens[i]+ " " + tokens[i+1] + tokens[i+2] + " ");
                    
                    // Enable to show consolidation actions
                    // System.out.println("Phase 4 -- Consolidated:" + tokens[i]+" " + tokens[i+1]+tokens[i+2]+" ");
                    i+=2;
                }
                else {
                    new_tokens = new_tokens.concat(tokens[i] + " ");
            }
            }
            else {
                    new_tokens = new_tokens.concat(tokens[i] + " ");
                }
        }
        // Enable to show consolidation results
        // System.out.println("Phase 4 - Tokens ("+ new_tokens.length() + "): " + new_tokens);

        tokens = new_tokens.split(" ");
                
        return tokens;
    }
    
    // This method determines what the datatypes are. modify carefully.
    // Be sure to update the other methods' conditional checks!!! (use "CTRL+F")
    public static String tokenFilter(String input)
    {       String result;
            
            //filter for digit
            if(input.matches("\\d+")){
                result = "Number";
            }
            else if(input.matches("-\\d+")){
                result = "Number";
            }

            else if(input.matches(".\\d+")){
                result = "Number";
            }
            else if(input.matches("\\d+.\\d+")){
                result = "Number";
            }

            else if(input.matches("-\\d+.\\d+")){
                result = "Number";
            }

                      
            else if(input.equals("=")){
                result = "Assignment";
            }

            else if(input.equals("==")){
                result = "Relational";
            }
            
            else if(input.equals("+")){
                result = "Arithmetic";
            }

            else if(input.equals("*")){
                result = "Arithmetic";
            }

            else if(input.equals("/")){
                result = "Arithmetic";
            }
            
            else if(input.equals(".")){
                result = "period";
            }
            
            else if(input.equals("-")){
                result = "Arithmetic";
            }
            
            else if(input.equals("<")){
                result = "Relational";
            }
            
            else if(input.equals(">")){
                result = "Relational";
            }
            
            else if(input.equals("'")){
                result = "Symbol";
            }
            
            else if(input.equals("\"")){
                result = "Symbol";
            }
            
            else if(input.equals(",")){
                result = "Symbol";
            }
            
            else if(input.equals(";")){
                result = "Symbol";
            }
            
            else if(input.equals("(")){
                result = "Symbol";
            }
            
            else if(input.equals(")")){
                result = "Symbol";
            }
            
            else if(input.equals("{")){
                result = "Symbol";
            }
            
            else if(input.equals("}")){
                result = "Symbol";
            }
            
            else if(input.matches("INT")){
                result = "Integer";
            }

            else if(input.matches("CHR")){
                result = "Character";
            }
            else if(input.matches("FLT")){
                result = "Float";
            }
            
            else if(input.matches("IF")){
                result = "If";
            }          

            else if(input.matches("IF")){
                result = "Else";
            }
            else if(input.matches("WHILE")){
                result = "While";
            }
            else if(input.matches("FUNC")){
                result = "Function";
            }
            else if(input.equals("")){
                result = "Empty";
            }
            
            else{
                result = "Varname";
            }
            
        return result;
    }
}