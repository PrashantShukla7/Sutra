# 🚀 Sutra Programming Language

# 🕉️ Sutra Programming Language

**Sutra** is a simple interpreted programming language built from scratch in **Java**. The goal of this project is to understand how programming languages work internally by implementing every stage—from lexical analysis to interpretation.

Instead of directly executing source code, Sutra converts your program into tokens, builds an Abstract Syntax Tree (AST), and then interprets that AST.

> ⚠️ This project is currently under active development. New language features will be added in future versions.

---

# ✨ Current Features

- Integer variables
- Arithmetic operations (`+`, `-`, `*`, `/`)
- Variable assignment
- Variable reassignment
- `bolo()` function to print output
- Operator precedence
- Parentheses
- Read programs from `.sut` files

---

# Example Program

Create a file named `Test_programs/test.sut`

```sut
man x = 10;
man y = 40;

bolo(x + y);

x = x * 10;

bolo(x);
```

Output

```
50
100
```

---

# Project Structure

```
Sutra/
│
├── src/
│   ├── Main.java
│   ├── Sutra.java
│   │
│   ├── lexer/
│   ├── parser/
│   ├── ast/
│   └── interpreter/
│
├── programs/
│   └── test.sut
│
├── README.md
└── .gitignore
```

---

# Language Keywords

| Keyword | Meaning |
|----------|---------|
| `man` | Declare a variable |
| `bolo()` | Print output |

Example

```sut
man age = 18;

bolo(age);
```

---

# How Sutra Works

When you run

```sut
man x = 10;

bolo(x + 5);
```

it goes through several stages.

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
Abstract Syntax Tree
      │
      ▼
Interpreter
      │
      ▼
Output
```

---

# Prerequisites

Install **Java 17 or later**.

Verify installation

```bash
java -version
javac -version
```

---

# Clone the Repository

```bash
https://github.com/PrashantShukla7/Sutra.git

cd Sutra
```


---

# Compile the Project

Move into the `src` directory.

```bash
cd src
```

Compile every Java file.

### Windows

```bash
javac Main.java lexer/*.java parser/*.java ast/*.java interpreter/*.java
```

### Linux/macOS

```bash
javac Main.java lexer/*.java parser/*.java ast/*.java interpreter/*.java
```

If compilation succeeds, several `.class` files will be generated.

---

# Run a Program

Assuming your source file is

```
Test_programs/test.sut
```

Run

```bash
java Main Test_programs/test.sut
```

If your `Main.java` uses another relative path, adjust the command accordingly.

---

# Writing Your Own Program

Create a new file

```
programs/hello.sut
```

Example

```sut
man a = 5;
man b = 7;

bolo(a * b);
```

Run it

```bash
java Main Test_programs/hello.sut
```

---

# Current Grammar

Variable declaration

```sut
man age = 18;
```

Assignment

```sut
age = age + 1;
```

Printing

```sut
bolo(age);
```

Expressions

```sut
bolo((10 + 20) * 5);

bolo(100 / 4);

bolo(7 + 5 * 3);
```

---

# Example

```sut
man price = 100;
man tax = 18;

bolo(price + tax);

price = price * 2;

bolo(price);
```

Output

```
118
200
```

---

# Current Limitations

At the moment, Sutra does **not** support:

- Strings
- Boolean values
- Comparison operators
- `if` statements
- Loops
- Functions
- Arrays
- Objects
- User-defined classes
- Imports
- Modules

These features are planned for future releases.

---

# Roadmap

## ✅ Completed

- Lexer
- Tokenizer
- Recursive Descent Parser
- Abstract Syntax Tree (AST)
- Visitor Pattern
- Interpreter
- Variables
- Assignments
- Arithmetic
- File execution

## 🚧 In Progress

- Comparison operators
- Boolean values
- `if` / `else`
- `while` loops
- Functions
- Strings
- Arrays
- Modules
- Standard library
- Interactive REPL
- Error reporting with line numbers

---

# Contributing

Contributions, suggestions, and bug reports are welcome.

If you'd like to improve Sutra:

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Open a Pull Request

---

# License

This project is licensed under the MIT License.

---

# Acknowledgements

Sutra is an fun learning project created to explore how programming languages are designed and implemented. It follows a traditional interpreter architecture using:

- Lexer
- Parser
- Abstract Syntax Tree (AST)
- Visitor Pattern
- Interpreter

The project is intended for anyone interested in learning compiler and interpreter design by building a language from scratch.