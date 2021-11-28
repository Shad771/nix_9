package ua.com.alevel.service.dao.impl;

import ua.com.alevel.service.dao.AdminDao;
import ua.com.alevel.service.db.impl.AdminDBImpl;
import ua.com.alevel.service.entity.Admin;

public class AdminDaoImpl implements AdminDao {

    @Override
    public void create(Admin admin) {
        AdminDBImpl.getInstance().create(admin);
    }

    @Override
    public void update(Admin admin) {
        AdminDBImpl.getInstance().update(admin);

    }

    @Override
    public Admin getAdmin() {
        return AdminDBImpl.getInstance().getAdmin();
    }
}
