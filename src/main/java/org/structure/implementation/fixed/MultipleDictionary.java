package org.structure.implementation.fixed;

import org.exception.ElementNotFoundADTException;
import org.exception.FullADTException;
import org.structure.definition.SetADT;
import org.structure.definition.MultipleDictionaryADT;

public class MultipleDictionary implements MultipleDictionaryADT {
    private int[] keys;
    private int[][] values;
    private int[] valueCounts;
    private int size;
    private final int DEFAULT_SIZE = 1000;
    private final int VALUES_PER_KEY = 100;

    public MultipleDictionary() {
        this.keys = new int[DEFAULT_SIZE];
        this.values = new int[DEFAULT_SIZE][VALUES_PER_KEY];
        this.valueCounts = new int[DEFAULT_SIZE];
        this.size = 0;
    }

    private int findKeyIndex(int key) {
        for (int i = 0; i < size; i++) {
            if (keys[i] == key) return i;
        }

        return -1;
    }

    @Override
    public void add(int key, int value) {
        int index = findKeyIndex(key);

        if (index == -1) {
            if (size == DEFAULT_SIZE) throw new FullADTException();

            keys[size] = key;
            values[size][0] = value;
            valueCounts[size] = 1;
            size++;
        } else {
            if (valueCounts[index] == VALUES_PER_KEY)  throw new FullADTException();

            values[index][valueCounts[index]] = value;
            valueCounts[index]++;
        }
    }

    @Override
    public void remove(int key) {
        int index = findKeyIndex(key);
        if (index == -1) return;

        keys[index] = keys[size - 1];
        valueCounts[index] = valueCounts[size - 1];

        for (int i = 0; i < valueCounts[index]; i++) {
            values[index][i] = values[size - 1][i];
        }

        valueCounts[size - 1] = 0;
        size--;
    }

    @Override
    public int[] get(int key) {
        int index = findKeyIndex(key);

        if (index == -1) throw new ElementNotFoundADTException();

        int[] result = new int[valueCounts[index]];

        for (int i = 0; i < valueCounts[index]; i++) {
            result[i] = values[index][i];
        }

        return result;
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

    @Override
    public void remove(int key, int value) {
        int index = findKeyIndex(key);

        if (index == -1) return;
        
        for (int i = 0; i < valueCounts[index]; i++) {
            if (values[index][i] == value) {
                values[index][i] = values[index][valueCounts[index] - 1];
                valueCounts[index]--;
                
                return;
            }
        }
    }
}
