package ru.dzmakats.entity;

import lombok.Data;

/**
 * Created by Denis Zolotarev on 06.02.2024
 */

@Data
public class Customer {

    private Long id;

    private String login;

    private String password;
}
