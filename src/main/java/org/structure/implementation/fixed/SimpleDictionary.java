package org.structure.implementation.fixed;

import org.exception.ElementNotFoundADTException;
import org.exception.FullADTException;
import org.structure.definition.SetADT;
import org.structure.definition.SimpleDictionaryADT;

public class SimpleDictionary<T> implements SimpleDictionaryADT<T> {
    private int[] keys;
    private Object[] values;
    private int size;
    private final int DEFAULT_SIZE = 1000;

    public SimpleDictionary() {
        this.keys = new int[DEFAULT_SIZE];
        this.values = new Object[DEFAULT_SIZE];
        this.size = 0;
    }

    @Override
    public void add(int key, T value) {
        for (int i = 0; i < size; i++) {
            if (keys[i] == key) {
                values[i] = value;
                return;
            }
        }
        if (size == DEFAULT_SIZE) throw new FullADTException();
        keys[size] = key;
        values[size] = value;
        size++;
    }

    @Override
    public void remove(int key) {
        for (int i = 0; i < size; i++) {
            if (keys[i] == key) {
                keys[i] = keys[size - 1];
                values[i] = values[size - 1];
                size--;
                return;
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int key) {
        for (int i = 0; i < size; i++) {
            if (keys[i] == key) return (T) values[i];
        }
        throw new ElementNotFoundADTException();
    }

    @Override
    public SetADT getKeys() {
        SetADT result = new Set();
        for (int i = 0; i < size; i++) {
            result.add(keys[i]);
        }
        return result;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
