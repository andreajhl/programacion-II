package org.structure.implementation.dynamic;

import org.exception.EmptyADTException;
import org.structure.definition.PriorityQueueADT;
import org.structure.implementation.PriorityNode;

public class PriorityQueue implements PriorityQueueADT {
    private PriorityNode firstNode;

    public int getElement() {
        if(firstNode != null)
            return firstNode.getValue();
        throw new EmptyADTException();
    }

    public int getPriority() {
        if(firstNode != null)
            return firstNode.getPriority();
        throw new EmptyADTException();
    }

    public void add(int value, int priority) {
        PriorityNode newNode = new PriorityNode(value,priority);
        if(firstNode == null){
            firstNode = newNode;
            return;
        }

        PriorityNode currentNode = firstNode;
        PriorityNode previusNode = null;
        boolean inserted = false;
        while(!inserted){
            if(priority > currentNode.getPriority()){
                newNode.setNextNode(currentNode);
                if(previusNode == null){
                    firstNode = newNode;
                }
                else{
                    previusNode.setNextNode(newNode);
                }
                inserted = true;
            }
            else if(currentNode.getNextNode() == null){
                currentNode.setNextNode(newNode);
                inserted = true;
            }
            previusNode = currentNode;
            currentNode = currentNode.getNextNode();
        }
    }

    public void remove() {
        if(isEmpty()){
            throw new EmptyADTException();
        }
        firstNode = firstNode.getNextNode();
    }

    public boolean isEmpty() {
        return firstNode == null;
    }
}
