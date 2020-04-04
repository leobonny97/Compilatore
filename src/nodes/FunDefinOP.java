package nodes;

import org.w3c.dom.Element;
import parse.CBuilder;
import parse.SemanticVisitor;
import parse.SymbolTable;
import parse.XMLBuilder;

import java.util.ArrayList;

public class FunDefinOP implements Visitable{

    private Identifier id;
    private ArrayList<ParDeclOP> parDeclOPS = new ArrayList<>();
    private BodyOP body;
    private String nodeType;
    private SymbolTable symbolTable;

    public FunDefinOP(Identifier id, BodyOP body) {
        this.id = id;
        this.body = body;
    }

    public FunDefinOP(Identifier id, ArrayList<ParDeclOP> parDeclOPS, BodyOP body) {
        this.id = id;
        this.parDeclOPS = parDeclOPS;
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

    public Identifier getId() {
        return id;
    }

    public void setId(Identifier id) {
        this.id = id;
    }

    public ArrayList<ParDeclOP> getParDeclOPS() {
        return parDeclOPS;
    }

    public void setParDeclOPS(ArrayList<ParDeclOP> parDeclOPS) {
        this.parDeclOPS = parDeclOPS;
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

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    @Override
    public String toString() {
        return "FunDefinOP [id="+ id + ", body=" + body + ", nodeType=" + nodeType + "]";
    }
}
