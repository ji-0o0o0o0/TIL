package java_practice.week01;

import java.util.Scanner;

public class W01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String title= sc.nextLine();
        float Grade= sc.nextFloat();
        Grade = (int)Grade;
        String[] menu = new String[11];
        for (int i = 0; i <=10 ; i++) {
            menu[i] = sc.nextLine();
        }

        System.out.println("["+title+"]");
        System.out.println(Grade +"("+(float)Grade/5*100+"%)");
        for (int i = 1; i <=10 ; i++) {
            System.out.println(i+"."+menu[i]);
        }
    }
}