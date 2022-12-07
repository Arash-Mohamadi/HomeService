package repository;


import entity.BaseEntity;
import jakarta.persistence.EntityManager;

import java.io.Serializable;
import java.util.List;

public abstract class BaseRepositoryImpl<E extends BaseEntity<ID>,ID extends Serializable>implements BaseRepository<E,ID> {

    public abstract Class<E> getEntityClass();



    @Override
    public void save(EntityManager entityManager ,E e) {
        entityManager.persist(e);

    }

    @Override
    public E update(EntityManager entityManager ,E e) {

        return entityManager.merge(e);
    }

    @Override
    public void delete(EntityManager entityManager ,Long id) {
        entityManager.remove(id);

    }

    @Override
    public E findById(EntityManager entityManager ,ID id) {
        return entityManager.find(getEntityClass(),id);
    }

    @Override
    public List<E> findAll(EntityManager entityManager ) {
        return entityManager.createQuery("from "+getEntityClass().getSimpleName(),getEntityClass()).getResultList();
    }

    @Override
    public boolean isExistsById(EntityManager entityManager ,ID id) {                                     //Not Completed
        return false;
    }



}
