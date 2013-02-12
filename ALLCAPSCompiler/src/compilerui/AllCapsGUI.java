/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package compilerui;

/**
 *
 * @author Jullian
 */
import java.io.*;
import java.io.File.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.LineNumbersTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AllCapsGUI extends javax.swing.JFrame {

    /**
     * Creates new form AllCapsGUI
     */
    public AllCapsGUI() {
        initComponents();
        chooser = new JFileChooser("C:/");
        allCapsFilter = new ALLCAPSFilter();
        lineNums = new TextLineNumber(editorTextArea);
        jScrollPane2.setRowHeaderView(lineNums);
      //  chooser.setAcceptAllFileFilterUsed(true);
        chooser.setFileFilter(allCapsFilter);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        editorTextArea = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newMenuItem = new javax.swing.JMenuItem();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        compileMenu = new javax.swing.JMenu();
        compileMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ALLCAPS Compiler");

        editorTextArea.setColumns(20);
        editorTextArea.setRows(5);
        jScrollPane2.setViewportView(editorTextArea);

        fileMenu.setText("File");

        newMenuItem.setText("New");
        newMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(newMenuItem);

        openMenuItem.setText("Open");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openMenuItem);

        saveMenuItem.setText("Save");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setText("Save As..");
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        jMenuBar1.add(fileMenu);

        compileMenu.setText("Compile");

        compileMenuItem.setText("Compile..");
        compileMenuItem.setText("Compile");

        compileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compileMenuItemActionPerformed(evt);
            }
        });
        compileMenu.add(compileMenuItem);

        jMenuBar1.add(compileMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newMenuItemActionPerformed
        // TODO add your handling code here:
        JFileChooser openFile = new JFileChooser();
        
    }//GEN-LAST:event_newMenuItemActionPerformed

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        // TODO add your handling code here:
        try{
            File selectedFile;//needed*
            BufferedReader in;//needed*
            FileReader reader = null;//needed*,look these three up for further info
   
            //opens dialog if button clicked
            if (chooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION)
            {
            //gets file from dialog
            selectedFile = chooser.getSelectedFile();
            //makes sure files can be processed before proceeding
                if (selectedFile.canRead() && selectedFile.exists())
                {
                    reader = new FileReader(selectedFile);
                    this.setTitle(this.getTitle() + " - " + selectedFile.getName());
                }
            }

        in = new BufferedReader(reader);

            //inputLine recieves file text
            String inputLine = in.readLine();
            int LineNumber = 0;

            editorTextArea.setText("");

            while(inputLine!=null)
            {
            //LineNumber isn't needed, but it adds a line count on the left, nice
            LineNumber++;
            StringTokenizer tokenizer = new StringTokenizer(inputLine);


            addText(inputLine + "\n");
            //next line in File opened
            inputLine = in.readLine();
            }
            //close stream, files stops loading
            in.close();
        }
        //catches input/output exceptions and all subclasses exceptions
        catch(IOException ex)
        {
            addText("Error occured :\n" + ex.getMessage()+"\n");
        }
        catch(NullPointerException ne){
            ne.printStackTrace();
        }
    }//GEN-LAST:event_openMenuItemActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void addText(String text)
    {
        editorTextArea.setText(editorTextArea.getText() + text);
        
    }    
    
    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        // TODO add your handling code here:
        try
  {
      
    // These are generic variable names.. From some old tutorial :)
    
    File selectedFile;
    BufferedReader in;
    FileWriter writer = null;
   
    
    if (chooser.showSaveDialog(this)==JFileChooser.APPROVE_OPTION)
    {
      selectedFile = chooser.getSelectedFile();
      this.setTitle("ALLCAPS Compiler - " + selectedFile.getName());
       BufferedWriter out = new BufferedWriter(new FileWriter(selectedFile));
        out.write(editorTextArea.getText());
         out.close();
      
    }
   
  
    
  }
  //catches input/output exceptions and all subclasses exceptions
  catch(IOException ex)
  {
    addText("Error occured :\n" + ex.getMessage()+"\n");
  }
  //catches nullpointer exception, file not found
  catch(NullPointerException ex)
  {
    ex.printStackTrace();
  }
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void compileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compileMenuItemActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_compileMenuItemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AllCapsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AllCapsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AllCapsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AllCapsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new AllCapsGUI().setVisible(true);
            }
        });
    }
    private TextLineNumber lineNums;
    private final JFileChooser chooser;
    private final ALLCAPSFilter allCapsFilter;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu compileMenu;
    private javax.swing.JMenuItem compileMenuItem;
    private javax.swing.JTextArea editorTextArea;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem newMenuItem;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    // End of variables declaration//GEN-END:variables
}
