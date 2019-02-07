package com.company.base;

public interface Visitable extends Node {
    void accept(Visitor visitor);
}
