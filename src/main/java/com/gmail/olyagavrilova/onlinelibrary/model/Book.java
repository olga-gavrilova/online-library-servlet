package com.gmail.olyagavrilova.onlinelibrary.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Book  {
    private int id;
    private String title;
    private String author;
    private String publisher;
    private int quantity;
    private int yearOfPublishing;
    private List<User> bookUsers = new ArrayList<>();

    public Book() {
    }

    public Book(int id, String title, String author, String publisher, int quantity, int yearOfPublishing) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.quantity = quantity;
        this.yearOfPublishing = yearOfPublishing;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setYearOfPublishing(int yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    public List<User> getBookUsers() {
        return bookUsers;
    }

    public void setBookUsers(List<User> bookUsers) {
        this.bookUsers = bookUsers;
    }

    @Override
    public String toString() {
        return "Book{" +System.identityHashCode(this)+
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", quantity=" + quantity +
                ", yearOfPublishing=" + yearOfPublishing +
                '}';
    }
}
