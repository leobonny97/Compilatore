package nodes;

import org.w3c.dom.Element;
import parse.CBuilder;
import parse.SemanticVisitor;
import parse.XMLBuilder;

import java.util.ArrayList;

public class WriteOP implements Visitable,Stat{
    private ArrayList<Expr> exprs;
    private String nodeType;

    public WriteOP(ArrayList<Expr> exprs) {
        this.exprs = exprs;
    }

    public ArrayList<Expr> getExprs() {
        return exprs;
    }

    public void setExprs(ArrayList<Expr> exprs) {
        this.exprs = exprs;
    }

    @Override
    public String getNodeType() {
        return nodeType;
    }

    @Override
    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String toString() {
        return "WriteOP [exprs=" + exprs + "]";
    }

    @Override
    public Element accept(Visitor visitor) {
        if (visitor instanceof XMLBuilder) {
            return ((XMLBuilder) visitor).visit(this);
        }
        if(visitor instanceof SemanticVisitor) {
            return ((SemanticVisitor)visitor).visit(this);
        }
        if(visitor instanceof CBuilder) {
            return ((CBuilder)visitor).visit(this);
        }
        return null;
    }
}
