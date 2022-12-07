package repository.capability.credit.impl;

import entity.capability.Credit;
import repository.BaseRepositoryImpl;
import repository.capability.credit.CreditRepository;

public class CreditRepositoryImpl extends BaseRepositoryImpl<Credit,Long> implements CreditRepository {
    @Override
    public Class<Credit> getEntityClass() {
        return Credit.class;
    }
}
