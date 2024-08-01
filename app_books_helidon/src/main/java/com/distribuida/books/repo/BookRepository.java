package com.distribuida.books.repo;

import com.distribuida.books.db.Book;

import java.util.List;

public interface BookRepository {
    List<Book> findAll();

    Book findById(Integer id);

    void persist(Book obj);

    void deleteById(Integer id);
}
