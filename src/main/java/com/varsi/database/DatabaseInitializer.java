package com.varsi.database;

import io.github.cdimascio.dotenv.Dotenv;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        String url = dotenv.get("DB_URL");
        String user = dotenv.get("DB_USER");
        String password = dotenv.get("DB_PASSWORD");
        String schema_path = dotenv.get("SCHEMA_PATH"); // src/main/resources/db/migration/V1__Initial_schema.sql

        if (url == null || user == null || password == null) {
            System.err.println("Missing database cedentials in .env file.");
        }

        System.out.println("Connecting to: " + url);

        try(Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = Files.readString(Path.of(schema_path));

            try (Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
                System.out.println("Schema created successfully.");
            }
        } catch (SQLException | IOException e) {
            System.err.println("Failed to apply schema.");
            e.printStackTrace();
        }
    }
}
