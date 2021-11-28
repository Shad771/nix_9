package ua.com.alevel.service.db;

import ua.com.alevel.service.entity.BaseEntity;

public interface BaseAdminDB<ENTITY extends BaseEntity> {

    void create(ENTITY entity);
    void update(ENTITY entity);
    ENTITY getAdmin();
}