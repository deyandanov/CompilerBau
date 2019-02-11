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

    @Override
    public void visit(OperandNode node) {
        followposTableEntries.put(node.getPosition(), new FollowposTableEntry(node.getPosition(), node.getSymbol()));
    }


    @Override
    public void visit(BinOpNode node) {         //walks through tree(binary OpNode==tree with two childs)
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
    public void visit(UnaryOpNode node) {        //walks through tree(unary opnode== tree with one child )
        visit(node.getSubNode());
        if (node.getOperator().equals("*") || node.getOperator().equals("+")) {
            for (int lastposindex : node.getLastpos()) {
                for (int firstpos : node.getFirstpos()) {
           //         System.out.println("Now Adding " + lastposindex + " advalue " + firstpos);        //just to easily see calcualted operands for followpos
                    followposTableEntries.get(lastposindex).getFollowpos().add(firstpos);       // table entry
          //          System.out.println(lastposindex + " added" + firstpos);                           //just to easily see calcualted operands for followpos
                }
            }
        }

    }

    //no need to implement OperandOpNode, cause in trees with child those were visited and  followpos will be calcualted

    @Override
    public void visit(Visitable visitable) {        // calls methods by type of node
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