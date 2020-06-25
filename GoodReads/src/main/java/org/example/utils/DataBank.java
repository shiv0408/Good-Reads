package org.example.utils;

import org.example.exception.InvalidRatingException;
import org.example.exception.UnauthorizedActionException;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

/*
    @author shivansh0408
*/
public class DataBank {
    private Map<String, Book> books;
    private Map<String, User> users;
    private Map<Book, Double> bookRatings;
    private Map<Book, Map<User, Integer>> userRatings;

    public DataBank() {
        this.books = new HashMap<>();
        this.users = new HashMap<>();
        this.bookRatings = new HashMap<>();
        this.userRatings = new HashMap<>();
    }

    public void addBook(@NotNull User user, @NotNull Book book) {
        if (user.isAdmin()) {
            books.put(book.getName(), book);
        } else {
            throw new UnauthorizedActionException("Only the admin has that privilege");
        }
    }

    public void addUser(User user) {
        users.put(user.getName(), user);
    }

    private void updateRatings() {
        userRatings.forEach((key, value) -> bookRatings.put(key, value.values().stream().mapToDouble(a -> a).average().orElse(0.0)));
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books.values());
    }

    public void addRating(User user, Book book, int rate) {
        if(rate > 10 || rate < 1) {
            throw new InvalidRatingException("Rating out of range");
        } else {
            Map<User, Integer> bookUserRatings = userRatings.getOrDefault(book, new HashMap<>());
            bookUserRatings.put(user, rate);
            userRatings.put(book, bookUserRatings);
            updateRatings();
        }
    }

    public List<Book> recommendBooks(List<Book> excludedBooks) {
        List<Map.Entry<Book, Double>> ratingList = new ArrayList<>(bookRatings.entrySet());
        ratingList.sort((t, t1) -> t1.getValue().compareTo(t.getValue()));
        List<Book> recommendation = new ArrayList<>();
        for (Map.Entry<Book, Double> entry : ratingList) {
            if (recommendation.size() == 5) {
                break;
            }
            if (!excludedBooks.contains(entry.getKey())) {
                recommendation.add(entry.getKey());
            }
        }
        return recommendation;
    }

    public Book findBook(String name) {
        return books.getOrDefault(name, null);
    }

    public User findUser(String name) {
        return users.getOrDefault(name, null);
    }
}
