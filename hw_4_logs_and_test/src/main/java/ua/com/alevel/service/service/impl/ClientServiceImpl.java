package ua.com.alevel.service.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.service.db.impl.ClientDBImpl;
import ua.com.alevel.service.entity.Client;
import ua.com.alevel.service.service.ClientService;

public class ClientServiceImpl implements ClientService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    @Override
    public void create(Client client) {
        LOGGER_INFO.info("New client creating started");
        ClientDBImpl.getInstance().create(client);
        LOGGER_INFO.info("New client creating finished");
    }

    @Override
    public void update(Client client) {
        LOGGER_INFO.info("Client updating started");
        ClientDBImpl.getInstance().update(client);
        LOGGER_INFO.info("Client updating finished");
    }

    @Override
    public void delete(String id) {
        LOGGER_WARN.warn("Client deleting started");
        ClientDBImpl.getInstance().delete(id);
        LOGGER_WARN.warn("Client deleting finished");
    }

    @Override
    public Client findById(String id) {
        try {
            return ClientDBImpl.getInstance().findById(id);
        } catch (RuntimeException e) {
            LOGGER_ERROR.error("Client not found by id: " + id);
        }
        return null;
    }

    @Override
    public Client[] findAll() {
        return ClientDBImpl.getInstance().findAll();
    }

    @Override
    public int sizeOf() {
        return ClientDBImpl.getInstance().sizeOf();
    }
}