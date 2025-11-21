package com.example.location.controller;

import com.example.location.entity.Client;
import com.example.location.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/client")
@RequiredArgsConstructor

public class ClientController {
    private final ClientService clientService;

    @GetMapping()
    public List<Client> getClients() throws Exception {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getClient(@PathVariable Long id) throws Exception {
        return clientService.getClientById(id);
    }

    @PostMapping()
    public Client createClient(@RequestBody Client client) throws Exception {
        return clientService.addClient(client);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client client) throws Exception {
        return clientService.updateClient(id, client);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) throws Exception {
        clientService.deleteClient(id);
    }

}
