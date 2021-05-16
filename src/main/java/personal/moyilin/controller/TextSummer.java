package personal.moyilin.controller;

import java.util.Arrays;
import java.util.Scanner;

public class TextSummer {
    public void firstImg(){
        Scanner scan =new Scanner(System.in);
//      输入数组
        System.out.println("请输入数组长度:");
        int i = scan.nextInt();
        if(i>=1&&i<=20) {
        }else {
            boolean t=true;
            while (t) {
                System.out.print("请输入1~20的数：");
                i = scan.nextInt();
                if(i>=1&&i<=20) {
                    t = false;
                }
            }
        }
        int s[][] = new int[i][3];
        for (int j = 0; j <i ; j++) {
            System.out.println("第"+(j+1)+"组");
            for (int k = 0; k < 3; k++) {
                System.out.print("第"+(k+1)+"个数:");
                s[j][k]=scan.nextInt();
                if(s[j][k] != 0 && s[j][k] != 1) {
                    boolean t=true;
                    while (t) {
                        System.out.print("请输入0或1：");
                        s[j][k] = scan.nextInt();
                        if(s[j][k] == 0 || s[j][k] == 1) {
                            t = false;
                        }
                    }
                }
            }
        }
//        显示输入数组
        System.out.print("输入：");
        System.out.println(Arrays.deepToString(s));
        int x=0;
        for (int j = 0; j <i ; j++) {
            x=s[j][0];
            s[j][0]=s[j][2];
            s[j][2]=x;
        }
        // 显示翻转数组
        System.out.print("翻转：");
        System.out.println(Arrays.deepToString(s));
        for (int j = 0; j < i; j++) {
            for (int k = 0; k < 3; k++) {
                if(s[j][k]==0){
                    s[j][k]=1;
                }else if(s[j][k]==1){
                    s[j][k]=0;
                }
            }
        }
        // 显示输出数组
        System.out.print("输出：");
        System.out.println(Arrays.deepToString(s));
    }
    public void secArray(){
        Scanner scan =new Scanner(System.in);
//      输入数组
        System.out.println("请输入数组元素数量:");
        int i = scan.nextInt();
        if(i>=1&&i<=104) {
        }else {
            boolean t=true;
            while (t) {
                System.out.print("请输入1~104的数：");
                i = scan.nextInt();
                if(i>=1&&i<=104) {
                    t = false;
                }
            }
        }
        int s[] = new int[i];
        for (int j = 0; j <i ; j++) {
            System.out.print("第"+(j+1)+"个数:");
            s[j]=scan.nextInt();
            if(s[j] <-105 || s[j] > 105) {
                boolean t=true;
                while (t) {
                    System.out.print("请输入-105~105的数：");
                    s[j] = scan.nextInt();
                    if(s[j] <-105 || s[j] > 105) {

                    }else {
                        t = false;
                    }
                }
            }
        }
        System.out.println("输入：nums="+Arrays.toString(s));
//        寻找位置
        int left=0,right=0;
        int min=s[0],max=s[i-1];
        if(i>1) {
            for (int j = 0; j < i; j++) {
                if(min <= s[j]){
                    min=s[j];
                }else if (min > s[j]) {
                    left = j-1;
//                    System.out.println("left:" + left);
                    break;
                }
            }
            for (int j = i - 1; j >= 0; j--) {
                if(max >= s[j]){
                    max=s[j];
                }else if (max < s[j]) {
                    right = j + 1;
//                    System.out.println("right:" + right);
                    break;
                }
            }
//           输出结果
            if(left==right){
                System.out.println("输出：0");
            }else {
                System.out.println("输出：" + (right - left + 1));
                System.out.print("只需要对[");
                for (int j = left; j <=right; j++) {
                    System.out.print(s[j]);
                    if(j<right){
                        System.out.print(",");
                    }
                }
                System.out.println("]进行排序");
            }

        }else{
            System.out.println("输出:"+0);
        }

    }
    public static void main(String[] args) {
        TextSummer textSummer = new TextSummer();
//        textSummer.firstImg();
        textSummer.secArray();
    }

}
