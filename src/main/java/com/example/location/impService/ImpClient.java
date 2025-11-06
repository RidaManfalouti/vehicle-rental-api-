package com.example.location.impService;

import com.example.location.entity.Client;
import com.example.location.repository.ClientRepository;
import com.example.location.service.ClientService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImpClient implements ClientService {

   private final ClientRepository clientRepository;


    @Override
    public List<Client> getAllClients() throws Exception {
        if (clientRepository.findAll().isEmpty()) {
            throw new Exception("pas des clients existant");
        }
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(Long id)  {
        if (clientRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Client with id " + id + " not found");
        }
        return clientRepository.findById(id).get();
    }


    @Override
    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Long id, Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(Long id) {
        if (clientRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Client with id " + id + " not found");
        }
        clientRepository.deleteById(id);

    }
}
