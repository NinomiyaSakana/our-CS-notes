package com.atguigu.java;

import org.junit.Test;

import java.util.*;

/**
 * set的无序性，不可重复性
 */

public class SetTest {


    @Test
    public void test1() {
        //跟添加顺序无关
        //Set set = new HashSet();

        //与添加顺序有关
        Set set = new LinkedHashSet();
        //这两个就重复
        set.add(456);
        //set.add(456);
        set.add(123);
        set.add("AA");
        //如果没在user中重写equals的话那么这两个不重复
        //重写的话则重复
        set.add(new User("Tom", 12));
        set.add(new User("Tom", 12));
        set.add("CC");
        set.add(129);

        Iterator iterator = set.iterator();
        //执行后发现跟添加的顺序有区别
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


    }


    @Test
    public void test2() {

        Set set = new TreeSet();
        //不能添加不同类的对象
//        set.add(456);
//        set.add(123);
//        set.add("AA");
//        set.add(new User("Tom",12));


//        set.add(456);
//        set.add(-34);
//        set.add(43);
//        set.add(11);
//        set.add(8);

        set.add(new User("Tom", 12));
        set.add(new User("Jerry", 4));
        set.add(new User("Mike", 10));
        set.add(new User("Daniel", 20));
        set.add(new User("Daniel", 56));


        Iterator iterator = set.iterator();
        //执行后发现跟添加的顺序有区别
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }

    @Test
    public void test3() {
        Comparator com = new Comparator() {
            //按照年龄从小到大排列,年龄一样就不要了
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof User && o2 instanceof User) {
                    User u1 = (User) o1;
                    User u2 = (User) o2;

                    return Integer.compare(u1.getAge(), u2.getAge());
                } else {
                    throw new RuntimeException("数据类型不匹配");
                }
            }
        };

        //不写com就是自然排序，写的话就是定制排序
        TreeSet set = new TreeSet(com);
        set.add(new User("Tom", 12));
        set.add(new User("Jerry", 4));
        set.add(new User("Mike", 10));
        set.add(new User("Daniel", 20));
        set.add(new User("Daniel", 56));

        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}
