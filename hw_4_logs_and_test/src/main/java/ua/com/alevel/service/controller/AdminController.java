package ua.com.alevel.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.service.entity.Access;
import ua.com.alevel.service.entity.Client;
import ua.com.alevel.service.entity.Order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdminController {

    private static final AccessServiceImpl accessService = new AccessServiceImpl();
    private static final AdminServiceImpl adminService = new AdminServiceImpl();
    private static final ClientServiceImpl clientService = new ClientServiceImpl();
    private static final OrderServiceImpl orderService = new OrderServiceImpl();

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    public static void runAsAdmin() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Access access = new Access();
        String menu = "";
        String id = "";

        LOGGER_INFO.info("Entering administration menu.");
        do {
            System.out.println("1. Find client by id.");
            System.out.println("2. Show all clients' info.");
            System.out.println("3. Show all clients' credentials.");
            System.out.println("4. Show all clients' orders.");
            System.out.println("5. Log out.");
            System.out.print("Your choice: ");
            try {
                menu = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            switch (menu) {
                case "1": {
                    try {
                        System.out.println();
                        System.out.print("Enter client's id: ");
                        id = reader.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (id.isEmpty() && id.isBlank()) {
                        LOGGER_ERROR.error("Invalid input.");
                        System.out.println("Invalid input.");
                    }
                    LOGGER_INFO.info("Trying to find access by id.");
                    if (accessService.sizeOf() == 0) {
                        LOGGER_ERROR.error("No such id found.");
                        System.out.println("No such id found.");
                    } else {
                        access = accessService.findById(id);
                        System.out.println(access);
                        if (access == null) {
                            LOGGER_ERROR.error("No such id found.");
                            System.out.println("No such id found.");
                        }
                    }
                    findById(access);
                    menu = "";
                    break;
                }
                case "2": {
                    showAllClients();
                    menu = "";
                    break;
                }
                case "3": {
                    showAllCredentials();
                    menu = "";
                    break;
                }
                case "4": {
                    showAllOrders();
                    menu = "";
                    break;
                }
            }
        } while (!menu.equals("5"));
    }

    public static void findById(Access access) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String menu = "";

        do {
            try {
                if (accessService.findById(access.getId()) == null) break;
                System.out.println("1. Show client's info.");
                System.out.println("2. Update client's credentials.");
                System.out.println("3. Update client's info.");
                System.out.println("4. Show client's orders.");
                System.out.println("5. Delete client's order by orderId.");
                System.out.println("6. Delete all client's orders.");
                System.out.println("7. Delete client.");
                System.out.println("8. Back.");
                System.out.print("Your choice: ");
                menu = reader.readLine();
                System.out.println();
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
            switch (menu) {
                case "1": {
                    System.out.println(access);
                    System.out.println(clientService.findById(access.getId()));
                    menu = "";
                    break;
                }
                case "2": {
                    updateCredentials(access);
                    menu = "";
                    break;
                }
                case "3": {
                    updateClient(access);
                    menu = "";
                    break;
                }
                case "4": {
                    showClientsOrders(access);
                    menu = "";
                    break;
                }
                case "5": {
                    deleteOrder(access);
                    menu = "";
                    break;
                }
                case "6": {
                    orderService.deleteByClientId(access.getId());
                    menu = "";
                    break;
                }
                case "7": {
                    System.out.print("Are you sure you want to delete your account? y/n: ");
                    String decision = "";
                    try {
                        decision = reader.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (decision.equals("y")) {
                        LOGGER_WARN.warn("Deleting client: " + clientService.findById(access.getId()));
                        LOGGER_WARN.warn("Deleting access: " + accessService.findById(access.getId()));
                        accessService.delete(access.getId());
                    }
                    menu = "";
                    break;
                }
            }
        } while (!menu.equals("8"));
    }

    public static void deleteOrder(Access access) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Enter orderId: ");
            String orderId = reader.readLine();
            Order order = orderService.findById(orderId);
            if (order != null && order.getId().equals(access.getId())) {
                LOGGER_WARN.warn("Deleting order with id: " + orderId);
                orderService.delete(orderId);
            } else {
                LOGGER_WARN.warn("No order with such orderId found.");
                System.out.println("No order with such orderId found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateClient(Access access) {
        Client client = clientService.findById(access.getId());
        Client[] clients = clientService.findAll();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean notVacantEmail = false;
        boolean notVacantPhone = false;

        do {
            try {
                System.out.print("Enter first name: ");
                String firstName = reader.readLine();
                System.out.print("Enter last name: ");
                String lastName = reader.readLine();
                System.out.print("Enter email: ");
                String email = reader.readLine();
                System.out.print("Enter phone number: ");
                String phone = reader.readLine();
                if (clientService.sizeOf() > 1) {
                    LOGGER_INFO.info(clientService.sizeOf() + "clients. Checking if Email and Phone unique.");
                    for (int i = 0; i < clientService.sizeOf(); i++) {
                        if (clients[i].getEmail().equals(email) && !clients[i].getId().equals(access.getId()))
                            notVacantEmail = true;
                        if (clients[i].getPhone().equals(phone) && !clients[i].getId().equals(access.getId()))
                            notVacantPhone = true;
                    }
                }
                if (notVacantEmail) {
                    LOGGER_WARN.warn("Email is already in use.");
                    System.out.println("Email is already in use.");
                    break;
                }
                if (notVacantPhone) {
                    LOGGER_WARN.warn("Phone number is already in use.");
                    System.out.println("Phone number is already in use.");
                } else {
                    if (!firstName.isEmpty() && !firstName.isBlank()) client.setFirstName(firstName);
                    else {
                        System.out.println("Invalid input.");
                        break;
                    }
                    if (!lastName.isEmpty() && !lastName.isBlank()) client.setLastName(lastName);
                    else {
                        System.out.println("Invalid input.");
                        break;
                    }
                    if (!email.isEmpty() && !email.isBlank()) client.setEmail(email);
                    else {
                        System.out.println("Invalid input.");
                        break;
                    }
                    if (!phone.isEmpty() && !phone.isBlank()) client.setPhone(phone);
                    else {
                        System.out.println("Invalid input.");
                        break;
                    }
                    clientService.update(client);
                }
                break;
            } catch (IOException e) {
                LOGGER_ERROR.error("Updating client failed, id: " + access.getId());
                e.printStackTrace();
            }
        } while (true);
    }

    public static void updateCredentials(Access access) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Access[] accesses = accessService.findAll();
        boolean notVacantLogin = false;

        do {
            try {
                System.out.print("Enter new login: ");
                String login = reader.readLine();
                System.out.print("Enter new password: ");
                String password = reader.readLine();
                if (login.equals(adminService.getAdmin().getAdminLogin())) {
                    System.out.println();
                    notVacantLogin = true;
                }
                if (accessService.sizeOf() > 0) {
                    for (int i = 0; i < accessService.sizeOf(); i++) {
                        if (accesses[i].getLogin().equals(login) && !accesses[i].getId().equals(access.getId()))
                            notVacantLogin = true;
                    }
                }
                if (notVacantLogin) {
                    System.out.println();
                    LOGGER_WARN.warn("Login is already in use.");
                    System.out.println("Login is already in use.");
                } else {
                    if (!login.isEmpty() && !login.isBlank()) access.setLogin(login);
                    else {
                        System.out.println("Invalid input.");
                        break;
                    }
                    if (!password.isEmpty() && !password.isBlank()) access.setPassword(password);
                    else {
                        System.out.println("Invalid input.");
                        break;
                    }
                    accessService.update(access);
                }
                break;
            } catch (IOException e) {
                LOGGER_ERROR.error("Updating access failed, id: " + access.getId());
                e.printStackTrace();
            }
        } while (true);
    }

    public static void showClientsOrders(Access access) {
        Order[] orders = orderService.findByClientId(access.getId());

        System.out.println();
        if (orders != null) {
            LOGGER_INFO.info("Printing all orders of client with id: " + access.getId());
            for (int i = 0; i < orders.length; i++) {
                System.out.println((i + 1) + " " + orders[i]);
            }
        } else {
            LOGGER_INFO.info("Client's orders list is empty.");
            System.out.println("Client's orders list is empty.");
        }
    }

    public static void showAllOrders() {
        Order[] orders = orderService.findAll();

        System.out.println();
        LOGGER_INFO.info("Printing all orders.");
        for (int i = 0; i < orderService.sizeOf(); i++) {
            System.out.println((i + 1) + " " + orders[i]);
        }
        if (orders == null) {
            System.out.println("No orders created yet.");
            LOGGER_INFO.info("No orders created yet.");
        }
    }

    public static void showAllCredentials() {
        Access[] accesses = accessService.findAll();

        System.out.println();
        LOGGER_INFO.info("Printing all accesses.");
        for (int i = 0; i < accessService.sizeOf(); i++) {
            System.out.println(i + " " + accesses[i]);
        }
        if (accesses == null) {
            System.out.println("No credentials created yet.");
            LOGGER_INFO.info("No credentials created yet.");
        }
    }

    public static void showAllClients() {
        Client[] clients = clientService.findAll();

        System.out.println();
        LOGGER_INFO.info("Printing all clients.");
        for (int i = 0; i < clientService.sizeOf(); i++) {
            System.out.println((i) + " " + clients[i]);
        }
        if (clients == null) {
            System.out.println("No clients created yet.");
            LOGGER_INFO.info("No clients created yet.");
        }
    }
}