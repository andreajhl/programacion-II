package org.structure.implementation.fixed;

import org.exception.EmptyADTException;
import org.exception.FullADTException;
import org.structure.definition.PriorityQueueADT;

public class PriorityQueue implements PriorityQueueADT {
    private int[] values;
    private int[] priorities;
    private int size;
    private final int MAX_SIZE = 1000;

    public PriorityQueue(){
        this.values = new int[MAX_SIZE];
        this.priorities = new int[MAX_SIZE];
    }

    @Override
    public int getElement() {
        if(isEmpty()){
            throw new EmptyADTException();
        }
        return values[0];
    }

    @Override
    public int getPriority() {
        if(isEmpty()){
            throw new EmptyADTException();
        }
        return priorities[0];
    }

    @Override
    public void add(int value, int priority) {
        if(this.size == MAX_SIZE){
            throw new FullADTException();
        }

        int index = 0;

        while(index < this.size){
            if(priority > priorities[index]) {
                break;
            }
            index++;
        }
        insert(index, value, priority);
        size++;
    }

    private void insert(int index, int value, int priority){
        if(index < this.size){
            System.arraycopy(values, index, values,index+1,this.size - index);
            System.arraycopy(priorities, index, priorities,index+1,this.size - index);
        }
        values[index] = value;
        priorities[index] = priority;
    }

    @Override
    public void remove() {
        if(this.isEmpty())
            throw new EmptyADTException();
        this.size--;
        System.arraycopy(values,1,values,0,this.size);
        System.arraycopy(priorities,1,priorities,0,this.size);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
