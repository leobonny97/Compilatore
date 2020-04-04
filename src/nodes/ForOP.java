package nodes;

import org.w3c.dom.Element;
import parse.CBuilder;
import parse.SemanticVisitor;
import parse.SymbolTable;
import parse.XMLBuilder;

public class ForOP implements Stat,Visitable {

    private Identifier id;
    private Expr expr1;
    private Expr expr2;
    private BodyOP body;
    private SymbolTable symbolTable; // scope
    private String nodeType;

    public ForOP(Identifier id, Expr expr1, Expr expr2, BodyOP body) {
        this.id = id;
        this.expr1 = expr1;
        this.expr2 = expr2;
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

    public Expr getExpr1() {
        return expr1;
    }

    public void setExpr1(Expr expr1) {
        this.expr1 = expr1;
    }

    public Expr getExpr2() {
        return expr2;
    }

    public void setExpr2(Expr expr2) {
        this.expr2 = expr2;
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
        return "ForOP [id="+ id +"expr1=" + expr1 + ", expr2=" + expr2 + ", body=" + body + ", nodeType=" + nodeType + "]";
    }

}
