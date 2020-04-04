package nodes;

import org.w3c.dom.Element;
import parse.CBuilder;
import parse.SemanticVisitor;
import parse.XMLBuilder;

public class VarDeclOP implements Visitable{

    private Identifier identifier;
    private String nodeType;
    private Expr expr1;

    public VarDeclOP(Identifier identifier, String nodeType, Expr expr1) {
        this.identifier = identifier;
        this.nodeType = nodeType;
        this.expr1 = expr1;
    }

    public VarDeclOP(Identifier identifier, Expr expr1) {
        this.identifier = identifier;
        this.expr1 = expr1;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public Expr getExpr1() {
        return expr1;
    }

    public void setExpr1(Expr expr1) {
        this.expr1 = expr1;
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
        return "VarDeclOP [identifier=" + identifier + ", expr1=" + expr1 + "]";
    }
}
