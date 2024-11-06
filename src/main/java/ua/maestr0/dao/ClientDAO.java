package ua.maestr0.dao;


import ua.maestr0.model.Client;

public class ClientDAO extends GenericDAO<Client, Long> {
    public ClientDAO() {
        super(Client.class);
    }
}
