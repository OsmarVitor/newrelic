package com.example.newrelic.domain.service;

import org.springframework.data.domain.Page;

public interface GenericService<T> {

    T create(T entity);

    T find(long id);

    T update(long id, T entity);

    void delete(Long id);

    Page<T> list(int page, int size);
}
