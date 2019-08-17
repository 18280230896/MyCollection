package com.llg.test;

import com.llg.collection.MyArrayList;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class TestArrayList {

    MyArrayList<String> list;

    @Before
    public void before() {
        list = new MyArrayList<>();
        list.add("str1");
        list.add("str2");
        list.add("str3");
        list.add("str3");
        list.add("str4");
        list.add("str5");
    }

    /**
     * 测试 boolean add(E element)
     * 将元素追加到集合末尾
     */
    @Test
    public void testAdd01() {
        System.out.println(list);
    }


    /**
     * 测试add(int index, E element)
     * 将元素追加到指定索引位置
     */
    @Test
    public void testAdd02() {
        list.add(0, "插入1");
        list.add(2, "插入2");
        list.add(list.size(), "插入3");
        System.out.println(list);
    }

    /**
     * 测试boolean addAll(Collection<? extends E> c)
     * 将指定集合中的元素添加到list末尾
     */
    @Test
    public void testAdd03() {
        MyArrayList<String> list2 = new MyArrayList<>();
        list2.add("haha");
        list2.add("hehe");
        list2.add("xixi");
        list.addAll(list2);
        System.out.println(list);
    }

    /**
     * 测试 boolean addAll(int index, Collection<? extends E> c)
     * 将指定集合中的元素添加到list指定位置
     */
    @Test
    public void testAdd04() {
        MyArrayList<String> list2 = new MyArrayList<>();
        list2.add("haha");
        list2.add("hehe");
        list2.add("xixi");
        list.addAll(3, list2);
        System.out.println(list);
    }

    /**
     * 测试size,isEmpty,clear,toString,toArray
     */
    @Test
    public void test() {
        System.out.println(list.size());
        System.out.println(list.isEmpty());
        System.out.println(list);
        System.out.println(Arrays.toString(list.toArray()));
        list.clear();
        System.out.println(list.size());
        System.out.println(list.isEmpty());
    }

    /**
     * 测试boolean remove(Object o)
     * 移除集合中的指定元素
     */
    @Test
    public void testRemove01() {
        System.out.println(list.remove("aaaaa"));
        System.out.println(list.remove("str2"));
        System.out.println(list);
    }

    /**
     * 测试E remove(int index)
     * 移除并返回指定索引的元素
     */
    @Test
    public void testRemove02() {
        System.out.println(list.remove(2));
        System.out.println(list);
    }

    /**
     * 测试 boolean removeAll(Collection<?> c)
     * 删除指定集合中的元素，如果成功删除了一个元素则返回true，否则返回false
     */
    @Test
    public void testRemove03() {
        List<String> list2 = new MyArrayList<>();
        list2.add("str3");
        list2.add("str4");
        list2.add("str5");
        list2.add("str6");
        list2.add("str7");
        System.out.println(list.removeAll(list2));
        System.out.println(list);
    }

    /**
     * 测试boolean removeIf(Predicate<? super E> filter)
     * 删除满足条件的元素
     */
    @Test
    public void testRemove04() {
        System.out.println(list);
        //删除所有以3结尾的元素
        System.out.println(list.removeIf(s -> s.endsWith("3")));
        System.out.println(list);
    }

    /**
     * 测试E get(int index)
     * 获取指定索引的元素
     */
    @Test
    public void testGet() {
        System.out.println(list.get(3));
    }

    /**
     * 测试 E set(int index, E element)
     * 设置指定索引的元素，并返回之前的元素
     */
    @Test
    public void testSet() {
        System.out.println(list.set(4, "haha"));
        System.out.println(list);
    }

    /**
     * 测试 int indexOf(Object o)
     * 返回指定元素在列表中第一次出现的索引，如果不存在则返回-1
     */
    @Test
    public void testIndexOf() {
        System.out.println(list);
        System.out.println(list.indexOf("str3"));
    }

    /**
     * 测试 lastIndexOf(Object o)
     * 回指定元素在列表中最后一次出现的索引，如果不存在返回-1
     */
    @Test
    public void testLastIndexOf() {
        System.out.println(list);
        System.out.println(list.lastIndexOf("str3"));
    }

    /**
     * 测试 boolean contains(Object o)
     * 判断list中是否包含对象
     */
    @Test
    public void testContains() {
        System.out.println(list.contains("haha"));
        System.out.println(list.contains("str2"));
    }

    /**
     * 测试  boolean containsAll(Collection<?> c)
     * 判断list中是否包含指定集合的所有元素
     */
    @Test
    public void testContainsAll() {
        List<String> list2 = new ArrayList<>();
        list2.add("str2");
        list2.add("str3");
        list2.add("str4");
        System.out.println(list.containsAll(list2));
        list2.add("haha");
        System.out.println(list.containsAll(list2));
    }


    /**
     * 测试 boolean retainAll(Collection<?> c)
     * 仅保留此列表中包含在指定集合中的元素（保留交集）
     */
    @Test
    public void testRetainAll() {
        List<String> list2 = new ArrayList<>();
        list2.add("str2");
        list2.add("haha");
        list2.add("hehe");
        list2.add("str4");
        System.out.println(list);
        list.retainAll(list2);
        System.out.println(list);
    }

    /**
     * 测试void replaceAll(UnaryOperator<E> operator)
     * 将集合中的元素替换为经过指定运算后的值
     */
    @Test
    public void testReplaceAll() {
        list.replaceAll(s->s+"哈哈");
        System.out.println(list);
    }

    /**
     * 测试void sort(Comparator<? super E> c)
     * 通过传入的比较器将集合排序
     */
    @Test
    public void testSort() {
        list.add("qrefafeqer");
        list.add("ouoiuo");
        list.add("qerq");
        list.add("qreqer");
        //通过字符串长度排序
        list.sort(Comparator.comparing(String::length));
        System.out.println(list);
    }

    /**
     * 测试Iterator<E> iterator()
     * 返回此集合的迭代器对象
     */
    @Test
    public void testIterator() {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * 测试ListIterator<E> listIterator()
     * 返回此集合的列表迭代器对象
     */
    @Test
    public void testItr1() {
        ListIterator<String> iterator = list.listIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("--------");
        while (iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }
    }

    /**
     * 测试ListIterator<E> listIterator()
     * 返回此集合的列表迭代器对象
     */
    @Test
    public void testItr2() {
        ListIterator<String> iterator = list.listIterator();
        iterator.add("add1");
        iterator.next();
        iterator.next();
        iterator.add("add2");
        System.out.println(list);
        iterator.previous();
        iterator.add("add3");
        System.out.println(list);

    }

    /**
     * 测试ListIterator<E> listIterator()
     * 返回此集合的列表迭代器对象
     */
    @Test
    public void testItr3() {
        ListIterator<String> it = list.listIterator();
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.previous());
        it.set("set");
        System.out.println(list);
    }
}