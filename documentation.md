# Sutra Documentation

## Architecture

```
Source Code
      │
      ▼
Lexer
      │
      ▼
Tokens
      │
      ▼
Parser
      │
      ▼
AST
      │
      ▼
Interpreter
      │
      ▼
Output
```

---

# Components

## Lexer

Responsibilities

- Reads characters
- Produces tokens
- Identifies keywords
- Identifies operators
- Reads numbers
- Reads identifiers
- Reads string literals

Example

```
man x = 10;
```

becomes

```
LET
IDENTIFIER(x)
EQUAL
NUMBER(10)
SEMICOLON
```

---

## Parser

The parser is a handwritten Recursive Descent Parser.

Current grammar

```
Program

↓

Declaration

↓

Statement

↓

Expression

↓

Assignment

↓

Equality

↓

Comparison

↓

Addition

↓

Multiplication

↓

Primary
```

Operator precedence is handled naturally through this chain.

---

## AST

Expressions

- Literal
- Variable
- Assign
- Binary
- Grouping

Statements

- Variable
- Print
- Expression
- Block
- If

---

## Interpreter

Uses the Visitor Pattern.

Each AST node calls

```java
accept(visitor)
```

The interpreter evaluates expressions recursively.

---

## Runtime

Current runtime supports

- Integer
- Boolean
- String

---

## Supported Operators

### Arithmetic

```
+
-
*
/
%
```

### Comparison

```
>
<
>=
<=
==
!=
```

### Keyword Operators

| Symbol | Keyword |
|---------|----------|
| > | bada |
| < | chhota |
| >= | bada_barabar |
| <= | chhota_barabar |
| == | barabar |
| != | alag |

Both keyword and symbolic operators generate the exact same AST.

---

## Keywords

```
man
bolo
yadi
anyatha
```

---

## Example

```sutra
man x = 30;
man y = 4;

bolo("Checking");

yadi(x % 2 barabar 0){
    bolo("Even");
}
anyatha{
    bolo("Odd");
}
```

---

## Current Features

✅ Variables

✅ Assignment

✅ Strings

✅ Arithmetic

✅ Comparison

✅ Equality

✅ Keyword operators

✅ Print

✅ If Else

✅ Blocks

---

## Future Features

- Logical operators
- Nested scopes
- While loop
- Functions
- Return
- Arrays
- Objects
- Classes
- Imports
- Standard library
- REPL
- Bytecode compiler
- Virtual machine