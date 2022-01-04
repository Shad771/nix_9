package ua.com.alevel.service.db;

import ua.com.alevel.service.entity.BaseEntity;

public interface BaseDB<ENTITY extends BaseEntity> {

    void create(ENTITY entity);

    void update(ENTITY entity);

    void delete(String id);

    ENTITY findById(String id);

    ENTITY[] findAll();

    int sizeOf();
}