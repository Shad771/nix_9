package ua.com.alevel.service.dao.impl;

import ua.com.alevel.service.dao.ClientDao;
import ua.com.alevel.service.db.impl.ClientDBImpl;
import ua.com.alevel.service.entity.Client;

public class ClientDaoImpl implements ClientDao {

    @Override
    public void create(Client client) {
        ClientDBImpl.getInstance().create(client);
    }

    @Override
    public void update(Client client) {
        ClientDBImpl.getInstance().update(client);
    }

    @Override
    public void delete(String id) {
        ClientDBImpl.getInstance().delete(id);
    }

    @Override
    public Client findById(String id) {
        return ClientDBImpl.getInstance().findById(id);
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