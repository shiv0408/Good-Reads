package org.example.utils;

import org.example.exception.ResourceNotFoundException;
import org.example.exception.UnauthorizedActionException;

/*
    @author shivansh0408
*/
public class Session {
    private User user;
    private Book currBook;
    private final static DataBank dataBank = new DataBank();

    public Session() {
    }

    public void login(String name) {
        user = dataBank.findUser(name);
        if (user == null) {
            throw new ResourceNotFoundException(name + "user was not found. Please register to continue.");
        }
        System.out.println("Welcome "+name);
    }

    public void logOut() {
        System.out.println("You are now logged out.");
        user = null;
        currBook = null;
    }

    public User register(String name) {
        user = new User(name);
        System.out.println("New account created, welcome "+name);
        dataBank.addUser(user);
        return user;
    }

    private void validateLogin() {
        if (user == null) {
            throw new UnauthorizedActionException("You are not logged in, make sure you are logged in or register now.");
        }
    }

    private void validateBook() {
        if (currBook == null) {
            throw new ResourceNotFoundException("Please start reading a book first to use that action.");
        }
    }

    public boolean addBook(String name, String content) {
        validateLogin();
        dataBank.addBook(user, new Book(name, content));
        return true;
    }

    public boolean purchase(String name) {
        validateLogin();
        Book purchasedBook = dataBank.findBook(name);
        if (purchasedBook == null) {
            throw new ResourceNotFoundException("Book with name " + name + "not found");
        }
        user.addBook(purchasedBook);
        System.out.println("Book added to your library");
        return true;
    }

    public boolean startRead(String name) {
        validateLogin();
        currBook = dataBank.findBook(name);
        if (currBook == null) {
            throw new ResourceNotFoundException("Book with name " + name + "not found");
        }
        try {
            System.out.println(user.readBook(currBook));
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean next() {
        validateLogin();
        validateBook();
        System.out.println(user.nextPage(currBook));
        return true;
    }

    public boolean prev() {
        validateLogin();
        validateBook();
        System.out.println(user.prevPage(currBook));
        return true;
    }

    public boolean goTo(int page) {
        validateLogin();
        validateBook();
        System.out.println(user.gotoBookPage(currBook, page));
        return true;
    }

    public boolean rate(int rating) {
        validateLogin();
        validateBook();
        dataBank.addRating(user, currBook, rating);
        return true;
    }

    public void listMyBooks() {
        validateLogin();
        for (Book book : user.getOwnedBooks()) {
            System.out.println(book.getName());
        }
    }

    public void listBooks() {
        for (Book book : dataBank.getBooks()) {
            System.out.println(book.getName());
        }
    }

    public void recommend() {
        validateLogin();
        for (Book book : dataBank.recommendBooks(user.getCurrentReads())) {
            System.out.println(book.getName());
        }
    }

    public boolean setPageSize(int size) {
        validateLogin();
        return user.setPageSize(size);
    }
}
