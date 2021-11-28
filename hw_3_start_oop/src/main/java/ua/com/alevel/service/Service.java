package ua.com.alevel.service;

public class Service {

    private final Dao modelDao = new Dao();

    public void create(User user) {
        modelDao.create(user);
    }

    public void update(User user) {
        modelDao.update(user);
    }

    public boolean delete(String id) {
        return modelDao.delete(id);
    }

    public User findById(String id) {
        return modelDao.findById(id);
    }

    public User[] findAll() {
        return modelDao.findAll();
    }
}
