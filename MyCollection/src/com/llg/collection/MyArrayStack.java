package com.llg.collection;

/**
 * 使用数组实现栈
 */
public class MyArrayStack<E> {

    //默认初始容量
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    //栈顶元素索引
    private int top;

    public MyArrayStack() {
        elements = new Object[DEFAULT_CAPACITY];
        top = -1;
    }

    /**
     * 判断栈是否满了
     * @return
     */
    public boolean isOverFolw(){
        return top == DEFAULT_CAPACITY - 1;
    }

    /**
     * 判断栈是否为空
     * @return
     */
    public boolean isEmpty(){
        return top == -1;
    }

    /**
     * 压栈，将元素放到栈顶位置
     * @param e
     */
    public void push(E e){
        if(isOverFolw()) throw new RuntimeException("栈满了");
        elements[++top] = e;
    }

    /**
     * 返回栈顶元素
     * @return
     */
    public E top(){
        if(isEmpty()) throw new RuntimeException("栈为空");
        return (E)elements[top];
    }

    /**
     * 删除并返回栈顶元素
     * @return
     */
    public E pop(){
        if(isEmpty()) throw new RuntimeException("栈为空");
        return (E)elements[top--];
    }
}
