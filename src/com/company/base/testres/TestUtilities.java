package com.company.base.testres;

import com.company.base.BinOpNode;
import com.company.base.OperandNode;
import com.company.base.UnaryOpNode;
import com.company.base.Visitable;

public class TestUtilities {

    public static Visitable createTestTree() {

        //creates needed Operands for Tree
        //==================================================================================================================

        OperandNode operandNode_a1 = new OperandNode("a");
        OperandNode operandNode_a2 = new OperandNode("a");

        OperandNode operandNode_b1 = new OperandNode("b");
        OperandNode operandNode_b2 = new OperandNode("b");
        OperandNode operandNode_b3 = new OperandNode("b");

        OperandNode operandNode_endtag = new OperandNode("#");


        //creates needed Tree from Bottom with unaray and binary Nodes
        //==================================================================================================================

        BinOpNode binOpNode_choice = new BinOpNode("|", operandNode_a1, operandNode_b1);


        UnaryOpNode unaryOpNode_StarNode = new UnaryOpNode("*", binOpNode_choice);

        BinOpNode binOpNode_concat1 = new BinOpNode("°", unaryOpNode_StarNode, operandNode_a2);

        BinOpNode binOpNode_concat2 = new BinOpNode("°", binOpNode_concat1, operandNode_b2);

        BinOpNode binOpNode_concat3 = new BinOpNode("°", binOpNode_concat2, operandNode_b3);

        BinOpNode binOpNode_concat4 = new BinOpNode("°", binOpNode_concat3, operandNode_endtag);



        return binOpNode_concat4;
    }


    public static Visitable createTestTreeFolllowPos(){
        OperandNode operandNode_a1 = new OperandNode("a");

        OperandNode operandNode_a2 = new OperandNode("a");

        OperandNode operandNode_b1 = new OperandNode("b");
        OperandNode operandNode_b2 = new OperandNode("b");
        OperandNode operandNode_b3 = new OperandNode("b");

        OperandNode operandNode_endtag = new OperandNode("#");


        //creates needed Tree from Bottom with unaray and binary Nodes
        //==================================================================================================================

        BinOpNode binOpNode_choice = new BinOpNode("|", operandNode_a1, operandNode_b1);


        UnaryOpNode unaryOpNode_StarNode = new UnaryOpNode("*", binOpNode_choice);

        BinOpNode binOpNode_concat1 = new BinOpNode("°", unaryOpNode_StarNode, operandNode_a2);

        BinOpNode binOpNode_concat2 = new BinOpNode("°", binOpNode_concat1, operandNode_b2);

        BinOpNode binOpNode_concat3 = new BinOpNode("°", binOpNode_concat2, operandNode_b3);

        BinOpNode binOpNode_concat4 = new BinOpNode("°", binOpNode_concat3, operandNode_endtag);



        //set firstpos and lastpos
        //==================================================================================================================

        binOpNode_choice.getFirstpos().add(1);
        binOpNode_choice.getFirstpos().add(2);
        binOpNode_choice.getLastpos().add(1);
        binOpNode_choice.getLastpos().add(2);

        unaryOpNode_StarNode.getFirstpos().add(1);
        unaryOpNode_StarNode.getFirstpos().add(2);
        unaryOpNode_StarNode.getLastpos().add(1);
        unaryOpNode_StarNode.getLastpos().add(2);

        binOpNode_concat1.getFirstpos().add(1);
        binOpNode_concat1.getFirstpos().add(2);
        binOpNode_concat1.getFirstpos().add(3);
        binOpNode_concat1.getLastpos().add(3);

        binOpNode_concat2.getFirstpos().add(1);
        binOpNode_concat2.getFirstpos().add(2);
        binOpNode_concat2.getFirstpos().add(3);
        binOpNode_concat2.getLastpos().add(4);

        binOpNode_concat3.getFirstpos().add(1);
        binOpNode_concat3.getFirstpos().add(2);
        binOpNode_concat3.getFirstpos().add(3);
        binOpNode_concat3.getLastpos().add(5);

        binOpNode_concat4.getFirstpos().add(1);
        binOpNode_concat4.getFirstpos().add(2);
        binOpNode_concat4.getFirstpos().add(3);
        binOpNode_concat4.getLastpos().add(6);

        operandNode_a1.getFirstpos().add(1);
        operandNode_a1.getLastpos().add(1);
        operandNode_a1.setPosition(1);

        operandNode_a2.getFirstpos().add(3);
        operandNode_a2.getLastpos().add(3);
        operandNode_a2.setPosition(3);


        operandNode_b1.getFirstpos().add(2);
        operandNode_b1.getLastpos().add(2);
        operandNode_b1.setPosition(2);

        operandNode_b2.getFirstpos().add(4);
        operandNode_b2.getLastpos().add(4);
        operandNode_b2.setPosition(4);

        operandNode_b3.getFirstpos().add(5);
        operandNode_b3.getLastpos().add(5);
        operandNode_b3.setPosition(5);

        operandNode_endtag.getFirstpos().add(6);
        operandNode_endtag.getLastpos().add(6);
        operandNode_endtag.setPosition(6);



        return binOpNode_concat4;

    }


}
