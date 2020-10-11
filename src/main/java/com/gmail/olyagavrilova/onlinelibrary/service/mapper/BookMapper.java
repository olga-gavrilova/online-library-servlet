package com.gmail.olyagavrilova.onlinelibrary.service.mapper;

import com.gmail.olyagavrilova.onlinelibrary.dao.entity.Book;
import com.gmail.olyagavrilova.onlinelibrary.model.BookDto;

public class BookMapper {

    public BookDto convertEntityToDto(Book entity) {
        BookDto bookDto = new BookDto();
        bookDto.setId(entity.getId());
        bookDto.setAuthor(entity.getAuthor());
        bookDto.setPublisher(entity.getPublisher());
        bookDto.setQuantity(entity.getQuantity());
        bookDto.setYearOfPublishing(entity.getYearOfPublishing());
        return bookDto;
    }
}
