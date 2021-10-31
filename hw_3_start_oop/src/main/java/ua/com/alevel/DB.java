package ua.com.alevel;

import java.util.Arrays;
import java.util.UUID;

public final class DB {

    private User[] users;
    private static DB instance;

    int size = 0;

    private DB() {
        users = new User[size];
    }

    public static DB getInstance() {
        if (instance == null) {
            instance = new DB();
        }
        return instance;
    }

    public void create(User user) {
        User[] userImproved = new User[size + 1];
        user.setId(generateId());
        if (size >= 0) System.arraycopy(users, 0, userImproved, 0, size);
        size++;
        userImproved[size - 1] = user;
        users = userImproved;

    }

    public void update(User user) {
        User current = findById(user.getId());
        current.setSize(user.getSize());
    }

    public boolean delete(String id) {
        for (int i = 0; i < size; i++) {
            if (users[i].getId().equals(id)) {
                deleteAt(i);
                return true;
            }
        }
        return false;
    }

    private void deleteAt(int index) {
        User[] modelsImproved = new User[size - 1];
        for (int i = 0, iD = 0; i < size; i++) {
            if (i != index) {
                modelsImproved[iD] = users[i];
                iD++;
            }

        }
        size--;
        users = modelsImproved;
    }

    public User findById(String id) {
        return Arrays.stream(users)
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public User[] findAll() {
        return users;
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        if (Arrays.stream(users).anyMatch(user -> user.getId().equals(id))) {
            return generateId();
        }
        return id;
    }
}