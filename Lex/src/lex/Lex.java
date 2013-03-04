package lex;

/**
 *
 * @author oni
 */
public class Lex {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /*  Insert File Chooser and string extractor code here, the following is 
         * line is used to emulate a successful extraction and creation of an 
         * array containing a string of characters.        * 
         *       
         * INPUT MUST BE A STRING ARRAY!! WITH KNOWN # OF LENGTH
         */
        
//      String[] input = {"a=-3--3.5","{int firstnum = 2; {int secondnum = 5;}","int }into arigato=-3-.9-5--55.6);+- == {0.83}{(}.8B.6u/sS+$cars-car$"};
        String[] input = {"start{","IF(hello){ a=-3--3.5; }","}"}; //Sample String Array Input 
        
      LexArray Run = new LexArray();
      LinkList[] LexOutput = Run.main(input); 
    }
}
