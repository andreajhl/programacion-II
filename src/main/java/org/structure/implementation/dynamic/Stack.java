package org.structure.implementation.dynamic;

import org.exception.EmptyADTException;
import org.structure.definition.StackADT;

public class Stack<T> implements StackADT<T> {
    private LinkedList<T> list;

    public Stack(){
        this.list = new LinkedList<>();
    }

    public T getElement() {
        if(list.isEmpty())
            throw new EmptyADTException();
        return list.get(list.size() - 1);
    }

    public void add(T value) {
        list.add(value);
    }

    public void remove() {
        if(list.isEmpty())
            throw new EmptyADTException();
        list.remove(list.size() - 1);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
