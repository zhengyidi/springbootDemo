package com.transcend.demo;

import java.util.*;

public class CountNum {

    public static void main(String[] args) {
        String str = "q2ewqeqe134eqwewqwrqeqwe2eqwewqeweqwqqqqs";
        char[] chars = str.toCharArray();
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < chars.length; i++) {
            if (null != map.get(chars[i]))
                map.put(chars[i], map.get(chars[i]) + 1);
            else
                map.put(chars[i], 1);
        }

        Map<Character, Integer> finalMap = new LinkedHashMap<>();

        map.entrySet().stream()
                .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed()).limit(1)
                .forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));
        System.out.println(finalMap);

//        String a = new String("ab43a2c43d");
//        System.out.println(a.replace("43aa","opp"));
//        System.out.println(a.replace("3ac","fty"));
//        System.out.println(a.replaceAll("\\d","f"));
//
//
//        Iterator<Map.Entry<Character, Integer>> iterator = map.entrySet().iterator();
//        while (iterator.hasNext()){
//            Map.Entry<Character, Integer> next = iterator.next();
//            System.out.println("Key:"+next.getKey()+",Value:"+next.getValue());
//        }

    }

}
