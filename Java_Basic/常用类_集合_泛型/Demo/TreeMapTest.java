package com.atguigu.java;

import org.junit.Test;

import java.util.*;

public class TreeMapTest {

    @Test
    public void test1(){
        TreeMap map=new TreeMap();
        User u1=new User("Tom",12);
        User u2=new User("Jerry",32);
        User u3=new User("Jack",20);
        User u4=new User("Rose",18);

        map.put(u1,98);
        map.put(u2,89);
        map.put(u3,72);
        map.put(u4,100);

        //按顺序遍历
        //自然排序
        //按照姓名从大到小来排序
        Set entryset = map.entrySet();
        Iterator iterator1 = entryset.iterator();
        while (iterator1.hasNext()) {
            Object obj = iterator1.next();
            //entryset集合中的元素都是entry
            Map.Entry entry=(Map.Entry)obj;
            System.out.println(entry.getKey()+"----"+entry.getValue());
        }
    }

    //定制排序
    @Test
    public void test2(){
        //定制排序一般用匿名内部类实现
        //按照年龄排序
        TreeMap map=new TreeMap(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof User&& o2 instanceof User){
                    User u1=(User)o1;
                    User u2=(User)o2;
                    return Integer.compare(u1.getAge(),u2.getAge());
                }
                throw new RuntimeException("类型不匹配");
            }
        });
        User u1=new User("Tom",12);
        User u2=new User("Jerry",32);
        User u3=new User("Jack",20);
        User u4=new User("Rose",18);

        map.put(u1,98);
        map.put(u2,89);
        map.put(u3,72);
        map.put(u4,100);

        Set entryset = map.entrySet();
        Iterator iterator1 = entryset.iterator();
        while (iterator1.hasNext()) {
            Object obj = iterator1.next();
            //entryset集合中的元素都是entry
            Map.Entry entry=(Map.Entry)obj;
            System.out.println(entry.getKey()+"----"+entry.getValue());
        }



    }

}
