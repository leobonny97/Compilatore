package nodes;

import org.w3c.dom.Element;
import parse.CBuilder;
import parse.SemanticVisitor;
import parse.XMLBuilder;

public class ParDeclOP implements Visitable{
    private Identifier identifier;
    private String nodeType;

    public ParDeclOP(Identifier identifier, String nodeType) {
        this.identifier = identifier;
        this.nodeType = nodeType;
    }

    public ParDeclOP(Identifier identifier) {
        this.identifier = identifier;
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
        return "VarDeclOP [identifier=" + identifier + ", nodeType=" + nodeType + "]";
    }
}
