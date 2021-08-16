package com.atguigu.java;

import java.util.ArrayList;
import java.util.List;

public class SubOrder extends Order<Integer>{

    public <E> List<E> copyFromArrayToList(E[] arr){
        ArrayList<E> list=new ArrayList<>();

        for(E e:arr){
            list.add(e);
        }
        return list;

    }

}
