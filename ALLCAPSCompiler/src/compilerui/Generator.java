/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package compilerui;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 *
 * @author B. Carla Yap
 */
public class Generator {

    /**
     * @param args the command line arguments
     */
    
    private ArrayList<Token> tokenList;
    private String writeCode="public class NewClass{\n\n";
    private String tempHolder;
    private boolean callPrint=false;
    
    public Generator(ArrayList<Token> list){
        this.tokenList =list;
    }
    
    public void convertCode2Java(){
        for(int traverse=0; traverse<tokenList.size(); traverse++){
            tempHolder=tokenList.get(traverse).getToken();
            
            //get main
            if(tempHolder.equals("MAIN")){
                writeCode+="public static void main(String[] args)";
            }
            
            // if it's an id.
            else if(tempHolder.equals("var_id") || tempHolder.equals("char_id") || 
                    tempHolder.equals("num_id") || tempHolder.equals("string_id"))
            {
                writeCode+=tokenList.get(traverse).getInfo();
            }
            
            //keywords
            else if(tempHolder.equals("IF") || tempHolder.equals("WHILE") || 
                    tempHolder.equals("ELSE") || tempHolder.equals("RETURN")
                    || tempHolder.equals("VOID")){
                writeCode+=tempHolder.toLowerCase();
            }
            
            else if(tempHolder.equals("PRINT"))
            {writeCode+="System.out.print";  
                callPrint=true;
            }
           
            //datatypes
            else if(tempHolder.equals("INT") || tempHolder.equals("CHR") ||
                    tempHolder.equals("FLT") || tempHolder.equals("STR"))
            {
       	String check = tokenList.get(++traverse).getToken();
                  if(check.equals("FUNC"))
                    	writeCode+=" public static ";
                  else if(check.equals("VAR")) //if VAR
                    writeCode+=" ";  
               
                  switch (tempHolder) {
                    case "CHR":
                        writeCode+="char ";
                        break;
                    case "INT":
                        writeCode+=tempHolder.toLowerCase()+" ";
                        break;
                    case "FLT":
                        writeCode+="float ";
                        break;
                    case "STR":
                        writeCode+="String ";
                        break;
                }
            }
           //logical, relational
            else if(tempHolder.equals("NOT="))
                writeCode+="!=";
            else if(tempHolder.equals("AND"))
                writeCode+="&&";
            else if(tempHolder.equals("OR"))
                writeCode+="||";
            
           //stoppers and open braces
           else if(tempHolder.equals("}") || tempHolder.equals(";") || tempHolder.equals("{"))
              writeCode+=tempHolder+"\n"+"\t";  
           else if(tempHolder.equals(")"))
           {   if(callPrint==true)
                 writeCode+="+ \" \\n \" )";
                 
           }
           else
              writeCode+=tempHolder;  
        }
        writeCode+="\n}";
       writeToFile();
    }
    
    public String  displayJavaCode(){
    return writeCode;
    }
    
   private void writeToFile(){
       FileWriter fileWriter=null;
       try{
           File codeFile=new File("NewClass.java");
           fileWriter = new FileWriter(codeFile);
           fileWriter.write(writeCode);
           fileWriter.close();
       }catch(IOException ex){
       Logger.getLogger(Generator.class.getName()).
               log(Level.SEVERE,null,ex);
       }finally{
           try{
               fileWriter.close();
           } catch(IOException ex){
               Logger.getLogger(Generator.class.getName()).
               log(Level.SEVERE,null,ex);
       
           }
       }
   }
   
   public void compileFile(){
//       JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
//       if(compiler.run(null, null, null, "NewClass.java")!=0){
//           System.out.print("ERROR");
//       }
       try{
           Runtime rt =Runtime.getRuntime();
           Process pr= rt.exec("javac NewClass.java");
           BufferedReader input= new BufferedReader(new InputStreamReader(pr.getInputStream()));
           String line=null;
          
           while(input.readLine()!=null){
           System.out.println(input.readLine());
           }
       }catch(Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
       }
   }
 }