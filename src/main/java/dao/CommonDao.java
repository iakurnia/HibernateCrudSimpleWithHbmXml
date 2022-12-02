package dao;

import model.Person;

import java.util.List;

public interface CommonDao<T> {
    String save(T obj);

    String update(T obj);

    String delete(Person obj);

    T findById(String id);

    List<T> findByName(String name);

    List<T> findAll();

    List<T> finByFieldValue(String field, String value);
}
