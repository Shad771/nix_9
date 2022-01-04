package ua.com.alevel.service.db.impl;

import ua.com.alevel.service.db.OrderDB;
import ua.com.alevel.service.entity.Order;
import ua.com.alevel.service.util.GenerateOrderIDUtil;

public class OrderDBImpl implements OrderDB {

    private static OrderDBImpl instance;
    private Order[] orders;
    private int nextOrderId = 0;
    private int capacity = 10;

    private OrderDBImpl() {
        orders = new Order[capacity];
    }

    public static OrderDBImpl getInstance() {
        if (instance == null) {
            instance = new OrderDBImpl();
        }
        return instance;
    }

    public void create(Order order) {
        if (nextOrderId == capacity) {
            Order[] orderCopyArr = new Order[capacity * 2];
            for (int i = 0; i < nextOrderId; i++) {
                orderCopyArr[i] = orders[i];
            }
            orders = orderCopyArr;
            order.setOrderId(GenerateOrderIDUtil.generate(order.getId()));
            orders[nextOrderId] = order;
            nextOrderId++;
            capacity *= 2;
        } else {
            order.setOrderId(GenerateOrderIDUtil.generate(order.getId()));
            orders[nextOrderId] = order;
            nextOrderId++;
        }
    }

    public void update(Order order) {
        Order current = findById(order.getOrderId());
        current.setFirstName(order.getFirstName());
        current.setLastName(order.getLastName());
        current.setWidth(order.getWidth());
        current.setHeight(order.getHeight());
        current.setColor(order.getColor());
        current.setType(order.getType());
    }

    public void deleteByClientId(String id) {
        for (int i = 0; i < nextOrderId; i++) {
            if (orders[i].getId().equals(id)) {
                for (int j = i; j < nextOrderId - 1; j++) {
                    orders[j] = orders[j + 1];
                }
                i--;
                nextOrderId--;
            }
        }
    }

    public void delete(String orderId) {
        for (int i = 0; i < nextOrderId; i++) {
            if (orders[i].getOrderId().equals(orderId)) {
                for (int j = i; j < nextOrderId - 1; j++) {
                    orders[j] = orders[j + 1];
                }
                break;
            }
        }
        nextOrderId--;
    }

    public Order findById(String orderId) {
        for (int i = 0; i < nextOrderId; i++) {
            if (orders[i].getOrderId().equals(orderId)) {
                return orders[i];
            }
        }
        return null;
    }

    public Order[] findByClientId(String id) {
        int clientsOrders = 0;
        int j = 0;
        for (int i = 0; i < nextOrderId; i++) {
            if (orders[i].getId().equals(id)) clientsOrders++;
        }
        if (clientsOrders == 0) return null;
        Order[] order = new Order[clientsOrders];
        for (int i = 0; i < nextOrderId; i++) {
            if (orders[i].getId().equals(id)) {
                order[j] = orders[i];
                j++;
            }
        }
        return order;
    }

    public Order[] findAll() {
        return orders;
    }

    @Override
    public int sizeOf() {
        return nextOrderId;
    }
}
