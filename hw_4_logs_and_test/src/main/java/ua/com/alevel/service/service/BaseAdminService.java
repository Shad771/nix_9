package ua.com.alevel.service.service;

import ua.com.alevel.service.entity.BaseEntity;

public interface BaseAdminService <ENTITY extends BaseEntity> {

    void create(ENTITY entity);
    void update(ENTITY entity);
    ENTITY getAdmin();
}