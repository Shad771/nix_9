package ua.com.alevel.service.dao;

import ua.com.alevel.service.entity.BaseEntity;

public interface BaseDao <ENTITY extends BaseEntity> {

    void create(ENTITY entity);
    void update(ENTITY entity);
    void delete(String id);
    ENTITY findById(String id);
    ENTITY[] findAll();
    int sizeOf();
}