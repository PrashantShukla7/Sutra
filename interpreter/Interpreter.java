package interpreter;

import ast.Expr;
import ast.Stmt;

import java.util.List;
import java.util.Objects;

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

    private boolean isTruthy(Object value) {

        if (value == null)
            return false;

        if (value instanceof Boolean)
            return (Boolean) value;

        if (value instanceof Integer)
            return (Integer) value != 0;

        if (value instanceof String)
            return !((String) value).isEmpty();

        return true;
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

    @Override
    public Void visitIfStmt(Stmt.If stmt) {

        if (isTruthy(evaluate(stmt.condition))) {

            execute(stmt.thenBranch);

        } else if (stmt.elseBranch != null) {

            execute(stmt.elseBranch);

        }

        return null;
    }

    @Override
    public Void visitBlockStmt(Stmt.Block stmt) {

        for (Stmt statement : stmt.statements) {
            execute(statement);
        }

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

        Object leftOperand = evaluate(expr.left);
        Object rightOperand = evaluate(expr.right);

        switch (expr.operator) {

            case "+":
            case "-":
            case "*":
            case "/":
            case "<":
            case ">":
            case "<=":
            case "%":
            case ">=": {

                int left = (Integer) leftOperand;
                int right = (Integer) rightOperand;

                switch (expr.operator) {
                    case "+": return left + right;
                    case "-": return left - right;
                    case "*": return left * right;
                    case "/": return left / right;
                    case "<": return left < right;
                    case ">": return left > right;
                    case "<=": return left <= right;
                    case ">=": return left >= right;
                    case "%": return left % right;
                }
            }

            case "==":
                return Objects.equals(leftOperand, rightOperand);

            case "!=":
                return !Objects.equals(leftOperand, rightOperand);


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