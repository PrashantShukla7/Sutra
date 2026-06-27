import java.util.List;

import ast.Stmt;
import interpreter.Interpreter;
import lexer.Lexer;
import parser.Parser;

public class Sutra {

    private final Interpreter interpreter =
            new Interpreter();

    public void run(String source){

        Lexer lexer = new Lexer(source);

        Parser parser =
                new Parser(lexer.scanTokens());

        List<Stmt> program =
                parser.parse();

        interpreter.interpret(program);
    }
}