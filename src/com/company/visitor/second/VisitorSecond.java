package com.company.visitor.second;

import com.company.base.*;
import com.company.base.BinOpNode;
import com.company.base.OperandNode;
import com.company.base.UnaryOpNode;

import java.util.SortedMap;
import java.util.TreeMap;

public class VisitorSecond implements Visitor {


    private SortedMap<Integer, FollowposTableEntry> followposTableEntries;

    public VisitorSecond() {
        followposTableEntries = new TreeMap<>();
    }

    private int sumNodes(Visitable node) {
        if (node instanceof OperandNode) {
            return ((OperandNode) node).getPosition();
        } else if (node instanceof UnaryOpNode) {
            return sumNodes(((UnaryOpNode) node).getSubNode());
        } else {
            return sumNodes(((BinOpNode) node).getRight());
        }
    }

    @Override
    public void visit(OperandNode node) {
        followposTableEntries.put(node.getPosition(), new FollowposTableEntry(node.getPosition(), node.getSymbol()));
    }


    @Override
    public void visit(BinOpNode node) {
        visit(node.getLeft());
        visit(node.getRight());
        if (node.getOperator().equals("°")) {
            for (int lastposindex : node.getLeft().getLastpos()) {
                for (int firstPos2 : node.getRight().getFirstpos()) {
                    followposTableEntries.get(lastposindex).getFollowpos().add(firstPos2);
                }
            }
        }
    }


    @Override
    public void visit(UnaryOpNode node) {
        visit(node.getSubNode());
        if (node.getOperator().equals("*") || node.getOperator().equals("+")) {
            for (int lastposindex : node.getLastpos()) {
                for (int firstpos : node.getFirstpos()) {
                    System.out.println("Now Adding " + lastposindex + " advalue " + firstpos);
                    followposTableEntries.get(lastposindex).getFollowpos().add(firstpos);

                    System.out.println(lastposindex + " added" + firstpos);
                }
            }
        }

    }

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
            System.err.println("This type of Node is not supported yet!");
        }
    }

    public SortedMap<Integer, FollowposTableEntry> getFollowposTableEntries() {
        return followposTableEntries;
    }
}