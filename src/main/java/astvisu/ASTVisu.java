package astvisu;

import spoon.Launcher;
import spoon.SpoonAPI;
import spoon.support.StandardEnvironment;

import java.io.*;

/**
 * Created by baudry on 18/10/16.
 */
public class ASTVisu {

    SpoonAPI spoon;

    public ASTVisu(){
        StandardEnvironment env = new StandardEnvironment();
        env.setAutoImports(true);
        env.setComplianceLevel(8);
        env.useTabulations(true);
        spoon = new Launcher();
    }

    public void run(String FileToPrint, String GraphFile) throws IOException {
        spoon.addInputResource(FileToPrint);
        spoon.buildModel();
        PrintWriter targetFile = new PrintWriter(new FileWriter(GraphFile));
        targetFile.write("digraph { \n");
        ASTVisuScanner scanner = new ASTVisuScanner(targetFile);
        scanner.scan(spoon.getFactory().Package().getRootPackage());
        targetFile.write("}");
        targetFile.close();
    }
}
