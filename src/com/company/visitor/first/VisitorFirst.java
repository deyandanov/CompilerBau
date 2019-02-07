package com.company.visitor.first;

import com.company.base.*;

import java.util.HashSet;
import java.util.Set;

public class VisitorFirst implements Visitor {
    public VisitorFirst() {

    }

    //TODO: HAT HIER NICHTS VERLOREN, IN ANDERE KLASSE
    @Override
    public void visit(Visitable visitable) {
        if (visitable instanceof OperandNode) {
            OperandNode operandNode = (OperandNode) visitable;
            operandNode.accept(this);
        } else if (visitable instanceof BinOpNode) {
            BinOpNode binOpNode = (BinOpNode) visitable;
            binOpNode.accept(this);
        } else if (visitable instanceof UnaryOpNode) {
            UnaryOpNode unaryOpNode = (UnaryOpNode) visitable;
            unaryOpNode.accept(this);
        } else {
            System.err.println("This type of Node is not supported!");
        }
    }

    @Override
    public void visit(OperandNode node) {
        checkNullable(node);
        calculateFirstPos(node);
        calculateLastPos(node);
    }

    @Override
    public void visit(BinOpNode node) {
        if (node.getLeft() != null) {
            visit(node.getLeft());
        }

        if (node.getRight() != null) {
            visit(node.getRight());
        }

        checkNullable(node);
        calculateFirstPos(node);
        calculateLastPos(node);
    }

    @Override
    public void visit(UnaryOpNode node) {
        visit(node.getSubNode());

        checkNullable(node);
        calculateFirstPos(node);
        calculateLastPos(node);
    }

    //Nullable fuer alle Knoten berechnen//

    public void checkNullable(OperandNode node) {
        if (node.getSymbol().equals("ε")) {
            node.setNullable(true);
        } else {
            node.setNullable(false);
        }
    }

    public void checkNullable(BinOpNode node) {
        Visitable leftNode = node.getLeft();
        boolean isNullableLeft = isNullable(leftNode);

        Visitable rightNode = node.getRight();
        boolean isNullableRight = isNullable(rightNode);

        String operator = node.getOperator();
        if (operator.equals("|")) {
            node.setNullable(isNullableLeft || isNullableRight);
        } else if (operator.equals("°")) {
            node.setNullable(isNullableLeft && isNullableRight);
        }
    }

    public void checkNullable(UnaryOpNode node) {
        String operator = node.getOperator();
        if (operator.equals("*")) {
            node.setNullable(true);
        }else if(operator.equals("+")) {
            node.setNullable(isNullable(node.getSubNode()));
        }else if (operator.equals("?")) {
            node.setNullable(true);
        }else {
            System.err.println("This type of operator is not supported!");
        }
    }

    //Firstpos fuer alle Knoten berechnen//

    public void calculateFirstPos(OperandNode node) {
        if (node.getSymbol().equals("ε")) {
        } else {
            node.getFirstpos().add(node.getPosition());
        }
    }

    public void calculateFirstPos(BinOpNode node) {
        String operator = node.getOperator();
        if (operator.equals("|")) {

        } else if (operator.equals("°")) {

        }
    }

    public void calculateFirstPos(UnaryOpNode node) {

    }

    //Firstpos fuer alle Knoten berechnen//

    public void calculateLastPos(OperandNode node) {
    }

    public void calculateLastPos(BinOpNode node) {

    }

    public void calculateLastPos(UnaryOpNode node) {

    }

    //Hilfsmethoden//
    private boolean isNullable(Visitable visitable) {
        boolean isNullable;

        if (visitable instanceof OperandNode) {
            OperandNode operandNode = (OperandNode) visitable;
            isNullable = operandNode.isNullable();
        } else if (visitable instanceof BinOpNode) {
            BinOpNode binOpNode = (BinOpNode) visitable;
            isNullable = binOpNode.isNullable();
        } else if (visitable instanceof UnaryOpNode) {
            UnaryOpNode unaryNode = (UnaryOpNode) visitable;
            isNullable = unaryNode.isNullable();
        } else {
            System.err.println("This type of node is not supported!");
            isNullable = false;
        }
        return isNullable;
    }
}
