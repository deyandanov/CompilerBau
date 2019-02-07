package com.company.visitor.first;

import com.company.base.*;

import java.util.Set;

public class VisitorFirst implements Visitor {
    private int position;

    public VisitorFirst() {
        position = 1;
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
        node.setPosition(position++);
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

    private void checkNullable(OperandNode node) {
        if (node.getSymbol().equals("ε")) {
            node.setNullable(true);
        } else {
            node.setNullable(false);
        }
    }

    private void checkNullable(BinOpNode node) {
        Visitable leftNode = node.getLeft();
        boolean isNullableLeft = leftNode.isNullable();

        Visitable rightNode = node.getRight();
        boolean isNullableRight = rightNode.isNullable();

        String operator = node.getOperator();
        if (operator.equals("|")) {
            node.setNullable(isNullableLeft || isNullableRight);
        } else if (operator.equals("°")) {
            node.setNullable(isNullableLeft && isNullableRight);
        }
    }

    private void checkNullable(UnaryOpNode node) {
        String operator = node.getOperator();
        if (operator.equals("*")) {
            node.setNullable(true);
        } else if (operator.equals("+")) {
            node.setNullable(node.getSubNode().isNullable());
        } else if (operator.equals("?")) {
            node.setNullable(true);
        } else {
            System.err.println("This type of operator is not supported!4" + node.getOperator());
        }
    }

    //Firstpos fuer alle Knoten berechnen//

    private void calculateFirstPos(OperandNode node) {
        if (node.getSymbol().equals("ε")) {
        } else {
            node.getFirstpos().add(node.getPosition());
        }
    }

    private void calculateFirstPos(BinOpNode node) {
        Set<Integer> firstPosLeftSet = node.getLeft().getFirstpos();
        Set<Integer> firstPosRightSet = node.getRight().getFirstpos();

        String operator = node.getOperator();
        if (operator.equals("|") || (operator.equals("°") && node.getLeft().isNullable())) {
            for (int firstPosLeft : firstPosLeftSet) {
                node.getFirstpos().add(firstPosLeft);
            }
            for (int firstPosRight : firstPosRightSet) {
                node.getFirstpos().add(firstPosRight);
            }
        } else if (operator.equals("°") && !node.getLeft().isNullable()) {
            for (int firstPosLeft : firstPosLeftSet) {
                node.getFirstpos().add(firstPosLeft);
            }
        } else {
            System.err.println("This type of operator is not supported!2" + node.getOperator());
        }
    }

    private void calculateFirstPos(UnaryOpNode node) {
        for (int firstPos : node.getSubNode().getFirstpos()) {
            node.getFirstpos().add(firstPos);
        }
    }

    //Firstpos fuer alle Knoten berechnen//

    private void calculateLastPos(OperandNode node) {
        if (node.getSymbol().equals("ε")) {
        } else {
            node.getLastpos().add(node.getPosition());
        }
    }

    private void calculateLastPos(BinOpNode node) {
        Set<Integer> lastPosLeftSet = node.getLeft().getLastpos();
        Set<Integer> lastPosRightSet = node.getRight().getLastpos();

        String operator = node.getOperator();
        if (operator.equals("|") || (operator.equals("°") && node.getRight().isNullable())) {
            for (int lastPosLeft : lastPosLeftSet) {
                node.getLastpos().add(lastPosLeft);
            }
            for (int lastPosRight : lastPosRightSet) {
                node.getLastpos().add(lastPosRight);
            }
        } else if (operator.equals("°") && !node.getRight().isNullable()) {
            for (int lastPosRight : lastPosRightSet) {
                node.getLastpos().add(lastPosRight);
            }
        } else {
            System.err.println("This type of operator is not supported!1" + node.getOperator());
        }
    }

    private void calculateLastPos(UnaryOpNode node) {
        for (int lastPos : node.getSubNode().getLastpos()) {
            node.getLastpos().add(lastPos);
        }
    }
}
