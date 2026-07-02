package org.structure.implementation.fixed;

import org.exception.EmptyADTException;
import org.exception.FullADTException;
import org.structure.definition.StackADT;

public class Stack<T> implements StackADT<T> {
    private final int DEFAULT_SIZE = 1000;
    private Object[] stack;
    private int size;

    public Stack() {
        this.stack = new Object[DEFAULT_SIZE];
        this.size = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getElement() {
        if (this.isEmpty()) throw new EmptyADTException();
        return (T) stack[this.size - 1];
    }

    @Override
    public void add(T value) {
        if (this.size == DEFAULT_SIZE) throw new FullADTException();
        stack[this.size] = value;
        this.size++;
    }

    @Override
    public void remove() {
        if (this.isEmpty()) throw new EmptyADTException();
        this.size--;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
