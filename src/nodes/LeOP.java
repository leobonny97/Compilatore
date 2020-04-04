package nodes;

import parse.*;
import org.w3c.dom.Element;

public class LeOP implements Visitable, Expr{
    private Expr expr1;
    private Expr expr2;
    private String nodeType;

    public LeOP(Expr expr1, Expr expr2, String nodeType) {
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.nodeType = nodeType;
    }

    public LeOP(Expr expr1, Expr expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    public Expr getExpr1() {
        return expr1;
    }

    public Expr getExpr2() {
        return expr2;
    }

    public void setExpr1(Expr expr1) {
        this.expr1 = expr1;
    }

    public void setExpr2(Expr expr2) {
        this.expr2 = expr2;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    @Override
    public String toString() {
        return "LeOP [expr1=" + expr1 + ", expr2=" + expr2 + ", nodeType=" + nodeType + "]";
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
}
