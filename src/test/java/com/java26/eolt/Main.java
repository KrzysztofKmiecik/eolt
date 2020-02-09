package com.java26.eolt;

public class Main {
    public static void main(String[] args) {
        String str=new String("28112222");
        //     str=new String("F12345678928223333");
        if(str.startsWith("F")){
            str=str.substring(10,18);
        }else{
            str=str.substring(0,8);
        }
        System.out.println(str);

    }
}
