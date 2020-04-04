package nodes;

import org.w3c.dom.Element;
import parse.CBuilder;
import parse.SemanticVisitor;
import parse.TypeChecker;
import parse.XMLBuilder;

public class BooleanConst implements Expr,Visitable{

    private boolean boolean_const;
    private String nodeType = TypeChecker.BOOL;

    public BooleanConst(boolean boolean_const) {
        this.boolean_const = boolean_const;
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

    public boolean isBoolean_const() {
        return boolean_const;
    }

    public void setBoolean_const(boolean boolean_const) {
        this.boolean_const = boolean_const;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    @Override
    public String toString() {
        return "BooleanConst [boolean=" + boolean_const  + ", nodeType=" + nodeType + "]";
    }
}
