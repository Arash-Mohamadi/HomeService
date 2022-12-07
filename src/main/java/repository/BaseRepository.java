package repository;


import entity.BaseEntity;
import jakarta.persistence.EntityManager;

import java.io.Serializable;
import java.util.List;

public interface BaseRepository<E extends BaseEntity<ID>, ID extends Serializable> {

    //CRUD Method
    void save(EntityManager entityManager ,E e);

    E update(EntityManager entityManager ,E e);

    void delete(EntityManager entityManager ,Long id);

    E findById(EntityManager entityManager ,ID id);


    List<E> findAll(EntityManager entityManager);

    boolean isExistsById(EntityManager entityManager ,ID id);



}
