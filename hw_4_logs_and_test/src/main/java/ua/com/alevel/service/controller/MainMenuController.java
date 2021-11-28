package ua.com.alevel.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.service.entity.Access;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainMenuController {
    public static final AccessServiceImpl accessService = new AccessServiceImpl();
    public static final ClientServiceImpl clientService = new ClientServiceImpl();

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    public static void runMainMenu(Access access) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String menu = "";

        LOGGER_INFO.info("Entering MainMenuController.");
        do {
            if (accessService.findById(access.getId()) == null) break;
            System.out.println();
            System.out.println("1. Orders.");
            System.out.println("2. Update credentials.");
            System.out.println("3. Update info.");
            System.out.println("4. Delete client.");
            System.out.println("5. Log out.");
            System.out.print("Your choice: ");
            try {
                menu = reader.readLine();
                System.out.println();
            } catch (IOException e) {
                e.printStackTrace();
            }
            switch (menu) {
                case "1": {
                    OrderController.orderMenu(access);
                    menu = "";
                    break;
                }
                case "2": {
                    AdminController.updateCredentials(access);
                    menu = "";
                    break;
                }
                case "3": {
                    AdminController.updateClient(access);
                    menu = "";
                    break;
                }
                case "4": {
                    try {
                        System.out.print("Are you sure you want to delete your account? y/n: ");
                        String decision = reader.readLine();
                        if (decision.equals("y")) {
                            LOGGER_WARN.warn("Deleting client: " + clientService.findById(access.getId()));
                            LOGGER_WARN.warn("Deleting access: " + access);
                            accessService.delete(access.getId());
                        }
                    } catch (IOException e) {
                        LOGGER_ERROR.error("Deleting client failed.");
                        e.printStackTrace();
                    }
                    menu = "";
                    break;
                }
                case "5": {
                    LOGGER_INFO.info("Returning to Log In menu.");
                    LogInController.runLogIn();
                    menu = "";
                    break;
                }
            }
        } while (true);
    }
}