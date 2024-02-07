package ru.dzmakats.service;

import org.springframework.stereotype.Service;
import ru.dzmakats.entity.Customer;
import ru.dzmakats.repository.Repo;

/**
 * Created by Denis Zolotarev on 06.02.2024
 */

@Service
public class CustomerService {

    private final Repo<Customer> customerRepo;

    public CustomerService(Repo<Customer> customerRepo) {
        this.customerRepo = customerRepo;
    }

    public Customer get(Long id) {
        return customerRepo.getById(id);
    }

    public void update(Customer customer) {
        customerRepo.update(customer);
    }
}
