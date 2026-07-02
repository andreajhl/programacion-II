package org.structure.implementation.fixed;

import org.exception.EmptyADTException;
import org.exception.FullADTException;
import org.structure.definition.SetADT;

import java.util.Random;

public class Set<T> implements SetADT<T> {
    private Object[] values;
    private final int DEFAULT_SIZE = 1000;
    private int size;

    public Set(){
        values = new Object[DEFAULT_SIZE];
        size = 0;
    }

    @Override
    public boolean exist(T value) {
        for(int i = 0; i < size; i++){
            if(values[i].equals(value))
                return true;
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T choose() {
        if(isEmpty()){
            throw new EmptyADTException();
        }
        Random randomGenerator = new Random();
        int randomValue = randomGenerator.nextInt(this.size);
        return (T) values[randomValue];
    }

    @Override
    public void add(T value) {
        if(this.size == DEFAULT_SIZE)
            throw new FullADTException();
        if(exist(value))
            return;
        values[this.size] = value;
        this.size++;
    }

    @Override
    public void remove(T element) {
        if(isEmpty()){
            throw new EmptyADTException();
        }
        boolean found = false;
        for(int i = 0; i < size; i++){
            if(found){
                values[i - 1] = values[i];
            }
            if(values[i].equals(element)){
                found = true;
            }
        }
        if(found){
            size--;
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
