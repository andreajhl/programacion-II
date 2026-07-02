package org.structure.implementation;

public class PriorityNode {
    private int priority;
    private int value;
    private PriorityNode nextNode;

    public PriorityNode(int value, int priority) {
        this.value = value;
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public PriorityNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(PriorityNode nextNode) {
        this.nextNode = nextNode;
    }
}
