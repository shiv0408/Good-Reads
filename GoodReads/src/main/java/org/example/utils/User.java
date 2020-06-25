package org.example.utils;

import org.example.exception.BookReadException;
import org.example.exception.EndOfBookException;
import org.example.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    @author shivansh0408
*/
public class User {
    private static int totalUser = 0;
    private int id;
    private String name;
    private boolean isAdmin;
    private List<Book> ownedBooks;
    private Map<Book, Integer> currentReads;
    private int pageSize = 1;

    public User(String name) {
        totalUser++;
        this.id = totalUser;
        this.name = name;
        this.isAdmin = false;
        this.ownedBooks = new ArrayList<>();
        this.currentReads = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public List<Book> getOwnedBooks() {
        return ownedBooks;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void addBook(Book book) {
        this.ownedBooks.add(book);
    }

    public String readBook(Book book) {
        try{
            if (currentReads.containsKey(book)) {
                return book.getPage(currentReads.get(book), pageSize);
            } else if (ownedBooks.contains(book)) {
                currentReads.put(book, 0);
                return book.getPage(0, pageSize);
            } else {
                throw new ResourceNotFoundException("Book you are trying to read is not in you library, " +
                        "please purchase it first to read it.");
            }
        } catch (EndOfBookException e) {
            currentReads.put(book, 0);
            throw e;
        } catch (Exception e) {
            throw new BookReadException(e.getMessage(), e.getCause());
        }
    }

    public String gotoBookPage(Book book, int page) {
        currentReads.put(book, page);
        return readBook(book);
    }

    public String nextPage(Book book) {
        currentReads.computeIfPresent(book, (b, p) -> p + 1);
        return readBook(book);
    }

    public String prevPage(Book book) {
        currentReads.computeIfPresent(book, (b, p) -> p - 1);
        return readBook(book);
    }

    public boolean setPageSize(int size) {
        if (size<1) {
            return false;
        } else {
            pageSize = size;
            return true;
        }
    }

    public List<Book> getCurrentReads() {
        return new ArrayList<>(currentReads.keySet());
    }
}
