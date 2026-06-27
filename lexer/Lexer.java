package lexer;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private final String source;
    private final List<Token> tokens = new ArrayList<>();

    private int current = 0;

    public Lexer(String source) {
        this.source = source;
    }

    public List<Token> scanTokens() {

        while (!isAtEnd()) {

            char c = advance();

            switch (c) {

                case '+':
                    add(TokenType.PLUS, "+");
                    break;

                case '-':
                    add(TokenType.MINUS, "-");
                    break;

                case '*':
                    add(TokenType.STAR, "*");
                    break;

                case '/':
                    add(TokenType.SLASH, "/");
                    break;

                case '=':
                    if(match('='))
                        add(TokenType.EQUAL_EQUAL, "==");
                    else
                        add(TokenType.EQUAL, "=");
                    break;

                case '(':
                    add(TokenType.LEFT_PAREN, "(");
                    break;

                case ')':
                    add(TokenType.RIGHT_PAREN, ")");
                    break;

                case '<':
                    if(match('=')) 
                        add(TokenType.LESS_EQUAL, "<=");
                    else
                        add(TokenType.LESS, "<");
                    break;

                case '>':
                    if(match('=')) {
                        add(TokenType.GREATER_EQUAL, ">=");
                    }
                    else
                        add(TokenType.GREATER, ">");
                    break;

                case '!':
                    if(match('=')) {
                        add(TokenType.NOT_EQUAL, "!=");
                    }
                    else
                        add(TokenType.NOT, "!");
                    break;

                case '{':
                    add(TokenType.LEFT_BRACE, "{");
                    break;
                
                case '}':
                    add(TokenType.RIGHT_BRACE, "}");
                    break;

                case '%':
                    add(TokenType.MODULO, "%");
                    break;

                case '"':
                    string();
                    break;

                case ';':
                    add(TokenType.SEMICOLON, ";");


                case ' ':
                case '\r':
                case '\t':
                case '\n':
                    break;

                default:

                    if (Character.isDigit(c)) {
                        number(c);
                    }

                    else if (Character.isLetter(c)) {
                        identifier(c);
                    }

                    else {
                        throw new RuntimeException("Unexpected character: " + c);
                    }
            }
        }

        tokens.add(new Token(TokenType.EOF, ""));

        return tokens;
    }

    private boolean isAtEnd() {
        return current >= source.length();
    }

    private char advance() {
        return source.charAt(current++);
    }

    private boolean match(char expected) {
        if (isAtEnd()) return false;
        
        if (source.charAt(current) != expected) {
            return false;
        }
        current++;
        return true;
    }

    private void add(TokenType type, String lexeme) {
        tokens.add(new Token(type, lexeme));
    }

    private void number(char first) {

        StringBuilder sb = new StringBuilder();
        sb.append(first);

        while (!isAtEnd() && Character.isDigit(source.charAt(current))) {

            sb.append(source.charAt(current));
            current++;
        }

        add(TokenType.NUMBER, sb.toString());
    }

    private void string() {

        int start = current;

        while (!isAtEnd() && peek() != '"') {
            advance();
        }

        if (isAtEnd()) {
            throw new RuntimeException("Unterminated string.");
        }

        String value = source.substring(start, current);

        // consume closing "
        advance();

        add(TokenType.STRING, value);
    }

    private char peek() {
        if (isAtEnd()) {
            return '\0';
        }
        return source.charAt(current);
    }

    private void identifier(char first) {

        StringBuilder sb = new StringBuilder();
        sb.append(first);

        while (!isAtEnd() &&
                Character.isLetterOrDigit(source.charAt(current))) {

            sb.append(source.charAt(current));
            current++;
        }

        String text = sb.toString();

        switch (text) {

            case "man":
                add(TokenType.LET, text);
                break;

            case "bolo":
                add(TokenType.PRINT, text);
                break;

            case "yadi":
                add(TokenType.IF, text);
                break;
            
            case "anyatha":
                add(TokenType.ELSE, text);
                break;

            case "bada":
                add(TokenType.GREATER, ">");
                break;

            case "true":
                add(TokenType.TRUE, text);
                break;

            case "false":
                add(TokenType.FALSE, text);
                break;

            default:
                add(TokenType.IDENTIFIER, text);
        }
    }
}