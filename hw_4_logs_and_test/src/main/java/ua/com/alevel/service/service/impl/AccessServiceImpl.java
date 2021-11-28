package ua.com.alevel.service.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.service.db.impl.AccessDBImpl;
import ua.com.alevel.service.entity.Access;
import ua.com.alevel.service.service.AccessService;

public class AccessServiceImpl implements AccessService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    @Override
    public void create(Access access) {
        LOGGER_INFO.info("New access creating started");
        AccessDBImpl.getInstance().create(access);
        LOGGER_INFO.info("New access creating finished, id: " + access.getId());
    }

    @Override
    public void update(Access access) {
        LOGGER_INFO.info("Access updating started");
        AccessDBImpl.getInstance().update(access);
        LOGGER_INFO.info("Access updating finished");
    }

    @Override
    public void delete(String id) {
        LOGGER_WARN.warn("Access deleting started");
        AccessDBImpl.getInstance().delete(id);
        LOGGER_WARN.warn("Access deleting finished");
    }

    @Override
    public Access findById(String id) {
        try {
            return AccessDBImpl.getInstance().findById(id);
        } catch (RuntimeException e) {
            LOGGER_ERROR.error("Access not found by id: " + id);
        }
        return null;
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