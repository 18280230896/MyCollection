package com.llg.collection;

/**
 * 使用链表实现队列
 * @param <E>
 */
public class MyLinkedQueue<E> {

    //头节点
    private Node head;
    //尾节点
    private Node tail;
    //元素个数
    private int size;

    public MyLinkedQueue() {
        head = new Node(null, null, null);
        tail = new Node(null, head, null);
        head.next = tail;
    }

    /**
     * 入队，将元素加入队列末尾
     * @param data
     * @return
     */
    public boolean enqueue(E data){
        Node node = new Node(data, null, null);
        tail.prev.next = node;
        node.next = tail;
        node.prev = tail.prev;
        tail.prev = node;
        size ++;
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
     * 出对，移除并返回对头元素
     * @return
     */
    public E dequeue(){
        if(isEmpty()) throw new RuntimeException("队列为空！不能执行出队操作！");
        E data = (E)head.next.data;
        head.next.next.prev = head;
        head.next = head.next.next;
        size--;
        return data;
    }

    public int size(){
        return size;
    }


    class Node<E>{
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
