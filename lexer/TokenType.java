package lexer;

public enum TokenType {

    // keywords
    LET,
    PRINT,

    // literals
    IDENTIFIER,
    NUMBER,

    // operators
    PLUS,
    MINUS,
    STAR,
    SLASH,
    EQUAL,

    // symbols
    LEFT_PAREN,
    RIGHT_PAREN,

    SEMICOLON,

    EOF
}