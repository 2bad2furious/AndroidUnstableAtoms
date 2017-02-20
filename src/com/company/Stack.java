package com.company;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack{
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITAIL_CAPACITY = 16;

    public Stack(){
        elements = new Object [DEFAULT_INITAIL_CAPACITY];
    }

    public void push(Object e){
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop(){
        if (size == 0)
            throw new EmptyStackException();
        return elements[--size];
    }

    private void ensureCapacity(){
        if(elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }
}