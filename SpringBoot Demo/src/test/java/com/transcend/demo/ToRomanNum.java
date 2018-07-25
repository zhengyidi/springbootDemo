package com.transcend.demo;

import java.util.Scanner;

public class ToRomanNum {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入数字:");
        int n = 0;
        try {
            n = Integer.parseInt(sc.next());
        } catch (NumberFormatException e){
            System.out.println("请输入正确的Integer值！");
            main(args);
        }
//        n = sc.nextInt();
        String roman = toRoman(n);
        System.out.println(roman);
        sc.close();
    }

    private static String toRoman(int n) {
        int[] aArray = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] rArray = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
                      //   1000  900   500  400   100  90    50  40    10    9     5    4     1
        StringBuilder sb = new StringBuilder();

        if (n < 0) {
            return "-1: 罗马数字不存在负值";
        } /*else if (n <= 3999) {
            for (int i = 0; i < aArray.length; i++) {
                while (n >= aArray[i]) {
                    sb.append(rArray[i]);
                    n -= aArray[i];
                }
            }
        } else {  //大于3999  应在数字上加- 意为1000倍
            int t = n / 1000;
            int q = n - t * 1000;
            for (int i = 0; i < aArray.length; i++) {
                while (t >= aArray[i]) {
                    sb.append("-").append(rArray[i]);
                    t -= aArray[i];
                }
            }
            for (int i = 0; i < aArray.length; i++) {
                while (q >= aArray[i]) {
                    sb.append(rArray[i]);
                    q -= aArray[i];
                }
            }
        }*/



        return sb.toString();
    }

}
