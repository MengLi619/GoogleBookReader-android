package com.xiaoheifamily.bookstore.service;

import com.xiaoheifamily.bookstore.model.Book;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookService {

    public List<Book> getBooks() {

        List<Book> books = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            books.add(createBook(i + 1, "book" + new Date()));
        }

        return books;
    }

    private Book createBook(int id, String name) {

        Book book = new Book();

        book.setId(id);
        book.setTitle(name);

        return book;
    }
}