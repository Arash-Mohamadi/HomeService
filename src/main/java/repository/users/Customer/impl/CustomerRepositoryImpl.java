package repository.users.Customer.impl;

import entity.users.Customer;
import repository.BaseRepositoryImpl;
import repository.users.Customer.CustomerRepository;

public class CustomerRepositoryImpl extends BaseRepositoryImpl<Customer,Long> implements CustomerRepository {

    @Override
    public Class<Customer> getEntityClass() {
        return Customer.class;
    }
}
