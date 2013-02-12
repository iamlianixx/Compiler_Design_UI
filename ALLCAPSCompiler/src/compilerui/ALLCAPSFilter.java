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
 import java.io.File;
 import java.util.*;
 import javax.swing.filechooser.FileFilter;

public class ALLCAPSFilter extends FileFilter{

    @Override
    public boolean accept(File f) {
        if(f.isDirectory()) return false;
        String s = f.getName();
        return s.endsWith(".ALLCAPS")|| s.endsWith(".allcaps");
    }

    @Override
    public String getDescription() {
        return "*.allcaps,*.ALLCAPS";
    }
    
}
