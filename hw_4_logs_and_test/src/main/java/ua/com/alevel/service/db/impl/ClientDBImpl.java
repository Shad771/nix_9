package ua.com.alevel.service.db.impl;

import ua.com.alevel.service.db.ClientDB;
import ua.com.alevel.service.entity.Client;

public class ClientDBImpl implements ClientDB {

    private Client[] clients;
    private static ClientDBImpl instance;
    private int nextClientId = 0;
    private int capacity = 10;

    private ClientDBImpl() {
        clients = new Client[capacity];
    }

    public static ClientDBImpl getInstance() {
        if (instance == null) {
            instance = new ClientDBImpl();
        }
        return instance;
    }

    public void create(Client client) {
        if (nextClientId == capacity) {
            Client[] clientCopyArr = new Client[capacity * 2];
            for (int i = 0; i < nextClientId; i++) {
                clientCopyArr[i] = clients[i];
            }
            clients = clientCopyArr;
            clients[nextClientId] = client;
            nextClientId++;
            capacity *= 2;
        } else {
            clients[nextClientId] = client;
            nextClientId++;
        }
    }

    public void update(Client client) {
        Client current = findById(client.getId());
        current.setFirstName(client.getFirstName());
        current.setLastName(client.getLastName());
        current.setEmail(client.getEmail());
        current.setPhone(client.getPhone());
    }

    public void delete(String id) {
        for (int i = 0; i < nextClientId; i++) {
            if (clients[i].getId().equals(id)) {
                for (int j = i; j < nextClientId - 1; j++) {
                    clients[j] = clients[j + 1];
                }
                break;
            }
        }
        nextClientId--;
    }

    public Client findById(String id) {
        for (int i = 0; i < nextClientId; i++) {
            if (clients[i].getId().equals(id)) {
                return clients[i];
            }
        }
        return null;
    }

    public Client[] findAll() {
        return clients;
    }

    @Override
    public int sizeOf() {
        return nextClientId;
    }
}