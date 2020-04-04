package nodes;

import org.w3c.dom.Element;
import parse.CBuilder;
import parse.SemanticVisitor;
import parse.TypeChecker;
import parse.XMLBuilder;

public class FloatConstOP implements Expr,Visitable{

    private float float_const;
    private String nodeType = TypeChecker.FLOAT;

    public FloatConstOP(float float_const) {
        this.float_const = float_const;
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

    public float getFloat_const() {
        return float_const;
    }

    public void setFloat_const(float float_const) {
        this.float_const = float_const;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    @Override
    public String toString() {
        return "FloatConstOP [float=" + float_const  + ", nodeType=" + nodeType + "]";
    }
}
