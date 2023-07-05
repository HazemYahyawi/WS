package com.iset.projetIntegration.CRUD.Repositories;

import com.iset.projetIntegration.CRUD.Models.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepo extends CrudRepository<Client, Long> {
    Optional<Client> findOneByEmailAndPassword(String email, String pwd);
    Client findByEmail(String email);
}
