package com.umesh.patidar.interestcalculator.date;

public class CustomDate {
    private int day, month, year;


    public CustomDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public CustomDate(String date) {
        parseDate(date);
    }

    public boolean isAfter(CustomDate d) {
        if (this.year < d.year) {
            return false;
        } else if (this.year == d.year) {
            if (this.month < d.month) {
                return false;
            } else if (this.month == d.month) {

                return this.day >= d.day;
            }
            return true;
        }
        return true;
    }

    private void parseDate(String date) {
        String[] dates = date.split("/");
        this.day = Integer.parseInt(dates[0]);
        this.month = Integer.parseInt(dates[1]);
        this.year = Integer.parseInt(dates[2]);
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }


    @Override
    public String toString() {
        return "CustomDate{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }
}
