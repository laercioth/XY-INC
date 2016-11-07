package br.com.xyinc.persistence.dao;

import java.util.List;

public interface IGenericDAO<T, ID> {
    List<T> listAll();

    T saveOrUpdate(T t);

    void remove(T t);

    void removeById(ID id);

    T findById(ID id);

}
