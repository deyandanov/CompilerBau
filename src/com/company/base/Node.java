package com.company.base;

import java.util.Set;

public interface Node {
    boolean isNullable();

    void setNullable(boolean nullable);

    Set<Integer> getFirstpos();

    Set<Integer> getLastpos();
}
