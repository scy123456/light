package com.example.administrator.summarize.utils;

import android.util.Log;

public class CalculateUtils {

    public static String calculate() {
        StringBuilder answer = new StringBuilder("这个整数可以是");
        boolean flag = true;
        for (int i = 11; flag; i++) {
            for (int j = 10; j < i; j++) {
                if ((i * i - 168) == j * j) {
                    answer.append("  ");
                    answer.append((j * j - 100) + "  ");
                    Log.e("scy", "初始数值为" + (j * j - 100) + "  i == " + i + "    j == " + j);
                }
            }
            if ((i * i - (i - 1) * (i - 1)) > 168) {
                flag = false;
                Log.e("scy","循环结束");
            }
        }
        return answer.toString();
    }
}
