package com.company.base;

import java.util.Set;

public interface Node {
    //Von Noah Börger eingefügt, wird von Visitable extended da jeder Knoten diese Methoden, sowie Visitable, implementiert, sparrt Arbeit da Visitable nicht immer zum jeweiligen Knoten gecastet werden muss

    boolean isNullable();

    void setNullable(boolean nullable);

    Set<Integer> getFirstpos();

    Set<Integer> getLastpos();
}
