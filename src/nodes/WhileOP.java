package nodes;

import org.w3c.dom.Element;
import parse.CBuilder;
import parse.SemanticVisitor;
import parse.XMLBuilder;

public class WhileOP implements Visitable,Stat{
    private Expr expr1;
    private BodyOP bodyOP;
    private String nodeType;

    public WhileOP(Expr expr1, BodyOP bodyOP) {
        this.expr1 = expr1;
        this.bodyOP = bodyOP;
    }

    public Expr getExpr1() {
        return expr1;
    }

    public void setExpr1(Expr expr1) {
        this.expr1 = expr1;
    }

    public BodyOP getBodyOP() {
        return bodyOP;
    }

    public void setBodyOP(BodyOP bodyOP) {
        this.bodyOP = bodyOP;
    }

    @Override
    public String getNodeType() {
        return nodeType;
    }

    @Override
    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
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

    @Override
    public String toString() {
        return "WhileOP [expr1=" + expr1 + ", bodyOP=" + bodyOP + "]";
    }
}
