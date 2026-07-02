package org.structure.implementation.fixed;

import org.exception.EmptyADTException;
import org.exception.FullADTException;
import org.structure.definition.QueueADT;

public class Queue<T> implements QueueADT<T> {
    private final int DEFAULT_SIZE = 1000;
    private Object[] queue;
    private int size;

    public Queue() {
        this.queue = new Object[DEFAULT_SIZE];
        this.size = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getElement() {
        if (this.isEmpty()) throw new EmptyADTException();
        return (T) queue[0];
    }

    @Override
    public void add(T value) {
        if (this.size == DEFAULT_SIZE) throw new FullADTException();
        queue[this.size] = value;
        this.size++;
    }

    @Override
    public void remove() {
        if (this.isEmpty()) throw new EmptyADTException();
        this.size--;
        System.arraycopy(queue, 1, queue, 0, this.size);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
