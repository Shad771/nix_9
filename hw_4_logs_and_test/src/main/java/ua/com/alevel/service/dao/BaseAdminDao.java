package ua.com.alevel.service.dao;

import ua.com.alevel.service.entity.BaseEntity;

public interface BaseAdminDao <ENTITY extends BaseEntity> {

    void create(ENTITY entity);
    void update(ENTITY entity);
    ENTITY getAdmin();
}