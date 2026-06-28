# 🕉️ Sutra

> A Sanskrit and Hindi inspired interpreted programming language built from scratch in Java.

Sutra is an fun learning programming language designed to understand how programming languages work internally.

Instead of using parser generators or compiler frameworks, Sutra implements every stage manually:

- Lexer
- Recursive Descent Parser
- Abstract Syntax Tree (AST)
- Visitor Pattern
- Tree-Walk Interpreter

---

## Features

- Variable declaration
- Variable assignment
- Integer & String literals
- Arithmetic operators
- Comparison operators
- Natural language operators
- Block statements
- Conditional statements
- Programs executed from `.sut` files

---

## Example

```sutra
man age = 18;

yadi(age bada_barabar 18){
    bolo("Adult");
}
anyatha{
    bolo("Minor");
}
```

The same program can also be written using symbolic operators.

```sutra
man age = 18;

yadi(age >= 18){
    bolo("Adult");
}
```

---

## Current Keywords

| Keyword | Meaning |
|----------|---------|
| man | Variable declaration |
| bolo | Print |
| yadi | If |
| anyatha | Else |

---

## Natural Language Operators

| Symbol | Keyword |
|---------|----------|
| > | bada |
| < | chhota |
| >= | bada_barabar |
| <= | chhota_barabar |
| == | barabar |
| != | alag |

Both symbolic and keyword operators are supported.

---

## Run

Compile

```bash
javac Main.java lexer/*.java parser/*.java ast/*.java interpreter/*.java
```

Run

```bash
java Main Test_programs/test.sut
```

---

## Roadmap

- Logical operators
- While loops
- Functions
- Arrays
- Objects
- Classes
- REPL
- Bytecode Compiler
- Virtual Machine

---

Built from scratch using Java ❤️