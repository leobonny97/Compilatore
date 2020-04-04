package parse;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Element;

import exception.FatalError;
import nodes.*;

public class CBuilder implements Visitor {

  private FileWriter outputC;

  private static final String STRING_CONCAT = "char* concat(const char *s1, const char *s2){\n\tchar *result = malloc(strlen(s1) + strlen(s2) + 1);\n\tstrcpy(result, s1);\n\tstrcat(result, s2);\n\treturn result;\n}\n\n\n";
  private static final String INT_TO_STRING = "char* IntToString(int i){\n\tint temp=i, count=1;\n\twhile (temp!=0){\n\t\ttemp/=10;\n\t\tcount++;\n\t}\n\tchar * buffer_temp = malloc ((count+1) * sizeof(char));\n\tchar buffer [strlen(buffer_temp) +1];\n\tsprintf(buffer, \"%d\", i);\n\tstrcpy(buffer_temp, buffer);\n\treturn buffer_temp;\n}\n\n\n";
  private static final String CHAR_TO_STRING = "char* CharToString(char c) {\n\tchar * buffer_temp = malloc ((2) * sizeof(char));\n\tchar buffer [2];\n\tsprintf(buffer, \"%c\", c);\n\tstrcpy(buffer_temp, buffer);\n\treturn buffer_temp;\n}\n\n";
  private static final String FLOAT_TO_STRING = "char* FloatToString(float d) {\n\tchar * buf;\n\tint n=20;\n\tfloat fraction = d - ((long)d);\n\tint number_of_decimal_digits=1, limit=1;\n\tint power=10;\n\twhile(power*fraction >= limit && number_of_decimal_digits<=4){\n\t\tfraction = power*fraction;\n\t\tpower*=10;\n\t\tnumber_of_decimal_digits++;\n\t}\n\tint p;\n\tbuf= malloc (number_of_decimal_digits*10 * sizeof(float));\n\tfor (p = 0; p < number_of_decimal_digits; p++) {\n\t\tfloat x;\n\t\tif (snprintf(buf, n, \"%.*g\", p, d) >= n)\n\t\t\tbreak;\n\t\tsscanf(buf, \"%f\", &x);\n\t\tif (x == d)\n\t\tbreak;\n\t}\n\treturn buf;\n}\n\n\n";
  private ArrayList<String> keyWord ;

  @SuppressWarnings("serial")
  public CBuilder(File out) throws IOException {
    outputC = new FileWriter(out);


    keyWord = new ArrayList<String>() {{
      add("auto");
      add("break");
      add("case");		    
      add("char");
      add("const");
      add("continue");
      add("default");
      add("do");
      add("double");
      add("else");
      add("enum");
      add("extern");
      add("float");
      add("for");
      add("goto");
      add("if");
      add("int");
      add("long");
      add("register");
      add("return");		    
      add("short");
      add("signed");
      add("sizeof");
      add("static");
      add("struct");
      add("switch");
      add("typedef");
      add("union");
      add("unsigned");
      add("void");
      add("volatile");
      add("while");
    }};
  }

  @Override
  public Element visit(Visitable visitable) {

    String className = visitable.getClass().getName();

    switch (className) {
      case "nodes.AddOP":
        try {
          cBuilderAddOP(visitable);
        } catch (IOException e) {
          e.printStackTrace();
          System.exit(0);
        }
        break;

      case "nodes.AndOP":
        try {
          cBuilderAndOP(visitable);
        } catch (IOException e) {
          e.printStackTrace();
          System.exit(0);
        }
        break;

      case "nodes.AssignOP":
        try {
          cBuilderAssignOP(visitable);
        } catch (IOException e) {
          e.printStackTrace();
          System.exit(0);
        }
        break;

      case "nodes.BodyOP":
        try {
          cBuilderBodyOP(visitable);
        } catch (IOException e) {
          e.printStackTrace();
          System.exit(0);
        }
        break;

      case "nodes.BooleanConst":
        try {
          cBuilderBooleanConst(visitable);
        } catch (IOException e) {
          e.printStackTrace();
          System.exit(0);
        }
        break;

      case "nodes.DiffOP":
        try {
          cBuilderDiffOP(visitable);
        } catch (IOException e) {
          e.printStackTrace();
          System.exit(0);
        }
        break;

      case "nodes.DivOP":
        try {
          cBuilderDivOP(visitable);
        } catch (IOException e) {
          e.printStackTrace();
          System.exit(0);
        }
        break;

      case "nodes.EqOP":
        try {
          cBuilderEqOP(visitable);
        } catch (IOException e) {
          e.printStackTrace();
          System.exit(0);
        }
        break;

      case "nodes.FloatConstOP":
        try {
          cBuilderFloatConstOP(visitable);
        } catch (IOException e) {
          e.printStackTrace();
          System.exit(0);
        }
        break;

      case "nodes.ForOP":
        try {
          cBuilderForOP(visitable);
        } catch (IOException e) {
          e.printStackTrace();
          System.exit(0);
        }
        break;


      case "nodes.FunCallOP":
        try {
          cBuilderFunCallOP(visitable);
        } catch (IOException e) {
          e.printStackTrace();
          System.exit(0);
        }
        break;

      case "nodes.FunDefinOP":
        try {
          cBuilderFunDefinOP(visitable);
        } catch (IOException e) {
          e.printStackTrace();
          System.exit(0);
        }
        break;

      case "nodes.GeOP":
        try {
          cBuilderGeOP(visitable);
        } catch (IOException e) {
          e.printStackTrace();
          System.exit(0);
        }
        break;


      case "nodes.GtOP":
        try {
          cBuilderGtOP(visitable);
        } catch (IOException e) {
          e.printStackTrace();
          System.exit(0);
        }
        break;


      case "nodes.Identifier":
        try {
          cBuilderIdentifier(visitable);
        } catch (IOException | FatalError e) {
          e.printStackTrace();
          System.exit(0);
        }
        break;

      case "nodes.IfThenElseOP":
        try {
          cBuilderIfThenElseOP(visitable);
        } catch (IOException e) {
          e.printStackTrace();
          System.exit(0);
        }
        break;

      case "nodes.IfThenOP":
        try {
          cBuilderIfThenOP(visitable);
        } catch (IOException e) {
          e.printStackTrace();
          System.exit(0);
        }
        break;

      case "nodes.IntConstOP":
        try {
          cBuilderIntConstOP(visitable);
        } catch (IOException e) {
          e.printStackTrace();
          System.exit(0);
        }
        break;

      case "nodes.LeOP":
        try {
          cBuilderLeOP(visitable);
        } catch (IOException e) {
          e.printStackTrace();
          System.exit(0);
        }
        break;

      case "nodes.LocalBlock":
        cBuilderLocalBlock(visitable);
        break;

      case "nodes.LtOP":
        try {
          cBuilderLtOP(visitable);
        } catch (IOException e) {
          e.printStackTrace();
          System.exit(0);
        }
        break;

      case "nodes.MulOP":
        try {
          cBuilderMulOP(visitable);
        } catch (IOException e) {
          e.printStackTrace();
          System.exit(0);
        }
        break;

      case "nodes.NeOP":
        try {
          cBuilderNeOP(visitable);
        } catch (IOException e) {
          e.printStackTrace();
          System.exit(0);
        }
        break;

      case "nodes.NilOP":
        try {
          cBuilderNilOP(visitable);
        } catch (IOException e) {
          e.printStackTrace();
          System.exit(0);
        }
        break;

      case "nodes.Nop":
        break;

      case "nodes.NotOP":
        try {
          cBuilderNotOP(visitable);
        } catch (IOException e) {
          e.printStackTrace();
          System.exit(0);
        }
        break;

      case "nodes.OrOP":
        try {
          cBuilderOrOP(visitable);
        } catch (IOException e) {
          e.printStackTrace();
          System.exit(0);
        }
        break;

      case "nodes.ParDeclOP":
        try {
          cBuilderParDeclOP(visitable);
        } catch (IOException e) {
          e.printStackTrace();
          System.exit(0);
        }
        break;

      case "nodes.ProgramOP":
        try {
          cBuilderProgramOP(visitable);
        } catch (IOException e) {
          e.printStackTrace();
          System.exit(0);
        }
        break;

      case "nodes.ReadOP":
        try {
          cBuilderReadOP(visitable);
        } catch (IOException  | FatalError e) {
          e.printStackTrace();
          System.exit(0);
        }
        break;

      case "nodes.ReturnOP":
        try {
          cBuilderReturnOP(visitable);
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;

      case "nodes.StringConstOP":
        try {
          cBuilderStringConstOP(visitable);
        } catch (IOException e) {
          e.printStackTrace();
          System.exit(0);
        }
        break;

      case "nodes.UminusOP":
        try {
          cBuilderUminusOP(visitable);
        } catch (IOException e) {
          e.printStackTrace();
          System.exit(0);
        }
        break;

      case "nodes.VarDeclOP":
        try {
          cBuilderVarDeclOP(visitable);
        } catch (IOException e) {
          e.printStackTrace();
          System.exit(0);
        }
        break;

      case "nodes.WhileOP":
        try {
          cBuilderWhileOP(visitable);
        } catch (IOException e) {
          e.printStackTrace();
          System.exit(0);
        }
        break;

      case "nodes.WriteOP":
        try {
          cBuilderWriteOP(visitable);
        } catch (IOException | FatalError e) {
          e.printStackTrace();
          System.exit(0);
        }
        break;

      default:
        System.out.println("[ERROR] -> No corresponding class definition found in CBuilder for " + className);
        break;
    }

    return null;
  }


  private void cBuilderAddOP(Visitable visitable) throws IOException {
    AddOP expr = (AddOP) visitable;

    String type1 = expr.getExpr1().getNodeType();
    String type2 = expr.getExpr2().getNodeType();

    if (type1.equals(TypeChecker.STRING) && type2.equals(TypeChecker.STRING)) {
      outputC.write("concat(");
      expr.getExpr1().accept(this);
      outputC.write(", ");
      expr.getExpr2().accept(this);
      outputC.write(")");
    } else if (type1.equals(TypeChecker.INT) && type2.equals(TypeChecker.STRING)) {
      outputC.write("concat(IntToString(");
      expr.getExpr1().accept(this);
      outputC.write(")");
      outputC.write(", ");
      expr.getExpr2().accept(this);
      outputC.write(")");
    } else if (type1.equals(TypeChecker.STRING) && type2.equals(TypeChecker.INT)) {
      outputC.write("concat(");
      expr.getExpr1().accept(this);
      outputC.write(", IntToString( ");
      expr.getExpr2().accept(this);
      outputC.write("))");
    } else if (type1.equals(TypeChecker.FLOAT) && type2.equals(TypeChecker.STRING)) {
      outputC.write("concat(FloatToString(");
      expr.getExpr1().accept(this);
      outputC.write(")");
      outputC.write(", ");
      expr.getExpr2().accept(this);
      outputC.write(")");
    } else if (type1.equals(TypeChecker.STRING) && type2.equals(TypeChecker.FLOAT)) {
      outputC.write("concat(");
      expr.getExpr1().accept(this);
      outputC.write(", FloatToString( ");
      expr.getExpr2().accept(this);
      outputC.write("))");
    } else {
      outputC.write("(");
      expr.getExpr1().accept(this);
      outputC.write(" + ");
      expr.getExpr2().accept(this);
      outputC.write(")");
    }
  }

  private void cBuilderAndOP(Visitable visitable) throws IOException {
    AndOP expr = (AndOP) visitable;
    outputC.write("(");
    expr.getExpr1().accept(this);
    outputC.write(" && ");
    expr.getExpr2().accept(this);
    outputC.write(")");
  }

  private void cBuilderAssignOP(Visitable visitable) throws IOException {
    AssignOP assignOP = (AssignOP) visitable;
    Identifier identifier = assignOP.getId();
    outputC.write("\t");
    identifier.accept(this);
    outputC.write(" = ");
    assignOP.getExpr().accept(this);
    if(!assignOP.getExpr().getClass().getName().equals(TypeChecker.FunCallOP)) {
      outputC.write(";\n");
    }
  }

  private void cBuilderBodyOP(Visitable visitable) throws IOException {
    BodyOP bodyOP = (BodyOP) visitable;
    for (Stat stat : bodyOP.getStat()) {
      stat.accept(this);
    }
  }

  private void cBuilderBooleanConst(Visitable visitable) throws IOException {
    BooleanConst booleanConst = (BooleanConst) visitable;
    outputC.write("" + booleanConst.isBoolean_const());
  }

  private void cBuilderDiffOP(Visitable visitable) throws IOException {
    DiffOP expr = (DiffOP) visitable;
    outputC.write("(");
    expr.getExpr1().accept(this);
    outputC.write(" - ");
    expr.getExpr2().accept(this);
    outputC.write(")");
  }

  private void cBuilderDivOP(Visitable visitable) throws IOException {
    DivOP expr = (DivOP) visitable;
    outputC.write("(");
    expr.getExpr1().accept(this);
    outputC.write(" / ");
    expr.getExpr2().accept(this);
    outputC.write(")");
  }

  private void cBuilderEqOP(Visitable visitable) throws IOException {
    EqOP expr = (EqOP) visitable;

    if (expr.getExpr1().getNodeType().equals(TypeChecker.STRING)) {
      outputC.write("strcmp(");
      expr.getExpr1().accept(this);
      outputC.write(", ");
      expr.getExpr2().accept(this);
      outputC.write(")");
      outputC.write("==0");
    } else {
      expr.getExpr1().accept(this);
      outputC.write(" == ");
      expr.getExpr2().accept(this);
    }
  }

  private void cBuilderFloatConstOP(Visitable visitable) throws IOException {
    FloatConstOP floatConstOP = (FloatConstOP) visitable;
    outputC.write("" + floatConstOP.getFloat_const());
  }

  //da rivedere
  private void cBuilderForOP(Visitable visitable) throws IOException {
    ForOP forOP = (ForOP) visitable;
    outputC.write("for(");
    Identifier identifier = forOP.getId();
    identifier.accept(this);
    outputC.write(" = ");
    forOP.getExpr1().accept(this);
    outputC.write(" ; ");
    forOP.getExpr2().accept(this);
    outputC.write(" ; ) ");
    outputC.write(" {\n");
    forOP.getBody().accept(this);
    outputC.write("\t}\n");
  }

  private void cBuilderFunCallOP(Visitable visitable) throws IOException {
    FunCallOP callOP = (FunCallOP) visitable;
    outputC.write(callOP.getId().getId()+"(");
    for (int c=0; c<callOP.getExprs().size(); c++) {
      callOP.getExprs().get(c).accept(this);
      if(c<callOP.getExprs().size()-1) {
        outputC.write(", ");
      }
      else {
        outputC.write(")");
      }
    }
    outputC.write(";");
  }

  private void cBuilderFunDefinOP(Visitable visitable) throws IOException {
    FunDefinOP funDefinOP = (FunDefinOP) visitable;
    if(funDefinOP.getId().getNodeType().equals(TypeChecker.Nil)) {
      outputC.write("void ");
    } else if(funDefinOP.getId().getNodeType().equals(TypeChecker.STRING)) {
      outputC.write("char * ");
    } else {
        outputC.write(funDefinOP.getId().getNodeType() + " ");
    }
    outputC.write(funDefinOP.getId().getId()+"(");
    int c;
    for (c=0; c<funDefinOP.getParDeclOPS().size(); c++) {
      funDefinOP.getParDeclOPS().get(c).accept(this);
      if(c<funDefinOP.getParDeclOPS().size()-1) {
        outputC.write(" , ");
      } else {
        outputC.write(") {\n");
      }
    }
    if(c==0) {
      outputC.write(") {\n");
    }
    funDefinOP.getBody().accept(this);
    outputC.write("}\n");
  }


  private void cBuilderGeOP(Visitable visitable) throws IOException {
    GeOP expr = (GeOP) visitable;
    if (expr.getExpr1().getNodeType().equals(TypeChecker.STRING)) {
      outputC.write("(strcmp(");
      expr.getExpr1().accept(this);
      outputC.write(", ");
      expr.getExpr2().accept(this);
      outputC.write(")");
      outputC.write(">0 || strcmp( ");
      expr.getExpr1().accept(this);
      outputC.write(", ");
      expr.getExpr2().accept(this);
      outputC.write(")");
      outputC.write("==0)");
    } else {
      outputC.write("(");
      expr.getExpr1().accept(this);
      outputC.write(" >= ");
      expr.getExpr2().accept(this);
      outputC.write(")");
    }
  }

  private void cBuilderGtOP(Visitable visitable) throws IOException {
    GtOP expr = (GtOP) visitable;

    if (expr.getExpr1().getNodeType().equals(TypeChecker.STRING)) {
      outputC.write("(strcmp(");
      expr.getExpr1().accept(this);
      outputC.write(", ");
      expr.getExpr2().accept(this);
      outputC.write(")");
      outputC.write(">0)");
    } else {
      outputC.write("(");
      expr.getExpr1().accept(this);
      outputC.write(" > ");
      expr.getExpr2().accept(this);
      outputC.write(")");
    }
  }

  private void cBuilderIdentifier(Visitable visitable) throws IOException, FatalError {
    Identifier identifier = (Identifier) visitable;
    if(keyWord.contains(identifier.getId())){
      throw new FatalError("C Keywords used");
    }
    outputC.write("" + identifier.getId());
  }

  private void cBuilderIfThenElseOP(Visitable visitable) throws IOException {
    IfThenElseOP ifThenElseOp = (IfThenElseOP) visitable;
    outputC.write("\tif(");
    ifThenElseOp.getExpr().accept(this);
    outputC.write(") \t{\n\t");
    ifThenElseOp.getBody1().accept(this);
    if( ifThenElseOp.getBody2().getStat().get(0).getClass().getName().equals("nodes.IfThenElseOP") ||
            ifThenElseOp.getBody2().getStat().get(0).getClass().getName().equals("nodes.IfThenOP") ) {
      outputC.write("\n\t} else ");
      ifThenElseOp.getBody2().accept(this);
    } else {
      outputC.write("\n\t} else {\n\t");
      ifThenElseOp.getBody2().accept(this);
      outputC.write("\n\t}\n\n");
    }
  }

  private void cBuilderIfThenOP(Visitable visitable) throws IOException {
    IfThenOP ifThenOp = (IfThenOP) visitable;
    outputC.write("if(");
    ifThenOp.getExpr().accept(this);
    outputC.write(") {\n");
    ifThenOp.getBody().accept(this);
    outputC.write("\n}\n\n");
  }

  private void cBuilderIntConstOP(Visitable visitable) throws IOException {
    IntConstOP intConst = (IntConstOP) visitable;
    outputC.write("" + intConst.getInt_const());
  }

  private void cBuilderLeOP(Visitable visitable) throws IOException {
    LeOP expr = (LeOP) visitable;
    if (expr.getExpr1().getNodeType().equals(TypeChecker.STRING)) {
      outputC.write("(strcmp(");
      expr.getExpr1().accept(this);
      outputC.write(",");
      expr.getExpr2().accept(this);
      outputC.write(")");
      outputC.write("<0 || strcmp( ");
      expr.getExpr1().accept(this);
      outputC.write(",");
      expr.getExpr2().accept(this);
      outputC.write(")");
      outputC.write("==0)");
    } else {
      outputC.write("(");
      expr.getExpr1().accept(this);
      outputC.write(" <= ");
      expr.getExpr2().accept(this);
      outputC.write(")");
    }
  }

  private void cBuilderLocalBlock(Visitable visitable){
    LocalBlock localBlock = (LocalBlock) visitable;
    for (VarDeclOP varDeclOP : localBlock.getVarDeclOPS()) {
      varDeclOP.accept(this);
    }
    localBlock.getBodyOP().accept(this);
  }

  private void cBuilderLtOP(Visitable visitable) throws IOException {
    LtOP expr = (LtOP) visitable;
    if (expr.getExpr1().getNodeType().equals(TypeChecker.STRING)) {
      outputC.write("(strcmp(");
      expr.getExpr1().accept(this);
      outputC.write(",");
      expr.getExpr2().accept(this);
      outputC.write(")");
      outputC.write("<0)");
    } else {
      outputC.write("(");
      expr.getExpr1().accept(this);
      outputC.write(" < ");
      expr.getExpr2().accept(this);
      outputC.write(")");
    }
  }

  private void cBuilderMulOP(Visitable visitable) throws IOException {
    MulOP expr = (MulOP) visitable;
    outputC.write("(");
    expr.getExpr1().accept(this);
    outputC.write(" * ");
    expr.getExpr2().accept(this);
    outputC.write(")");
  }

  private void cBuilderNeOP(Visitable visitable) throws IOException {
    NeOP expr = (NeOP) visitable;
    if (expr.getExpr1().getNodeType().equals(TypeChecker.STRING)) {
      outputC.write("(strcmp(");
      expr.getExpr1().accept(this);
      outputC.write(",");
      expr.getExpr2().accept(this);
      outputC.write(")");
      outputC.write("!=0)");
    } else {
      outputC.write("(");
      expr.getExpr1().accept(this);
      outputC.write(" != ");
      expr.getExpr2().accept(this);
      outputC.write(")");
    }
  }

  private void cBuilderNilOP(Visitable visitable) throws IOException {
    outputC.write("NULL");
  }

  private void cBuilderNotOP(Visitable visitable) throws IOException {
    NotOP expr = (NotOP) visitable;
    outputC.write("!(");
    expr.getExpr1().accept(this);
    outputC.write(")");
  }

  private void cBuilderOrOP(Visitable visitable) throws IOException {
    OrOP expr = (OrOP) visitable;
    outputC.write("(");
    expr.getExpr1().accept(this);
    outputC.write(" || ");
    expr.getExpr2().accept(this);
    outputC.write(")");
  }

  private void cBuilderParDeclOP(Visitable visitable) throws IOException {
    ParDeclOP parDeclOP = (ParDeclOP) visitable;
    if( parDeclOP.getIdentifier().getNodeType().equals(TypeChecker.STRING)) {
      outputC.write("char *");
    }else {
      outputC.write(" " + parDeclOP.getIdentifier().getNodeType() + " ");
    }
    outputC.write(parDeclOP.getIdentifier().getId());
  }

  private void cBuilderProgramOP(Visitable visitable) throws IOException {
    ProgramOP programOP = (ProgramOP) visitable;
    outputC.write("#include <stdio.h>\n");
    outputC.write("#include <stdlib.h>\n");
    outputC.write("#include <string.h>\n");
    outputC.write("#include <stdbool.h>\n\n");
    outputC.write(STRING_CONCAT);
    outputC.write(INT_TO_STRING);
    outputC.write(FLOAT_TO_STRING);
    if(programOP.getVar()!=null)
    {
      for (VarDeclOP varDeclOP : programOP.getVar())
      {
        varDeclOP.accept(this);
      }
    }
    for (FunDefinOP funDefinOP : programOP.getFun()) {
      funDefinOP.accept(this);
      outputC.write("\n");
    }
    outputC.close();
  }

  private void cBuilderReadOP(Visitable visitable) throws IOException, FatalError {
    ReadOP readOP = (ReadOP) visitable;
    outputC.write("\t");
    for (Identifier identifier : readOP.getIdentifiers()) {
      String type = identifier.getNodeType();
      if (type.equals(TypeChecker.STRING)) {
        outputC.write("scanf(\"\\n%s\", " + identifier.getId() + ");");
      } else if (type.equals(TypeChecker.INT)) {
        outputC.write("scanf(\"\\n%d\", &"+ identifier.getId() +");");
      } else if (type.equals(TypeChecker.FLOAT)) {
        outputC.write("scanf(\"\\n%f\", &"+ identifier.getId() +");");
      } else{
        throw new FatalError("Not recognized type " + type + " in readOp.");
      }
    }
    outputC.write("\n");
  }

  private void cBuilderReturnOP(Visitable visitable) throws IOException {
    ReturnOP returnOP = (ReturnOP) visitable;
    outputC.write("\treturn ");
    if(!returnOP.getExpr1().getNodeType().equals(TypeChecker.Nil)) {
      returnOP.getExpr1().accept(this);
    }
    outputC.write(";");
  }

  private void cBuilderStringConstOP(Visitable visitable) throws IOException {
    StringConstOP stringConst = (StringConstOP) visitable;
    outputC.write(stringConst.getStringConst());
  }

  private void cBuilderUminusOP(Visitable visitable) throws IOException {
    UminusOP expr = (UminusOP) visitable;
    outputC.write("-(");
    expr.getExpr1().accept(this);
    outputC.write(")");
  }

  private void cBuilderVarDeclOP(Visitable visitable) throws IOException {
    VarDeclOP varDeclOP = (VarDeclOP) visitable;
    if (varDeclOP.getIdentifier().getNodeType().equals(TypeChecker.STRING))
      outputC.write("char *");
    else
      outputC.write(varDeclOP.getIdentifier().getNodeType() + " ");
      varDeclOP.getIdentifier().accept(this);
    if(varDeclOP.getExpr1()!=null) {
      outputC.write(" = ");
      varDeclOP.getExpr1().accept(this);
    }
    outputC.write(";\n");
  }

  private void cBuilderWhileOP(Visitable visitable) throws IOException {
    WhileOP whileOP = (WhileOP) visitable;
    outputC.write("\twhile(");
    whileOP.getExpr1().accept(this);
    outputC.write(") {\n");
    whileOP.getBodyOP().accept(this);
    outputC.write("\t}\n");
  }

  private void cBuilderWriteOP(Visitable visitable) throws IOException, FatalError {
    WriteOP writeOP = (WriteOP) visitable;

    for (Expr e : writeOP.getExprs()) {
      outputC.write("\t");
      String type = e.getNodeType();
      if (type.equals(TypeChecker.STRING)) {
        outputC.write("printf(\"%s \\n\",");
        e.accept(this);
      } else if (type.equals(TypeChecker.INT)) {
        outputC.write("printf(\"%d\\n\",");
        e.accept(this);
      } else if (type.equals(TypeChecker.FLOAT)) {
        outputC.write("printf(\"%f\\n\",");
        e.accept(this);
      } else if (type.equals(TypeChecker.BOOL)) {
        outputC.write("printf(\"%s\\n\",");
        e.accept(this);
        outputC.write("? \"true\\n\" : \"false\\n\"");
      }else{
        throw new FatalError("Not recognized type " + type + " in writeOp.");
      }
      outputC.write(");\n");
    }

  }

}
