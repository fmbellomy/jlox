package jlox.interpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    static boolean hadError = false;
    static boolean hadRuntimeError = false;
    private static final Interpreter interpreter = new Interpreter();

    public static void main(String[] args) throws IOException {

        if (args.length > 1) {
            System.out.println("Usage: jlox [script]");
            System.exit(64);
        } else if (args.length == 1) {
            runFile(args[0]);
        } else {
            runPrompt();
        }
    }

    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));
        // Indicate an error in the exit code.
        if (hadError)
            System.exit(65);
        if (hadRuntimeError)
            System.exit(70);
    }

    private static void runPrompt() throws IOException {
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(in);
        while (true) {
            System.out.print("> ");
            String line = reader.readLine();
            if (line == null)
                break;
            run(line);
            // this line was not in the book but i'm like 99% sure it was supposed to
            // be
            hadError = false;
        }

    }

    private static void run(String src) {
        Scanner scanner = new Scanner(src);
        List<Token> tkns = scanner.scanTokens();
        Parser parser = new Parser(tkns);
        List<Stmt> statements = parser.parse();
        if (hadError)
            return;
        // we resolving!!!1!
        Resolver resolver = new Resolver(interpreter);
        resolver.resolve(statements);
        if (hadError)
            return;
        interpreter.interpret(statements);
    }

    static void error(int line, String msg) {
        report(line, "", msg);
    }

    static void error(Token token, String message) {
        if (token.type == TokenType.EOF) {
            report(token.line, "at end", message);
        } else {
            report(token.line, "at '" + token.lexeme + "'", message);
        }
    }

    static void runtimeError(RuntimeError error) {
        System.err.println(error.getMessage() +
                "\n[line " + error.token.line + "]");
        hadRuntimeError = true;
    }

    private static void report(int line, String where, String msg) {
        System.err.println("[line " + line + "] Error " + where + ": " + msg);
        hadError = true;
    }
}
