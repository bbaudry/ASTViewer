package astvisu;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SimpleList {

    private int[] tab = new int[10];
    private int current = -1;

    public void iterate() throws IOException {
        PrintWriter targetFile = new PrintWriter(new FileWriter("./tmp.txt"));
        for(int i=0;i<tab.length;i++){
            targetFile.write(tab[i]+"\n");
            if (tab[i]==0){
                targetFile.write("Nothing here \n");
            }
        }
        targetFile.close();
    }
}
