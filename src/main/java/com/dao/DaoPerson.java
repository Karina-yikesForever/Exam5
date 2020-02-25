package com.dao;

import com.model.Person;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@XmlRootElement

public class DaoPerson {
    private static final String url = "jdbc:postgresql://localhost:5432/";
    private static final String user = "postgres";
    private static final String password = "55555555k";


    public static Connection connect() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static Person getName(Person person) {
        return person;
    }

    public Person getAllUsers() {
        String Sql = "select * from person";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(Sql);) {
            Person person = new Person();
            person.setName(rs.getString("name"));
            person.setGender(rs.getString("gender"));
            person.setBirthDay(rs.getString("birthDay"));
            return person;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Person getPersonById(int id) {
        String Sql = "select * from person where id=? ";
        try (Connection conn = connect();
             PreparedStatement statement = conn.prepareStatement(Sql);) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Person person = new Person();
                    person.setName(rs.getString("name"));
                    person.setGender(rs.getString("gender"));
                    person.setBirthDay(rs.getString("birthDay"));
                    return person;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Person getPersonByBirthDay(String birthDay) {
        String Sql = "select * from person where birthday=? ";
        try (Connection conn = connect();
             PreparedStatement statement = conn.prepareStatement(Sql);) {
            statement.setInt(1, Integer.parseInt(birthDay));
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Person person = new Person();
                    person.setBirthDay(rs.getString("birthDay"));
                    return person;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (birthDay < 2000) {
            return null;
        }
    }
}

