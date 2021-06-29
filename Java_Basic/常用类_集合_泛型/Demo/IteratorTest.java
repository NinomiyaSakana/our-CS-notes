package com.atguigu.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 集合元素的遍历操作，使用迭代器iterator接口
 */

public class IteratorTest {

    @Test
    public void test1() {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new String("Tom"));
        coll.add(false);


        Iterator iterator = coll.iterator();
        //方式一：
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
        //报错，没有next了
        //System.out.println(iterator.next());

        //方式二：
//        for(int i=0;i<coll.size();i++){
//            System.out.println(iterator.next());
//        }

        //方式三：推荐使用
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


    }


    @Test
    public void test2() {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new String("Tom"));
        coll.add(false);


        Iterator iterator = coll.iterator();
        while (iterator.hasNext()) {
            //指针下移
            Object obj = iterator.next();
            if ("Tom".equals(obj)) {
                iterator.remove();
            }
        }

        //当前对象的指针已经到最末尾了
        //从头遍历
        //但不是再生成一个对象，只是重新定义（相当于赋值）
        iterator = coll.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


    }


    @Test
    public void test3() {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new String("Tom"));
        coll.add(false);

        //for(集合元素类型 局部变量：集合对象) obj局部变量，名字可以随便写
        //内部仍然调用了迭代器
        for (Object obj : coll) {
            System.out.println(obj);
        }
    }


}
