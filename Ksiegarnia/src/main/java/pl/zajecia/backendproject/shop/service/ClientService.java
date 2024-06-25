package pl.zajecia.backendproject.shop.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajecia.backendproject.shop.exception.InvalidCredentialsException;
import pl.zajecia.backendproject.shop.exception.UserAlreadyExistsException;
import pl.zajecia.backendproject.shop.exception.UserNotFoundException;
import pl.zajecia.backendproject.shop.exception.UserWithIdDoesNotExistsException;
import pl.zajecia.backendproject.shop.model.Client;
import pl.zajecia.backendproject.shop.model.Product;
import pl.zajecia.backendproject.shop.model.command.ClientCommand;
import pl.zajecia.backendproject.shop.model.command.LoginCommand;
import pl.zajecia.backendproject.shop.repository.ClientRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;

    @PostConstruct
    public void init() {
        repository.saveAndFlush(new Client("Seba", "seba@gmail.com", "Czerniak", LocalDate.now(), "seba", "seba", "Warszawa", "123456789"));
        repository.saveAndFlush(new Client("Majki", "majki@gmail.com", "Czarnota", LocalDate.now(), "majki", "majki", "Warszawa", "123456789"));
        repository.saveAndFlush(new Client("Gawciu", "gawciu@gmail.com", "Gawlik", LocalDate.now(), "gawciu", "gawciu", "Warszawa", "123456789"));
    }

    public void addClient(ClientCommand command) {
        if (repository.existsByEmail(command.getEmail())) {
            throw new UserAlreadyExistsException("User with this email already exists.", command.getEmail());
        }
        Client client = new Client(command.getName(), command.getEmail(), command.getLastName(), command.getBirthDate(), command.getUserName(), command.getPassword(), command.getAdress(), command.getPhoneNumber());
        repository.saveAndFlush(client);

    }

    public Client login(LoginCommand command) {
        Client client = repository.findByEmail(command.getEmail()).orElseThrow(() -> new UserNotFoundException("User with that email doesnt exist", command.getEmail()));
        if (!client.getPassword().equals(command.getPassword())) {
            throw new InvalidCredentialsException("Bad password");
        }

        return client;
    }

    public void deleteClient(ClientCommand command) {
        Client client = repository.findByEmail(command.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User with this email does not exist.", command.getEmail()));
        repository.delete(client);
    }

    public void updateClient(ClientCommand command) {
        Client client = repository.findByEmail(command.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User with this email does not exist.", command.getEmail()));
        client.setName(command.getName());
        client.setLastName(command.getLastName());
        client.setBirthDate(command.getBirthDate());
        client.setUserName(command.getUserName());
        client.setPassword(command.getPassword());
        client.setAdress(command.getAdress());
        client.setPhoneNumber(command.getPhoneNumber());
        repository.saveAndFlush(client);

    }

    @Transactional(readOnly = true)
    public Client findById(Long id) {
        return repository.findByIdAndFetchOrders(id).orElseThrow(() -> new UserWithIdDoesNotExistsException("User with that id doesnt exist", id));
    }

    @Transactional(readOnly = true)
    public Client findByIdLastOrder(Long id) {
        return repository.findByIdAndFetchLastOrder(id).orElseThrow(() -> new UserWithIdDoesNotExistsException("User with that id doesnt exist", id));
    }

}
