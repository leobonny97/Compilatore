package parse;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import nodes.Visitable;
import nodes.Visitor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;
import nodes.*;

public class XMLBuilder implements Visitor {
    private Document doc;

    public XMLBuilder() throws Exception {
        initDocument();
    }

    @Override
    public Element visit(Visitable visitable) {
        String className = visitable.getClass().getName();
        //System.out.println("class name "+ className);

        switch (className) {
            case "nodes.AddOP":
                return xmlAddOP(visitable);
            case "nodes.AndOP":
                return xmlAndOP(visitable);
            case "nodes.AssignOP":
                return xmlAssignOP(visitable);
            case "nodes.BodyOP":
                return xmlBodyOP(visitable);
            case "nodes.BooleanConst":
                return xmlBooleanConst(visitable);
            case "nodes.DiffOP":
                return xmlDiffOP(visitable);
            case "nodes.DivOP":
                return xmlDivOP(visitable);
            case "nodes.EqOP":
                return xmlEqOP(visitable);
            case "nodes.FloatConstOP":
                return xmlFloatConstOP(visitable);
            case "nodes.ForOP":
                return xmlForOP(visitable);
            case "nodes.FunCallOP":
                return xmlFunCallOP(visitable);
            case "nodes.FunDefinOP":
                return xmlFunDefinOP(visitable);
            case "nodes.GeOP":
                return xmlGeOP(visitable);
            case "nodes.GtOP":
                return xmlGtOP(visitable);
            case "nodes.Identifier":
                return xmlIdentifier(visitable);
            case "nodes.IfThenElseOP":
                return xmlIfThenElseOP(visitable);
            case "nodes.IfThenOP":
                return xmlIfThenOP(visitable);
            case "nodes.IntConstOP":
                return xmlIntConstOP(visitable);
            case "nodes.LeOP":
                return xmlLeOP(visitable);
            case "nodes.LocalBlock":
                return xmlLocalBlock(visitable);
            case "nodes.LtOP":
                return xmlLtOP(visitable);
            case "nodes.MulOP":
                return xmlMulOP(visitable);
            case "nodes.NeOP":
                return xmlNeOP(visitable);
            case "nodes.NilOP":
                return xmlNilOP(visitable);
            case "nodes.Nop":
                return xmlNop(visitable);
            case "nodes.NotOP":
                return xmlNotOP(visitable);
            case "nodes.OrOP":
                return xmlOrOP(visitable);
            case "nodes.ParDeclOP":
                return xmlParDeclOP(visitable);
            case "nodes.ProgramOP":
                return xmlProgramOP(visitable);
            case "nodes.ReadOP":
                return xmlReadOP(visitable);
            case "nodes.ReturnOP":
                return xmlReturnOP(visitable);
            case "nodes.StringConstOP":
                return xmlStringConstOP(visitable);
            case "nodes.UminusOP":
                return xmlUminusOP(visitable);
            case "nodes.VarDeclOP":
                return xmlVarDeclOP(visitable);
            case "nodes.WhileOP":
                return xmlWhileOP(visitable);
            case "nodes.WriteOP":
                return xmlWriteOP(visitable);
            default:
                System.out.println("[ERROR] -> No corresponding class definition found in XMLBuilder for " + className);
                return null;
        }

    }

    public void initDocument() throws Exception {
        doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
    }

    public void exportDocument(File file) throws Exception {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(file);
        transformer.transform(source, result);
    }


    // --------------------------------------STARTING XML METHODS------------------------------------------------

    private Element xmlAddOP(Visitable visitable)  {
        AddOP expr = (AddOP) visitable;
        Element e = doc.createElement("AddOP");

        e.appendChild(expr.getExpr1().accept(this));
        e.appendChild(expr.getExpr2().accept(this));

        return e;
    }

    private Element xmlAndOP(Visitable visitable)  {
        AndOP expr = (AndOP) visitable;
        Element e = doc.createElement("AndOP");

        e.appendChild(expr.getExpr1().accept(this));
        e.appendChild(expr.getExpr2().accept(this));

        return e;
    }

    private Element xmlAssignOP(Visitable visitable) {
        AssignOP assignOP = (AssignOP) visitable;
        Element e = doc.createElement("Assign_op");

        e.appendChild(assignOP.getId().accept(this));
        e.appendChild(assignOP.getExpr().accept(this));

        return e;
    }

    private Element xmlBodyOP(Visitable visitable) {
        BodyOP bodyOP = (BodyOP) visitable;
        Element e = doc.createElement("Body_op");

        for(Stat s: bodyOP.getStat()) {
            e.appendChild(s.accept(this));
        }

        return e;
    }

    private Element xmlBooleanConst(Visitable visitable)  {
        BooleanConst booleanConst = (BooleanConst) visitable;
        Element e = doc.createElement("Boolean_const");

        e.appendChild(doc.createTextNode(booleanConst.isBoolean_const() + ""));

        return e;
    }

    private Element xmlDiffOP(Visitable visitable)  {
        DiffOP expr = (DiffOP) visitable;
        Element e = doc.createElement("Diff_op");

        e.appendChild(expr.getExpr1().accept(this));
        e.appendChild(expr.getExpr2().accept(this));

        return e;
    }

    private Element xmlDivOP(Visitable visitable)  {
        DivOP expr = (DivOP) visitable;
        Element e = doc.createElement("Div_op");

        e.appendChild(expr.getExpr1().accept(this));
        e.appendChild(expr.getExpr2().accept(this));

        return e;
    }

    private Element xmlEqOP(Visitable visitable)  {
        EqOP expr = (EqOP) visitable;
        Element e = doc.createElement("Eq_op");

        e.appendChild(expr.getExpr1().accept(this));
        e.appendChild(expr.getExpr2().accept(this));

        return e;
    }

    private Element xmlFloatConstOP(Visitable visitable) {
        FloatConstOP floatConstOP = (FloatConstOP) visitable;

        Element e = doc.createElement("FloatConstOP");
        e.appendChild(doc.createTextNode(""+floatConstOP.getFloat_const()));

        return e;
    }

    private Element xmlForOP(Visitable visitable) {
        ForOP forOP = (ForOP) visitable;
        Element e = doc.createElement("forOP");

        e.appendChild(forOP.getId().accept(this));
        e.appendChild(forOP.getExpr1().accept(this));
        e.appendChild(forOP.getExpr2().accept(this));
        e.appendChild(forOP.getBody().accept(this));

        return e;
    }

    private Element xmlFunCallOP(Visitable visitable) {
        FunCallOP funCallOP = (FunCallOP) visitable;
        Element e = doc.createElement("FunCall_op");

        e.appendChild(funCallOP.getId().accept(this));
        Element e2= doc.createElement("Parameters");
        e.appendChild(e2);

        for(Expr exp: funCallOP.getExprs()) {
            e2.appendChild(exp.accept(this));
        }

        return e;
    }

    private Element xmlFunDefinOP(Visitable visitable) {
        FunDefinOP funDefinOP = (FunDefinOP) visitable;
        Element e = doc.createElement("funDefin_op");

        e.appendChild(funDefinOP.getId().accept(this));
        for(ParDeclOP parDecl: funDefinOP.getParDeclOPS()) {
            e.appendChild(parDecl.accept(this));
        }
       // e.appendChild(doc.createTextNode(funDefinOP.getVariableType()));
        e.appendChild(funDefinOP.getBody().accept(this));

        return e;
    }

    private Element xmlGeOP(Visitable visitable)  {
        GeOP expr = (GeOP) visitable;
        Element e = doc.createElement("Ge_op");

        e.appendChild(expr.getExpr1().accept(this));
        e.appendChild(expr.getExpr2().accept(this));

        return e;
    }

    private Element xmlGtOP(Visitable visitable)  {
        GtOP expr = (GtOP) visitable;
        Element e = doc.createElement("Gt_op");

        e.appendChild(expr.getExpr1().accept(this));
        e.appendChild(expr.getExpr2().accept(this));

        return e;
    }

    private Element xmlIdentifier(Visitable visitable)  {
        Identifier identifier = (Identifier) visitable;
        Element e = doc.createElement("Identifier");
        Element e1 = doc.createElement("Id");
        e1.appendChild(doc.createTextNode(identifier.getId()));
        e.appendChild(e1);
        if(identifier.getNodeType()!=null)
        {
            Element e2 = doc.createElement("Type");
            e2.appendChild(doc.createTextNode(identifier.getNodeType()));
            e.appendChild(e2);
        }
        if(identifier.getFunctionOrVariable()!=null)
        {
            Element e3 = doc.createElement("VariableOrFunction");
            e3.appendChild(doc.createTextNode(identifier.getFunctionOrVariable()));
            e.appendChild(e3);
        }
        return e;
    }

    private Element xmlIfThenElseOP(Visitable visitable)  {
        IfThenElseOP ifThenElseOp = (IfThenElseOP) visitable;
        Element n = doc.createElement("IfThenElseOP");

        n.appendChild(ifThenElseOp.getExpr().accept(this));
        n.appendChild(ifThenElseOp.getBody1().accept(this));
        n.appendChild(ifThenElseOp.getBody2().accept(this));

        return n;
    }

    private Element xmlIfThenOP(Visitable visitable)  {
        IfThenOP ifThenOp = (IfThenOP) visitable;
        Element n = doc.createElement("IfThenOP");

        n.appendChild(ifThenOp.getExpr().accept(this));
        n.appendChild(ifThenOp.getBody().accept(this));

        return n;
    }

    private Element xmlIntConstOP(Visitable visitable) {
        IntConstOP intConstOP = (IntConstOP) visitable;

        Element e = doc.createElement("IntConstOP");
        e.appendChild(doc.createTextNode(""+intConstOP.getInt_const()));

        return e;
    }

    private Element xmlLeOP(Visitable visitable)  {
        LeOP expr = (LeOP) visitable;
        Element e = doc.createElement("Le_op");
        e.appendChild(expr.getExpr1().accept(this));
        e.appendChild(expr.getExpr2().accept(this));
        return e;
    }

    private Element xmlLocalBlock(Visitable visitable)  {
        LocalBlock localBlock = (LocalBlock) visitable;
        Element e = doc.createElement("Local_Block");
        for(VarDeclOP varDeclOP: localBlock.getVarDeclOPS()) {
            e.appendChild(varDeclOP.accept(this));
        }
        e.appendChild(localBlock.getBodyOP().accept(this));
        return e;
    }

    private Element xmlLtOP(Visitable visitable)  {
        LtOP expr = (LtOP) visitable;
        Element e = doc.createElement("Lt_op");
        e.appendChild(expr.getExpr1().accept(this));
        e.appendChild(expr.getExpr2().accept(this));
        return e;
    }

    private Element xmlMulOP(Visitable visitable)  {
        MulOP expr = (MulOP) visitable;
        Element e = doc.createElement("Mul_op");
        e.appendChild(expr.getExpr1().accept(this));
        e.appendChild(expr.getExpr2().accept(this));
        return e;
    }

    private Element xmlNeOP(Visitable visitable)  {
        NeOP expr = (NeOP) visitable;
        Element e = doc.createElement("Ne_op");
        e.appendChild(expr.getExpr1().accept(this));
        e.appendChild(expr.getExpr2().accept(this));
        return e;
    }

    private Element xmlNilOP(Visitable visitable)  {
        Element e = doc.createElement("Nil_op");
        return e;
    }

    private Element xmlNop(Visitable visitable)  {
        Element e = doc.createElement("Nop_op");
        return e;
    }

    private Element xmlNotOP(Visitable visitable)  {
        NotOP expr = (NotOP) visitable;
        Element e = doc.createElement("Not_op");
        e.appendChild(expr.getExpr1().accept(this));
        return e;
    }

    private Element xmlOrOP(Visitable visitable)  {
        OrOP expr = (OrOP) visitable;
        Element e = doc.createElement("Or_op");
        e.appendChild(expr.getExpr1().accept(this));
        e.appendChild(expr.getExpr2().accept(this));
        return e;
    }

    private Element xmlParDeclOP(Visitable visitable)  {
        ParDeclOP parDeclOP = (ParDeclOP) visitable;
        Element e = doc.createElement("Par_Decl_op");
        e.appendChild(parDeclOP.getIdentifier().accept(this));
        return e;
    }

    private Element xmlProgramOP(Visitable visitable)  {
        ProgramOP programOP = (ProgramOP) visitable;
        Element e = doc.createElement("Program_op");
        if(programOP.getVar()!=null)
        {
            for(VarDeclOP varDeclOP: programOP.getVar()) {
                e.appendChild(varDeclOP.accept(this));
            }
        }
        for(FunDefinOP funDefinOP: programOP.getFun()) {
            e.appendChild(funDefinOP.accept(this));
        }
        doc.appendChild(e);
        return e;
    }

    private Element xmlReadOP(Visitable visitable)  {
        ReadOP stat = (ReadOP) visitable;
        Element e = doc.createElement("Read_op");
        for(Identifier identifier: stat.getIdentifiers()) {
            e.appendChild(identifier.accept(this));
        }
        return e;
    }

    private Element xmlReturnOP(Visitable visitable)  {
        ReturnOP expr = (ReturnOP) visitable;
        Element e = doc.createElement("Return_op");
        e.appendChild(expr.getExpr1().accept(this));
        return e;
    }

    private Element xmlStringConstOP(Visitable visitable)  {
        StringConstOP expr = (StringConstOP) visitable;
        Element e = doc.createElement("String_Const_op");
        e.appendChild(doc.createTextNode(expr.getStringConst()));
        return e;
    }

    private Element xmlUminusOP(Visitable visitable)  {
        UminusOP expr = (UminusOP) visitable;
        Element e = doc.createElement("Uminus_op");
        e.appendChild(expr.getExpr1().accept(this));
        return e;
    }

    private Element xmlVarDeclOP(Visitable visitable)  {
        VarDeclOP expr = (VarDeclOP) visitable;
        Element e = doc.createElement("Var_Decl_op");
        e.appendChild(expr.getIdentifier().accept(this));
        if(expr.getExpr1()!=null)
        {
            e.appendChild(expr.getExpr1().accept(this));
        }
        return e;
    }

    private Element xmlWhileOP(Visitable visitable)  {
        WhileOP stat = (WhileOP) visitable;
        Element e = doc.createElement("While_op");
        e.appendChild(stat.getExpr1().accept(this));
        e.appendChild(stat.getBodyOP().accept(this));
        return e;
    }

    private Element xmlWriteOP(Visitable visitable)  {
        WriteOP stat = (WriteOP) visitable;
        Element e = doc.createElement("Write_op");
        for(Expr expr: stat.getExprs()) {
            e.appendChild(expr.accept(this));
        }
        return e;
    }

    public Document getDocument() {
        return doc;
    }

    public void setDocument(Document d) {
        this.doc = d;
    }
}
