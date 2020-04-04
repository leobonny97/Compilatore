package nodes;

import org.w3c.dom.Element;
import parse.CBuilder;
import parse.SemanticVisitor;
import parse.SymbolTable;
import parse.XMLBuilder;

import java.util.ArrayList;

public class ProgramOP implements Visitable {
    private ArrayList<FunDefinOP> fun = new ArrayList<>();
    private ArrayList<VarDeclOP> var = new ArrayList<>();
    private SymbolTable symbolTable; // scope
    private String nodeType;

    public ProgramOP(ArrayList<FunDefinOP> fun, ArrayList<VarDeclOP> var) {
        this.fun = fun;
        this.var = var;
    }

    public ArrayList<FunDefinOP> getFun() {
        return fun;
    }

    public void setFun(ArrayList<FunDefinOP> fun) {
        this.fun = fun;
    }

    public ArrayList<VarDeclOP> getVar() {
        return var;
    }

    public void setVar(ArrayList<VarDeclOP> var) {
        this.var = var;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    @Override
    public String toString()  {
        return "ProgramOP [FunDefinOP=" + fun + ", VarDeclOP=" + var + "]";
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
