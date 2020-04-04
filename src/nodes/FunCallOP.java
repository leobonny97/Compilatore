package nodes;

import org.w3c.dom.Element;
import parse.CBuilder;
import parse.SemanticVisitor;
import parse.XMLBuilder;

import java.util.ArrayList;

public class FunCallOP implements Stat,Expr,Visitable{

    private Identifier id;
    private ArrayList<Expr> exprs = new ArrayList<>();
    private String nodeType;

    public FunCallOP(Identifier id) {
        this.id = id;
    }

    public FunCallOP(Identifier id, ArrayList<Expr> exprs) {
        this.id = id;
        this.exprs = exprs;
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

    public Identifier getId() {
        return id;
    }

    public void setId(Identifier id) {
        this.id = id;
    }

    public ArrayList<Expr> getExprs() {
        return exprs;
    }

    public void setExprs(ArrayList<Expr> exprs) {
        this.exprs = exprs;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    @Override
    public String toString() {
        return "FunCallOP [" + id + ", "+ exprs + ", nodeType=" + nodeType + "]";
    }
}
