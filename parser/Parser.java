package parser;

import ast.Expr;
import ast.Stmt;
import lexer.Token;
import lexer.TokenType;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    private final List<Token> tokens;
    private int current = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public List<Stmt> parse() {

        // for(Token token: tokens) {
        //     System.out.println(token);
        // }

        List<Stmt> statements = new ArrayList<>();

        while (!isAtEnd()) {
            statements.add(declaration());
        }

        return statements;
    }

    // -----------------------------------------------------

    private Stmt declaration() {

        if (match(TokenType.LET)) {
            return variableDeclaration();
        }

        return statement();
    }

    // let x = expression;

    private Stmt variableDeclaration() {

        Token name = consume(TokenType.IDENTIFIER,
                "Expected variable name.");

        consume(TokenType.EQUAL,
                "Expected '=' after variable name.");

        Expr initializer = expression();

        consume(TokenType.SEMICOLON,
                "Expected ';' after declaration.");

        return new Stmt.Var(name.lexeme, initializer);
    }

    // -----------------------------------------------------

    private Stmt statement() {

        if (match(TokenType.PRINT)) {
            return printStatement();
        }

        if (match(TokenType.LEFT_BRACE)) {
            return block();
        }

        if (match(TokenType.IF)) {
            return ifStatement();
        }

        Expr expr = expression();

        consume(TokenType.SEMICOLON,
                "Expected ';' after expression.");

        return new Stmt.Expression(expr);
    }

    private Stmt printStatement() {

        consume(TokenType.LEFT_PAREN,
                "Expected '(' after print.");

        Expr expr = expression();

        consume(TokenType.RIGHT_PAREN,
                "Expected ')' after expression.");

        consume(TokenType.SEMICOLON,
                "Expected ';' after print.");

        return new Stmt.Print(expr);
    }

    private Stmt ifStatement() {

        consume(TokenType.LEFT_PAREN,
                "Expected '(' after yadi.");

        Expr condition = expression();

        consume(TokenType.RIGHT_PAREN,
                "Expected ')' after condition.");

        // Parse whatever statement comes next
        Stmt thenBranch = statement();

        Stmt elseBranch = null;

        if (match(TokenType.ELSE)) {
            elseBranch = statement();
        }

        return new Stmt.If(
                condition,
                thenBranch,
                elseBranch
        );
    }

    private Stmt block() {

        List<Stmt> statements = new ArrayList<>();

        while (!check(TokenType.RIGHT_BRACE)
                && !isAtEnd()) {

            statements.add(declaration());
        }

        consume(TokenType.RIGHT_BRACE,
                "Expected '}' after block.");

        return new Stmt.Block(statements);
    }

    // -----------------------------------------------------

    private Expr expression() {
        return assignment();
    }

    private Expr assignment() {

        Expr expr = equality();

        if (match(TokenType.EQUAL)) {

            Expr value = assignment();

            if (expr instanceof Expr.Variable variable) {

                return new Expr.Assign(
                        variable.name,
                        value
                );
            }

            throw new RuntimeException(
                    "Invalid assignment target.");
        }

        return expr;
    }

    private Expr equality() {
        Expr expr = comparision();
        while(match(TokenType.EQUAL_EQUAL)) {
            Token operator = previous();

            Expr right = comparision();

            expr = new Expr.Binary(expr, operator.lexeme, right);
        }

        return expr;
    }

    // < > <= >= != 
    private Expr comparision() {
        Expr expr = addition();

        while(match(TokenType.GREATER, TokenType.GREATER_EQUAL, TokenType.LESS, TokenType.LESS_EQUAL, TokenType.NOT_EQUAL, TokenType.EQUAL_EQUAL)) {
            Token operator = previous();

            Expr right = addition();

            expr = new Expr.Binary(expr, operator.lexeme, right);
        }

        return expr;
    }

    // + -

    private Expr addition() {

        Expr expr = multiplication();

        while (match(TokenType.PLUS, TokenType.MINUS)) {

            Token operator = previous();

            Expr right = multiplication();

            expr = new Expr.Binary(
                    expr,
                    operator.lexeme,
                    right
            );
        }

        return expr;
    }

    // * /

    private Expr multiplication() {

        Expr expr = primary();

        while (match(TokenType.STAR, TokenType.SLASH, TokenType.MODULO)) {

            Token operator = previous();

            Expr right = primary();

            expr = new Expr.Binary(
                    expr,
                    operator.lexeme,
                    right
            );
        }

        return expr;
    }

    // numbers, variables, ()

    private Expr primary() {

        if (match(TokenType.NUMBER)) {

            return new Expr.Literal(
                    Integer.parseInt(previous().lexeme)
            );
        }

        if (match(TokenType.IDENTIFIER)) {

            return new Expr.Variable(previous().lexeme);
        }

        if (match(TokenType.LEFT_PAREN)) {

            Expr expr = expression();

            consume(TokenType.RIGHT_PAREN,
                    "Expected ')'.");

            return new Expr.Grouping(expr);
        }

        if (match(TokenType.TRUE)) {
            return new Expr.Literal(true);
        }

        if (match(TokenType.FALSE)) {
            return new Expr.Literal(false);
        }

        if (match(TokenType.STRING)) {
            return new Expr.Literal(previous().lexeme);
        }

        throw error("Expected expression.");
    }

    // -----------------------------------------------------

    private boolean match(TokenType... types) {

        for (TokenType type : types) {

            if (check(type)) {

                advance();

                return true;
            }
        }

        return false;
    }

    private Token consume(TokenType type, String message) {

        if (check(type))
            return advance();

        throw error(message);
    }

    private boolean check(TokenType type) {

        if (isAtEnd())
            return false;

        return peek().type == type;
    }

    private Token advance() {

        if (!isAtEnd())
            current++;

        return previous();
    }

    private boolean isAtEnd() {

        return peek().type == TokenType.EOF;
    }

    private Token peek() {

        return tokens.get(current);
    }

    private Token previous() {

        return tokens.get(current - 1);
    }

    private RuntimeException error(String message) {

        return new RuntimeException(message);
    }
}