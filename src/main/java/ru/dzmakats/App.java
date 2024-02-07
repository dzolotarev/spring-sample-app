package ru.dzmakats;

import ru.dzmakats.entity.Customer;
import ru.dzmakats.repository.ConnectionPool;
import ru.dzmakats.repository.impl.CustomerRepo;
import ru.dzmakats.service.CustomerService;

/**
 * Created by Denis Zolotarev on 06.02.2024
 */
public class App {
    public static void main(String[] args) {

        CustomerService customerService = new CustomerService();
        ConnectionPool pool = new ConnectionPool();
        CustomerRepo customerRepo = new CustomerRepo(pool);
        customerService.setCustomerRepo(customerRepo);

        long id = 1L;
        Customer customer = customerService.get(id);
        System.out.println("Show customer with id = " + id + ":");
        System.out.println(customer);

        //save old password
        String oldPassword = customer.getPassword();

        //set new password
        customer.setPassword("newPassword");

        // update customer in repo
        customerService.update(customer);

        customer = customerService.get(1L);
        System.out.println("Show updated customer id = " + id + ":");
        System.out.println(customer);

        // restore old password and save in repo
        customer.setPassword(oldPassword);
        customerService.update(customer);
    }
}
