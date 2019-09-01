package com.llg.collection;

/**
 * 使用链表实现栈结构
 */
public class MyLinkedStack<E> {

    //头节点
    private Node head;
    //用于记录最后一个节点的引用
    private Node tail;

    public MyLinkedStack() {
        head = new Node(null, null,null);
        tail = head;
    }

    /**
     * 压栈，将元素添加到栈顶部
     *
     * @param e
     */
    public void push(E e) {
        Node node = new Node(e, tail, null);
        tail.next = node;
        tail = node;
    }

    /**
     * 判断栈是否为空
     * @return
     */
    public boolean isEmpty(){
        return tail == head;
    }

    /**
     * 返回栈顶元素的值，但不删除元素
     * @return
     */
    public E top(){
        if(isEmpty()) throw  new RuntimeException("栈为空");
        return (E) tail.data;
    }

    /**
     * 返回并移除栈顶元素
     * @return
     */
    public E pop(){
        if(isEmpty()) throw  new RuntimeException("栈为空");
        E data = (E)tail.data;
        tail = tail.prev;
        tail.next = null;
        return data;
    }


    class Node<E> {
        E data;
        Node prev;
        Node next;

        public Node(E data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }
}
