/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler_design_logic;

/**
 *
 * @author Jullian
 */

/*
*  Lexer.java
*
*  Created on Feb 11, 2013 8:06:48 PM 
*  Licensed to:
*               Jullian Robin Sibi
*               B. Carla Yap
*               Zita Mary Enriquez
*               Jonie Francis Tan
*               Clyde Aldrich Elarcosa
*               Satchel Samantha Magsambol
*               Jullian Robin Sibi
*
*  for more information, please contact
*  Sibi: Pineda??
*  Yap: bcarlayap@ymail.com
*  Enriquez: zitame@gmail.com
*  Tan: Tan??
*  Elarcosa: Pestano??
*  Magsambol: satchelmags@gmail.com
*  
*/


import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author B. Carla Yap
 */
public class AssignmentLexer {
String datatype="ready";
String token="stop";
String check_char_value;
String varname;
boolean remarks=true;
int braceCount=0;

List<String> names, tokens;

    AssignmentLexer(){
        names=new ArrayList<String>();
        tokens=new ArrayList<String>();
    }
    
    
    public void assignment(StreamTokenizer stream) throws IOException
  {
    while(stream.nextToken()!= StreamTokenizer.TT_EOF && remarks!=false )
    {
       System.out.println(stream.toString());
       check_char_value=stream.sval;
       
        switch(stream.ttype){
            case StreamTokenizer.TT_WORD:{ 
                
                switch(stream.sval)
                {
                    case "int": checkAssignment("integer");
                    break;
                    case "float": checkAssignment("float"); 
                    break;
                    case "char": checkAssignment("character"); 
                    break;
                    case "str": checkAssignment("string"); 
                    break;
                    case "if": checkAssignment("if"); 
                    break;
                    default:
                    { 
                        checkAssignment("name");                      
                    }
                    break; 
                }              
            break;  
            }
            case StreamTokenizer.TT_NUMBER:{
                checkAssignment("number");
                break;
            }    
            case 61: checkAssignment("equals"); break; 
            case 43: checkAssignment("add"); break; 
            case 45: checkAssignment("subtract"); break; 
            case 60: checkAssignment("less-than");break;
            case 62: checkAssignment("greater-than");break;
            
            case 39: {
               check_char_value=stream.sval;
                checkAssignment("apostrophe"); break; 
            
            }
            case 34: {
                check_char_value=stream.sval;
                 checkAssignment("double-apostrophe"); break; 
             
             } 
            case 44: checkAssignment("comma"); break;           
            case 59: checkAssignment("semi-colon"); break;
            case 40: checkAssignment("open-parenthesis"); break;
            case 41: checkAssignment("close-parenthesis"); break;
            case 123: checkAssignment("open-brace"); break;
            case 125: checkAssignment("close-brace"); break;
            case 10: checkAssignment("new-line"); break;
                      
                
        }
  }
    if(remarks ==true && token.equals("stop"))
        System.out.println("compiled!!");
    else
    {
        remarks=false;
        System.out.println("error!");
    }
   
}
  
 private void checkAssignment(String process){
 
     switch(process){
         
         case "integer":{
                System.out.println(process); 
             if(token.equals("stop"))
             {  
                datatype=process; 
                token="start";
             }else
                remarks=false;
         
         break;
         }
         case "float":{
             System.out.println(process); 
             if(token.equals("stop"))
             {  
                datatype=process; 
                token="start";
             }else
                remarks=false;
             
	      break;
	      }
         case "character":{ 
                System.out.println(process);
             if(datatype.equals("ready"))
             {   
                datatype=process; 
                token="start";
             }else
                remarks=false;
         
         break;
         }
         case "string":{ 
             System.out.println(process);
          if(datatype.equals("ready"))
          {   
             datatype=process; 
             token="start";
          }else
             remarks=false;
      
      break;
      }
         case "if":{ 
             System.out.println(process);
          if(token.equals("stop"))  
             token=process;
          else
             remarks=false;
      
      break;
      }
         case "name":{   
                System.out.println(process);
            if(tokens.get(tokens.size()-1).equals("equals")){
               	 boolean isMember = false;
               	 Iterator<String> iterator = names.iterator();
               	 while(iterator.hasNext() && !isMember) {
               		 if(iterator.next().equals(check_char_value))
               			 isMember = true;
               		System.out.println(""+isMember);
               	 }
               	 if(!isMember)
               		 remarks=false;
             }
             if(token.equals("start") || token.equals("equals") ||
                     token.equals("comma") || token.equals("open-parenthesis") || token.equals("less-than"))
                token=process; 
             else if(token.equals("apostrophe")){
                 if(check_char_value.length()<2)
                     token=process;
             }
             else	 
                remarks=false;
             names.add(check_char_value);
         break;
         }  
         case "equals":{ 
                System.out.println(process);
             if(token.equals("name"))
                token=process;
             else
                remarks=false; 
         break;
         }
         case "add": {
        	 if(token.equals("value"))
        		 token="arithmetic";
        	 else
        		 remarks=false;
        	 break;
         }
         case "subtract": {
        	 if(token.equals("value"))
        		 token="arithmetic";
        	 else
        		 remarks=false;
        	 break;
         }
         case "semi-colon":{
                System.out.println(process); 
             if(token.equals("value") || token.equals("name")||
                     token.equals("apostrophe") || token.equals("double-apostrophe"))
             {
                datatype="ready"; 
                token="stop";
             }else
                 remarks=false;
         break;
         }
         case "number":{ 
                System.out.println(process);
             if((datatype.equals("integer") && token.equals("equals")) || token.equals("arithmetic")
            		 || token.equals("less-than") || token.equals("greater-than"))
                token="value";
             else if((datatype.equals("float") && token.equals("equals")) || token.equals("arithmetic")
            		 || token.equals("less-than") || token.equals("greater-than"))
                 token="value";
             else
                 remarks=false;
         break;
         }
         case "less-than": {
        	 if(token.equals("name"))
        		 token=process;
        	 else
        		 remarks=false;
        	 break;
         }
         case "greater-than": {
        	 if(token.equals("name"))
        		 token=process;
        	 else
        		 remarks=false;
        	 break;
         }
         case "comma":{ 
                System.out.println(process);
             if(token.equals("value") || token.equals("name"))
                token=process;
             else
                 remarks=false;
         break;
         }
         
         case "apostrophe":{ 
                System.out.println(process);
                   
              if(datatype.equals("character") && token.equals("equals") ){
                 token=process;
                  
                  if(check_char_value.length()>=2)
                     remarks=false;
                
                  System.out.println("length is:"+check_char_value.length());
                  System.out.println("value is:"+check_char_value);
                }
              else
                  remarks=false;
             
            break;
         }
         case "double-apostrophe":{ 
             System.out.println(process);
                
           if(datatype.equals("string") && token.equals("equals"))
              token=process;
           else
        	   remarks=false;

         break;
      }
         case "open-parenthesis": {
        	 if(token.equals("if") || token.equals("add"))
        		 token=process;
        	 else
        		 remarks=false;
        	 break;
         }
         case "close-parenthesis": {
        	 if(token.equals("name") || token.equals("value"))
        		 token=process;
        	 else
        		 remarks=false;
        	 break;
         }
         case "open-brace": {
        	 if(token.equals("close-parenthesis")){
        		 token=process;
        		 braceCount++;
        	 }
        	 else
        		 remarks=false;
        	 break;
         }
         case "close-brace": {
        	 if(braceCount == 1 || (braceCount%2 == 1)){
        		 token="stop";
        		 braceCount--;
        	 }else
        		 remarks=false;
        	 break;
         }
         case "new-line":{
             System.out.println("\n"); 
	         tokens.clear();
	      break;
	      }
         
     }
     tokens.add(process);
 }
      

 }
