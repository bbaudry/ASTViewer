import astvisu.ASTVisu;

/**
 * Created by baudry on 18/10/16.
 */
public class ASTVisuTest {
    ASTVisu vizualizer = new ASTVisu();
    @org.junit.Test
    public void run() throws Exception {
        vizualizer.run("src/main/java/astvisu/SimpleList.java","tmpGraph.dot");
    }

}