package com.nhatquang.dao;

import java.util.List;

/**
 * @author Quang Nguyen
 */
public interface BasicDao<T> {

    default int save(T t) {
        return 0;
    }

    default int save(List<T> list) {
        return 0;
    }

    default void update(T t) {}

    default void delete(T t) {}

    default T findOne(String query) {
        return null;
    };

    default List<T> findAll() {
        return null;
    }

    default List<T> find(String query) {
        return null;
    };

    default List<T> search(String text) {
        return null;
    }
}
