package com.work;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *自己实现一了个LinkedList，主要实现List接口里的方法
 *
 * 已经实现的方法：
 *      public int size();
 *      public boolean isEmpty();
 *      public boolean contains(Object o);
 *      public Iterator<E> iterator();
 *      public Object[] toArray();
 *      public boolean add(E e);
 *      public boolean remove(Object o);
 *      public void clear();
 *      public E get(int index);
 *      public E set(int index, E element);
 *      public void addFirst(E element);
 *      public void add(int index, E element);
 *      public E remove(int index);
 *      public int indexOf(Object o);
 *      public int lastIndexOf(Object o);
 *      public ListIterator<E> listIterator();
 *      private void isRightIndex(int index);
 *      private Node<E> getNode(int index);
 *
 * 其中定义了一个静态内部类存放节点：
 *      private static class Node<E>
 *
 * @author ETtian
 * @time 2021/11/25
 * @param <E>
 */
public class MyList<E> implements List<E> {

    private Node<E> first = null;//头节点
    private Node<E> current;//当前节点
    private Node<E> last;//尾节点
    private int size = 0;//list长度

    /**
     * 返回集合大小（链表长度）
     * @return int size
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 判断集合size是否为0
     * @return boolean
     */
    @Override
    public boolean isEmpty() {
        if (size == 0)
            return true;
        else
            return false;
    }

    /**
     * 判断集合中是否包含对象o
     * @param o
     * @return
     */
    @Override
    public boolean contains(Object o) {
        if(indexOf(o) != -1)
            return true;
        else
            return false;
    }

    /**
     * 迭代器
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {//直接使用匿名内部类实现Iterator接口
            Node<E> node = first;//访问链头
            @Override
            public boolean hasNext() {
                return node != null;//判断节点是否为空
            }

            @Override
            public E next() {
                E temp = node.element;
                node = node.next;//更新节点
                return temp;
            }

        };
    }

    /**
     * 将集合转换为Object类型数组
     * @return Object[]
     */
    @Override
    public Object[] toArray() {
        Object objects[] = new Object[size];
        Node<E> temp = first;//用链形式获得下一个，比通过索引拿更节省时间
        for (int i = 0; i < size; i++) {//遍历且存入Object数组
            objects[i] = temp.element;
            temp = temp.next;//更新节点
        }
        return objects;
    }


    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    /**
     * 尾部添加元素
     * @param e
     * @return
     */
    @Override
    public boolean add(E e) {

        if (first == null) {//如果头节点为空，则直接链接到头上
            Node<E> newNode = new Node<>(null, e, null);
            first = newNode;
            last = newNode;//更新尾节点
        }else {//将新节点链接到原尾节点
            Node<E> newNode = new Node<>(last, e, null);//新节点-->旧节点
            last.next = newNode;//旧节点-->新节点 自此实现双向链接
            last = newNode;//更新尾节点
        }
        size++;
        return true;
    }

    /**
     * 删除集合中的元素o，如果存在多个只删除第一个
     * @param o 要删除的元素
     * @return 存在该元素并删除返回true , 若没有该元素，返回false
     */
    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index == -1) //如果没有该元素返回false
            return false;
        remove(index);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    /**
     * 清空集合所有的元素
     */
    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * 得到索引处的元素
     * @param index 索引
     * @return 元素
     */
    @Override
    public E get(int index) {

        return getNode(index).element;
    }

    /**
     * 重设索引处的元素
     * @param index 索引
     * @param element 新元素
     * @return 旧元素
     */
    @Override
    public E set(int index, E element) {
        Node<E> Node = getNode(index);//得到旧元素节点
        E temp = element;
        Node.element =  temp;//重设该节点元素
        return temp;
    }

    /**
     * 添加到头部
     * @param element 元素
     */
    public void addFirst(E element){
        if (first != null) {//如果链头不为空，只需把原头节点链在要添加节点的后面（双向）
            Node<E> newNode = new Node(null, element, first);//新头-->原头
            first.prevent = newNode;//原头-->新头 ，至此达成双向
            first = newNode;//更新头节点
        }else {//如果头节点为空
            Node<E> newNode = new Node(null, element, null);
            first = newNode;
            last = newNode;
        }
        size++;
    }

    /**
     * 指定索引添加元素
     * @param index
     * @param element
     */
    @Override
    public void add(int index, E element) {
        if (index == size) {//如果索引是添加到最末尾，直接添加
            add(element);
        }else if (index == 0) {//如果索引是0，添加到头部
            addFirst(element);
        }else {
            isRightIndex(index);//判断index是否合法
            Node<E> node = getNode(index);//找到索引位置节点
            Node<E> newNode = new Node<>(node.prevent,element,node);//为新节点链接前后
            //完成双向链接
            node.prevent.next = newNode;//旧前一节点-->新节点
        }

    }

    /**
     * 清除指定索引处的元素
     * @param index 要清除元素的索引
     * @return 删除元素
     */
    @Override
    public E remove(int index) {
        isRightIndex(index);//先检查索引是否合法
        Node<E> node = getNode(index);//获得索引处节点
        if (index == 0){//index=0不存在前一节点,且需要更新first头节点
            node.next.prevent = node.prevent;//后一节点-->前一节点(null)
            first = node.next;//更新头节点
        }else if (index == size-1) {//index=size-1,则不存在后一节点，且更新尾节点
            node.prevent.next = node.next;//前一节点-->后一节点(null)
            last = node.prevent;
        }else {
            node.prevent.next = node.next;//前一节点-->后一节点
            node.next.prevent = node.prevent;//后一节点-->前一节点
        }
        size--;
        return node.element;
    }

    /**
     * 获得指定对象的索引位置，存在多个返回第一个，不存在时返回-1
     * @param o 查找的对象
     * @return index或-1
     */
    @Override
    public int indexOf(Object o) {
        int index = 0;//记录索引
        if (o == null){//考虑如果是空对象
            for (Node<E> node = first;node.next != null;node = node.next){//从头开始查
                if (node == null) {
                    return index;
                }
                index ++;
            }
        }else {
            for (Node<E> node = first;node.next != null;node = node.next){//从头开始查
                if (o.equals(node.element)) {
                    return index;
                }
                index ++;
            }
        }
        return -1;
    }

    /**
     * 获得指定对象的索引位置，存在多个返回最后一个，不存在时返回-1
     * @param o 查找的对象
     * @return index或-1
     */
    @Override
    public int lastIndexOf(Object o) {
        int index = size - 1;//记录索引
        if (o == null){//考虑如果是空对象
            for (Node<E> node = last;node.prevent != null;node = node.prevent){//从尾开始查
                if (node == null) {
                    return index;
                }
                index--;
            }
        }else {
            for (Node<E> node = last;node.prevent != null;node = node.prevent){//从尾开始查
                if (o.equals(node.element)) {
                    return index;
                }
                index--;
            }
        }
        return -1;
    }

    /**
     * 双向迭代器
     * @return
     */
    @Override
    public ListIterator<E> listIterator() {
        return new ListIterator<E>() {//同样采用匿名内部类
            //Node<E> node = first;//该迭代器默认从头开始
            Node<E> nextNode = first;//下一个节点
            Node<E> lastNode = first;//上一个节点
            int index = 0;//当前所处索引
            @Override
            public boolean hasNext() {
                if (nextNode == null)
                    return false;
                else
                    return true;
            }

            @Override
            public E next() {
                lastNode = nextNode;//更新上一节点
                nextNode = nextNode.next;
                index++;
                return lastNode.element;//更新后的上一节点即本次要的元素
            }

            @Override
            public boolean hasPrevious() {
                if (lastNode == null)
                    return false;
                else
                    return true;
            }

            @Override
            public E previous() {
                nextNode = lastNode;//更新下一节点
                lastNode = lastNode.prevent;
                index--;
                return nextNode.element;//更新后的下一节点即所需要的元素
            }

            @Override
            public int nextIndex() {
                return index;
            }

            @Override
            public int previousIndex() {
                return index - 1;
            }

            @Override
            public void remove() {

            }

            @Override
            public void set(E e) {

            }

            @Override
            public void add(E e) {

            }
        };
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    /**
     * 静态的内部类，定义节点
     * @param <E>
     */
    private static class Node<E>{
        E element;//元素
        Node<E> next;//下一个节点
        Node<E> prevent;//前一个节点

        Node(Node<E> prevent, E element, Node<E> next){
            this.prevent = prevent;
            this.element = element;
            this.next = next;
        }
    }

    /**
     * 判断索引是否合法，非法索引将抛出异常
     * @param index 传入的索引
     */
    private void isRightIndex(int index){
        if (index < 0 || index >= size)//索引不正确抛出异常
            throw new RuntimeException("索引越界! index:"+ index + "非法");
    }

    /**
     * 返回index处的节点
     * @param index 索引
     * @return Node index处的节点
     */
    private Node<E> getNode(int index){
        Node<E> temp;
        if (index < size/2) {//前后对半去搜索确定
            temp = first;//从头搜确定
            for (int i = 0; i < index; i++)
                temp = temp.next;

        }else {
            temp = last;//从尾搜确定
            for (int i = size-1; i > index; i--)
                temp = temp.prevent;
        }
        return temp;
    }

}
