package com.iset.projetIntegration.CRUD.Repositories;

import com.iset.projetIntegration.CRUD.Models.Client;
import logindto.LoginDTO;
import logindto.LoginMesage;

public interface ClientSer {
    String addClientAccount(Client client);
    LoginMesage loginClient(LoginDTO loginDTO);
}
