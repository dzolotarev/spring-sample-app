package ru.dzmakats;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.dzmakats.config.AppConfig;
import ru.dzmakats.entity.Customer;
import ru.dzmakats.service.CustomerService;

/**
 * Created by Denis Zolotarev on 06.02.2024
 */
public class App {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        try (context) {
            long id = 1L;
            CustomerService customerService = context.getBean(CustomerService.class);
            Customer customer = customerService.get(id);
            System.out.println("Show customer with id = " + id + ":");
            System.out.println(customerService.get(id));

            //save old password
            String oldPassword = customer.getPassword();

            //set new password
            customer.setPassword("newPassword");

            // update customer in repo
            customerService.update(customer);

            System.out.println("Show updated customer id = " + id + ":");
            System.out.println(customerService.get(id));

            // restore old password and save in repo
            customer.setPassword(oldPassword);
            customerService.update(customer);
        }
    }
}
