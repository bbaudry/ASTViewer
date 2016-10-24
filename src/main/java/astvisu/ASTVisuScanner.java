package astvisu;

import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.visitor.CtScanner;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by baudry on 18/10/16.
 */
public class ASTVisuScanner extends CtScanner {

    private PrintWriter outputFile;
    private Stack<CtElement> stack;
    private Map<CtElement,Integer> counter;
    private int count;

    public ASTVisuScanner(PrintWriter file){
        outputFile = file;
        stack = new Stack<CtElement>();
        counter = new HashMap<CtElement, Integer>();
    }


    @Override
    protected void enter(CtElement e) {
        if(!counter.containsKey(e)) {
            counter.put(e, count++);
        }
        outputFile.write(counter.get(e)+" [label=\""+e.getClass().getSimpleName()+"\"];\n");
        if (! stack.isEmpty()){
            outputFile.write(counter.get(stack.peek())+" -> "+counter.get(e)+";\n");
        }
        stack.push(e);
        super.enter(e);
    }

    @Override
    public <T> void visitCtMethod(CtMethod<T> m) {
        if(!counter.containsKey(m)) {
            counter.put(m, count++);
        }
        outputFile.write(counter.get(m)+" [label=\""+m.getSimpleName()+"\"];\n");
        if (! stack.isEmpty()){
            outputFile.write(counter.get(stack.peek())+" -> "+counter.get(m)+";\n");
        }
        stack.push(m);
        this.scan((Collection)m.getAnnotations());
        this.scan((Collection)m.getFormalCtTypeParameters());
        this.scan((CtElement)m.getType());
        this.scan((Collection)m.getParameters());
        this.scan((Collection)m.getThrownTypes());
        this.scan((CtElement)m.getBody());
        this.scan((Collection)m.getComments());
        this.exit(m);
    }

    @Override
    protected void exit(CtElement e) {
        stack.pop();
        super.exit(e);
    }
}
