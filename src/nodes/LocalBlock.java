package nodes;

import org.w3c.dom.Element;
import parse.CBuilder;
import parse.SemanticVisitor;
import parse.SymbolTable;
import parse.XMLBuilder;

import java.util.ArrayList;

public class LocalBlock implements Visitable,Stat{
    private ArrayList<VarDeclOP> varDeclOPS;
    private BodyOP bodyOP;
    private String nodeType;
    private SymbolTable symbolTable;


    public LocalBlock(ArrayList<VarDeclOP> varDeclOPS, BodyOP bodyOP) {
        this.varDeclOPS = varDeclOPS;
        this.bodyOP = bodyOP;
    }

    public ArrayList<VarDeclOP> getVarDeclOPS() {
        return varDeclOPS;
    }

    public void setVarDeclOPS(ArrayList<VarDeclOP> varDeclOPS) {
        this.varDeclOPS = varDeclOPS;
    }

    public BodyOP getBodyOP() {
        return bodyOP;
    }

    public void setBodyOP(BodyOP bodyOP) {
        this.bodyOP = bodyOP;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
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
        return "LocalBlock [varDexlOPS=" + varDeclOPS + ", bodyOP=" + bodyOP +", nodeType=" + nodeType +  "]";
    }
}
