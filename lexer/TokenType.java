package lexer;

import org.w3c.dom.ls.LSResourceResolver;

public enum TokenType {

    // keywords
    LET,
    PRINT,
    IF,
    ELSE,
    TRUE,
    FALSE,

    // literals
    IDENTIFIER,
    NUMBER,

    // operators
    PLUS,
    MINUS,
    STAR,
    SLASH,
    MODULO,
    EQUAL,
    GREATER,
    LESS,
    GREATER_EQUAL,
    LESS_EQUAL,
    EQUAL_EQUAL,
    NOT_EQUAL,
    NOT,


    // symbols
    LEFT_PAREN,
    RIGHT_PAREN,
    LEFT_BRACE,
    RIGHT_BRACE,
    STRING,



    SEMICOLON,

    EOF
}