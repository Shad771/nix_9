package ua.com.alevel.service.db.impl;

import ua.com.alevel.service.db.AdminDB;
import ua.com.alevel.service.entity.Admin;

public class AdminDBImpl implements AdminDB {

    private static AdminDBImpl instance;
    private final Admin admin = new Admin();

    public static AdminDBImpl getInstance() {
        if (instance == null) {
            instance = new AdminDBImpl();
        }
        return instance;
    }

    @Override
    public void create(Admin admin) {
        this.admin.setAdminLogin(admin.getAdminLogin());
        this.admin.setAdminPassword(admin.getAdminPassword());
    }

    @Override
    public void update(Admin admin) {
        this.admin.setAdminLogin(admin.getAdminLogin());
        this.admin.setAdminPassword(admin.getAdminPassword());
    }

    @Override
    public Admin getAdmin() {
        return admin;
    }
}