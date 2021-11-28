package ua.com.alevel.service.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.service.db.impl.OrderDBImpl;
import ua.com.alevel.service.entity.Order;
import ua.com.alevel.service.service.OrderService;

public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    @Override
    public void create(Order order) {
        LOGGER_INFO.info("New order creating started.");
        OrderDBImpl.getInstance().create(order);
        LOGGER_INFO.info("New order creating finished id: " + order.getOrderId());
    }

    @Override
    public void update(Order order) {
        LOGGER_INFO.info("Order updating started id: " + order.getOrderId());
        OrderDBImpl.getInstance().update(order);
        LOGGER_INFO.info("Order updating finished id: " + order.getOrderId());
    }

    @Override
    public void delete(String orderId) {
        LOGGER_WARN.warn("Order deleting started id: " + orderId);
        OrderDBImpl.getInstance().delete(orderId);
        LOGGER_WARN.warn("Order deleting finished id: " + orderId);
    }

    @Override
    public void deleteByClientId(String id) {
        LOGGER_ERROR.error("Orders deleting started, client id: " + id);
        OrderDBImpl.getInstance().deleteByClientId(id);
        LOGGER_ERROR.error("Orders deleting finished, client id: " + id);
    }

    @Override
    public Order findById(String orderId) {
        try {
            return OrderDBImpl.getInstance().findById(orderId);
        } catch (RuntimeException e) {
            LOGGER_ERROR.error("Order not found by id: " + orderId);
        }
        return null;
    }

    @Override
    public Order[] findByClientId(String id) {
        try {
            return OrderDBImpl.getInstance().findByClientId(id);
        } catch (RuntimeException e) {
            LOGGER_ERROR.error("Orders not found by client id: " + id);
        }
        return null;
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