package service.users.customer;

import entity.capability.Opinion;
import entity.capability.Order;
import entity.capability.SubServices;
import entity.users.Customer;
import service.users.BaseUsersServices;

public interface CustomerService extends BaseUsersServices   {
    void signup(Customer customer);
    void registerOpinion(Opinion opinion,Long orderId);
    void registerOrder(Order order,Long subServiceId);
    void payment (Long orderId,double money);


}
