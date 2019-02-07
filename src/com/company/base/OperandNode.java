package com.company.base;

public class OperandNode extends SyntaxNode implements Visitable {
    private int position;
    private String symbol;

    public OperandNode(String symbol) {
        position = -1; // bedeutet: noch nicht initialisiert
        this.symbol = symbol;

    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}