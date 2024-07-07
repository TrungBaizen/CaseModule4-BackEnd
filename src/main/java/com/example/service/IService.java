package com.example.service;

import java.util.List;

public interface IService<T> {
    T save(T t);
    T update(T t,Long id);
    T findById(Long id);
    T delete(Long id);
    List<T> findAll();
    List<T> findByName(String name);
}
