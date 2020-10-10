package com.gmail.olyagavrilova.onlinelibrary.dao.dto;



public class SubscriptionDto {

    private long bookId;


    private String  bookTitle;


    private String  author;


    private String  publisher;

    public SubscriptionDto(long bookId, String bookTitle, String author, String publisher) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.author = author;
        this.publisher = publisher;
    }

    public SubscriptionDto() {
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
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
}
