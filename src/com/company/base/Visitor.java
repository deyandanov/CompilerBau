package com.company.base;

import com.company.base.syntaxtree.BinOpNode;
import com.company.base.syntaxtree.OperandNode;
import com.company.base.syntaxtree.UnaryOpNode;

public interface Visitor {
    void visit(OperandNode node);

    void visit(BinOpNode node);

    void visit(UnaryOpNode node);

    void visit(Visitable visitable);
}
