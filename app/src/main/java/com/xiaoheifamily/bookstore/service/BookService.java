package com.xiaoheifamily.bookstore.service;

import com.xiaoheifamily.bookstore.model.Book;

import java.util.Arrays;
import java.util.List;

public class BookService {

    public List<Book> getBooks() {

        return Arrays.asList(
                createBook(1, "book1"),
                createBook(2, "book2")
        );
    }

    private Book createBook(int id, String name) {

        Book book = new Book();

        book.setId(id);
        book.setTitle(name);

        return book;
    }
}