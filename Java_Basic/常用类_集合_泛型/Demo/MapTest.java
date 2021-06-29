package com.atguigu.java;

import com.sun.javafx.geom.transform.GeneralTransform3D;
import org.junit.Test;

import java.util.*;

public class MapTest {


    @Test
    public void test() {

        Map map = new HashMap();
        map.put("AA", 1234);
        map.put(45, 123);
        map.put("BB", 65);


        //遍历key集，用key来
        //获得key的set
        Set set = map.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("***********");
        //遍历所有的value
        Collection values = map.values();
        for (Object obj : values) {
            System.out.println(obj);
        }

        System.out.println("***********");
        //遍历所有的key-value
        Set entryset = map.entrySet();
        Iterator iterator1 = entryset.iterator();
        while (iterator1.hasNext()) {
            Object obj = iterator1.next();
            //entryset集合中的元素都是entry
            Map.Entry entry=(Map.Entry)obj;
            System.out.println(entry.getKey()+"----"+entry.getValue());
        }

        //方式二
        Set keySet=map.keySet();
        Iterator iterator2=keySet.iterator();
        while(iterator2.hasNext()){
            Object key=iterator2.next();
            Object value=map.get(key);
            System.out.println(key+"========="+value);
        }
    }
}
