package org.example.utils;

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/*
    @author shivansh0408
*/
public class Helper {
    private Session session;
    private Scanner scanner;

    public Helper(Session session, Scanner scanner) {
        this.session = session;
        this.scanner = scanner;
    }

    public void initiate() {
        session.register("shiv").setAdmin(true);
        session.logOut();
    }

    private void help(@Nullable String command) {

    }

    private void help() {

    }

    public void process(String command) {
        String[] commandParts = command.split(" ", 2);
        try{
            switch (commandParts[0]) {
                case "addBook": {
                    String title = scanner.nextLine();
                    String content = scanner.nextLine();
                    session.addBook(title, content);
                    break;
                }
                case "login": session.login(commandParts[1]);break;
                case "logout": session.logOut();break;
                case "register": session.register(commandParts[1]);break;
                case "purchase": session.purchase(commandParts[1]);break;
                case "startReading":
                case "resumeReading": session.startRead(commandParts[1]);break;
                case "next": session.next();break;
                case "prev": session.prev();break;
                case "goTo": session.goTo(Integer.parseInt(commandParts[1]));break;
                case "rate": session.rate(Integer.parseInt(commandParts[1]));break;
                case "listMyBooks": session.listMyBooks();break;
                case "listBooks": session.listBooks();break;
                case "recommend": session.recommend();break;
                case "setPageSize": session.setPageSize(Integer.parseInt(commandParts[1]));break;
                case "help": help(commandParts[1]);break;   
                default: help();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
