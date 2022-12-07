package repository.capability.opinion.impl;


import entity.capability.Opinion;
import repository.BaseRepositoryImpl;
import repository.capability.opinion.OpinionRepository;




public class OpinionRepositoryImpl extends BaseRepositoryImpl<Opinion,Long> implements OpinionRepository {

    @Override
    public Class<Opinion> getEntityClass() {
        return Opinion.class;
    }
}
