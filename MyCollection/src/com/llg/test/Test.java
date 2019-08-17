package com.llg.test;

import com.llg.collection.MyArrayList;

import java.util.Iterator;
import java.util.function.Function;

public class Test {
    public static void main(String[] args) {
//        testIterator();
//        testRemoveIf();
//        testRetainAll();
        testReplaceAll();
    }

    public static void testReplaceAll(){
        MyArrayList<Integer> list1 = new MyArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(5);
        list1.replaceAll(i -> i + 1);
        System.out.println(list1);
    }

    public static void testRetainAll(){
        MyArrayList<Integer> list1 = new MyArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(5);
        MyArrayList<Integer> list2 = new MyArrayList<>();
        list2.add(3);
        list2.add(4);
        list2.add(5);
        list2.add(6);

        System.out.println(list1);
        list1.retainAll(list2);
        System.out.println(list1);
    }

    public static void testRemoveIf(){
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(4);
        list.add(3);
        list.add(1);
        list.add(5);
        list.add(2);
        System.out.println(list);
        list.removeIf(i -> i > 3);
        System.out.println(list);
    }

    public static void testIterator(){
        MyArrayList<String> list = new MyArrayList<>();
        list.add("str1");
        list.add("str2");
        list.add("str3");
        list.add("str4");
        list.add("str5");
        //获得迭代器
        Iterator<String> it = list.iterator();
        int index = 0;
        while(it.hasNext()){
            System.out.println(it.next());
            if(index == 3) it.remove();
            index ++;
        }
        System.out.println(list);
    }
}
