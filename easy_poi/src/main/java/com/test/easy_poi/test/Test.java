package com.test.easy_poi.test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @className Test
 * @description
 * @author fengjun
 * @date 2022年01月26日 下午4:08
 */
public class Test
{

    public static void main(String[] args) {

        List<Integer> list1 = Arrays.asList(1, 2, 3, 4);
        List<Integer> list2 = Arrays.asList( 3, 4,5,6);

        List<List<Integer>> list = new ArrayList<>();
        list.add(list1);
        list.add(list2);

        Set<Integer> result = list.stream().flatMap(Collection::stream
        ).collect(Collectors.toSet());

        System.out.println(result);
    }

}
