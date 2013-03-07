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



public class ParseArray {
    private ArrayList<ParseEntry> parseArray;
    private int lastIndex, programIndex;
    
       public ParseArray(){
           parseArray = new ArrayList<>();
           lastIndex = -1;
           programIndex = -1;
       }
       
       public int getLastIndex(){
           return this.lastIndex;
       }
       
       public void insertParseEntry(ParseEntry given){
           this.parseArray.add(given);
           lastIndex++;
           if(given.getToken().equals("<program>"))
               programIndex = lastIndex;
       }
       
       public ParseEntry retrieveEntry(String given){
           ParseEntry temp = null;
           boolean check=false;
           for(int i=0; check == false && i<lastIndex; i++){
               if(this.parseArray.get(i).getToken().equals(given))
                   temp = this.parseArray.get(i);
           }
           return temp;
       }
       
       public ParseEntry retrieveEntryWithIndex(int given){
           return this.parseArray.get(given);
       }

       
       
}
