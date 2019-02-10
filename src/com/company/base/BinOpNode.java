package com.company.base;

public class BinOpNode extends SyntaxNode implements Visitable {
    private String operator;
    private Visitable left;
    private Visitable right;

    public BinOpNode(String operator, Visitable left, Visitable right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Visitable getLeft() {
        return left;
    }

    public void setLeft(Visitable left) {
        this.left = left;
    }

    public Visitable getRight() {
        return right;
    }

    public void setRight(Visitable right) {
        this.right = right;
    }
}
