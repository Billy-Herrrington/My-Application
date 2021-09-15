package com.company;

public class Main {
    public static void main(String[] args) {
        //Операция О1 и О2 "*"
        //константа С = 2
        //Тип индексов Long
        //Заликова книжка 9320
        int m = 15, n = 10, a = 6, b = 2;
        long i, j;
        int s = 0;

        for (i=a; i<=n; i++) {
            for (j=b; j<=m; j++) {
                if (i==2) {
                    System.out.println("Виникла помилка в обчисленнях через ділення на нуль.");
                    return;
                } else {
                    s += (i * j) / (i * 2);
                }
            }
        }
        System.out.println("S = " + s);
    }
}
