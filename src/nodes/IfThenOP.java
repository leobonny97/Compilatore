package nodes;

import org.w3c.dom.Element;
import parse.CBuilder;
import parse.SemanticVisitor;
import parse.XMLBuilder;

public class IfThenOP implements Stat,Visitable{

    private Expr expr;
    private BodyOP body;
    private String nodeType;

    public IfThenOP(Expr expr, BodyOP body) {
        this.expr = expr;
        this.body = body;
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

    public BodyOP getBody() {
        return body;
    }

    public void setBody(BodyOP body) {
        this.body = body;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    @Override
    public String toString() {
        return "IfThenOP [expr="+ expr +"body=" + body + ", nodeType=" + nodeType + "]";
    }
}
