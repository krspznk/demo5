package com.example.demo5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Zadacha {

    public static int k_zadacha;
    public static int n_zadacha;
    public static int [][] value;
    public static int [] zapas;
    public static int[] potreby;

    public static String[] answer_zadacha;

    public static void setK_zadacha(int a){
        k_zadacha=a;
    }
    public static void setN_zadacha(int a){
        n_zadacha=a;
    }

    public static void set_zapas(int [] a){
        zapas=a;
    }
    public static void set_potreby(int [] a){
        potreby=a;
    }

    public static void set_value(int[] [] a){
        value=a;
    }

    public static void set_answer(String[] a){
        answer_zadacha=final_step(value, potreby, zapas);
    }

    public static int sum(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }

    //Метод для зрізу масиву
    public static int[] zriz(int[] array, int n) {
        int[] array_new = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            array_new[i] = array[i];
        }
        return array_new;
    }


    //Метод для пошуку індексу мінімального елементу масиву, який !=-1
    public static int minindex(int[] array) {
        int ind = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != -1) {
                ind = i;
                break;
            }
        }
        if (ind == -1) {
            return ind;
        }
        int min = array[ind];
        for (int i = 0; i < array.length; i++) {
            int n = array[i];
            if ((n < min) && (n != -1)) {
                min = n;
                ind = i;
            }
        }
        return ind;
    }

    //Метод для підрахунку суми матриці(для визначення загальної вартості перевезень)
    public static int sumarr(int[][] value, int[][] array2) {
        int sum = 0;
        for (int i = 0; i < value.length; i++) {
            for (int j = 0; j < value[i].length; j++) {
                if ((array2[i][j] != 0) && (array2[i][j] != -1)) {
                    sum += value[i][j] * array2[i][j];
                }
            }
        }
        return sum;
    }


    //Метод для транспонування матриці
    public static int[][] trans(int[][] array) {
        int[][] array_new = new int[array[0].length][array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array_new[j][i] = array[i][j];
            }
        }
        return array_new;
    }

    //Якщо задача відкритого типу, то ми додаємо колонку в матрицю вартості та додаємо різницю потреб та запасів в потреби
    public static Help<int[][], int[]> vidkryta(int num, int[] zapas, int[][] value, int[] potreby) {
        int[] array = new int[zapas.length];
        value = Arrays.copyOf(value, value.length + 1);
        value[value.length - 1] = array;
        potreby = Arrays.copyOf(potreby, potreby.length + 1);
        potreby[potreby.length - 1] = num;
        return new Help(value, potreby);

    }

    //За допомогою методу мінімальної вартості розставляємо значення перевезень відповідно до матриці вартостей
    public static int[][] iteration(int[][] value, int[] potreby, int[] zapas) {

        int[][] value_copy = new int[value.length][value[0].length];
        for (int i = 0; i < value.length; i++) {
            for (int j = 0; j < value[i].length; j++) {
                value_copy[i][j] = value[i][j];
            }
        }
        int[][] vartist = new int[value_copy.length][value_copy[0].length];
        if (sum(value_copy[value_copy.length - 1]) == 0) {
            while (sum(zriz(potreby, potreby.length-2)) != 0) {
                for (int i = 0; i < value_copy.length - 1; i++) {
                    if (potreby[i] != 0) {
                        int ind = minindex(value_copy[i]);
                        if (potreby[i] < zapas[ind]) {
                            zapas[ind] -= potreby[i];
                            vartist[i][ind] = potreby[i];
                            potreby[i] = 0;
                        } else {
                            vartist[i][ind] = zapas[ind];
                            potreby[i] -= zapas[ind];
                            zapas[ind] = 0;
                        }
                        value_copy[i][ind] = -1;
                    }
                }
            }
            while (sum(zapas) != 0) {
                for (int i = 0; i < zapas.length; i++) {
                    if (zapas[i] != 0) {
                        vartist[vartist.length - 1][i] = zapas[i];
                        potreby[potreby.length - 1] -= zapas[i];
                        zapas[i] = 0;
                    }
                }

            }

        } else {
            while (sum(zapas) != 0) {
                for (int i = 0; i < value_copy.length; i++) {
                    if (potreby[i] != 0) {
                        int ind = minindex(value_copy[i]);
                        if (potreby[i] < zapas[ind]) {
                            zapas[ind] -= potreby[i];
                            vartist[i][ind] = potreby[i];
                            potreby[i] = 0;
                        } else {
                            vartist[i][ind] = zapas[ind];
                            potreby[i] -= zapas[ind];
                            zapas[ind] = 0;
                        }
                        value_copy[i][ind] = -1;
                    }
                }
            }
        }
        return vartist;
    }

    //Перевірка матриці значень перевезень на виродженість
    public static boolean vyrodzhenist(int[][] value, int[] potreby, int[] zapas) {
        int n = 0;
        for (int i = 0; i < value.length; i++) {
            for (int j = 0; j < value[i].length; j++) {
                if (value[i][j] != 0) {
                    n++;
                }
            }
        }
        if (potreby.length + zapas.length - 1 == n) {
            return true;
        } else {
            return false;
        }
    }

    //Якщо матриця вироджена, то додаємо ‘зайняту’ клітинку і позначеємо як -1
    public static int[][] vyrodarray(int[][] value, int[][] vartist, int[] potreby, int[] zapas) {
        while (!vyrodzhenist(vartist, potreby, zapas)) {
            int min = value[0][0];
            int k = 0;
            int n = 0;
            for (int i = 0; i < value.length; i++) {
                for (int j = 0; j < value[i].length; j++) {
                    if ((vartist[i][j] == 0) && (value[i][j] < min) && (vartist[i][j] != -1) && (value[i][j] != 0)) {
                        min = vartist[i][j];
                        k = i;
                        n = j;
                    }
                }
            }
            vartist[k][n] = -1;
        }
        return vartist;
    }

    //Вираховуэмо значення u, v для
    public static Help<int[], int[]> find_u_v(int[][] value, int[][] vartist, int[] zapas, int[] potreby) {
        int[] u = new int[zapas.length];
        int[] v = new int[potreby.length];
        List<Integer> uperevirka = new ArrayList<>();
        List<Integer> vperevirka = new ArrayList<>();
        uperevirka.add(0);
        int flag = potreby.length + zapas.length - 1;
        int[][] value_t = trans(value);
        if (!vyrodzhenist(vartist, potreby, zapas)) {
            vartist = vyrodarray(value, vartist, potreby, zapas);
        }
        int[][] vartist_t = trans(vartist);
        for (int i = 0; i < value_t[0].length; i++) {
            int g= vartist_t[0][i];
            if ((vartist_t[0][i] != 0 | vartist_t[0][i] == -1) && !vperevirka.contains(i)) {
                v[i] = value_t[0][i];
                vperevirka.add(i);
                flag -= 1;
            }
        }
        int i = 1;
        while (flag != 0) {
            for (int j= 0; j<vperevirka.size(); j++) {
                int n= vartist_t[i][vperevirka.get(j)];
                int k =vperevirka.get(j);
                int c=value_t[i][vperevirka.get(j)];
                int b=v[vperevirka.get(j)];
                if ((vartist_t[i][vperevirka.get(j)] != 0 | vartist_t[i][vperevirka.get(j)] == -1) && !uperevirka.contains(i)) {
                    u[i] = c - b;
                    flag -= 1;
                    uperevirka.add(i);
                }
            }
            for (int j = 0; j < value_t[i].length; j++) {
                if ((vartist_t[i][j] != 0 | vartist_t[i][j] == -1) && !vperevirka.contains(j)) {
                    v[j] = value_t[i][j] - u[i];
                    vperevirka.add(j);
                    flag -= 1;
                }
            }
            i++;
        }
        return new Help(u, v);
    }

    //Перевіряємо оптимальність плану методом потенціалів, якщо план не оптимальний, переходимо до функції pattern
    public static Help<int[][], Integer> pererahunok(int[][] value, int[][] vartist, int[] u, int[] v) {
        int[][] value_t = trans(value);
        int[][] vartist_t = trans(vartist);
        int flag = 0;
        for (int i = 0; i < value_t.length; i++) {
            for (int j = 0; j < value_t[i].length; j++) {
                if (vartist_t[i][j] == 0) {
                    vartist_t[i][j] = (u[i] + v[j]) - value_t[i][j];
                    if (vartist_t[i][j] > 0) {
                        flag = vartist_t[i][j];
                    }
                } else if (vartist_t[i][j] == -1) {
                    vartist_t[i][j] = 0;
                }
            }
        }
        return new Help(trans(vartist_t), flag);
    }

    //За допомогою перетворень шукаємо оптимальний план(для кожного нового плану виконуємо методи find_u_v та potentsial)
    public static int[][] pattern(int[][] value, int[][] vartist, int[][] vartist_main, int[] zapas, int[] potreby, int flag) {
        int[][] array2t = trans(vartist);
        ArrayList<Integer> no = new ArrayList<>();
        ArrayList<Integer> no_t = new ArrayList<>();
        int k = 0;
        int n = 0;
        int a1 = 0;
        int a2 = 0;
        while (flag != 0) {
            for (int i = 0; i < array2t.length; i++) {
                for (int j = 0; j < array2t[i].length; j++) {
                    if (array2t[i][j] == flag) {
                        k = i;
                        n = j;
                    }
                }
            }
            for (int i = 0; i < vartist[n].length; i++) {
                if ((vartist[n][i] > 0) && (vartist[n][i] != flag) && (!no.contains(vartist[n][i]))) {
                    a1 = i;
                    break;
                }
            }
            for (int i = 0; i < array2t[k].length; i++) {
                if ((array2t[k][i] > 0) && (array2t[k][i] != flag) && (!no_t.contains(array2t[k][i]))) {
                    a2 = i;
                    break;
                }
            }
            while (vartist_main[a2][a1] <= 0) {
                no.add(vartist_main[n][a1]);
                no_t.add(vartist_main[a2][k]);
                for (int i = 0; i < vartist_main[n].length; i++) {
                    if ((vartist_main[n][i] > 0) && (!no.contains(vartist_main[n][i]))) {
                        a1 = i;
                        break;
                    }
                }
                if (vartist_main[a1][a2] > 0) {
                    break;
                }
                for (int i = 0; i < vartist_main.length; i++) {
                    if ((vartist_main[i][k] > 0) && (!no_t.contains(vartist_main[i][k]))) {
                        a2 = i;
                        break;
                    }
                }
            }
            int num = Math.min(vartist_main[a2][k], vartist_main[n][a1]);
            vartist_main[n][k] = num;
            vartist_main[n][a1] -= num;
            vartist_main[a2][k] -= num;
            vartist_main[a2][a1] += num;
            Help<int[], int[]> u_v = find_u_v(value, vartist_main, zapas, potreby);
            int[] u = u_v.get1();
            int[] v = u_v.get2();
            Help<int[][], Integer> nedeed = pererahunok(value, vartist, u, v);
            vartist = nedeed.get1();
            flag = nedeed.get2();
        }
        return vartist_main;
    }

    //Метод для виводу фінальних значень
    public static void show(int[][] array1, int[][] array2, int n) {
        System.out.println("Vartist perevezen: " + sumarr(array1, array2));
        System.out.println();
        System.out.println("Perevezenya:");
        for (int i = 0; i < n; i++){
            System.out.print(("   B" + (i + 1)+"  "));
        } System.out.println();

        array2 = trans(array2);
        for (int i = 0; i < array2.length; i++) {
            System.out.print(("A" + (i + 1))+ " ");
            for (int j = 0; j < n; j++){
                if ((array2[i][j] == 0) || (array2[i][j] == -1)) {
                    System.out.print(String.valueOf(0) +"      ");
                } else {
                    System.out.print(String.valueOf(array2[i][j]) +"     ");
                }
            }
            System.out.println();
        }
    }


    public static String[] generateOutput(int[][] array1, int[][] array2, int n) {
        StringBuilder sb = new StringBuilder();
        sb.append("Vartist perevezen: ").append(sumarr(array1, array2)).append("\n\n");
        sb.append("Perevezenya:\n");

        // Додамо відступи для міток стовпців
        sb.append(String.format("%1$5s", ""));
        for (int i = 0; i < n; i++) {
            sb.append(String.format("%1$8s", "B" + (i + 1)));
        }
        sb.append("\n");

        array2 = trans(array2);

        // Додамо відступи для міток рядків та значень в таблиці
        String[] output = new String[array2.length + 1];
        for (int i = 0; i < array2.length; i++) {
            sb.append(String.format("%1$-4s", "A" + (i + 1)));
            output[i + 1] = String.format("%1$-4s", "A" + (i + 1));
            for (int j = 0; j < n; j++) {
                if ((array2[i][j] == 0) || (array2[i][j] == -1)) {
                    sb.append(String.format("%1$8s", "0"));
                    output[i + 1] += String.format("%1$8s", "0");
                } else {
                    sb.append(String.format("%1$8d", array2[i][j]));
                    output[i + 1] += String.format("%1$8d", array2[i][j]);
                }
            }
            sb.append("\n");
            output[i + 1] += "\n";
        }

        output[0] = sb.toString();
        return output;
    }

    //Стручктуруючий метод
    public static String[] final_step(int[][] value, int[] potreby, int[] zapas)
    {
        int n = potreby.length;
        if (sum(potreby) != sum(zapas)) {
            Help<int[][], int[]> perevirka = vidkryta(sum(zapas) - sum(potreby), zapas, value, potreby);;
            value = perevirka.get1();
            potreby = perevirka.get2();
        }
        int [][] vartist = iteration(value, potreby, zapas);
        Help<int[], int[]> u_v = find_u_v(value, vartist, zapas, potreby);
        int[] u = u_v.get1();
        int[] v = u_v.get2();
        Help<int[][], Integer> nedeed = pererahunok(value, vartist, u, v);
        int [][] vartist_main = nedeed.get1();
        int flag = nedeed.get2();
        String[] output = new String[vartist.length + 1];
        if (flag != 0){
            vartist = pattern(value, vartist_main, vartist, zapas, potreby, flag);
        }
        output=generateOutput(value, vartist, n);
        return output;
    }



}
