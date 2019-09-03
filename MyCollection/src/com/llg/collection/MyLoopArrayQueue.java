package com.llg.collection;

/**
 * 使用循环数组实现队列
 */
public class MyLoopArrayQueue<E> {
    //队列默认大小
    private static final int DEFAULT_CAPACITY = 10;
    //队列大小
    private int capacity;
    //队列元素个数
    private int size;
    //队头索引
    private int headIndex;
    //队尾索引
    private int tailIndex;
    private Object[] elements;

    /**
     * 使用默认容量初始化队列
     */
    public MyLoopArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    public MyLoopArrayQueue(int capacity) {
        this.capacity = capacity;
        elements = new Object[capacity];
    }

    /**
     * 返回队列是否满了
     * @return
     */
    public boolean isOverFlow(){
        return size == capacity;
    }

    /**
     * 入队，将元素添加到队列末尾
     * @param e
     * @return
     */
    public boolean enqueue(E e){
        if(isOverFlow()) throw new RuntimeException("队列满了，不能执行入队操作！");
        if(tailIndex == capacity) tailIndex = 0;
        elements[tailIndex++] = e;
        size++;
        return true;
    }

    /**
     * 判断队列是否为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 出队：移除并返回对头元素
     * @return
     */
    public E dequeue(){
        if(isEmpty()) throw new RuntimeException("队列为空，不能执行出队操作！");
        size--;
        if(headIndex == capacity) headIndex = 0;
        return (E)elements[headIndex++];
    }

    /**
     * 返回队列元素个数
     * @return
     */
    public int size(){
        return size;
    }
}
