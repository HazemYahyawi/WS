package com.iset.projetIntegration.CRUD.controller;

import com.iset.projetIntegration.CRUD.Models.Client;
import com.iset.projetIntegration.CRUD.Repositories.ClientSer;
import com.iset.projetIntegration.CRUD.Services.ClientService;
import logindto.LoginDTO;
import logindto.LoginMesage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
public class ClientController {
    //Clients Control
    @Autowired
    private ClientService serviceClient ;

    @RequestMapping("/clients")
    public List<Client> some(){

    return serviceClient.getClients();
    }
    //get client by id
    @RequestMapping("/client/{id}")
    public Client getclient(@PathVariable long id){
    return serviceClient.getclient(id);
    }
    @RequestMapping(method = RequestMethod.DELETE,value = "/client/{id}")
    public void DeleteClient(@PathVariable long id){
     serviceClient.DeleteClient(id);
}
    @RequestMapping(method = RequestMethod.POST,value = ("/clients"))
    public void AddClient(@RequestBody Client c){
        serviceClient.addClient(c);
}
    @RequestMapping(method = RequestMethod.PUT,value = "/client/{id}")
    public void updateClient(@RequestBody Client c , @PathVariable long id ){
        serviceClient.updateclient(c,id);
}

    @Autowired
    private ClientSer employeeService;
    @PostMapping(path = "/save")
    public String saveEmployee(@RequestBody Client employeeDTO)
    {
        String id = employeeService.addClientAccount(employeeDTO);
        return id;
    }
    @PostMapping(path = "/login")
    public ResponseEntity<?> loginEmployee(@RequestBody LoginDTO loginDTO)
    {
        LoginMesage loginResponse = employeeService.loginClient(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }



}
