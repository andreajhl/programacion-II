package org.structure.implementation.dynamic;

import org.exception.ElementNotFoundADTException;
import org.structure.definition.LinkedListADT;
import org.structure.definition.MultipleDictionaryADT;
import org.structure.definition.SetADT;

public class MultipleDictionary implements MultipleDictionaryADT {
    private LinkedListADT<Integer> keys;
    private LinkedListADT<Integer>[] valuesList;
    private int size;
    private final int DEFAULT_SIZE = 1000;

    @SuppressWarnings("unchecked")
    public MultipleDictionary() {
        this.keys = new LinkedList<>();
        this.valuesList = new LinkedListADT[DEFAULT_SIZE];
        this.size = 0;
    }

    private int findKeyIndex(int key) {
        for (int i = 0; i < size; i++) {
            if (keys.get(i) == key) return i;
        }

        return -1;
    }

    @Override
    public void add(int key, int value) {
        int index = findKeyIndex(key);
        if (index == -1) {
            if (size == DEFAULT_SIZE)  throw new RuntimeException("Dictionary is full");

            keys.add(key);
            valuesList[size] = new LinkedList<>();
            valuesList[size].add(value);
            size++;
        } else {
            valuesList[index].add(value);
        }
    }

    @Override
    public void remove(int key) {
        int index = findKeyIndex(key);
        if (index == -1) return;

        keys.remove(index);

        for (int i = index; i < size - 1; i++) {
            valuesList[i] = valuesList[i + 1];
        }

        valuesList[size - 1] = null;
        size--;
    }

    @Override
    public int[] get(int key) {
        int index = findKeyIndex(key);
        if (index == -1) throw new ElementNotFoundADTException();

        LinkedListADT<Integer> list = valuesList[index];
        int[] result = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }

    @Override
    public SetADT getKeys() {
        SetADT result = new Set();

        for (int i = 0; i < size; i++) {
            result.add(keys.get(i));
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

        LinkedListADT<Integer> list = valuesList[index];

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == value) {
                list.remove(i);
                
                return;
            }
        }
    }
}
