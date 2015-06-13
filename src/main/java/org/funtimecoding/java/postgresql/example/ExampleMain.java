package org.funtimecoding.java.postgresql.example;

import java.sql.*;

public class ExampleMain {
    public static void main(String[] args) {
        ExampleMain main = new ExampleMain();

        try {
            main.run();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void run() throws SQLException, ClassNotFoundException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/example?user=example&password=example");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM \"user\"");
        System.out.println("Printing schema for table: " + resultSet.getMetaData().getTableName(1));
        int columnCount = resultSet.getMetaData().getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            System.out.println(i + " " + resultSet.getMetaData().getColumnName(i));
        }

        System.out.println("Searching for user.");
        boolean exampleUserFound = false;

        while (resultSet.next()) {
            String username = resultSet.getString("name");

            if (username.equals("shiin")) {
                Timestamp createdAt = resultSet.getTimestamp("created_at");
                Timestamp updatedAt = resultSet.getTimestamp("updated_at");
                System.out.println("Example user found.");
                System.out.println("Name: " + username);
                System.out.println("Created at: " + createdAt);
                System.out.println("Updated at: " + updatedAt);
                exampleUserFound = true;

                break;
            }
        }

        if (!exampleUserFound) {
            System.out.println("Example user not found. Inserting a new user.");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO \"user\" (name, password, created_at, updated_at) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, "shiin");
            preparedStatement.setString(2, "insecure");
            Timestamp now = new Timestamp(new java.util.Date().getTime());
            preparedStatement.setTimestamp(3, now);
            preparedStatement.setTimestamp(4, now);
            preparedStatement.executeUpdate();
        }
    }
}
