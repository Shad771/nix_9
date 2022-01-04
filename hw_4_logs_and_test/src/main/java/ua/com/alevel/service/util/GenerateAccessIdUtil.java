package ua.com.alevel.service.util;

import ua.com.alevel.service.entity.Access;

import java.util.UUID;

public final class GenerateAccessIdUtil {

    private GenerateAccessIdUtil() {
    }

    public static String generate(Access[] accesses, int nextAccessId) {
        String id = UUID.randomUUID().toString();
        if (nextAccessId != 0) {
            for (int i = 0; i < nextAccessId; i++) {
                if (accesses[i].getId().equals(id)) {
                    return generate(accesses, nextAccessId);
                }
            }
        }
        return id;
    }
}