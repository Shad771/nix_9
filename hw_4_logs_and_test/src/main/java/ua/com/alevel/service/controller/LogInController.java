package ua.com.alevel.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ua.com.alevel.service.entity.Access;

public class LogInController {
    private static final AccessServiceImpl accessService = new AccessServiceImpl();
    private static final AdminServiceImpl adminService = new AdminServiceImpl();
    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    public static void runLogIn() {
        String menu = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        do {
            LOGGER_INFO.info("Log in page.");
            System.out.println();
            System.out.println("1. Log in.");
            System.out.println("2. Register.");
            System.out.println("3. Exit.");
            System.out.print("Your choice: ");
            try {
                menu = reader.readLine();
                System.out.println();
            } catch (IOException e) {
                e.printStackTrace();
            }
            switch (menu) {
                case "1": {
                    verification();
                    menu = "";
                    break;
                }
                case "2": {
                    createAccess();
                    menu = "";
                    break;
                }
                case "3": {
                    System.exit(0);
                }
            }
        } while (true);
    }

    public static void verification() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Access[] accesses = accessService.findAll();
        Access access;
        boolean found = false;
        String login = "";
        String password = "";

        try {
            LOGGER_INFO.info("Verification started.");
            System.out.print("Enter login: ");
            login = reader.readLine();
            System.out.print("Enter admin password: ");
            password = reader.readLine();
            if (login.equals(adminService.getAdmin().getAdminLogin()) && password.equals(adminService.getAdmin().getAdminPassword()))
                AdminController.runAsAdmin();
            else if (accessService.sizeOf() > 0) {
                for (int i = 0; i < accessService.sizeOf(); i++) {
                    if (accesses[i].getLogin().equals(login) && accesses[i].getPassword().equals(password)) {
                        access = accesses[i];
                        found = true;
                        LOGGER_INFO.info("Verification successful. Logged in as: " + access.getLogin());
                        MainMenuController.runMainMenu(access);
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Invalid Password or Login.");
                    LOGGER_WARN.warn("Failed log in operation. No such combination of Login/Password found.");
                }
            }
        } catch (IOException e) {
            LOGGER_ERROR.error("Verification failed.");
            e.printStackTrace();
        }
    }

    public static void createAccess() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Access access = new Access();
        Access[] accesses = accessService.findAll();
        boolean notVacantLogin = false;
        String login = "";
        String password = "";

        LOGGER_INFO.info("Starting creating access process.");
        try {
            System.out.print("Enter login: ");
            login = reader.readLine();
            System.out.print("Enter password: ");
            password = reader.readLine();
            access.setPassword(password);
            LOGGER_INFO.info("Checking if login is in use.");
            if (login.equals(adminService.getAdmin().getAdminLogin()) && password.equals(adminService.getAdmin().getAdminPassword())) notVacantLogin = true;
            for (int i = 0; i < accessService.sizeOf(); i++) {
                if (accesses[i].getLogin().equals(login)) notVacantLogin = true;
            }
            if (notVacantLogin) {
                LOGGER_WARN.warn("Login is already in use.");
                System.out.println("Login is already in use.");
            }
            else if ((!login.isEmpty() && !login.isBlank()) && (!password.isEmpty() && !password.isBlank())) {
                access.setLogin(login);
                access.setPassword(password);
                accessService.create(access);
                ClientController.registration(access);
            } else System.out.println("Fields are empty.");
        } catch (IOException e) {
            LOGGER_ERROR.error("Creating access failed.");
            e.printStackTrace();
        }
    }
}