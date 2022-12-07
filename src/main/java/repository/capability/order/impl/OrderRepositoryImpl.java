package repository.capability.order.impl;


import entity.capability.Order;
import repository.BaseRepositoryImpl;
import repository.capability.order.OrderRepository;



public class OrderRepositoryImpl extends BaseRepositoryImpl<Order,Long> implements OrderRepository {

    @Override
    public Class<Order> getEntityClass() {
        return Order.class;
    }
}
