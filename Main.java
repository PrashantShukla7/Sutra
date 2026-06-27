import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import interpreter.Interpreter;
import lexer.Lexer;
import parser.Parser;

public class Main {

    public static void main(String[] args)
            throws Exception {

        Sutra sutra = new Sutra();

        if(args.length > 0){

            String source =
                Files.readString(Path.of(args[0]));

            sutra.run(source);

        }else{

            // Start REPL

            Scanner scanner = new Scanner(System.in);

            Interpreter interpreter = new Interpreter();

            while(true){

                System.out.print(">> ");

                String line = scanner.nextLine();

                if(line.equals("exit"))
                    break;

                Lexer lexer = new Lexer(line);

                Parser parser = new Parser(lexer.scanTokens());

                interpreter.interpret(parser.parse());
            }

            scanner.close();
        }
    }
}