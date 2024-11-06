package ua.maestr0.service;

import ua.maestr0.dao.ClientDAO;
import ua.maestr0.model.Client;

public class ClientService {
    private final ClientDAO clientDAO;

    public ClientService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public ClientService() {
        this(new ClientDAO());
    }

    public Client getClient(Long id) {
        return clientDAO.get(id);
    }

    public void saveClient(Client client) {
        clientDAO.save(client);
    }

    public void updateClient(Client client) {
        clientDAO.update(client);
    }

    public void deleteClient(Long id) {
        clientDAO.delete(id);
    }
}
