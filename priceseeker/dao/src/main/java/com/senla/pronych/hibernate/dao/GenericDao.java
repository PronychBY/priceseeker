package com.senla.pronych.hibernate.dao;

import java.util.List;

public interface GenericDao<T> {
    long save(T t);
    T getById(long id);
    void update(T t);
    void delete(long id);
    List<T> getAll();
}
