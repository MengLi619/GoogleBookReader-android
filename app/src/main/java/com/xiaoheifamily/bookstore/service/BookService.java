package com.xiaoheifamily.bookstore.service;

import com.xiaoheifamily.bookstore.model.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookService {

    public List<Book> getBooks() {

        List<Book> books = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            books.add(createBook(i, "book" + i));
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