package org.example.utils;

import org.example.exception.EndOfBookException;
import org.example.exception.PageAccessException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.lang.Math.ceil;

/*
    @author shivansh0408
*/
public class Book {
    private String name;
    private String content;
    private List<String> word;

    public Book(String name) {
        this.name = name;
    }

    public Book(String name, String content) {
        this.name = name;
        this.content = content;
        this.word = Arrays.asList(content.split(" "));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.word = Arrays.asList(content.split(" "));
        this.content = content;
    }

    public String getPage(int currPage, int pageSize) {
        if (getTotalPages(pageSize) < currPage) {
            throw new EndOfBookException("You have finished the book " + this.name + ".");
        }
        try{
            return String.join(" ", word.subList(pageSize*currPage, pageSize*(currPage+1)));
        } catch (Exception e) {
            throw new PageAccessException("Can't open page.", e.getCause());
        }
    }

    public int getTotalPages(int pageSize) {
        return (int) ceil(word.size()*1.0/pageSize);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return name.equals(book.name) &&
                content.equals(book.content) &&
                word.equals(book.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, content, word);
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
