package com.company.visitor.first;

import com.company.base.*;

public class VisitorFirst implements Visitor {
    public VisitorFirst() {

    }

    @Override
    public void visit(Visitable visitable) {
        if (visitable instanceof OperandNode) {
            visit((OperandNode) visitable);
        } else if (visitable instanceof BinOpNode) {
            visit((BinOpNode) visitable);
        } else if (visitable instanceof UnaryOpNode) {
            visit((UnaryOpNode) visitable);
        } else {
            System.err.println("This type of Node is not supported yet!");
        }
    }

    @Override
    public void visit(OperandNode node) {
        if (node.getSymbol().equals("ε")) {
            node.setNullable(true);
        } else {
            node.setNullable(false);
        }
    }

    @Override
    public void visit(BinOpNode node) {
        if (node.getLeft() != null) {
            visit(node.getLeft());
        }

        checkNullable(BinOpNode node);

        if (node.getRight() != null) {
            visit(node.getRight());
        }
    }

    @Override
    public void visit(UnaryOpNode node) {
    }

    public void checkNullable(BinOpNode node) {
        if (node.getOperator().equals("|")) {
            boolean isNullableLeft;
            Visitable leftNode = node.getLeft();

            if (leftNode instanceof OperandNode) {
                OperandNode leftOperandNode = (OperandNode) leftNode;
                isNullableLeft = leftOperandNode.isNullable();
            } else if (leftNode instanceof BinOpNode) {
                BinOpNode leftBinOpNode = (BinOpNode) leftNode;
                isNullableLeft = leftBinOpNode.isNullable();
            } else if (leftNode instanceof UnaryOpNode) {
                UnaryOpNode leftUnaryNode = (UnaryOpNode) leftNode;
                isNullableLeft = leftUnaryNode.isNullable();
            } else {
                System.err.println("This type of Node is not supported yet!");
            }

            boolean isNullableRight = false
            Visitable rightNode = node.getRight();

        }
    }
}
