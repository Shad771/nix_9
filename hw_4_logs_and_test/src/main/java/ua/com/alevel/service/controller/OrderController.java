package ua.com.alevel.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.service.entity.Access;
import ua.com.alevel.service.entity.Order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OrderController {
    private static final AccessServiceImpl accessService = new AccessServiceImpl();
    private static final OrderServiceImpl orderService = new OrderServiceImpl();

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    public static void orderMenu(Access access) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String menu = "";

        LOGGER_INFO.info("Entering OrderController.");
        do {
            if (accessService.findById(access.getId()) == null) break;
            System.out.println("1. New order.");
            System.out.println("2. Show all orders.");
            System.out.println("3. Delete order by orderId.");
            System.out.println("4. Back.");
            System.out.print("Your choice: ");
            try {
                menu = reader.readLine();
                System.out.println();
            } catch (IOException e) {
                e.printStackTrace();
            }
            switch (menu) {
                case "1": {
                    createOrder(access);
                    menu = "";
                    break;
                }
                case "2": {
                    showOrders(access);
                    menu = "";
                    break;
                }
                case "3": {
                    AdminController.deleteOrder(access);
                    menu = "";
                    break;
                }
            }
        } while (!menu.equals("4"));
    }

    public static void showOrders(Access access) {
        Order[] orders = orderService.findByClientId(access.getId());

        if (orders != null) {
            LOGGER_INFO.info("Printing orders of client with id: " + access.getId());
            for (int i = 0; i < orders.length; i++) {
                System.out.println((i + 1) + " " + orders[i]);
            }
        }
        else {
            LOGGER_INFO.info("Client's orders list is empty.");
            System.out.println("No orders yet created.");
        }
    }

    public static void createOrder(Access access) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Order order = new Order();

        do {
            try {
                LOGGER_INFO.info("Starting creating of new order.");
                order.setId(access.getId());
                System.out.print("Enter customer's first name: ");
                String firstName = reader.readLine();
                if (!firstName.isEmpty() && !firstName.isBlank()) order.setFirstName(firstName);
                else {
                    LOGGER_WARN.warn("Invalid input.");
                    continue;
                }
                System.out.print("Enter customer's last name: ");
                String lastName = reader.readLine();
                if (!lastName.isEmpty() && !lastName.isBlank()) order.setLastName(lastName);
                else {
                    LOGGER_WARN.warn("Invalid input.");
                    continue;
                }
                System.out.print("Enter width in mm: ");
                String width = reader.readLine();
                if (!lastName.isEmpty() && !lastName.isBlank()) order.setWidth(Integer.parseInt(width));
                else {
                    LOGGER_WARN.warn("Invalid input.");
                    continue;
                }
                System.out.print("Enter height in mm: ");
                String height = reader.readLine();
                if (!lastName.isEmpty() && !lastName.isBlank()) order.setHeight(Integer.parseInt(height));
                else {
                    LOGGER_WARN.warn("Invalid input.");
                    continue;
                }
                System.out.print("Enter color: ");
                String color = reader.readLine();
                if (!lastName.isEmpty() && !lastName.isBlank()) order.setColor(color);
                else {
                    LOGGER_WARN.warn("Invalid input.");
                    continue;
                }
                System.out.print("Enter type [multi, sds, storsan]: ");
                String type = reader.readLine();
                if (!lastName.isEmpty() && !lastName.isBlank()) order.setType(type);
                else {
                    LOGGER_WARN.warn("Invalid input.");
                    continue;
                }
                switch (type) {
                    case "multi": {
                        order.setPrice(((order.getHeight()) * (order.getWidth()) * 35));
                        break;
                    }
                    case "sds": {
                        order.setPrice(((order.getHeight()) * (order.getWidth()) * 25));
                        break;
                    }
                    case "storsan": {
                        order.setPrice(((order.getHeight()) * (order.getWidth()) * 20));
                        break;
                    }
                    default: {
                        LOGGER_WARN.warn("No such type.");
                        System.out.println("No such type.");
                        break;
                    }
                }
                orderService.create(order);
                break;
            } catch (IOException | NumberFormatException e) {
                LOGGER_ERROR.error("Invalid input.");
                System.out.println("Invalid input.");
                break;
            }
        } while (true);
    }
}