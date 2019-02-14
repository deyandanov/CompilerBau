package com.company.visitor.first;

import com.company.base.*;
import com.company.base.BinOpNode;
import com.company.base.OperandNode;
import com.company.base.UnaryOpNode;

import java.util.Set;

public class VisitorFirst implements Visitor {
    //Teil von Noah Börger

    //Der Visitior traversiert selbst den Baum, da bei uns ein Knoten erst als besucht gilt, wenn auch die Referenzen auf seine untergeordneten Subknoten besucht sind

    private int position;

    public VisitorFirst() {
        position = 1;
    }

    @Override
    public void visit(Visitable visitable) {
        //Methode eingefügt, um nachfolgende Visitable die die Knoten liefern nicht immer nochmal in den Methoden vor dem Besuchen casten zu müssen
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
        if (node.getSubNode() != null) {
            visit(node.getSubNode());
        }

        checkNullable(node);
        calculateFirstPos(node);
        calculateLastPos(node);
    }

    //Nullable fuer alle Knoten berechnen//

    private void checkNullable(OperandNode node) {
        if ("ε".equals(node.getSymbol())) {
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
        if ("|".equals(operator)) {
            node.setNullable(isNullableLeft || isNullableRight);
        } else if ("°".equals(operator)) {
            node.setNullable(isNullableLeft && isNullableRight);
        }
    }

    private void checkNullable(UnaryOpNode node) {
        String operator = node.getOperator();
        switch (operator) {
            case "*":
                node.setNullable(true);
                break;
            case "+":
                node.setNullable(node.getSubNode().isNullable());
                break;
            case "?":
                node.setNullable(true);
                break;
            default:
                System.err.println("This type of operator is not supported!");
                break;
        }
    }

    //FirstPos fuer alle Knoten berechnen//

    private void calculateFirstPos(OperandNode node) {
        if (!"ε".equals(node.getSymbol())) {
            node.getFirstpos().add(node.getPosition());
        }
    }

    private void calculateFirstPos(BinOpNode node) {
        Set<Integer> firstPosLeftSet = node.getLeft().getFirstpos();
        Set<Integer> firstPosRightSet = node.getRight().getFirstpos();

        String operator = node.getOperator();
        if ("|".equals(operator) || ("°".equals(operator) && node.getLeft().isNullable())) {
            for (int firstPosLeft : firstPosLeftSet) {
                node.getFirstpos().add(firstPosLeft);
            }
            for (int firstPosRight : firstPosRightSet) {
                node.getFirstpos().add(firstPosRight);
            }
        } else if ("°".equals(operator) && !node.getLeft().isNullable()) {
            for (int firstPosLeft : firstPosLeftSet) {
                node.getFirstpos().add(firstPosLeft);
            }
        } else {
            System.err.println("This type of operator is not supported!");
        }
    }

    private void calculateFirstPos(UnaryOpNode node) {
        for (int firstPos : node.getSubNode().getFirstpos()) {
            node.getFirstpos().add(firstPos);
        }
    }

    //LastPos fuer alle Knoten berechnen//

    private void calculateLastPos(OperandNode node) {
        if (!"ε".equals(node.getSymbol())) {
            node.getLastpos().add(node.getPosition());
        }
    }

    private void calculateLastPos(BinOpNode node) {
        Set<Integer> lastPosLeftSet = node.getLeft().getLastpos();
        Set<Integer> lastPosRightSet = node.getRight().getLastpos();

        String operator = node.getOperator();
        if ("|".equals(operator) || ("°".equals(operator) && node.getRight().isNullable())) {
            for (int lastPosLeft : lastPosLeftSet) {
                node.getLastpos().add(lastPosLeft);
            }
            for (int lastPosRight : lastPosRightSet) {
                node.getLastpos().add(lastPosRight);
            }
        } else if ("°".equals(operator) && !node.getRight().isNullable()) {
            for (int lastPosRight : lastPosRightSet) {
                node.getLastpos().add(lastPosRight);
            }
        } else {
            System.err.println("This type of operator is not supported!");
        }
    }

    private void calculateLastPos(UnaryOpNode node) {
        for (int lastPos : node.getSubNode().getLastpos()) {
            node.getLastpos().add(lastPos);
        }
    }
}
