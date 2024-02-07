package ru.dzmakats.repository.impl;

import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;
import ru.dzmakats.entity.Customer;
import ru.dzmakats.repository.ConnectionPool;
import ru.dzmakats.repository.Repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * Created by Denis Zolotarev on 06.02.2024
 */

@Repository
public class CustomerRepo implements Repo<Customer> {

    private static final String GET_BY_ID = "SELECT id, login, password FROM users WHERE id=?";
    private static final String UPDATE = "UPDATE users SET login=?, password=? WHERE id=?";

    private final ConnectionPool pool;

    public CustomerRepo(ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    @SneakyThrows
    public Customer getById(Long id) {
        Connection connection = pool.getConnection();
        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getLong("id"));
                customer.setLogin(resultSet.getString("login"));
                customer.setPassword(resultSet.getString("password"));
                return customer;
            }
            throw new RuntimeException("Incorrect id");
        }
    }

    @Override
    @SneakyThrows
    public void update(Customer entity) {
        Connection connection = pool.getConnection();
        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setLong(3, entity.getId());
            preparedStatement.execute();
        }
    }
}
