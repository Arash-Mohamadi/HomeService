package repository.users.Staff.impl;

import entity.users.Staff;
import repository.BaseRepositoryImpl;
import repository.users.Staff.StaffRepository;

public class StaffRepositoryImpl extends BaseRepositoryImpl<Staff,Long> implements StaffRepository {


    @Override
    public Class<Staff> getEntityClass() {
        return Staff.class;
    }
}
