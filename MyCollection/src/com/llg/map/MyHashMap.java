package com.llg.map;


import java.util.Objects;
import java.util.function.BiConsumer;

public class MyHashMap<K, V> {

    //哈希表默认长度
    public static final int DEFAULT_LENGTH = 16;
    //哈希表长度
    private int tableLength;

    private int size;

    private Node[] hashTable;

    public MyHashMap() {
        this(DEFAULT_LENGTH);
    }

    public MyHashMap(int length) {
        tableLength = length;
        hashTable = new Node[tableLength];
    }

    /**
     * 添加一组键值对到hash表中
     *
     * @param key
     * @param value
     * @return
     */
    public V put(K key, V value) {
        //获取key的hash值
        int hash = key == null ? 0 : key.hashCode();
        //找到元素对应的hash表索引
        int index = hash & (tableLength - 1);
        //如果该索引位置为空，则在该位置创建一个链表头节点，然后将元素插入到头节点后
        if (hashTable[index] == null) {
            Node node = new Node(key, value, null);
            Node head = new Node(null, null, node);
            hashTable[index] = head;
            size++;
            return null;
        }
        //否则遍历链表，将元素插入链表末尾或者覆盖之前的元素
        Node node = hashTable[index];
        while (node.next != null) {
            node = node.next;
            //判断key是否相同
            if (Objects.equals(key, node.key)) {
                //相等则覆盖并返回之前的值
                V oldValue = (V) node.value;
                node.value = value;
                return oldValue;
            }
        }
        //将值添加到链表尾部
        node.next = new Node(key, value, null);
        size++;
        return null;
    }

    /**
     * 通过key返回value,没找到返回null
     *
     * @param k
     * @return
     */
    public V get(K k) {
        //遍历hashTable
        for (Node head : hashTable) {
            if (head == null) continue;
            Node node = head.next;
            while (node != null) {
                //判断key值是否相等
                if (Objects.equals(node.key, k)) return (V) node.value;
                node = node.next;
            }
        }
        return null;
    }

    /**
     * 返回当前哈希表的元素个数
     *
     * @return
     */
    public int size() {
        return size;
    }


    /**
     * 返回哈希表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 判断表中是否包含某个key
     *
     * @param k
     * @return
     */
    public boolean containsKey(K k) {
        //遍历hashTable
        for (Node head : hashTable) {
            if (head == null) continue;
            Node node = head.next;
            while (node != null) {
                //判断key值是否相等
                if (Objects.equals(node.key, k)) return true;
                node = node.next;
            }
        }
        return false;
    }

    /**
     * 判断表中是否包含某个value
     *
     * @param v
     * @return
     */
    public boolean containsValue(V v) {
        //遍历hashTable
        for (Node head : hashTable) {
            if (head == null) continue;
            Node node = head.next;
            while (node != null) {
                //判断value值是否相等
                if (Objects.equals(node.value, v)) return true;
                node = node.next;
            }
        }
        return false;
    }

    /**
     * 根据key删除某个键值对
     *
     * @param k
     * @return
     */
    public V remove(K k) {
        //遍历hashTable
        for (Node head : hashTable) {
            if (head == null) continue;
            Node prevNode = head;
            Node node = head.next;
            while (node != null) {
                //判断value值是否相等
                if (Objects.equals(node.key, k)) {
                    prevNode.next = node.next;
                    return (V) node.value;
                }
                node = node.next;
                prevNode = prevNode.next;
            }
        }
        return null;
    }

    /**
     * 对外提供一个遍历的方法
     *
     * @param consumer
     */
    public void forEach(BiConsumer<K, V> consumer) {
        //遍历hashTable
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] == null) continue;
            //遍历链表
            Node node = hashTable[i].next;
            if (node != null) {
                //调用
                consumer.accept((K) node.key, (V) node.value);
            }
            node = node.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        boolean isFirst = true;
        for (Node head : hashTable) {
            if (head == null) continue;
            Node node = head.next;
            while (node != null) {
                if (isFirst) isFirst = false;
                else sb.append(" ,");
                sb.append(node.key);
                sb.append(" = ");
                sb.append(node.value);
                node = node.next;
            }
        }
        sb.append("]");
        return sb.toString();
    }

    //链表节点类
    class Node<K, V> {
        K key;
        V value;
        Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

}
