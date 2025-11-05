package com.example.location.service;

import com.example.location.entity.Client;

import java.util.List;

public interface ClientService {
    List<Client> getAllClients()throws Exception ;
    Client getClientById(Long id);
    Client addClient(Client client);
    Client updateClient(Long id, Client client);
    void deleteClient(Long id);


}
