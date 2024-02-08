package ru.dzmakats.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dzmakats.entity.Customer;
import ru.dzmakats.processor.Benchmark;
import ru.dzmakats.processor.Tx;
import ru.dzmakats.repository.Repo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by Denis Zolotarev on 06.02.2024
 */

@Service
@Benchmark
public class CustomerService {

    private Repo<Customer> customerRepo;

    @Autowired
    public void setCustomerRepo(Repo<Customer> customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Tx
    public Customer get(Long id) {
        return customerRepo.getById(id);
    }

    public void update(Customer customer) {
        customerRepo.update(customer);
    }

    @PostConstruct
    void init() {
        System.out.println("init CustomerService()");
    }

    @PreDestroy
    void destroy() {
        System.out.println("destroy CustomerService()");
    }
}
