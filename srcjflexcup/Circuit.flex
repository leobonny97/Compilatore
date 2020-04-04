import java_cup.runtime.*;
import java.io.*;
import java.util.HashMap;

%%
%class Lexer
%unicode
%cupsym sym
%cup
%public
%line
%column

%{
        private FileReader reader;
        private HashMap<String, Symbol> stringTable;
        private  File file;

        public Lexer()
        {
            stringTable = new HashMap<String, Symbol>();
        }

        public Boolean initialize(String filePath)
        {
            file = new File(filePath);

            if (file.exists())
            {
                try {
                    reader = new FileReader(file);
                    this.zzReader = reader;
                } catch (FileNotFoundException e) {
                    return false;
                }
                return true;
            }
            else
            {
                return false;
            }

        }

        public void insertID(String lessema)
       {
            if(!stringTable.containsKey(lessema))
            {
               Symbol symbol= new Symbol(sym.ID,lessema);
               stringTable.put(lessema,symbol);
            }
       }
%}

id = [:jletter:] [:jletterdigit:]*
digit =[0-9]
digits= {digit}+
float_const = {digits} (.{digits})
int_const = {digits}
string_const = (\") [^\"]* (\")
lineTerminator = \r|\n|\r\n
whitespace = {lineTerminator} | [ \t\f]
comment=("/*" [^*] ~"*/" | "/*" "*"+ "/")|("//"~"\r")

%%

//parole chiavi
"function"      { return new Symbol(sym.FUNCTION); }
"end"           { return new Symbol(sym.END); }
"global"        { return new Symbol(sym.GLOBAL); }
"local"         { return new Symbol(sym.LOCAL); }
"if"            { return new Symbol(sym.IF); }
"then"          { return new Symbol(sym.THEN); }
"else"          { return new Symbol(sym.ELSE); }
"while"         { return new Symbol(sym.WHILE); }
"do"            { return new Symbol(sym.DO); }
"for"           { return new Symbol(sym.FOR); }
"int"           { return new Symbol(sym.INT); }
"bool"          { return new Symbol(sym.BOOL); }
"true"          { return new Symbol(sym.TRUE); }
"false"         { return new Symbol(sym.FALSE); }
"float"         { return new Symbol(sym.FLOAT); }
"string"        { return new Symbol(sym.STRING); }
"nil"           { return new Symbol(sym.NIL); }
"nop"           { return new Symbol(sym.NOP); }
"return"        { return new Symbol(sym.RETURN); }

//Operazioni aritmetiche, logiche e relazionali
"+"		    { return new Symbol(sym.PLUS); }
"-"		    { return new Symbol(sym.MINUS); }
"/"         { return new Symbol(sym.DIV); }
"*"         { return new Symbol(sym.TIMES); }
"and"       { return new Symbol(sym.AND); }
"or"        { return new Symbol(sym.OR); }
"not"       { return new Symbol(sym.NOT); }
">"		    { return new Symbol(sym.GT); }
">="        { return new Symbol(sym.GE); }
"<"         { return new Symbol(sym.LT); }
"<="        { return new Symbol(sym.LE); }
"=="        { return new Symbol(sym.EQ); }
"!="        { return new Symbol(sym.NE); }

{id}		    { this.insertID(yytext()); return new Symbol(sym.ID, yytext()); }
"="		        { return new Symbol(sym.ASSIGN); }
"<=="           { return new Symbol(sym.READ); }
"==>"           { return new Symbol(sym.WRITE); }
{int_const} 	{ return new Symbol(sym.INT_CONST ,yytext()); }
{float_const} 	{ return new Symbol(sym.FLOAT_CONST ,yytext()); }
{string_const} 	{ return new Symbol(sym.STRING_CONST ,yytext()); }

//Separator
"("         { return new Symbol(sym.LPAR); }
")"         { return new Symbol(sym.RPAR); }
";"         { return new Symbol(sym.SEMI); }
","         { return new Symbol(sym.COMMA); }
":"         { return new Symbol(sym.COLON); }

{comment}    { /* ignore */ }
{whitespace}    { /* ignore */ }
[^]	            { return new Symbol(sym.error,yytext());}


