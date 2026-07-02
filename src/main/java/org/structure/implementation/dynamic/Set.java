package org.structure.implementation.dynamic;

import org.exception.ElementNotFoundADTException;
import org.exception.EmptyADTException;
import org.structure.definition.LinkedListADT;
import org.structure.definition.SetADT;

import java.util.Random;

public class Set<T> implements SetADT<T> {
    private LinkedListADT<T> values;

    public Set(){
        values = new LinkedList<>();
    }

    @Override
    public boolean exist(T value) {
        for(int i = 0; i < values.size(); i++){
            if(values.get(i).equals(value)){
                return true;
            }
        }
        return false;
    }

    @Override
    public T choose() {
        if(isEmpty()){
            throw new EmptyADTException();
        }
        Random randomGenerator = new Random();
        int randomValue = randomGenerator.nextInt(values.size());
        return values.get(randomValue);
    }

    @Override
    public void add(T value) {
        if(exist(value)){
            return;
        }
        values.add(value);
    }

    @Override
    public void remove(T element) {
        for(int i = 0; i < values.size(); i++){
            if(values.get(i).equals(element)){
                values.remove(i);
                return;
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return values.isEmpty();
    }
}
