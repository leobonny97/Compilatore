package nodes;

import org.w3c.dom.Element;
import parse.CBuilder;
import parse.SemanticVisitor;
import parse.XMLBuilder;

import java.util.ArrayList;

public class BodyOP implements Visitable{

    private ArrayList<Stat> stat = new ArrayList<>();
    private String nodeType;

    public BodyOP(ArrayList<Stat> stat) {
        this.stat = stat;
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

    public ArrayList<Stat> getStat() {
        return stat;
    }

    public void setStat(ArrayList<Stat> stat) {
        this.stat = stat;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    @Override
    public String toString() {
        return "BodyOP [stat=" + stat + ", nodeType=" + nodeType + "]";
    }
}
