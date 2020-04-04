package nodes;

import org.w3c.dom.Element;
import parse.CBuilder;
import parse.SemanticVisitor;
import parse.XMLBuilder;

public class ReturnOP implements Visitable,Stat{
    private Expr expr1;
    private String nodeType;

    public ReturnOP(Expr expr1) {
        this.expr1 = expr1;
    }

    public Expr getExpr1() {
        return expr1;
    }

    public void setExpr1(Expr expr1) {
        this.expr1 = expr1;
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
        return "ReturnOP [expr1=" + expr1 + "]";
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
