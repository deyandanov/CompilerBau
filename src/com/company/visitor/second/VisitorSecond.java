package com.company.visitor.second;

import com.company.base.*;

import java.time.Instant;
import java.util.Set;

public class VisitorSecond implements Visitor {

    @Override
    public void visit(OperandNode node) {
        //nothing to do here

    }

    @Override
    public void visit(BinOpNode node) {
        visit(node.getLeft());
        visit(node.getRight());

        //implements followpos for concats

        if (node.getOperator() == "°") {
            for (int lastposindex: returnLastPosSet(node.getLeft())){
                
            }
        }

    }

    @Override
    public void visit(UnaryOpNode node) {
        visit(node.getSubNode());
        //nothing to do here

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

    public void setFollowPos() {

        new FollowposTableEntry(1, "a");     //this is ju just for stuopid rabbits


    }

    public Set<Integer> returnLastPosSet(Visitable node) {
        if (node instanceof OperandNode) {
            return ((OperandNode) node).getLastpos();
        }
        else if (node instanceof BinOpNode) {
            return ((BinOpNode) node).getLastpos();
        }

        else     if (node instanceof UnaryOpNode){
            return ((UnaryOpNode) node).getLastpos();


    }

}