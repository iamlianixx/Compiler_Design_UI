/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler_design_logic;

/**
 *
 * @author Jullian
 */

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;

/**
 *
 * @author B. Carla Yap
 */
public class Compiler_Design_Logic {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
       
        //try this:
       // Reader given = new StringReader("char x='a',y;");
        
      //  Reader given = new StringReader("int x='a',y;");
        
       // Reader given = new StringReader("char x='a',y");
        
        Reader given = new StringReader("int a=3-5;");
        AssignmentLexer lex = new AssignmentLexer();
        lex.assignment(new StreamTokenizer(given));
    
    }
}

