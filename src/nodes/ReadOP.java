package nodes;

import org.w3c.dom.Element;
import parse.CBuilder;
import parse.SemanticVisitor;
import parse.XMLBuilder;

import java.util.ArrayList;

public class ReadOP implements Visitable,Stat{
    private ArrayList<Identifier> identifiers;
    private String nodeType;

    public ReadOP(ArrayList<Identifier> identifiers) {
        this.identifiers = identifiers;
    }

    public ArrayList<Identifier> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(ArrayList<Identifier> identifiers) {
        this.identifiers = identifiers;
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
        return "ReadOP [identifiers=" + identifiers + "]";
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
