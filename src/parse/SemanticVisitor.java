package parse;

import java.util.ArrayList;
import java.util.Stack;

import exception.ParameterError;
import org.w3c.dom.Element;
import exception.MultipleDeclarationException;
import exception.NotDeclarationException;
import exception.TypeMismatchException;
import nodes.*;

public class SemanticVisitor implements Visitor {

  private Stack<SymbolTable> stack;
  private boolean errored = false; 

  public SemanticVisitor() {
    stack = new Stack<SymbolTable>();

  }

  @Override
  public Element visit(Visitable visitable) {

    String className = visitable.getClass().getName();

    switch (className) {
      case "nodes.AddOP":
        try {
          semanticAddOP(visitable);
        } catch (TypeMismatchException e) {
          errored = true;
          e.printStackTrace();
          break;
        }
        break;

      case "nodes.AndOP":
        try {
          semanticAndOP(visitable);
        } catch (TypeMismatchException e) {
          errored = true;
          e.printStackTrace();
          break;
        }
        break;

      case "nodes.AssignOP":
        try {
          semanticAssignOP(visitable);
        } catch (NotDeclarationException | TypeMismatchException e) {
          errored = true;
          e.printStackTrace();
          break;
        }
        break;

      case "nodes.BodyOP":
        semanticBodyOP(visitable);
        break;

      case "nodes.BooleanConst":
        semanticBooleanConst(visitable);
        break;

      case "nodes.DiffOP":
        try {
          semanticDiffOP(visitable);
        } catch (TypeMismatchException e) {
          errored = true;
          e.printStackTrace();
          break;
        }
        break;

      case "nodes.DivOP":
        try {
          semanticDivOP(visitable);
        } catch (TypeMismatchException e) {
          errored = true;
          e.printStackTrace();
          break;
        }
        break;

      case "nodes.EqOP":
        try {
          semanticEqOP(visitable);
        } catch (TypeMismatchException e) {
          errored = true;
          e.printStackTrace();
          break;
        }
        break;

      case "nodes.FloatConstOP":
        semanticFloatConst(visitable);
        break;

      case "nodes.ForOP":
        try {
          semanticForOP(visitable);
        } catch (TypeMismatchException e) {
          errored = true;
          e.printStackTrace();
          break;
        }
        break;

      case "nodes.FunCallOP":
        try {
          semanticFunCallOP(visitable);
        } catch (NotDeclarationException | TypeMismatchException | ParameterError e) {
          errored = true;
          e.printStackTrace();
          break;
        }
        break;

      case "nodes.FunDefinOP":
        try {
          semanticFunDefinOP(visitable);
        } catch (MultipleDeclarationException | TypeMismatchException e) {
          errored = true;
          e.printStackTrace();
        }
        break;

      case "nodes.GeOP":
        try {
          semanticGeOP(visitable);
        } catch (TypeMismatchException e) {
          errored = true;
          e.printStackTrace();
          break;
        }
        break;

      case "nodes.GtOP":
        try {
          semanticGtOP(visitable);
        } catch (TypeMismatchException e) {
          errored = true;
          e.printStackTrace();
          break;
        }
        break;

      case "nodes.Identifier":
        try {
          semanticIdentifier(visitable);
        } catch (NotDeclarationException e) {
          errored = true;
          e.printStackTrace();
          break;
        }
        break;

      case "nodes.IfThenElseOP":
        try {
          semanticIfThenElseOp(visitable);
        } catch (TypeMismatchException e) {
          errored = true;
          e.printStackTrace();
          break;
        }
        break;

      case "nodes.IfThenOP":
        try {
          semanticIfThenOp(visitable);
        } catch (TypeMismatchException e) {
          errored = true;
          e.printStackTrace();
          break;
        }
        break;

      case "nodes.IntConstOP":
        semanticIntConst(visitable);
        break;

      case "nodes.LeOP":
        try {
          semanticLeOP(visitable);
        } catch (TypeMismatchException e) {
          errored = true;
          e.printStackTrace();
          break;
        }
        break;

      case "nodes.LocalBlock":
        semanticLocalBlock(visitable);
        break;

      case "nodes.LtOP":
        try {
          semanticLtOP(visitable);
        } catch (TypeMismatchException e) {
          errored = true;
          e.printStackTrace();
          break;
        }
        break;

      case "nodes.MulOP":
        try {
          semanticMulOP(visitable);
        } catch (TypeMismatchException e) {
          errored = true;
          e.printStackTrace();
          break;
        }
        break;

      case "nodes.NeOP":
        try {
          semanticNeOP(visitable);
        } catch (TypeMismatchException e) {
          errored = true;
          e.printStackTrace();
          break;
        }
        break;

      case "nodes.NilOP":
        semanticNilOP(visitable);
        break;

      case "nodes.Nop":
        semanticNop(visitable);
        break;

      case "nodes.NotOP":
        try {
          semanticNotOP(visitable);
        } catch (TypeMismatchException e) {
          errored = true;
          e.printStackTrace();
          break;
        }
        break;

      case "nodes.OrOP":
        try {
          semanticOrOP(visitable);
        } catch (TypeMismatchException e) {
          errored = true;
          e.printStackTrace();
          break;
        }
        break;

      case "nodes.ParDeclOP":
        try {
          semanticParDeclOP(visitable);
        } catch (MultipleDeclarationException e) {
          errored = true;
          e.printStackTrace();
          break;
        }
        break;

      case "nodes.ProgramOP":
        try {
          semanticProgramOP(visitable);
        } catch (NotDeclarationException | TypeMismatchException e) {
          errored = true;
          e.printStackTrace();
        }
        break;

      case "nodes.ReadOP":
        try {
          semanticReadOP(visitable);
        } catch (NotDeclarationException | TypeMismatchException e) {
          errored = true;
          e.printStackTrace();
          break;
        }
        break;

      case "nodes.ReturnOP":
        semanticReturnOP(visitable);
        break;

      case "nodes.StringConstOP":
        semanticStringConst(visitable);
        break;

      case "nodes.UminusOP":
        try {
          semanticUminusOP(visitable);
        } catch (TypeMismatchException e) {
          errored = true;
          e.printStackTrace();
          break;
        }
        break;

      case "nodes.VarDeclOP":
        try {
          semanticVarDeclOP(visitable);
        } catch (MultipleDeclarationException | TypeMismatchException e) {
          errored = true;
          e.printStackTrace();
          break;
        }
        break;

      case "nodes.WhileOP":
        try {
          semanticWhileOP(visitable);
        } catch (TypeMismatchException e) {
          errored = true;
          e.printStackTrace();
          break;
        }
        break;

      case "nodes.WriteOP":
        semanticWriteOP(visitable);
        break;

      default:
        System.out.println("[ERROR] -> No corresponding class definition found in SemanticVisitor for " + className);
        break;
      }
    return null;
  }


  private void semanticAddOP(Visitable visitable) throws TypeMismatchException {
    AddOP expr = (AddOP) visitable;
    expr.getExpr1().accept(this);
    expr.getExpr2().accept(this);
    String resultType = TypeChecker.typeCheck(TypeChecker.AddOP, expr.getExpr1().getNodeType(), expr.getExpr2().getNodeType());
    expr.setNodeType(resultType);
  }

  private void semanticAndOP(Visitable visitable) throws TypeMismatchException {
    AndOP expr = (AndOP) visitable;
    expr.getExpr1().accept(this);
    expr.getExpr2().accept(this);
    String resultType = TypeChecker.typeCheck(TypeChecker.AndOp, expr.getExpr1().getNodeType(), expr.getExpr2().getNodeType());
    expr.setNodeType(resultType);
  }

  private void semanticAssignOP(Visitable visitable) throws NotDeclarationException, TypeMismatchException {
    AssignOP expr = (AssignOP) visitable;
    expr.getId().accept(this);
    expr.getExpr().accept(this);
    String resultType = TypeChecker.typeCheck(TypeChecker.AssignOP, expr.getId().getNodeType(), expr.getExpr().getNodeType());
    expr.setNodeType(resultType);
  }

  private void semanticBodyOP(Visitable visitable) {
    BodyOP bodyOP = (BodyOP) visitable;
    for (Stat stat : bodyOP.getStat()) {
      stat.accept(this);
    }
  }

  private void semanticBooleanConst(Visitable visitable) {
    BooleanConst booleanConst = (BooleanConst) visitable;
    booleanConst.setNodeType(TypeChecker.BOOL);
  }

  private void semanticDiffOP(Visitable visitable) throws TypeMismatchException {
    DiffOP expr = (DiffOP) visitable;
    expr.getExpr1().accept(this);
    expr.getExpr2().accept(this);
    String resultType = TypeChecker.typeCheck(TypeChecker.DiffOp, expr.getExpr1().getNodeType(), expr.getExpr2().getNodeType());
    expr.setNodeType(resultType);
  }

  private void semanticDivOP(Visitable visitable) throws TypeMismatchException {
    DivOP expr = (DivOP) visitable;
    expr.getExpr1().accept(this);
    expr.getExpr2().accept(this);
    String resultType = TypeChecker.typeCheck(TypeChecker.DivOp, expr.getExpr1().getNodeType(), expr.getExpr2().getNodeType());
    expr.setNodeType(resultType);
  }

  private void semanticEqOP(Visitable visitable) throws TypeMismatchException {
    EqOP expr = (EqOP) visitable;
    expr.getExpr1().accept(this);
    expr.getExpr2().accept(this);
    String resultType = TypeChecker.typeCheck(TypeChecker.EqOp, expr.getExpr1().getNodeType(), expr.getExpr2().getNodeType());
    expr.setNodeType(resultType);
  }

  private void semanticFloatConst(Visitable visitable) {
    FloatConstOP floatConst = (FloatConstOP) visitable;
    floatConst.setNodeType(TypeChecker.FLOAT);
  }

  private void semanticForOP(Visitable visitable) throws TypeMismatchException {
    ForOP expr = (ForOP) visitable;
    SymbolTable symTable = new SymbolTable("ForOP");
    stack.push(symTable);
    expr.getId().accept(this);
    expr.getExpr1().accept(this);

    TypeChecker.typeCheck(TypeChecker.AssignOP, expr.getId().getNodeType(), expr.getExpr1().getNodeType());
    expr.getExpr2().accept(this);
    expr.getBody().accept(this);
    expr.setSymbolTable(stack.pop());
    expr.setNodeType(TypeChecker.VOID);
  }

  private void semanticFunCallOP(Visitable visitable) throws NotDeclarationException, TypeMismatchException, ParameterError {
    FunCallOP funCallOP = (FunCallOP) visitable;
    Identifier functionId = findInStackTables(funCallOP.getId().getId());
    if (functionId.isFunction()) {
      ArrayList<Expr> exprs = funCallOP.getExprs();
      for (Expr e : exprs) {
        e.accept(this);
      }
      if(exprs.size() == functionId.getParDeclOP().size()) {
        for (int i = 0; i < exprs.size(); i++) {
          TypeChecker.typeCheck(TypeChecker.FunCallOP, exprs.get(i).getNodeType(), functionId.getParDeclOP().get(i).getIdentifier().getNodeType());
        }
      } else {
        throw new ParameterError("Error with the parameters number in the CallOp");
      }
    } else {
      throw new NotDeclarationException("\nFunction " + functionId.getId() + " does not exist\n");
    }
    funCallOP.setNodeType(functionId.getNodeType());
  }

  private void semanticFunDefinOP(Visitable visitable) throws MultipleDeclarationException, TypeMismatchException {
    FunDefinOP funDefinOP = (FunDefinOP) visitable;
    stack.peek().installID(funDefinOP.getId());
    SymbolTable symTable = new SymbolTable("FunDefinOP");
    stack.push(symTable);

    for (ParDeclOP parDeclOP : funDefinOP.getParDeclOPS()) {
      parDeclOP.accept(this);
    }
    funDefinOP.getBody().accept(this);


    ArrayList<Stat> stats=funDefinOP.getBody().getStat();
    for (Stat stat : stats)
    {
      controlloTipoRestituito(stat, funDefinOP.getId().getNodeType());
    }


    funDefinOP.setSymbolTable(stack.pop());
    funDefinOP.setNodeType(TypeChecker.VOID);
  }

  private void controlloTipoRestituito(Stat stat, String type) throws TypeMismatchException {
    if(stat.getClass().getName().equals("nodes.ReturnOP")) {
      ReturnOP returnOP=(ReturnOP) stat;
      if(!returnOP.getExpr1().getNodeType().equals(type)) {
        throw new TypeMismatchException("Il tipo da restituire ed il valore restituito non corrispondono");
      }
    } else if(stat.getClass().getName().equals("nodes.WhileOP")) {
      WhileOP whileOP=(WhileOP) stat;
      ArrayList<Stat> stats=whileOP.getBodyOP().getStat();
      for(Stat s : stats) {
        controlloTipoRestituito(s, type);
      }
    } else if(stat.getClass().getName().equals("nodes.IfThenOP")) {
      IfThenOP ifThenOP=(IfThenOP) stat;
      ArrayList<Stat> stats=ifThenOP.getBody().getStat();
      for(Stat s : stats) {
        controlloTipoRestituito(s, type);
      }
    } else if(stat.getClass().getName().equals("nodes.IfThenElseOP")) {
      IfThenElseOP ifThenElseOP=(IfThenElseOP) stat;
      ArrayList<Stat> stats1=ifThenElseOP.getBody1().getStat();
      for(Stat s : stats1) {
        controlloTipoRestituito(s, type);
      }
      ArrayList<Stat> stats2=ifThenElseOP.getBody2().getStat();
      for(Stat s : stats2) {
        controlloTipoRestituito(s, type);
      }
    } else if(stat.getClass().getName().equals("nodes.ForOP")) {
      ForOP forOP=(ForOP) stat;
      ArrayList<Stat> stats=forOP.getBody().getStat();
      for(Stat s : stats) {
        controlloTipoRestituito(s, type);
      }
    } else if(stat.getClass().getName().equals("nodes.LocalBlock")) {
      LocalBlock localBlock=(LocalBlock) stat;
      ArrayList<Stat> stats=localBlock.getBodyOP().getStat();
      for(Stat s : stats) {
        controlloTipoRestituito(s, type);
      }
    }
  }

  private void semanticGeOP(Visitable visitable) throws TypeMismatchException {
    GeOP expr = (GeOP) visitable;
    expr.getExpr1().accept(this);
    expr.getExpr2().accept(this);
    String resultType = TypeChecker.typeCheck(TypeChecker.RelOP, expr.getExpr1().getNodeType(), expr.getExpr2().getNodeType());
    expr.setNodeType(resultType);
  }

  private void semanticGtOP(Visitable visitable) throws TypeMismatchException {
    GtOP expr = (GtOP) visitable;
    expr.getExpr1().accept(this);
    expr.getExpr2().accept(this);
    String resultType = TypeChecker.typeCheck(TypeChecker.RelOP, expr.getExpr1().getNodeType(), expr.getExpr2().getNodeType());
    expr.setNodeType(resultType);
  }

  private void semanticIdentifier(Visitable visitable) throws NotDeclarationException {
    Identifier identifier = (Identifier) visitable;
    Identifier resultId = findInStackTables(identifier.getId());
    identifier.setNodeType(resultId.getNodeType());
  }

  private void semanticIfThenElseOp(Visitable visitable) throws TypeMismatchException {
    IfThenElseOP expr = (IfThenElseOP) visitable;
    expr.getExpr().accept(this);
    expr.getBody1().accept(this);
    expr.getBody2().accept(this);
    TypeChecker.typeCheck(TypeChecker.Conditional, expr.getExpr().getNodeType());
    expr.setNodeType(TypeChecker.VOID);
  }

  private void semanticIfThenOp(Visitable visitable) throws TypeMismatchException {
    IfThenOP expr = (IfThenOP) visitable;
    expr.getExpr().accept(this);
    expr.getBody().accept(this);
    TypeChecker.typeCheck(TypeChecker.Conditional, expr.getExpr().getNodeType());
    expr.setNodeType(TypeChecker.VOID);
  }

  private void semanticIntConst(Visitable visitable) {
    IntConstOP intConst = (IntConstOP) visitable;
    intConst.setNodeType(TypeChecker.INT);
  }

  private void semanticLeOP(Visitable visitable) throws TypeMismatchException {
    LeOP expr = (LeOP) visitable;
    expr.getExpr1().accept(this);
    expr.getExpr2().accept(this);
    String resultType = TypeChecker.typeCheck(TypeChecker.RelOP, expr.getExpr1().getNodeType(), expr.getExpr2().getNodeType());
    expr.setNodeType(resultType);
  }

  private void semanticLocalBlock(Visitable visitable) {
    LocalBlock localBlock = (LocalBlock) visitable;
    SymbolTable symTable = new SymbolTable("LocalBlock");
    stack.push(symTable);
    for (VarDeclOP varDeclOP : localBlock.getVarDeclOPS()) {
      varDeclOP.accept(this);
    }
    localBlock.getBodyOP().accept(this);
    localBlock.setSymbolTable(stack.pop());
    localBlock.setNodeType(TypeChecker.VOID);
  }

  private void semanticLtOP(Visitable visitable) throws TypeMismatchException {
    LtOP expr = (LtOP) visitable;
    expr.getExpr1().accept(this);
    expr.getExpr2().accept(this);
    String resultType = TypeChecker.typeCheck(TypeChecker.RelOP, expr.getExpr1().getNodeType(), expr.getExpr2().getNodeType());
    expr.setNodeType(resultType);
  }

  private void semanticMulOP(Visitable visitable) throws TypeMismatchException {
    MulOP expr = (MulOP) visitable;
    expr.getExpr1().accept(this);
    expr.getExpr2().accept(this);
    String resultType = TypeChecker.typeCheck(TypeChecker.MulOP, expr.getExpr1().getNodeType(), expr.getExpr2().getNodeType());
    expr.setNodeType(resultType);
  }

  private void semanticNeOP(Visitable visitable) throws TypeMismatchException {
    NeOP expr = (NeOP) visitable;
    expr.getExpr1().accept(this);
    expr.getExpr2().accept(this);
    String resultType = TypeChecker.typeCheck(TypeChecker.EqOp, expr.getExpr1().getNodeType(), expr.getExpr2().getNodeType());
    expr.setNodeType(resultType);
  }

  private void semanticNilOP(Visitable visitable){
    NilOP expr = (NilOP) visitable;
    expr.setNodeType(TypeChecker.Nil);
  }

  private void semanticNop(Visitable visitable){
    Nop expr = (Nop) visitable;
    expr.setNodeType(TypeChecker.VOID);
  }

  private void semanticNotOP(Visitable visitable) throws TypeMismatchException {
    NotOP expr = (NotOP) visitable;
    expr.getExpr1().accept(this);
    String resultType = TypeChecker.typeCheck(TypeChecker.NotOP, expr.getExpr1().getNodeType());
    expr.setNodeType(resultType);
  }

  private void semanticOrOP(Visitable visitable) throws TypeMismatchException {
    OrOP expr = (OrOP) visitable;
    expr.getExpr1().accept(this);
    expr.getExpr2().accept(this);
    String resultType = TypeChecker.typeCheck(TypeChecker.OrOP, expr.getExpr1().getNodeType(), expr.getExpr2().getNodeType());
    expr.setNodeType(resultType);
  }

  private void semanticParDeclOP(Visitable visitable) throws MultipleDeclarationException {
    ParDeclOP parDeclOP = (ParDeclOP) visitable;
    stack.peek().installID(parDeclOP.getIdentifier());
  }

  private void semanticProgramOP(Visitable visitable) throws NotDeclarationException, TypeMismatchException {
    ProgramOP programOP = (ProgramOP) visitable;

    SymbolTable symTable = new SymbolTable("ProgramOP");
    stack.push(symTable);
    if(programOP.getVar()!=null)
    {
      for (VarDeclOP varDeclOP : programOP.getVar()) {
        varDeclOP.accept(this);
      }
    }
    for (FunDefinOP funDefinOP : programOP.getFun()) {
      funDefinOP.accept(this);
    }
    Identifier identifier = findInStackTables(TypeChecker.Main);
    if(identifier.getNodeType()!=TypeChecker.INT) {
      throw new TypeMismatchException("La classe main si aspetta il tipo int");
    }
    programOP.setSymbolTable(stack.pop());
    programOP.setNodeType(TypeChecker.VOID);
  }

  private void semanticReadOP(Visitable visitable) throws NotDeclarationException, TypeMismatchException {
    ReadOP readOP = (ReadOP) visitable;
    ArrayList<Identifier> identifiers = readOP.getIdentifiers();
    for (Identifier identifier : identifiers) {
      Identifier id = findInStackTables(identifier.getId());
      identifier.setNodeType(id.getNodeType());
      TypeChecker.typeCheck(TypeChecker.ReadOP,id.getNodeType());
    }
    readOP.setNodeType(TypeChecker.VOID);
  }

  private void semanticReturnOP(Visitable visitable) {
    ReturnOP returnOP = (ReturnOP) visitable;
    returnOP.getExpr1().accept(this);
    returnOP.setNodeType(returnOP.getExpr1().getNodeType());
  }

  private void semanticStringConst(Visitable visitable) {
    StringConstOP stringConst = (StringConstOP) visitable;
    stringConst.setNodeType(TypeChecker.STRING);
  }

  private void semanticUminusOP(Visitable visitable) throws TypeMismatchException {
    UminusOP expr = (UminusOP) visitable;
    expr.getExpr1().accept(this);
    String resultType = TypeChecker.typeCheck(TypeChecker.UminusOP, expr.getExpr1().getNodeType());
    expr.setNodeType(resultType);
  }

  private void semanticVarDeclOP(Visitable visitable) throws MultipleDeclarationException, TypeMismatchException {
    VarDeclOP varDeclOP = (VarDeclOP) visitable;
    stack.peek().installID(varDeclOP.getIdentifier());
    if(varDeclOP.getExpr1()!=null)
    {
      varDeclOP.getExpr1().accept(this);
      String resultType = TypeChecker.typeCheck(TypeChecker.AssignOP, varDeclOP.getIdentifier().getNodeType(),
              varDeclOP.getExpr1().getNodeType());
      varDeclOP.setNodeType(resultType);
    }
  }

  private void semanticWhileOP(Visitable visitable) throws TypeMismatchException {
    WhileOP whileOP = (WhileOP) visitable;
    whileOP.getExpr1().accept(this);
    TypeChecker.typeCheck(TypeChecker.Conditional, whileOP.getExpr1().getNodeType());
    whileOP.getBodyOP().accept(this);
    whileOP.setNodeType(TypeChecker.VOID);
  }

  private void semanticWriteOP(Visitable visitable) {
    WriteOP writeOP = (WriteOP) visitable;
    for (Expr e : writeOP.getExprs()) {
      e.accept(this);
    }
    writeOP.setNodeType(TypeChecker.VOID);
  }

  private Identifier findInStackTables(String id) throws NotDeclarationException {
    for(int i = stack.size() - 1; i > -1 ; i--) {
      SymbolTable temp = stack.get(i);
      if(temp.containsKey(id)) {
        return temp.get(id);
      }
    }
    throw new NotDeclarationException("ID: " + id + " not declarated!");
  }

  public boolean errored() {
    return errored;
  }
}
