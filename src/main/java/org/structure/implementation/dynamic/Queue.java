package org.structure.implementation.dynamic;

import org.exception.EmptyADTException;
import org.structure.definition.QueueADT;

public class Queue<T> implements QueueADT<T> {
    private LinkedList<T> list;

    public Queue(){
        this.list = new LinkedList<>();
    }

    public T getElement() {
        if(list.isEmpty())
            throw new EmptyADTException();
        return list.get(0);
    }

    public void add(T value) {
        list.add(value);
    }

    public void remove() {
        if(list.isEmpty())
            throw new EmptyADTException();
        list.remove(0);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
