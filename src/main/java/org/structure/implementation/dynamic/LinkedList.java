package org.structure.implementation.dynamic;

import org.exception.ElementNotFoundADTException;
import org.exception.EmptyADTException;
import org.exception.IndexOutOfBoundsADTException;
import org.structure.definition.LinkedListADT;
import org.structure.implementation.Node;

public class LinkedList<T> implements LinkedListADT<T> {
    private Node<T> firstNode;

    public LinkedList() {
        this.firstNode = null;
    }

    @Override
    public void add(T value) {
        Node<T> lastNode = getLastNode();
        Node<T> newNode = new Node<>(value);
        if (lastNode == null)
            this.firstNode = newNode;
        else
            lastNode.setNextNode(newNode);
    }

    @Override
    public void insert(int index, T value) {
        if (index < 0 || index > this.size())
            throw new IndexOutOfBoundsADTException();
        if (index == this.size()) {
            this.add(value);
            return;
        }
        Node<T> insertedNode = new Node<>(value);
        if (index == 0) {
            insertedNode.setNextNode(this.firstNode);
            this.firstNode = insertedNode;
            return;
        }
        Node<T> nextNode = getNode(index);
        Node<T> previousNode = getNode(index - 1);
        insertedNode.setNextNode(nextNode);
        previousNode.setNextNode(insertedNode);
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsADTException();
        Node<T> nextNode = getNode(index + 1);
        if (index == 0) {
            firstNode = nextNode;
        } else {
            Node<T> previousNode = getNode(index - 1);
            previousNode.setNextNode(nextNode);
        }
    }

    @Override
    public T get(int index) {
        if (index < 0)
            throw new IndexOutOfBoundsADTException();
        if (isEmpty())
            throw new EmptyADTException();
        Node<T> node = getNode(index);
        if (node == null)
            throw new ElementNotFoundADTException();
        return node.getValue();
    }

    @Override
    public int size() {
        Node<T> currentNode = firstNode;
        int size = 0;
        while (currentNode != null) {
            currentNode = currentNode.getNextNode();
            size++;
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return firstNode == null;
    }

    private Node<T> getNode(int index) {
        Node<T> currentNode = firstNode;
        int i = 0;
        while (currentNode != null && i < index) {
            currentNode = currentNode.getNextNode();
            i++;
        }
        return currentNode;
    }

    private Node<T> getLastNode() {
        Node<T> currentNode = firstNode;
        Node<T> lastNode = null;
        while (currentNode != null) {
            lastNode = currentNode;
            currentNode = currentNode.getNextNode();
        }
        return lastNode;
    }
}
