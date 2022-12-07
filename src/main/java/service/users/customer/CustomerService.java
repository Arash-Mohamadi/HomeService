package service.users.customer;

import entity.users.Customer;
import service.users.BaseUsersServices;

public interface CustomerService extends BaseUsersServices   {
    void signup(Customer  customer);

}
