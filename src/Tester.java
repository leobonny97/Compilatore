import nodes.ProgramOP;
import parse.*;

import java.io.*;

public class Tester {

        public static void main(String[] args) throws Exception {
                String filePathAST = "Tree.xml";
                String filePathSource = "CodiceC.txt";
                String filePath = args[0];
                FileReader k = new FileReader(filePath);
                Lexer lexer=new Lexer();
                lexer.initialize(filePath);
                parser p = new parser(lexer);
                ProgramOP programOP=(ProgramOP) p.debug_parse().value;
                System.out.println(programOP);
                XMLBuilder xmlB = new XMLBuilder();
                programOP.accept(xmlB);
                xmlB.exportDocument(new File(filePathAST));
                SemanticVisitor semanticVisitor = new SemanticVisitor();
                programOP.accept(semanticVisitor);
                CBuilder cBuilder = new CBuilder(new File(filePathSource));
                programOP.accept(cBuilder);
        }
}