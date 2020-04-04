package nodes;

import org.w3c.dom.Element;
import parse.CBuilder;
import parse.SemanticVisitor;
import parse.TypeChecker;
import parse.XMLBuilder;

public class StringConstOP implements Visitable,Expr{
    private String stringConst;
    private String nodeType = TypeChecker.STRING;

    public StringConstOP(String stringConst) {
        this.stringConst = stringConst;
    }

    public String getStringConst() {
        return stringConst;
    }

    public void setStringConst(String stringConst) {
        this.stringConst = stringConst;
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
    public String toString() {
        return "StringConstOP";
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
