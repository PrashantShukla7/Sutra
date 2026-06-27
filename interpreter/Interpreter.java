package interpreter;

import ast.Expr;
import ast.Stmt;

import java.util.List;

public class Interpreter implements Expr.Visitor<Object>,
        Stmt.Visitor<Void> {

    private final Environment environment = new Environment();

    public void interpret(List<Stmt> statements) {

        for (Stmt stmt : statements) {
            execute(stmt);
        }
    }

    // =========================

    private void execute(Stmt stmt) {
        stmt.accept(this);
    }

    private Object evaluate(Expr expr) {
        return expr.accept(this);
    }

    // =========================
    // Statements
    // =========================

    @Override
    public Void visitVarStmt(Stmt.Var stmt) {

        Object value = evaluate(stmt.initializer);

        environment.define(stmt.name, value);

        return null;
    }

    @Override
    public Void visitPrintStmt(Stmt.Print stmt) {

        Object value = evaluate(stmt.expression);

        System.out.println(value);

        return null;
    }

    @Override
    public Void visitExpressionStmt(Stmt.Expression stmt) {

        evaluate(stmt.expression);

        return null;
    }

    // =========================
    // Expressions
    // =========================

    @Override
    public Object visitLiteralExpr(Expr.Literal expr) {

        return expr.value;
    }

    @Override
    public Object visitVariableExpr(Expr.Variable expr) {

        return environment.get(expr.name);
    }

    @Override
    public Object visitGroupingExpr(Expr.Grouping expr) {

        return evaluate(expr.expression);
    }

    @Override
    public Object visitBinaryExpr(Expr.Binary expr) {

        int left = (Integer) evaluate(expr.left);
        int right = (Integer) evaluate(expr.right);

        switch (expr.operator) {

            case "+":
                return left + right;

            case "-":
                return left - right;

            case "*":
                return left * right;

            case "/":
                return left / right;

            default:
                throw new RuntimeException(
                        "Unknown operator " + expr.operator);
        }
    }

    @Override
    public Object visitAssignExpr(Expr.Assign expr) {

        Object value = evaluate(expr.value);

        environment.assign(expr.name, value);

        return value;
    }
}