package com.example.ordermeal.utils;

import android.text.format.DateFormat;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

public class FunctionHelper {

    // 将整数价格格式化为带千位分隔符的货币形式
    public static String priceFormat(double price) {
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        return "RMB " + formatter.format(price);
    }

    // 获取今天的日期，格式化为 "yyyy-MM-d HH:mm" 例如：2024-11-29 12:34）
    public static String getTodayTime() {
        Date date = Calendar.getInstance().getTime();
        return (String) DateFormat.format("yyyy-MM-d HH:mm", date);
    }

}