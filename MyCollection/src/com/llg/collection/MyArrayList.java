package com.llg.collection;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class MyArrayList<E> extends AbstractList<E> {

    //默认初始容量
    private static final int DEFAULT_CAPACITY = 10;
    //默认扩容阈值
    private static final float EXPANSION_THRESHOLD = 0.75f;
    //默认增长因子
    private static final float EXPANSION_FACTOR = 0.5f;
    //默认缩小阈值
    private static final float NARROW_THRESHOLD = 0.2f;
    //默认缩小因子
    private static final float NARROW_FACTOR = 0.5f;

    //存储元素的数组
    private Object[] elements;
    //list元素个数
    private int size;
    //该集合的操作次数
    private int modifyCount;

    public MyArrayList() {
        //以默认初始容量创建list
        this(DEFAULT_CAPACITY);
    }

    /**
     * 创建具有指定初始容量的集合
     *
     * @param capacity
     */
    public MyArrayList(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("容量不能为负数！");
        elements = new Object[capacity];
    }

    /**
     * 返回集合元素个数
     *
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 返回集合是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 清空集合
     */
    @Override
    public void clear() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i > 0) builder.append(", ");
            builder.append(elements[i].toString());
        }
        builder.append("]");
        return builder.toString();
    }


    /**
     * 返回次集合对象的数组表示形式
     *
     * @return
     */
    @Override
    public Object[] toArray() {
        //创建一个长度为size的数组
        Object[] objects = new Object[size];
        //将elements中的元素复制到数组中
        for (int i = 0; i < objects.length; i++) {
            objects[i] = elements[i];
        }
        return objects;
    }

    /**
     * 判断list是否需要扩容
     *
     * @return
     */
    private boolean isExpansion() {
        return size >= elements.length * EXPANSION_THRESHOLD;
    }

    /**
     * 判断是否需要减少容量
     *
     * @return
     */
    private boolean isNarrow() {
        return elements.length > DEFAULT_CAPACITY && size <= elements.length * NARROW_THRESHOLD;
    }

    /**
     * 对集合进行扩容操作
     */
    private void expansion() {
        //创建一个新数组,长度为扩容后的长度
        Object[] newElement = new Object[(int) (elements.length * (1 + EXPANSION_FACTOR))];
        //遍历element，将元素复制到新数组中
        for (int i = 0; i < size; i++) {
            newElement[i] = elements[i];
        }
        //用新数组覆盖旧数组完成扩容
        elements = newElement;
    }

    /**
     * 对集合进行缩小容量的操作
     */
    private void narrow() {
        //定义一个新数组，长度为减少容量后的长度
        int len = (int) (elements.length * NARROW_FACTOR) < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : (int) (elements.length * NARROW_FACTOR);
        Object[] newElements = new Object[len];
        //遍历list，将list中的元素复制到新数组中
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        //用新数组覆盖原数组完成减少容量
        elements = newElements;
    }


    /**
     * 向list中添加一个元素,添加到末尾
     *
     * @param element
     * @return
     */
    @Override
    public boolean add(E element) {
        add(size, element);
        return true;
    }

    /**
     * 向list指定位置添加一个元素
     *
     * @param index
     * @param element
     */
    @Override
    public void add(int index, E element) {
        //判断索引是否越界
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        //判断是否需要扩容
        if (isExpansion()) expansion();
        //反向遍历数组，将数组每个元素向后移动一个位置，直到找到index对应的位置，将element插入
        for (int i = size; i >= 0; i--) {
            //判断当前索引和index是否匹配，如果匹配则直接插入element,否则将前一个元素移至当前位置
            if (index == i) {
                elements[i] = element;
                break;
            } else elements[i] = elements[i - 1];
        }
        //list大小加一
        size++;
        //对该集合的操作次数加一
        modifyCount++;
    }

    /**
     * 将指定集合中的元素添加到list末尾
     *
     * @param c
     * @return
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean isModify = false;
        //遍历集合，将集合中的元素放入list
        for (E t : c) {
            add(size, t);
            isModify = true;
        }
        return isModify;

    }

    /**
     * 将指定集合中的元素添加到list指定位置
     *
     * @param index
     * @param c
     * @return
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        //判断索引是否越界
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        //遍历集合，将集合中的元素添加到list指定位置
        //获得集合迭代器
        Iterator iterator = c.iterator();
        boolean isModify = false;
        for (int i = 0; i < c.size(); i++) {
            add(index + i, (E) iterator.next());
            isModify = true;
        }
        return isModify;
    }


    /**
     * 移除list中的指定元素
     *
     * @param o
     * @return
     */
    @Override
    public boolean remove(Object o) {
        //遍历list，找到指定元素索引并移除
        for (int i = 0; i < size; i++) {
            if (elements[i] == o) {
                remove(i);
                return true;
            }
        }
        return false;
    }


    /**
     * 删除并返回指定索引的元素
     *
     * @param index
     * @return
     */
    @Override
    public E remove(int index) {
        //判断索引是否越界
        if (index < 0 || index > size - 1) throw new ArrayIndexOutOfBoundsException("索引越界异常：index=" + index);
        E result = (E) elements[index];
        //遍历数组，将元素前移
        for (int i = index; i < size; i++) {
            elements[i] = elements[i + 1];
        }
        //list大小减一
        size--;
        //集合操作次数加一
        modifyCount++;
        //判断是否需要减少容量
        if (isNarrow()) narrow();
        return result;
    }

    /**
     * 删除指定集合中的元素，如果成功删除了一个元素则返回true，否则返回false
     *
     * @param c
     * @return
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        //定义标记，表示是否删除了元素
        boolean mark = false;
        //遍历集合，删除相同的元素
        for (Object o : c) {
            if (remove(o)) mark = true;
        }
        return mark;
    }

    /**
     * 删除满足条件的元素
     *
     * @param filter
     * @return
     */
    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        //定义标记，表示是否删除了元素
        boolean mark = false;
        //遍历list，删除满足条件的元素
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            E item = iterator.next();
            if (filter.test(item)) {
                iterator.remove();
                mark = true;
            }
        }
        return mark;
    }


    /**
     * 通过索引获取元素
     *
     * @param index
     * @return
     */
    @Override
    public E get(int index) {
        if (index < 0 || index > size - 1) throw new ArrayIndexOutOfBoundsException("索引越界异常：index=" + index);
        return (E) elements[index];
    }

    /**
     * 设置指定索引的元素，并返回之前的元素
     *
     * @param index
     * @param element
     * @return
     */
    @Override
    public E set(int index, E element) {
        if (index < 0 || index > size - 1) throw new ArrayIndexOutOfBoundsException("索引越界异常：index=" + index);
        E old = (E) elements[index];
        elements[index] = element;
        return old;
    }

    /**
     * 返回指定元素在列表中第一次出现的索引，如果不存在则返回-1
     *
     * @param o
     * @return
     */
    @Override
    public int indexOf(Object o) {
        int index = -1;
        //遍历element找到元素对应索引
        for (int i = 0; i < size; i++) {
            if (elements[i] == o) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * 返回指定元素在列表中最后一次出现的索引，如果不存在返回-1
     *
     * @param o
     * @return
     */
    @Override
    public int lastIndexOf(Object o) {
        int index = -1;
        //反向遍历element找到元素对应索引
        for (int i = size - 1; i >= 0; i--) {
            if (elements[i] == o) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * 判断list中是否包含对象
     *
     * @param o
     * @return
     */
    @Override
    public boolean contains(Object o) {
        for (Object o1 : elements) {
            if (o == o1) {
                return true;
            }
        }
        return false;
    }


    /**
     * 判断list中是否包含指定集合的所有元素
     *
     * @param c
     * @return
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        //遍历集合
        for (Object o : c) {
            if (indexOf(o) == -1) return false;
        }
        return true;
    }

    /**
     * 仅保留此列表中包含在指定集合中的元素（保留交集）
     *
     * @param c
     * @return 如果此集合因此发生了改变则返回true
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isModify = false;
        //通过迭代器遍历
        Iterator<E> it1 = iterator();
        while (it1.hasNext()) {
            E t = it1.next();
            boolean isEquals = false;
            Iterator<?> it2 = c.iterator();
            while (it2.hasNext()) {
                if (t == it2.next()) {
                    isEquals = true;
                    break;
                }
            }
            if (!isEquals) {
                it1.remove();
                isModify = true;
            }
        }
        return isModify;
    }

    /**
     * 将集合中的元素替换为经过指定运算后的值
     *
     * @param operator
     */
    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        for (int i = 0; i < this.size(); i++) {
            elements[i] = operator.apply((E) elements[i]);
        }
    }

    /**
     * 通过传入的比较器将集合排序
     *
     * @param c
     */
    @Override
    public void sort(Comparator<? super E> c) {
        //使用冒泡排序
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (c.compare((E) elements[j], (E) elements[j + 1]) > 0) {
                    Object temp = elements[j];
                    elements[j] = elements[j + 1];
                    elements[j + 1] = temp;
                }
            }
        }
    }


    /**
     * 返回此集合的迭代器对象
     *
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return new MyIterator<>();
    }


    /**
     * 返回此对象的listIterator对象
     *
     * @return
     */
    @Override
    public ListIterator<E> listIterator() {
        return new MyListIterator();
    }

    /**
     * 自定义一个迭代器，继承Iterator
     *
     * @param <E>
     */
    private class MyIterator<E> implements Iterator<E> {

        //当前指针指向位置
        int cursor;
        //使用迭代器修改集合的次数
        int iterModifyCount;
        //是否可以执行remove操作
        boolean isRemove = false;

        public MyIterator() {
            iterModifyCount = modifyCount;
        }

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        /**
         * 使指针指向下一个元素并返回当前指向的元素
         *
         * @return
         */
        @Override
        public E next() {
            //如果在使用迭代器过程中使用了非迭代器的remove方法修改了集合结构则抛出此异常
            if (iterModifyCount != modifyCount) throw new ConcurrentModificationException();
            isRemove = true;
            return (E) elements[cursor++];
        }

        /**
         * 使用移除当前指针指向的前一个元素
         */
        @Override
        public void remove() {
            if (!isRemove) throw new IllegalStateException("在调用remove()方法前必须调用next()方法");
            //调用集合的remove方法删除指定索引的元素
            MyArrayList.this.remove(--cursor);
            iterModifyCount++;
            isRemove = false;
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            throw new NullPointerException();
        }
    }


    /**
     * 自定义一个listIterator类
     *
     */
    private class MyListIterator implements ListIterator<E> {
        //指针指向位置
        int cursor;
        //用迭代器操作集合的次数
        int iterModifyCount;
        //定义一个变量记录下一次调用remove方法删除那个元素,0表示不能删除，1表示删除指针前一个，2表示删除指针后一个
        int removeElement = 0;
        //定义一个变量记录下一次调用add方法时将元素插入的位置，true表示插入指针前，false表示插入指针后
        boolean insertPrev = true;
        //定义一个变量表示下一次调用set方法时修改那个元素，0表示不能修改，1表示修改指针前一个，2表示修改指针后一个
        int setElement = 0;

        public MyListIterator() {
            modifyCount = iterModifyCount;
        }

        /**
         * 是否还有下一个元素
         *
         * @return
         */
        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        /**
         * 将指针指向下一个元素并返回当前指向的元素
         *
         * @return
         */
        @Override
        public E next() {
            //如果在使用迭代器过程中使用了非迭代器的remove方法修改了集合结构则抛出此异常
            if (iterModifyCount != modifyCount) throw new ConcurrentModificationException();
            //设置可删除和修改指针指向的上一个元素
            removeElement = 1;
            setElement = 1;
            //设置下一次调用add方法将元素插入指针指向的前一个元素位置
            insertPrev = true;
            return (E) elements[cursor++];
        }

        /**
         * 返回是否有上一个元素
         *
         * @return
         */
        @Override
        public boolean hasPrevious() {
            return cursor > 0;
        }

        /**
         * 将指针指向前一个元素并返回当前指向的元素
         *
         * @return
         */
        @Override
        public E previous() {
            //如果在使用迭代器过程中使用了非迭代器的方法修改了集合结构则抛出此异常
            if (iterModifyCount != modifyCount) throw new ConcurrentModificationException();
            //设置可删除和修改指针指向的下一个元素
            removeElement = 2;
            setElement = 2;
            //设置下一次调用add方法将元素插入指针指向的后一个元素位置
            insertPrev = false;
            return (E) elements[--cursor];
        }

        /**
         * 返回下一个next()方法返回元素的索引
         *
         * @return
         */
        @Override
        public int nextIndex() {
            return cursor;
        }

        /**
         * 返回下一个previous方法所返回的元素的索引
         *
         * @return
         */
        @Override
        public int previousIndex() {
            return cursor;
        }

        /**
         *
         */
        @Override
        public void remove() {
            if(removeElement == 0)  throw new IllegalStateException("在调用remove()方法前必须调用next()或者previous()方法");
            else if(removeElement == 1){
                //删除指针指向的上一个元素
                MyArrayList.this.remove(--cursor);
            }else{
                //删除后一个元素
                MyArrayList.this.remove(cursor+1);
            }
            //操作次数+1
            iterModifyCount++;
            //设置removeElement = 0,如果连续调用remove将抛出异常
            removeElement = 0;
        }

        /**
         * 修改上一次调用next或者previous方法所返回的元素
         * @param e
         */
        @Override
        public void set(E e) {
            if(setElement == 0)  throw new IllegalStateException("在调用set()方法前必须调用next()或者previous()方法");
            else if(setElement == 1){
                //在没有调用remove方法的前提下才能执行修改操作
                if(removeElement != 1) throw new IllegalStateException("该元素已经被删除了");
                //修改元素的值
                elements[cursor - 1] = e;
            }else{
                //在没有调用remove方法的前提下才能执行修改操作
                if(removeElement != 2) throw new IllegalStateException("该元素已经被删除了");
                //修改元素的值
                elements[cursor] = e;
            }
        }

        /**
         * 在上一次调用next返回的元素后面插入一个元素或者在上一次调用previous返回元素的前面插入一个元素
         * @param e
         */
        @Override
        public void add(E e) {
            if(insertPrev) MyArrayList.this.add(cursor++,e);
            else MyArrayList.this.add(cursor+1,e);
            //操作次数+1
            iterModifyCount++;
        }
    }
}
