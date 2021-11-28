package ua.com.alevel.service.dao.impl;

import ua.com.alevel.service.dao.AccessDao;
import ua.com.alevel.service.db.impl.AccessDBImpl;
import ua.com.alevel.service.entity.Access;

public class AccessDaoImpl implements AccessDao {

    @Override
    public void create(Access access) {
        AccessDBImpl.getInstance().create(access);
    }

    @Override
    public void update(Access access) {
        AccessDBImpl.getInstance().update(access);
    }

    @Override
    public void delete(String id) {
        AccessDBImpl.getInstance().delete(id);
    }

    @Override
    public Access findById(String id) {
        return AccessDBImpl.getInstance().findById(id);
    }

    @Override
    public Access[] findAll() {
        return AccessDBImpl.getInstance().findAll();
    }

    @Override
    public int sizeOf() {
        return AccessDBImpl.getInstance().sizeOf();
    }
}