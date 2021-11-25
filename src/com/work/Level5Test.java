package com.work;

import java.util.Iterator;
import java.util.ListIterator;

public class Level5Test {
    public static void main(String[] args) {
        MyList<Double> myList = new MyList();
        myList.add(10.0);
        myList.add(45.0);
        myList.addFirst(75.1);
        myList.add(0,144.2);
        myList.addFirst(5222.2);

        /*for(int x: myList){
            System.out.println(x);
        }*/

        Iterator myIt = myList.iterator();//获取迭代器
        while (myIt.hasNext()){
            System.out.println(myIt.next());
        }
        System.out.println("---------");
        //双向迭代
        ListIterator myIt2 = myList.listIterator();
        while (myIt2.hasNext()){
            System.out.println(myIt2.next());
        }
        System.out.println("------反向迭代-----");
        while (myIt2.hasPrevious()){
            System.out.println(myIt2.previous());
        }

        System.out.println("-------删除：" + myList.remove(4));
        for (double x: myList){
            System.out.println(x);
        }
        System.out.println("----------重设0号元素,为999.0");
        myList.set(0,999.0);
        for (double x: myList){
            System.out.println(x);
        }
        System.out.println("得到3号位元素：" + myList.get(4));

        myList.add(10.0);//添加一个10

        System.out.println("查看第一个10.0是几号位元素" + myList.indexOf(10.0));
        System.out.println("查看最一个10.0是几号位元素" + myList.lastIndexOf(10.0));
        System.out.println("查看第一个4554.4是几号位元素(实际没有存该元素)" + myList.indexOf(4554));
        System.out.println("查看最后一个4554.4是几号位元素(实际没有存该元素)" + myList.lastIndexOf(4554));

        myList.remove(999.0);//删除
        System.out.println("-----删除999.0后再次查看所有元素");
        for (double x: myList){
            System.out.println(x);
        }
        System.out.println("此时集合大小:"+ myList.size());
        System.out.println("集合中是否含有999.0 : "+ myList.contains(999.0));
        System.out.println("集合中是否含有75.1 : "+ myList.contains(75.1));

        System.out.println("------------------");
        Object test[] = myList.toArray();//将集合转换为数组对象
        for (int i = 0; i < test.length; i++) {
            System.out.println(test[i]);
        }
        System.out.println("---------------------");
        System.out.println("清空集合");
        myList.clear();
        System.out.println("集合是否为空：" + myList.isEmpty());
        System.out.println("此时集合大小：" + myList.size());
        System.out.println("尝试用迭代器遍历一下...");
        ListIterator newIterator = myList.listIterator();
        while (newIterator.hasNext()){
            System.out.println(newIterator.next());
        }
        System.out.println("END...所有功能测试完毕");
    }
}
