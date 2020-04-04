package nodes;

import org.w3c.dom.Element;
import parse.CBuilder;
import parse.SemanticVisitor;
import parse.TypeChecker;
import parse.XMLBuilder;

import java.util.ArrayList;

public class Identifier implements Expr,Visitable{

    private String id;
    private String nodeType;
    private String functionOrVariable;
    private ArrayList<ParDeclOP> parDeclOP;

    public Identifier(String id) {
        this.id = id;
    }

    public Identifier(String id, String nodeType) {
        this.id = id;
        this.nodeType = nodeType;
    }

    public Identifier(String id, String nodeType, String functionOrVariable) {
        this.id = id;
        this.nodeType = nodeType;
        this.functionOrVariable = functionOrVariable;
    }

    public Identifier(String id, String nodeType, String functionOrVariable, ArrayList<ParDeclOP> parDeclOP) {
        this.id = id;
        this.nodeType = nodeType;
        this.functionOrVariable = functionOrVariable;
        this.parDeclOP = parDeclOP;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getFunctionOrVariable() {
        return functionOrVariable;
    }

    public ArrayList<ParDeclOP> getParDeclOP() {
        return parDeclOP;
    }

    public void setParDeclOP(ArrayList<ParDeclOP> parDeclOP) {
        this.parDeclOP = parDeclOP;
    }

    public void setFunctionOrVariable(String functionOrVariable) {
        this.functionOrVariable = functionOrVariable;
    }

    public boolean isVariable() {
        return (!functionOrVariable.equals(TypeChecker.VARIABLE));
    }

    public boolean isFunction() {
        return functionOrVariable.equals(TypeChecker.FUNCTION);
    }

    @Override
    public String toString() {
        return "Identifier [id=" + id + ", nodeType=" + nodeType + ", functionOrVariable=" + functionOrVariable + "]";
    }
}
