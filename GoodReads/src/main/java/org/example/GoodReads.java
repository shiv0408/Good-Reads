package org.example;

/*
    @author shivansh0408
*/

import org.example.utils.Helper;
import org.example.utils.Session;
import org.example.utils.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GoodReads {
    public static void main(String[] args) {
        try {
            Session session = new Session();
            //File file = new File(ClassLoader.getSystemClassLoader().getResource("input.txt").getFile());
            Scanner scanner = new Scanner(System.in);
            Helper helper = new Helper(session, scanner);
            helper.initiate();
            String input = "";
            while (!input.equals("exit")) {
                input = scanner.nextLine();
                helper.process(input);
                Thread.sleep(10000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
