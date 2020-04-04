package nodes;

import org.w3c.dom.Element;
import parse.CBuilder;
import parse.SemanticVisitor;
import parse.XMLBuilder;

public class IfThenElseOP implements Stat,Visitable{

    private Expr expr;
    private BodyOP body1;
    private BodyOP body2;
    private String nodeType;

    public IfThenElseOP(Expr expr, BodyOP body1, BodyOP body2) {
        this.expr = expr;
        this.body1 = body1;
        this.body2 = body2;
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

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }

    public BodyOP getBody1() {
        return body1;
    }

    public void setBody1(BodyOP body1) {
        this.body1 = body1;
    }

    public BodyOP getBody2() {
        return body2;
    }

    public void setBody2(BodyOP body2) {
        this.body2 = body2;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    @Override
    public String toString() {
        return "IfThenElseOP [expr="+ expr +"body1=" + body1 + ", body2=" + body2 + ", nodeType=" + nodeType + "]";
    }
}
