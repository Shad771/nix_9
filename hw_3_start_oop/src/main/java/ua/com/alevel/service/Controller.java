package ua.com.alevel.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {

    private final Service service = new Service();

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("select your option");
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                if (position.equals("0")) {
                    System.exit(0);
                }
                crud(position, reader);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void runNavigation() {
        System.out.println();
        System.out.println("Create user, please enter 1");
        System.out.println("Update user, please enter 2");
        System.out.println("Delete user, please enter 3");
        System.out.println("Find by Id user, please enter 4");
        System.out.println("Find all user, please enter 5");
        System.out.println("Exit, please enter 0");
        System.out.println();
    }

    private void crud(String position, BufferedReader reader) {
        switch (position) {
            case "1" -> create(reader);
            case "2" -> update(reader);
            case "3" -> delete(reader);
            case "4" -> findById(reader);
            case "5" -> findAll(reader);
        }
        runNavigation();
    }

    private void create(BufferedReader reader) {
        System.out.println("Create");
        try {
            System.out.println("Please, enter your size");
            String sizeString = reader.readLine();
            int size = Integer.parseInt(sizeString);

            User user = new User();
            user.setSize(size);

            service.create(user);
        } catch (NumberFormatException | IOException e) {
            System.out.println("Error: = " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        System.out.println("Update");
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();

            System.out.println("Please, enter your size");
            String sizeString = reader.readLine();
            int size = Integer.parseInt(sizeString);

            User user = new User();
            user.setId(id);
            user.setSize(size);

            service.update(user);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error: = " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        System.out.println("Delete");
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();

            boolean deleteResult = service.delete(id);
            if (!deleteResult) {
                System.out.println("User not found");
            }
        } catch (IOException e) {
            System.out.println("Error: = " + e.getMessage());
        }
    }

    private void findById(BufferedReader reader) {
        System.out.println("Find by Id");
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();

            User user = service.findById(id);

            String message = user != null ? ("user = " + user) : "user not found ";
            System.out.println(message);
        } catch (IOException e) {
            System.out.println("Error: = " + e.getMessage());
        }
    }

    private void findAll(BufferedReader reader) {
        System.out.println("Find all");
        User[] users = service.findAll();
        if (users != null && users.length != 0) {
            for (User user : users) {
                System.out.println("user = " + user);
            }
        } else {
            System.out.println("users empty");
        }
    }
}
