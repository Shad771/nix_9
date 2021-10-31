package ua.com.alevel;


public class Dao {

    public void create(User user) {
        DB.getInstance().create(user);
    }

    public void update(User user) {
        DB.getInstance().update(user);
    }

    public boolean delete(String id) {
        return DB.getInstance().delete(id);
    }

    public User findById(String id) {
        return DB.getInstance().findById(id);
    }

    public User[] findAll() {
        return DB.getInstance().findAll();
    }
}
