package com.distribuida.books.repo;

import com.distribuida.books.db.Book;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class BookRepositoryImpl implements BookRepository{

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Book> findAll() {
        return em.createQuery("select o from Book o", Book.class)
                .getResultList();
    }

    @Override
    public Book findById(Integer id) {
        return em.find(Book.class, id);
    }

    @Override
    public void persist(Book obj) {
        em.persist(obj);
    }

    @Override
    public void deleteById(Integer id) {
        var obj = em.getReference(Book.class, id);
        em.remove(obj);
    }
}
