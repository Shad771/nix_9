package ua.com.alevel.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ua.com.alevel.service.entity.Admin;

public class FirstRunController {

    private FirstRunController() { }

    private static final AdminServiceImpl adminService = new AdminServiceImpl();
    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    public static void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Admin admin = new Admin();

        System.out.println();

        do {
            try {
                LOGGER_INFO.info("First Run page.");
                System.out.print("Enter admin login: ");
                String adminLogin = reader.readLine();
                if (!adminLogin.isEmpty() && !adminLogin.isBlank()) admin.setAdminLogin(adminLogin);
                else continue;
                System.out.print("Enter admin password: ");
                String adminPassword = reader.readLine();
                if (!adminPassword.isEmpty() && !adminPassword.isBlank()) admin.setAdminPassword(adminPassword);
                else continue;
                System.out.println();
                LOGGER_WARN.warn("Admin access creating started");
                adminService.create(admin);
                LOGGER_WARN.warn("Admin access creating finished. ");
                LOGGER_WARN.warn("Admin login:  " + admin.getAdminLogin() + "; admin password: " + admin.getAdminPassword());
                break;
            } catch (IOException e) {
                LOGGER_ERROR.error("Admin access creating failed.");
                e.printStackTrace();
            }
        } while (true);

        LogInController.runLogIn();
    }
}