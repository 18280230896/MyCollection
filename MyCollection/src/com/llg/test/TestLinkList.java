package com.llg.test;

import com.llg.collection.MyLinkedList;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TestLinkList {
    private MyLinkedList<String> list;
    @Before
    public void before(){
        list = new MyLinkedList<>();
        list.add("str1");
        list.add("str2");
        list.add("str3");
        list.add("str3");
        list.add("str4");
        list.add("str5");
    }

    @Test
    public void testAdd01(){
        System.out.println(list);
    }

    @Test
    public void testAdd02(){
        list.addFirst("add1");
        list.addFirst("add2");
        list.addFirst("add3");
        System.out.println(list);
    }

    @Test
    public void testAdd03(){
        list.add(0,"add1");
        list.add(list.size(),"add2");
        list.add(3,"add3");
        System.out.println(list);
    }

    @Test
    public void testAdd04(){
        List<String> list2 = new ArrayList<>();
        list2.add("111");
        list2.add("222");
        list2.add("333");
        list.addAll(list2);
        System.out.println(list);
    }

    @Test
    public void testAdd05(){
        List<String> list2 = new ArrayList<>();
        list2.add("111");
        list2.add("222");
        list2.add("333");
        list.addAll(2,list2);
        System.out.println(list);
    }

    @Test
    public void testGet(){
        System.out.println(list.get(0));
        System.out.println(list.get(2));
        System.out.println(list.get(list.size()-1));
    }


    @Test
    public void testSet(){
        System.out.println(list.set(0,"111"));
        System.out.println(list.set(2,"222"));
        System.out.println(list.set(list.size()-1,"333"));
        System.out.println(list);
    }

    @Test
    public void remove01(){
        System.out.println(list.removeLast());
        System.out.println(list.removeLast());
        System.out.println(list);
    }

    @Test
    public void remove02(){
        System.out.println(list.removeFirst());
        System.out.println(list.removeFirst());
        System.out.println(list);
    }

    @Test
    public void remove03(){
        System.out.println(list.remove(0));
        System.out.println(list.remove(list.size()-1));
        System.out.println(list);
    }

    @Test
    public void remove04(){
        System.out.println(list);
        System.out.println(list.remove("str1"));
        System.out.println(list.remove("aaa"));
        System.out.println(list);
    }

    @Test
    public void test(){
        System.out.println(list.isEmpty());
        System.out.println(list.contains("str1"));
        System.out.println(list.contains("str"));
        System.out.println(Arrays.toString(list.toArray()));

        System.out.println(list.indexOf("str"));
        System.out.println(list.indexOf("str3"));
        System.out.println(list.lastIndexOf("str3"));
    }

    @Test
    public void testIter(){
        System.out.println(list);
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println(list);
    }

    @Test
    public void testIter2(){
        System.out.println(list);
        Iterator<String> it = list.iterator();
        System.out.println(it.next());
        System.out.println(it.next());
        it.remove();
        System.out.println(list);
    }
}
