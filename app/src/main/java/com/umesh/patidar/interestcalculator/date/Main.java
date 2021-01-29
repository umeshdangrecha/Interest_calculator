package com.umesh.patidar.interestcalculator.date;

public class Main {
    public static void main(String[] args) {


        String d21 = "20/09/2020";
        String d22 = "10/08/2021";
        //10 m 22d


        String d11 = "20/08/2020";
        String d12 = "10/08/2021";
        // 11 m 22d




        String d31 = "20/07/2020";
        String d32 = "10/08/2021";
        //1 y 0 m 22d


        String d41 = "10/08/2020";
        String d42 = "20/08/2021";
        //1y 0m 11d


        String d51 = "10/09/2020";
        String d52 = "20/08/2021";
        //11m 11d


        String d61 = "10/07/2020";
        String d62 = "20/08/2021";
        //1y 1m 11d

        System.out.println(new DateDifference(d21,d22).getDifference());
        System.out.println(new DateDifference(d11,d12).getDifference());
        System.out.println(new DateDifference(d31,d32).getDifference());
        System.out.println(new DateDifference(d41,d42).getDifference());
        System.out.println(new DateDifference(d51,d52).getDifference());
        System.out.println(new DateDifference(d61,d62).getDifference());

    }
}
