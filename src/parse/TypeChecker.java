package parse;

import exception.TypeMismatchException;

public class TypeChecker {
    public static final String INT = "int";
    public static final String FLOAT = "float";
    public static final String STRING = "string";
    public static final String BOOL = "bool";
    public static final String VOID = "void";
    public static final String TRUE = "true";
    public static final String FALSE = "false";
    public static final String AddOP = "AddOP";
    public static final String AndOp = "AndOp";
    public static final String AssignOP = "AssignOP";
    public static final String Conditional = "Conditional";
    public static final String DiffOp = "DiffOp";
    public static final String DivOp = "DivOp";
    public static final String EqOp = "EqOp";
    public static final String FunCallOP = "FunCallOP";
    public static final String FUNCTION = "Function";
    public static final String Main = "main";
    public static final String MulOP = "MulOP";
    public static final String Nil = "NilOP";
    public static final String NotOP = "NotOP";
    public static final String OrOP = "OrOP";
    public static final String ReadOP = "ReadOP";
    public static final String RelOP = "RelOP";
    public static final String UminusOP = "UminusOP";
    public static final String VARIABLE = "Variable";

    public TypeChecker() {
    }

    public static String typeCheck(String op, String type1) throws TypeMismatchException {
        if (op.equals(UminusOP)) {
            return typeCheckUminusOP(type1);
        } else if (op.equals(ReadOP)) {
            return typeCheckReadOP(type1);
        } else if (op.equals(NotOP)) {
            return typeCheckNotOP(type1);
        } else if (op.equals(Conditional)) {
            return typeCheckConditionOP(type1);
        } else {
            throw new TypeMismatchException("Operation " + op + " wrong for the type " + type1);
        }
    }

    public static String typeCheck(String op, String type1, String type2) throws TypeMismatchException {
        if (op.equals(AddOP)) {
            return typeCheckAddOp(type1, type2);
        } else if (op.equals(MulOP) || op.equals(DiffOp) || op.equals(DivOp)) {
            return typeCheckAritmeticOP(type1, type2);
        } else if (op.equals(RelOP)) {
            return typeCheckRelationalOP(type1, type2);
        }  else if (op.equals(EqOp)) {
            return typeCheckEqOP(type1, type2);
        } else if (op.equals(AssignOP)) {
            return typeCheckAssignOP(type1, type2);
        } else if (op.equals(FunCallOP)) {
            return typeCheckFunCallOP(type1, type2);
        } else if (op.equals(OrOP) || op.equals(AndOp)) {
            return typeCheckOrAndOP(type1, type2);
        }
        throw new TypeMismatchException("Operation " + op + " wrong for the type " + type1);
    }

    //addizione
    private static String typeCheckAddOp(String type1, String type2) throws TypeMismatchException {
        if (type1.equals(INT) && type2.equals(INT)) {
            return INT;
        } else if (type1.equals(INT) && type2.equals(FLOAT)) {
            return FLOAT;
        } else if (type1.equals(INT) && type2.equals(STRING)) {
            return STRING;
        } else if (type1.equals(FLOAT) && type2.equals(FLOAT)) {
            return FLOAT;
        } else if (type1.equals(FLOAT) && type2.equals(INT)) {
            return FLOAT;
        } else if (type1.equals(FLOAT) && type2.equals(STRING)) {
            return STRING;
        } else if (type1.equals(STRING) && type2.equals(STRING)) {
            return STRING;
        } else if (type1.equals(STRING) && type2.equals(INT)) {
            return STRING;
        } else if (type1.equals(STRING) && type2.equals(FLOAT)) {
            return STRING;
        } else {
            throw new TypeMismatchException("Can't add " + type2 + " to " + type1);
        }
    }

    //AND e  OR
    private static String typeCheckOrAndOP(String type1, String type2) throws TypeMismatchException {
        if (type1.equals(BOOL) && type2.equals(BOOL)) {
            return BOOL;
        } else {
            throw new TypeMismatchException("Can't and/or between not boolean values");
        }
    }

    // moltiplicazione, divisione, differenza
    private static String typeCheckAritmeticOP(String type1, String type2) throws TypeMismatchException {
        if (type1.equals(INT) && type2.equals(INT)) {
            return INT;
        } else if (type1.equals(INT) && type2.equals(FLOAT)) {
            return FLOAT;
        } else if (type1.equals(FLOAT) && type2.equals(INT)) {
            return FLOAT;
        } else if (type1.equals(FLOAT) && type2.equals(FLOAT)) {
            return FLOAT;
        } else {
            throw new TypeMismatchException("Can't mul div or sub " + type2 + " to " + type1);
        }
    }

    //assegnamento e vardecl
    private static String typeCheckAssignOP(String type1, String type2) throws TypeMismatchException {
        if (type1.equals(INT) && type2.equals(INT)) {
            return INT;
        } else if (type1.equals(FLOAT) && type2.equals(FLOAT)) {
            return FLOAT;
        } else if (type1.equals(BOOL) && type2.equals(BOOL)) {
            return BOOL;
        } else if (type1.equals(STRING) && type2.equals(STRING)) {
            return STRING;
        } else if (type1.equals(STRING) && type2.equals(Nil)) {
            return STRING;
        } else {
            throw new TypeMismatchException("Can't assign " + type2 + " to " + type1);
        }
    }

    //uguaglianza
    private static String typeCheckEqOP(String type1, String type2) throws TypeMismatchException {
        return type1.equals(BOOL) && type2.equals(BOOL) ? BOOL : typeCheckRelationalOP(type1, type2);
    }

    //chiamata di funzione
    private static String typeCheckFunCallOP(String type1, String type2) throws TypeMismatchException {
        if (type1.equals(INT) && type2.equals(INT)) {
            return INT;
        } else if (type1.equals(FLOAT) && type2.equals(FLOAT)) {
            return FLOAT;
        } else if (type1.equals(BOOL) && type2.equals(BOOL)) {
            return BOOL;
        } else if (type1.equals(STRING) && type2.equals(STRING)) {
            return STRING;
        } else {
            throw new TypeMismatchException("Expected type " + type2 + " from the definition. Encountered " + type1);
        }
    }

    //gt,ge,lt,le,ne
    private static String typeCheckRelationalOP(String type1, String type2) throws TypeMismatchException {
        if (type1.equals(INT) && type2.equals(INT)) {
            return BOOL;
        } else if (type1.equals(INT) && type2.equals(FLOAT)) {
            return BOOL;
        }  else if (type1.equals(FLOAT) && type2.equals(INT)) {
            return BOOL;
        } else if (type1.equals(FLOAT) && type2.equals(FLOAT)) {
            return BOOL;
        } else if (type1.equals(STRING) && type2.equals(STRING)) {
            return BOOL;
        } else {
            throw new TypeMismatchException("Can't make a relation between " + type2 + " and " + type1);
        }
    }

    // if then ,if then else, while
    private static String typeCheckConditionOP(String type) throws TypeMismatchException {
        if (type.equals(BOOL)) {
            return VOID;
        } else {
            throw new TypeMismatchException("The condition, which is " + type + " , has to be bool (true)");
        }
    }

    //not
    private static String typeCheckNotOP(String type) throws TypeMismatchException {
        if (type.equals(BOOL)) {
            return BOOL;
        } else {
            throw new TypeMismatchException("Can't negate something different of bool");
        }
    }

    //read
    private static String typeCheckReadOP(String type1) throws TypeMismatchException {
        if (!type1.equals(BOOL)) {
            return VOID;
        } else {
            throw new TypeMismatchException("Can't read bool");
        }
    }

    //uminus
    private static String typeCheckUminusOP(String type1) throws TypeMismatchException {
        if (type1.equals(INT)) {
            return INT;
        } else if (type1.equals(FLOAT)) {
            return FLOAT;
        } else {
            throw new TypeMismatchException("Can't make uminus of " + type1);
        }
    }
}
