package com.wellscosta.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

    private static EntityManagerFactory emf;
    private static final Map<String, String> properties = new HashMap<>();
    
    static {
        properties.put("javax.persistence.jdbc.url", "jdbc:postgresql://" + System.getenv("DB_POSTGRES_HOST") + "/" + System.getenv("DB_POSTGRES_NAME"));
        properties.put("javax.persistence.jdbc.user", System.getenv("DB_POSTGRES_USER"));
        properties.put("javax.persistence.jdbc.password", System.getenv("DB_POSTGRES_PASSWORD"));
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            return Persistence.createEntityManagerFactory("TaskListJPA", properties);
        }
        return emf;
    }
}

