package ua.com.alevel.service.db.impl;

import ua.com.alevel.service.db.AccessDB;
import ua.com.alevel.service.entity.Access;
import ua.com.alevel.service.entity.Client;
import ua.com.alevel.service.util.GenerateAccessIdUtil;

public class AccessDBImpl implements AccessDB {

    private static AccessDBImpl instance;
    private Access[] accesses;
    private int nextAccessId = 0;
    private int capacity = 10;

    private AccessDBImpl() {
        accesses = new Access[capacity];
    }

    public static AccessDBImpl getInstance() {
        if (instance == null) {
            instance = new AccessDBImpl();
        }
        return instance;
    }

    public void create(Access access) {
        Client client = new Client();
        if (nextAccessId == capacity) {
            Access[] accessCopyArr = new Access[capacity * 2];

            for (int i = 0; i < nextAccessId; i++) {
                accessCopyArr[i] = accesses[i];
            }
            accesses = accessCopyArr;
            access.setId(GenerateAccessIdUtil.generate(accesses, nextAccessId));
            client.setId(access.getId());
            client.setFirstName(null);
            client.setLastName(null);
            client.setEmail(null);
            client.setPhone(null);
            ClientDBImpl.getInstance().create(client);
            accesses[nextAccessId] = access;
            nextAccessId++;
            capacity *= 2;
        } else {
            access.setId(GenerateAccessIdUtil.generate(accesses, nextAccessId));
            client.setId(access.getId());
            client.setFirstName(null);
            client.setLastName(null);
            client.setEmail(null);
            client.setPhone(null);
            ClientDBImpl.getInstance().create(client);
            accesses[nextAccessId] = access;
            nextAccessId++;
        }
    }

    public void update(Access access) {
        Access current = findById(access.getId());
        current.setLogin(access.getLogin());
        current.setPassword(access.getPassword());
    }

    public void delete(String id) {
        for (int i = 0; i < nextAccessId; i++) {
            if (accesses[i].getId().equals(id)) {
                for (int j = i; j < nextAccessId - 1; j++) {
                    accesses[j] = accesses[j + 1];
                }
                break;
            }
        }
        nextAccessId--;
        ClientDBImpl.getInstance().delete(id);
    }

    public Access findById(String id) {
        for (int i = 0; i < nextAccessId; i++) {
            if (accesses[i].getId().equals(id)) {
                return accesses[i];
            }
        }
        return null;
    }

    public Access[] findAll() {
        return accesses;
    }

    @Override
    public int sizeOf() {
        return nextAccessId;
    }
}
