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
        System.out.println(customer);

    }
}
