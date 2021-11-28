package ua.com.alevel.service;

public class User {

    private String id;
    private int size;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id='" + id + '\'' +
                ", size=" + size +
                '}';
    }
}
