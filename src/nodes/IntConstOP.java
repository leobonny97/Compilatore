package nodes;

import org.w3c.dom.Element;
import parse.CBuilder;
import parse.SemanticVisitor;
import parse.TypeChecker;
import parse.XMLBuilder;

public class IntConstOP implements Expr,Visitable{

    private int int_const;
    private String nodeType = TypeChecker.INT;

    public IntConstOP(int int_const) {
        this.int_const = int_const;
    }

    @Override
    public Element accept(Visitor visitor) {
        if(visitor instanceof XMLBuilder) {
            return ((XMLBuilder)visitor).visit(this);
        }
        if(visitor instanceof SemanticVisitor) {
            return ((SemanticVisitor)visitor).visit(this);
        }
        if(visitor instanceof CBuilder) {
            return ((CBuilder)visitor).visit(this);
        }
        return null;
    }

    public int getInt_const() {
        return int_const;
    }

    public void setInt_const(int int_const) {
        this.int_const = int_const;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    @Override
    public String toString() {
        return "IntConstOP [int=" + int_const  + ", nodeType=" + nodeType + "]";
    }
}
