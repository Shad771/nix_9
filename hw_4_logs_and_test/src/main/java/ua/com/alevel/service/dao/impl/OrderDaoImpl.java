package ua.com.alevel.service.dao.impl;

import ua.com.alevel.service.dao.OrderDao;
import ua.com.alevel.service.db.impl.OrderDBImpl;
import ua.com.alevel.service.entity.Order;

public class OrderDaoImpl implements OrderDao {

    @Override
    public void create(Order order) {
        OrderDBImpl.getInstance().create(order);
    }

    @Override
    public void update(Order order) {
        OrderDBImpl.getInstance().update(order);
    }

    @Override
    public void delete(String orderId) {
        OrderDBImpl.getInstance().delete(orderId);
    }

    @Override
    public void deleteByClientId(String id) {
        OrderDBImpl.getInstance().deleteByClientId(id);
    }

    @Override
    public Order findById(String orderId) {
        return OrderDBImpl.getInstance().findById(orderId);
    }

    @Override
    public Order[] findByClientId(String id) {
        return OrderDBImpl.getInstance().findByClientId(id);
    }

    @Override
    public Order[] findAll() {
        return OrderDBImpl.getInstance().findAll();
    }

    @Override
    public int sizeOf() {
        return OrderDBImpl.getInstance().sizeOf();
    }
}