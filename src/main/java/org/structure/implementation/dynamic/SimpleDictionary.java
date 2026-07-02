package org.structure.implementation.dynamic;

import org.exception.ElementNotFoundADTException;
import org.structure.definition.LinkedListADT;
import org.structure.definition.SetADT;
import org.structure.definition.SimpleDictionaryADT;

public class SimpleDictionary<T> implements SimpleDictionaryADT<T> {
    private LinkedListADT<Integer> keys;
    private LinkedListADT<T> values;

    public SimpleDictionary() {
        this.keys = new LinkedList<>();
        this.values = new LinkedList<>();
    }

    @Override
    public void add(int key, T value) {
        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i) == key) {
                values.remove(i);
                values.insert(i, value);
                return;
            }
        }
        keys.add(key);
        values.add(value);
    }

    @Override
    public void remove(int key) {
        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i) == key) {
                keys.remove(i);
                values.remove(i);
                return;
            }
        }
    }

    @Override
    public T get(int key) {
        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i) == key) return values.get(i);
        }
        throw new ElementNotFoundADTException();
    }

    @Override
    public SetADT getKeys() {
        SetADT result = new Set();
        for (int i = 0; i < keys.size(); i++) {
            result.add(keys.get(i));
        }
        return result;
    }

    @Override
    public boolean isEmpty() {
        return keys.isEmpty();
    }
}
