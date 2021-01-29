package com.umesh.patidar.interestcalculator.date;

public class DateDifference {

    private CustomDate d1, d2;
    private final int[] values = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public DateDifference(String d1, String d2) {
        this.d1 = new CustomDate(d1);
        this.d2 = new CustomDate(d2);
        if (this.d1.isAfter(this.d2)) {
            //swap because d1 is after d2
            swapDate();
        }

        includeFirstDay();
    }

    private void includeFirstDay() {
        int day,month,year;
        day = this.d1.getDay();
        month = this.d1.getMonth();
        year = this.d1.getYear();

        if(day!=1){
            day = day-1;
        }else{
            if(d1.getMonth()!=1){
                month = month-1;
                day = values[month-1];
            }else{
              year = year-1;
              month = 12;
              day = 31;
            }
        }
        this.d1 = new CustomDate(day,month,year);
    }


    public CustomDate getDifference() {
        int day, month, year;
        //d1 = 15/05/2020
        //d2 = 10/06/2020
        if (d1.getDay() > d2.getDay()) {
            day = getDays(d2) - d1.getDay() + d2.getDay();


            if (d1.getMonth() > d2.getMonth()) {
                month = 12 - d1.getMonth() + d2.getMonth() - 1;
                year = d2.getYear() - d1.getYear() - 1;
            }
            else if(d1.getMonth() == d2.getMonth()){
                month = 11;
                year = d2.getYear() - d1.getYear() - 1;
            }

            else {
                month = d2.getMonth() - d1.getMonth() -1;
                year = d2.getYear() - d1.getYear() ;
            }
        } else {
            day = d2.getDay() - d1.getDay();

            if (d1.getMonth() > d2.getMonth()) {
                month = 12 - d1.getMonth() + d2.getMonth();
                year = d2.getYear() - d1.getYear() - 1;
            } else {
                month = d2.getMonth() - d1.getMonth();
                year = d2.getYear() - d1.getYear();
            }
        }

        return new CustomDate(day,month,year);
    }

//    private CustomDate includeLastDay(int day, int month, int year) {
//
//        int days = getDays(d1);
//        day+=1;
//        if (day >= days) {
//            day = days - day;
//            month += 1;
//
//            if (month > 12) {
//                month = 0;
//                year += 1;
//            }
//        }
//        return new CustomDate(day, month, year);
//    }

    private int getDays(CustomDate date) {
        if ((date.getMonth()-1) == 2) {
            return checkLeapYear(date.getYear()) ? 29 : 28;
        }
        return values[date.getMonth()-2];
    }

    private boolean checkLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    private void swapDate() {
        CustomDate date = this.d1;
        this.d1 = this.d2;
        this.d2 = date;
    }

}
