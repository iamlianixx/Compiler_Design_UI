
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




package compilerui;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;

/**
 * 
 * @author B. Carla Yap
 */
public class AssignmentLexer {
String datatype_assignment="assignment";
String assignment="stop";
String check_char_value;
boolean remarks=true;
    AssignmentLexer(){}
    
    
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
                    case "char": checkAssignment("character"); 
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
            case 39: {
               check_char_value=stream.sval;
                checkAssignment("apostrophe"); break; 
            
            }          
            case 44: checkAssignment("comma"); break;           
            case 59: checkAssignment("semi-colon"); break;   
                      
                
        }
  }
    if(remarks ==true && assignment.equals("stop"))
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
             if(assignment.equals("stop"))
             {  
                datatype_assignment=process; 
                assignment="start";
             }else
                remarks=false;
         
         break;
         }
         case "character":{ 
                System.out.println(process);
             if(datatype_assignment.equals("assignment"))
             {   
                datatype_assignment=process; 
                assignment="start";
             }else
                remarks=false;
         
         break;
         }
         case "name":{   
                System.out.println(process);         
             if(assignment.equals("start") || assignment.equals("equals") ||
                     assignment.equals("comma"))
                assignment=process; 
             else if(assignment.equals("apostrophe")){
                 if(check_char_value.length()<2)
                     assignment=process;
             }
             else
                remarks=false;
         break;
         }  
         case "equals":{ 
                System.out.println(process);
             if(assignment.equals("name"))
                assignment=process;
             else
                remarks=false; 
         break;
         }
         case "semi-colon":{
                System.out.println(process); 
             if(assignment.equals("value") || assignment.equals("name")||
                     assignment.equals("apostrophe"))
             {
                datatype_assignment="assignmnent"; 
                assignment="stop";
             }else
                 remarks=false;
         break;
         }
         case "number":{ 
                System.out.println(process);
             if(datatype_assignment.equals("integer") && assignment.equals("equals"))
                assignment="value";
             else
                 remarks=false;
         break;
         }
         case "comma":{ 
                System.out.println(process);
             if(assignment.equals("value") || assignment.equals("name"))
                assignment=process;
             else
                 remarks=false;
         break;
         }
         
         case "apostrophe":{ 
                System.out.println(process);
                   
              if(datatype_assignment.equals("character") &&assignment.equals("equals") ){
                 assignment=process;
                  
                  if(check_char_value.length()>=2)
                     remarks=false;
                
                  System.out.println("length is:"+check_char_value.length());
                  System.out.println("value is:"+check_char_value);
                }
              else
                  remarks=false;
             
            break;
         }
         
     }
 }
      

 }
