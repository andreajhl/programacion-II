package org.structure.implementation.dynamic;

import org.structure.definition.GraphADT;
import org.structure.definition.LinkedListADT;
import org.structure.definition.SetADT;

public class Graph<T> implements GraphADT<T> {

    private Node firstNode;

    private Node findNode(T value) {
        Node current = firstNode;
        while (current != null) {
            if (current.value.equals(value))
                return current;
            current = current.nextNode;
        }
        return null;
    }

    @Override
    public SetADT<T> getVertxs() {
        SetADT<T> result = new Set<>();
        Node current = firstNode;
        while (current != null) {
            result.add(current.value);
            current = current.nextNode;
        }
        return result;
    }

    @Override
    public void addVertx(T vertex) {
        if (findNode(vertex) != null)
            return;
        Node newNode = new Node(vertex);
        newNode.nextNode = firstNode;
        firstNode = newNode;
    }

    @Override
    public void removeVertx(T vertex) {
        if (firstNode == null)
            return;

        Node current = firstNode;
        while (current != null) {
            Edge prevEdge = null;
            Edge edge = current.edges;
            while (edge != null) {
                if (edge.destination.value.equals(vertex)) {
                    if (prevEdge == null)
                        current.edges = edge.nextEdge;
                    else
                        prevEdge.nextEdge = edge.nextEdge;
                } else {
                    prevEdge = edge;
                }
                edge = edge.nextEdge;
            }
            current = current.nextNode;
        }

        if (firstNode.value.equals(vertex)) {
            firstNode = firstNode.nextNode;
            return;
        }
        Node prev = firstNode;
        while (prev.nextNode != null) {
            if (prev.nextNode.value.equals(vertex)) {
                prev.nextNode = prev.nextNode.nextNode;
                return;
            }
            prev = prev.nextNode;
        }
    }

    @Override
    public void addEdge(T vertxOne, T vertxTwo, int weight) {
        Node nodeOne = findNode(vertxOne);
        Node nodeTwo = findNode(vertxTwo);
        if (nodeOne == null || nodeTwo == null)
            return;

        Edge edge = nodeOne.edges;
        while (edge != null) {
            if (edge.destination.value.equals(vertxTwo)) {
                edge.weight = weight;
                return;
            }
            edge = edge.nextEdge;
        }

        Edge newEdge = new Edge();
        newEdge.destination = nodeTwo;
        newEdge.weight = weight;
        newEdge.nextEdge = nodeOne.edges;
        nodeOne.edges = newEdge;
    }

    @Override
    public void removeEdge(T vertxOne, T vertxTwo) {
        Node nodeOne = findNode(vertxOne);
        if (nodeOne == null)
            return;

        Edge prev = null;
        Edge edge = nodeOne.edges;
        while (edge != null) {
            if (edge.destination.value.equals(vertxTwo)) {
                if (prev == null)
                    nodeOne.edges = edge.nextEdge;
                else
                    prev.nextEdge = edge.nextEdge;
                return;
            }
            prev = edge;
            edge = edge.nextEdge;
        }
    }

    @Override
    public boolean existsEdge(T vertxOne, T vertxTwo) {
        Node nodeOne = findNode(vertxOne);
        if (nodeOne == null)
            return false;
        Edge edge = nodeOne.edges;
        while (edge != null) {
            if (edge.destination.value.equals(vertxTwo))
                return true;
            edge = edge.nextEdge;
        }
        return false;
    }

    @Override
    public int edgeWeight(T vertxOne, T vertxTwo) {
        Node nodeOne = findNode(vertxOne);
        if (nodeOne == null)
            return 0;
        Edge edge = nodeOne.edges;
        while (edge != null) {
            if (edge.destination.value.equals(vertxTwo))
                return edge.weight;
            edge = edge.nextEdge;
        }
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return firstNode == null;
    }

    class Node {
        Node nextNode;
        T value;
        Edge edges;

        public Node(T value) {
            this.value = value;
        }
    }


    class Edge {
        int weight;
        Node destination;
        Edge nextEdge;
    }
}
