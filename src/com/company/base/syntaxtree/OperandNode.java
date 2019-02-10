package com.company.base.syntaxtree;

import com.company.base.Visitable;
import com.company.base.Visitor;
import com.company.base.syntaxtree.SyntaxNode;

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