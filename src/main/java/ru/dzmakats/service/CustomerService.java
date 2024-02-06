package ru.dzmakats.service;

import ru.dzmakats.entity.Customer;
import ru.dzmakats.repository.Repo;

/**
 * Created by Denis Zolotarev on 06.02.2024
 */
public class CustomerService {

    private Repo<Customer> customerRepo;

    public void setCustomerRepo(Repo<Customer> customerRepo) {
        this.customerRepo = customerRepo;
    }

    public Customer get(Long id) {
        return customerRepo.getById(id);
    }

    public void update(Customer customer) {
        customerRepo.update(customer);
    }
}
