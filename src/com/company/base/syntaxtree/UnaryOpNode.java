package com.company.base.syntaxtree;

import com.company.base.Visitable;
import com.company.base.Visitor;
import com.company.base.syntaxtree.SyntaxNode;

public class UnaryOpNode extends SyntaxNode implements Visitable {
    private String operator;
    private Visitable subNode;

    public UnaryOpNode(String operator, Visitable subNode) {
        this.operator = operator;
        this.subNode = subNode;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Visitable getSubNode() {
        return subNode;
    }

    public void setSubNode(Visitable subNode) {
        this.subNode = subNode;
    }
}
