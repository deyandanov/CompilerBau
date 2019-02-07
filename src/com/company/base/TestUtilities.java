package com.company.base;

public  class TestUtilities {

public static Visitable createTestTree(){

    //creates needed Operands for Tree
   //==================================================================================================================

   OperandNode operandNode_a1 =new OperandNode("a");
    OperandNode operandNode_a2 =new OperandNode("a");

    OperandNode operandNode_b1 =new OperandNode("b");
    OperandNode operandNode_b2 =new OperandNode("b");
    OperandNode operandNode_b3 =new OperandNode("b");

    OperandNode operandNode_endtag =new OperandNode("#");



    //creates needed Tree from Bottom with unaray and binary Nodes
    //==================================================================================================================

    BinOpNode binOpNode_choice=new BinOpNode("|",operandNode_a1,operandNode_b1);


    UnaryOpNode unaryOpNode_StarNode= new UnaryOpNode("*",binOpNode_choice);

    BinOpNode binOpNode_concat1=new BinOpNode("°",unaryOpNode_StarNode,operandNode_a2);

    BinOpNode binOpNode_concat2=new BinOpNode("°",binOpNode_concat1,operandNode_b2);

    BinOpNode binOpNode_concat3=new BinOpNode("°",binOpNode_concat2,operandNode_b3);

    BinOpNode binOpNode_concat4=new BinOpNode("°",binOpNode_concat3,operandNode_endtag);

    return binOpNode_concat4;


}

}
