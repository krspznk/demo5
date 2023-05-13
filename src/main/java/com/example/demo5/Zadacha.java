package com.example.demo5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Zadacha {

    public static int k_zadacha;
    public static int n_zadacha;
    public static int [][] vartist;
    public static int [] zapas;
    public static int[] potreby;

    public static void setK_zadacha(int a){
        k_zadacha=a;
    }
    public static void setN_zadacha(int a){
        n_zadacha=a;
    }

    public static void set_zapas(String input){
        String[] values = input.split(",");
        int[] array = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            array[i] = Integer.parseInt(values[i]);
        }
        zapas=array;
    }
    public static void set_potreby(String input){
        String[] values = input.split(",");
        int[]array = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            array[i] = Integer.parseInt(values[i]);
        }
        potreby=array;
    }

    public static void set_vartist(String input){
        String[] rows = input.split("\n"); // Розділяємо текст на рядки за допомогою нового рядка
        int[][] array1 = new int[rows.length][]; // Створюємо двовимірний масив цілих чисел з необхідною кількістю рядків

        // Конвертуємо рядки у масиви цілих чисел
        for (int i = 0; i < rows.length; i++) {
            String[] vartist = rows[i].split(","); // Розділяємо рядок на елементи за допомогою коми
            array1[i] = new int[vartist.length]; // Створюємо рядок масиву з необхідною кількістю елементів

            // Конвертуємо елементи рядка у цілі числа та заповнюємо рядок масиву
            for (int j = 0; j < vartist.length; j++) {
                array1[i][j] = Integer.parseInt(vartist[j]);
            }
        }
        int[][] array_new = new int[Zadacha.k_zadacha][Zadacha.n_zadacha];
        for (int i = 0; i < Zadacha.n_zadacha; i++) {
            for (int j = 0; j < Zadacha.k_zadacha; j++) {
                array_new[j][i] = array1[i][j];
            }
        }
        vartist=array_new;
    }

    public static int[][] trans(int[][] array) {
        int[][] new_array = new int[array[0].length][array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                new_array[j][i] = array[i][j];
            }
        }
        return new_array;
    }

    public static int sum(int[] array) {
        int s = 0;
        for (int i = 0; i < array.length; i++) {
            s += array[i];
        }
        return s;
    }

    public static int sumarr(int[][] value, int[][] vartist) {
        int sumar = 0;
        for (int i = 0; i < value.length; i++) {
            for (int j = 0; j < value[i].length; j++) {
                if (value[i][j] > 0) {
                    sumar += value[i][j] * vartist[i][j];
                }
            }
        }
        return sumar;
    }

    public static int sumarray(int[][] value) {
        int sumarray = 0;
        for (int i = 0; i < value.length; i++) {
            for (int j = 0; j < value[i].length; j++) {
                sumarray += value[i][j];
            }
        }
        return sumarray;
    }


    public static int is_open(int[] potreby, int[] zapas) {
        int num = 0;
        if (sum(potreby)!=sum(zapas)) {
            num = sum(potreby)-sum(zapas);
        }
        return num;
    }

    public static Object[] open_zapas(int num, int[][] vartist, int[] zapass) {
        for (int i = 0; i < vartist.length; i++) {
            int[] row = vartist[i];
            int[] newRow = Arrays.copyOf(row, row.length + 1);
            newRow[newRow.length - 1] = 0;
            vartist[i] = newRow;
        }
        int[] newZapas = Arrays.copyOf(zapass, zapass.length + 1);
        newZapas[newZapas.length - 1] = num;

        return new Object[] {vartist, newZapas};
    }

    public static Object[] open_potreby(int num, int[][] vartist, int[] potrebyy) {
        num = Math.abs(num);
        int[] newRow = new int[zapas.length];
        vartist = Arrays.copyOf(vartist, vartist.length + 1);
        vartist[vartist.length - 1] = newRow;
        potrebyy = Arrays.copyOf(potrebyy, potrebyy.length + 1);
        potrebyy[potrebyy.length - 1] = num;
        return new Object[] {vartist, potrebyy};
    }

    public static int[] min_index(int[][] array) {
        int min = 0;
        int k = 0;
        int n = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] != 0) {
                    min = array[i][j];
                    k = i;
                    n = j;
                    break;
                }
            }
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                int a = array[i][j];
                if ((array[i][j] < min) && (array[i][j] != 0)) {
                    min = array[i][j];
                    k = i;
                    n = j;
                }
            }
        }
        return new int[]{k, n};
    }

    public static int[][] min_vartist(int[][] vartist, int[] potreby, int[] zapas) {
        int[] potreby_change = Arrays.copyOf(potreby, potreby.length);
        int[] zapas_change = Arrays.copyOf(zapas, zapas.length);
        int[][] vartist_change = new int[vartist.length][];
        for (int i = 0; i < vartist.length; i++) {
            vartist_change[i] = Arrays.copyOf(vartist[i], vartist[i].length);
        }
        int[][] value = new int[vartist.length][vartist[0].length];
        while (sum(potreby_change) - potreby_change[potreby_change.length - 1] != 0
                && sum(zapas_change) - zapas_change[zapas_change.length - 1] != 0) {
            int[] index = min_index(vartist_change);
            int i = index[0];
            int j = index[1];
            if (potreby_change[i] > zapas_change[j]) {
                value[i][j] = zapas_change[j];
                potreby_change[i] -= zapas_change[j];
                zapas_change[j] = 0;
                vartist_change[i][j] = 0;
            } else if (potreby_change[i] < zapas_change[j]) {
                value[i][j] = potreby_change[i];
                zapas_change[j] -= potreby_change[i];
                potreby_change[i] = 0;
                vartist_change[i][j] = 0;
            } else {
                value[i][j] = potreby_change[i];
                zapas_change[j] = 0;
                potreby_change[i] = 0;
                vartist_change[i][j] = 0;
            }
        }
        if (sum(potreby_change) - potreby_change[potreby_change.length - 1] == 0
                && sum(zapas_change) != 0) {
            for (int i = 0; i < vartist_change[vartist_change.length - 1].length; i++) {
                if (potreby_change[potreby_change.length - 1] > zapas_change[i]) {
                    value[value.length - 1][i] = zapas_change[i];
                    potreby_change[potreby_change.length - 1] -= zapas_change[i];
                    zapas_change[i] = 0;
                    vartist_change[vartist_change.length - 1][i] = 0;
                } else if (potreby_change[potreby_change.length - 1] < zapas_change[i]) {
                    value[value.length - 1][i] = potreby_change[potreby_change.length - 1];
                    zapas_change[i] -= potreby_change[potreby_change.length - 1];
                    potreby_change[potreby_change.length - 1] = 0;
                    vartist_change[vartist_change.length - 1][i] = 0;
                } else {
                    value[value.length - 1][i] = potreby_change[potreby_change.length - 1];
                    zapas_change[i] = 0;
                    potreby_change[potreby_change.length - 1] = 0;
                    vartist_change[vartist_change.length - 1][i] = 0;
                }
                if (sum(potreby_change) == 0 && sum(zapas_change) == 0) {
                    break;
                }
            }
        } else if (sum(zapas_change) - zapas_change[zapas_change.length - 1] == 0 && sum(potreby_change) != 0) {
            for (int i = 0; i < vartist_change.length; i++) {
                if (potreby_change[i] > zapas_change[zapas_change.length - 1]) {
                    value[i][value[i].length - 1] = zapas_change[zapas_change.length - 1];
                    potreby_change[i] -= zapas_change[zapas_change.length - 1];
                    zapas_change[zapas_change.length - 1] = 0;
                    vartist_change[i][vartist_change[i].length - 1] = 0;
                } else if (potreby_change[i] < zapas_change[zapas_change.length - 1]) {
                    value[i][value[i].length - 1] = potreby_change[i];
                    zapas_change[zapas_change.length - 1] -= potreby_change[i];
                    potreby_change[i] = 0;
                    vartist_change[i][vartist_change[i].length - 1] = 0;
                } else {
                    value[i][value[i].length - 1] = potreby_change[i];
                    zapas_change[zapas_change.length - 1] = 0;
                    potreby_change[i] = 0;
                    vartist_change[i][vartist_change[i].length - 1] = 0;
                }
                if ((sum(potreby_change) == 0) && (sum(zapas_change) == 0)) {
                    break;
                }
            }
        }
        return value;
    }

    public static boolean vyrodzhenist(int[][] value, int[] potreby, int[] zapas) {
        int n = 0;
        for (int i = 0; i < value.length; i++) {
            for (int j = 0; j < value[i].length; j++) {
                if (value[i][j] != 0) {
                    n++;
                }
            }
        }
        return (potreby.length + zapas.length - 1 == n);
    }

    public static int[][] vyrodarray(int[][] value, int[][] vartist, int[] potreby, int[] zapas) {
        while (!vyrodzhenist(value, potreby, zapas)) {
            int min = vartist[0][0];
            int k = 0;
            int n = 0;
            for (int i = 0; i < value.length; i++) {
                for (int j = 0; j < value[i].length; j++) {
                    if (value[i][j] == 0 && vartist[i][j] < min && value[i][j] != -1 && vartist[i][j] != 0) {
                        min = vartist[i][j];
                        k = i;
                        n = j;
                    }
                }
            }
            value[k][n] = -1;
        }
        return value;
    }

    public static int[][] find_u_v(int[][] value, int[][] vartist, int[] zapas, int[] potreby) {
        int[] u = new int[zapas.length];
        int[] v = new int[potreby.length];
        ArrayList<Integer> uperevirka = new ArrayList<Integer>();
        ArrayList<Integer> vperevirka = new ArrayList<Integer>();
        int flag = potreby.length + zapas.length - 1;
        int[][] vartist_t = trans(vartist);
        if (!vyrodzhenist(value, potreby, zapas)) {
            value = vyrodarray(value, vartist, potreby, zapas);
        }
        int[][] value_t = trans(value);
        for (int i = 0; i < value_t[0].length; i++) {
            if ((value_t[0][i] != 0 || value_t[0][i] == -1) && !vperevirka.contains(i)) {
                v[i] = vartist_t[0][i];
                vperevirka.add(i);
                flag--;
            }
        }
        int i = 1;
        while (flag != 0) {
            for (int j : vperevirka) {
                if ((value_t[i][j] != 0 || value_t[i][j] == -1) && !uperevirka.contains(i)) {
                    u[i] = vartist_t[i][j] - v[j];
                    flag--;
                    uperevirka.add(i);
                }
            }
            for (int j = 0; j < value_t[i].length; j++) {
                if ((value_t[i][j] != 0 || value_t[i][j] == -1) && !vperevirka.contains(j)) {
                    v[j] = vartist_t[i][j] - u[i];
                    vperevirka.add(j);
                    flag--;
                }
            }
            i++;
            if (i >= zapas.length) {
                i = 0;
            }
        }

        return new int[][]{u, v};
    }

    public static Object[] potentials(int[][] value, int[][] vartist, int[] u, int[] v) {
        int[][] value_t = trans(value);
        int[][] vartist_t = trans(vartist);
        int[][] potential = new int[vartist_t.length][vartist_t[0].length];
        int flag = 0;
        int k=value_t.length;
        int n =value.length;
        for (int i = 0; i < value_t.length; i++) {
            for (int j = 0; j < value_t[i].length; j++) {
                if (value_t[i][j] == 0) {
                    potential[i][j] = (u[i] + v[j]) - vartist_t[i][j];
                    if (potential[i][j] > 0) {
                        flag = potential[i][j];
                    }
                } else if (value_t[i][j] == -1) {
                    value_t[i][j] = 0;
                }
            }
        }

        return new Object[] {trans(potential), flag};
    }

    public static int[][] pererahunok(int[][] potential, int[][] value, int flag) {
        int col = -1;
        int row = -1;
        for (int i = 0; i < value.length; i++) {
            for (int j = 0; j < value[i].length; j++) {
                if (potential[i][j] > 0) {
                    col = i;
                    row = j;
                }
            }
        }

        List<Integer> rows = new ArrayList<Integer>();
        List<Integer> cols = new ArrayList<Integer>();
        for (int i = 0; i < value[0].length; i++) {
            int a=value[col][i];
            if (value[col][i] > 0) {
                rows.add(i);
            }
        }
        for (int i = 0; i < value.length; i++) {
            int a=value[i][row];
            if (value[i][row] > 0) {
                cols.add(i);
            }
        }

        int c = -1;
        int r = -1;
        for (int i : cols) {
            for (int j : rows) {
                int a= value[i][j];
                if (value[i][j] > 0) {
                    c = i;
                    r = j;
                }
            }
        }

        if (c == -1) {
            int[][] array = new int[value.length][value[0].length];
            return array;
        }

        int n = Math.min(value[c][row], value[col][r]);
        value[c][row] -= n;
        value[col][r] -= n;
        value[col][row] = n;
        value[c][r] += n;
        return value;
    }

    public static String final_step() {
        int num = is_open(potreby, zapas);
        if (num > 0) {
            Object[] array= open_zapas(num, vartist, zapas);
            vartist=(int[][]) array[0];
            zapas=(int[]) array[1];
        } else if (num < 0) {
            Object[] array=open_potreby(num, vartist, potreby);
            vartist=(int[][]) array[0];
            potreby=(int[]) array[1];
        }
        int[][] value = min_vartist(vartist, potreby, zapas);
        int[] u = find_u_v(value, vartist, zapas, potreby)[0];
        int[] v = find_u_v(value, vartist, zapas, potreby)[1];
        Object[] potentials = potentials(value, vartist, u, v);
        int[][] potential=(int[][]) potentials[0];
        int flag = (int) potentials[1];
        while (flag != 0) {
            int[][] value_new = pererahunok(potential, value, flag);
            if (sumarray(value_new) == 0) {
                break;
            } else {
                value = value_new;
            }
            u = find_u_v(value, vartist, zapas, potreby)[0];
            v = find_u_v(value, vartist, zapas, potreby)[1];
            potentials = potentials(value, vartist, u, v);
            potential=(int[][]) potentials[0];
            flag = (int) potentials[1];
        }
        for (int i = 0; i < value.length; i++) {
            for (int j = 0; j < value[i].length; j++) {
                if (value[i][j] == -1) {
                    value[i][j] = 0;
                }
            }
        }
        return  generateOutput(value, vartist);
    }



    //Метод для виводу фінальних значень
    public static String generateOutput(int[][] value, int[][] vartist) {
        StringBuilder sb = new StringBuilder();
        sb.append("Загальна вартість: ").append(sumarr(value, vartist)).append("\n\n");
        sb.append("Матриця перевезень:\n");
        // Додамо відступи для міток стовпців
        sb.append(String.format("%1$5s", ""));
        for (int i = 0; i < n_zadacha; i++) {
            sb.append(String.format("%1$8s", "B" + (i + 1)));
        }
        sb.append("\n");

        value = trans(value);

        String output = new String();
        for (int i = 0; i <n_zadacha; i++) {
            sb.append(String.format("%1$-4s", "A" + (i + 1)));
            for (int j = 0; j < k_zadacha; j++) {
                if ((value[i][j] == 0) || (value[i][j] == -1)) {
                    sb.append(String.format("%1$8s", "0"));
                } else {
                    sb.append(String.format("%1$8d", value[i][j]));
                }
            }
            sb.append("\n");
        }

        output = sb.toString();
        return output;
    }



}
