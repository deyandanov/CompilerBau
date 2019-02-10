package com.company.base;

import java.util.HashSet;
import java.util.Set;

public abstract class SyntaxNode {
    private boolean nullable;
    private final Set<Integer> firstpos = new HashSet<>();
    private final Set<Integer> lastpos = new HashSet<>();

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public Set<Integer> getFirstpos() {
        return firstpos;
    }

    public Set<Integer> getLastpos() {
        return lastpos;
    }
}
