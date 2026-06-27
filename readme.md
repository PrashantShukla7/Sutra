# 🕉️ Sutra Programming Language

> **A Sanskrit-inspired interpreted programming language built from scratch in Java.**

Sutra is an educational programming language designed to understand how programming languages work internally. It is implemented entirely in Java and follows the traditional interpreter architecture:

```
Source Code
     │
     ▼
Lexer
     │
     ▼
Parser
     │
     ▼
Abstract Syntax Tree (AST)
     │
     ▼
Interpreter
     │
     ▼
Program Output
```

The project is built incrementally, with every feature implemented from scratch without using parser generators or compiler frameworks.

---

# ✨ Features

## Variables

Declare variables using the `man` keyword.

```sutra
man x = 10;
man y = 20;
```

---

## Variable Assignment

```sutra
x = x + 5;
```

---

## Arithmetic Operators

Sutra currently supports:

| Operator | Description |
|----------|-------------|
| `+` | Addition |
| `-` | Subtraction |
| `*` | Multiplication |
| `/` | Division |
| `%` | Modulus |

Example:

```sutra
man a = 30;
man b = 4;

bolo(a % b);
```

Output

```
2
```

---

## Comparison Operators

Supported comparison operators:

| Operator | Description |
|----------|-------------|
| `>` | Greater Than |
| `<` | Less Than |
| `>=` | Greater Than or Equal |
| `<=` | Less Than or Equal |
| `==` | Equal |
| `!=` | Not Equal |

Example

```sutra
man age = 18;

yadi(age >= 18){
    bolo("Adult");
}
```

---

## Conditional Statements

Sutra supports conditional execution using Sanskrit-inspired keywords.

```sutra
man x = 10;
man y = 20;

yadi(x > y){
    bolo("x is greater");
}
anyatha{
    bolo("y is greater");
}
```

---

## Blocks

Multiple statements can be grouped using braces.

```sutra
yadi(x > 5){

    bolo(x);

    x = x + 10;

    bolo(x);

}
```

---

## Printing

Print variables

```sutra
bolo(x);
```

Print expressions

```sutra
bolo(x + y);
```

Print strings

```sutra
bolo("Namaste Sutra!");
```

---

## String Literals

Sutra supports string literals enclosed in double quotes.

```sutra
bolo("Hello World");
```

---

# Example Program

```sutra
man x = 30;
man y = 4;

bolo("Checking Even Number");

yadi(x % 2 == 0){
    bolo("Even");
}
anyatha{
    bolo("Odd");
}
```

Output

```
Checking Even Number
Even
```


---

# Running Sutra

## Compile

```bash
javac Main.java lexer/*.java parser/*.java ast/*.java interpreter/*.java
```

## Run

```bash
java Main Test_programs/test.sut
```

---

# Current Keywords

| Keyword | Meaning |
|----------|---------|
| `man` | Variable Declaration |
| `bolo` | Print |
| `yadi` | If |
| `anyatha` | Else |

---

# Current Architecture

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
Recursive Descent Parser
      │
      ▼
Abstract Syntax Tree
      │
      ▼
Visitor Pattern
      │
      ▼
Interpreter
```

---

# Features Implemented

- ✅ Lexer
- ✅ Tokenizer
- ✅ Recursive Descent Parser
- ✅ AST (Abstract Syntax Tree)
- ✅ Visitor Pattern
- ✅ Tree Walking Interpreter
- ✅ Variables
- ✅ Variable Assignment
- ✅ Integer Literals
- ✅ String Literals
- ✅ Arithmetic Operators
- ✅ Comparison Operators
- ✅ Equality Operators
- ✅ Print Statements
- ✅ Block Statements
- ✅ If / Else Statements
- ✅ Program Execution from `.sut` files

---

## Developer Features

- Better parser error messages
- Runtime stack traces
- REPL (Interactive Shell)
- Package manager

---
