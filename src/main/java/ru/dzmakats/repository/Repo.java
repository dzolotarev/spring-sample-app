package ru.dzmakats.repository;

/**
 * Created by Denis Zolotarev on 06.02.2024
 */
public interface Repo<E> {

    E getById(Long id);

    void update(E entity);
}
