package com.llg.collection;

import java.util.*;
import java.util.function.Consumer;

public class MyLinkedList<E> extends AbstractList<E> {

    //头节点
    private Node head;
    //组后一个节点
    private Node lastNode;
    //list大小
    private int size;
    //list的操作次数
    int modCount;

    public MyLinkedList() {
        head = new Node();
        lastNode = head;
    }

    /**
     * 向list末尾添加一个元素
     *
     * @param e
     * @return
     */
    @Override
    public boolean add(E e) {
        if (e == null) throw new NullPointerException();
        //创建一个entity
        Node node = new Node(e);
        //将entry插入到lastNode后
        insert(lastNode, node);
        //将entry赋值给lastNode节点
        lastNode = node;
        //元素个数+1
        size++;
        //list操作次数+1
        modCount++;
        return true;
    }

    /**
     * 在头部添加一个元素
     *
     * @param e
     */
    public void addFirst(E e) {
        if (e == null) throw new NullPointerException();
        //如果size == 0则直接在尾部添加
        if (size == 0) {
            add(e);
            return;
        }
        //创建一个Entry
        Node node = new Node(e);
        Node nextNode = head.next;
        //将entry插入到head后
        insert(head, node);
        //将nextNode插入到entry后
        insert(node, nextNode);
        //操作次数+1
        modCount++;
        size++;
    }

    /**
     * 将元素插入到指定索引位置
     *
     * @param index
     * @param element
     */
    @Override
    public void add(int index, E element) {
        if (element == null) throw new NullPointerException();
        if (index < 0 || index > size) throw new ArrayIndexOutOfBoundsException();
        //index == size在尾部添加
        if (index == size) {
            add(element);
            return;
        }
        //index == 0在头部添加
        if (index == 0) {
            addFirst(element);
            return;
        }
        //创建一个entity
        Node entry = new Node(element);
        //找到指定索引的节点
        Node node = getNode(index);
        Node prevNode = node.prev;
        //将entry插入到prevNode后面
        insert(prevNode, entry);
        //将node插入到entry后面
        insert(entry, node);
        size++;
        modCount++;
    }


    /**
     * 将指定集合中的元素插入到尾部
     *
     * @param c
     * @return
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    /**
     * 将指定集合元素插入到指定位置
     *
     * @param index
     * @param c
     * @return
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index < 0 || index > size) throw new ArrayIndexOutOfBoundsException();
        if (c == null) throw new NullPointerException();
        if (c.size() == 0) return false;
        Iterator<? extends E> iterator = c.iterator();
        while (iterator.hasNext()) {
            //将集合元素插入到list中
            add(index++, iterator.next());
        }
        return true;
    }

    /**
     * 将insert节点插入到entry后
     *
     * @param entry
     * @param insert
     */
    private void insert(Node entry, Node insert) {
        if (entry == null || insert == null) throw new NullPointerException();
        //将entry的next指向insert
        entry.next = insert;
        //将insert的prev指向entry
        insert.prev = entry;
    }

    /**
     * 获取指定索引的元素
     *
     * @param index
     * @return
     */
    @Override
    public E get(int index) {
        return getNode(index).data;
    }

    /**
     * 返回指定索引的节点
     * @param index
     * @return
     */
    private Node getNode(int index){
        if (index < 0 || index > size - 1) throw new ArrayIndexOutOfBoundsException();
        Node node;
        //判断index大小决定遍历方式
        if (index > size / 2) {
            node = lastNode;
            //倒序遍历
            for (int i = 0; i < size - index - 1; i++) {
                node = node.prev;
            }
        } else {
            //正序遍历
            node = head;
            for (int i = 0; i <= index; i++) {
                node = node.next;
            }
        }
        return node;
    }

    /**
     * 获取指定元素的节点
     * @param o
     * @return
     */
    private Node getNode(Object o){
        if(o == null) throw new NullPointerException();
        Node node = head.next;
        for (int i = 0; i < size; i++) {
            if (node.data == o) break;
            node = node.next;
        }
        return node;
    }

    /**
     * 设置指定索引位置的值，并返回原来的值
     * @param index
     * @param element
     * @return
     */
    @Override
    public E set(int index, E element) {
        //获取指定索引的节点
        Node node = getNode(index);
        //获取原值
        E data = node.data;
        //设置新值
        node.data = element;
        return data;
    }

    /**
     * 移除并返回最后一个元素
     * @return
     */
    public E removeLast(){
        return remove(size - 1);
    }

    /**
     * 移除并返回第一个元素
     * @return
     */
    public E removeFirst(){
        return remove(0);
    }

    /**
     * 移除指定索引的元素,并返回该元素
     * @param index
     * @return
     */
    @Override
    public E remove(int index) {
        if(index < 0 || index > size -1) throw new ArrayIndexOutOfBoundsException();
        //获取指定索引的节点
        Node node = getNode(index);
        size--;
        modCount++;
        if(node == lastNode){
            lastNode = lastNode.prev;
            lastNode.next = null;
            return node.data;
        }else{
            //移除节点
            insert(node.prev,node.next);
            return node.data;
        }
    }

    /**
     * 删除指定元素
     * @param o
     * @return
     */
    @Override
    public boolean remove(Object o) {
        //获取指定元素对应的节点
        Node node = getNode(o);
        if(node == null) return false;
        //删除节点
        if(node == lastNode){ removeLast();
        }else {
            insert(node.prev,node.next);
            size--;
            modCount++;
        }
        return true;
    }

    /**
     * 判断list是否为空
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 判断是否包含某个元素
     * @param o
     * @return
     */
    @Override
    public boolean contains(Object o) {
        return getNode(o) != null;
    }

    /**
     * 将list转换为数组并返回
     * @return
     */
    @Override
    public Object[] toArray() {
        Object[] objects = new Object[size];
        for (int i = 0; i < size; i++) {
            objects[i] = get(i);
        }
        return objects;
    }


    /**
     * 返回元素的索引，没找到返回-1
     * @param o
     * @return
     */
    @Override
    public int indexOf(Object o) {
        if(size == 0) return -1;
        int index = 0;
        Node node = head;
        for (int i = 0; i < size; i++) {
            node = node.next;
            if(node.data == o) break;
            index++;
        }
        if(index == size) return -1;
        return index;
    }

    /**
     * 返回指定元素最后一次出现的索引
     * @param o
     * @return
     */
    @Override
    public int lastIndexOf(Object o) {
        if(size == 0) return -1;
        int index = size-1;
        Node entry = lastNode;
        for (int i = 0; i < size; i++) {
            if(entry.data == o) break;
            entry = entry.prev;
            index--;
        }
        if(index == -1) return -1;
        return index;
    }

    /**
     * 清空list
     */
    @Override
    public void clear() {
        size = 0;
        head.next = null;
        lastNode = head;
    }

    /**
     * 返回该list的一个迭代器
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        //遍历list
        Node entry = head;
        for (int i = 0; i < size; i++) {
            entry = entry.next;
            if (i > 0) sb.append(" ,");
            sb.append(entry.data.toString());
        }
        sb.append("]");
        return sb.toString();
    }
    @Override
    public int size() {
        return size;
    }

    class Node {
        E data;
        Node prev;
        Node next;

        public Node() {
        }

        public Node(E data) {
            this.data = data;
        }
    }

    class MyIterator implements Iterator<E>{

        //当前指针指向位置
        Node cursor = head;
        //记录list的操作次数
        int iterModCount;
        //是否可以调用remove操作
        boolean isRemove;

        public MyIterator() {
            iterModCount = modCount;
            isRemove = false;
        }

        @Override
        public boolean hasNext() {
            return cursor != lastNode;
        }

        @Override
        public E next() {
            if(iterModCount != modCount) throw new ConcurrentModificationException();
            isRemove = true;
            cursor = cursor.next;
            return cursor.data;
        }

        @Override
        public void remove() {
            if(!isRemove) throw new IllegalStateException("在调用remove()方法前必须调用next()方法");
            isRemove = false;
            insert(cursor.prev,cursor.next);
            cursor = cursor.next;
            size--;
        }
    }
}
