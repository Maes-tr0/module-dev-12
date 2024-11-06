package ua.maestr0;

import ua.maestr0.model.Client;
import ua.maestr0.model.Planet;
import ua.maestr0.service.ClientService;
import ua.maestr0.service.PlanetService;
import ua.maestr0.util.database.FlywayConfig;
import ua.maestr0.util.database.HibernateUtil;

public class Application {
    public static void main(String[] args) {
        // 1. Міграції бази даних за допомогою Flyway
        FlywayConfig.migrate();

        // 2. Створення сервісів
        ClientService clientService = new ClientService();
        PlanetService planetService = new PlanetService();

        // 3. Створення та збереження нового клієнта
        Client newClient = new Client();
        newClient.setName("John Doe");
        clientService.saveClient(newClient);
        System.out.println("Клієнт збережений: " + newClient);

        // 3. Створення та збереження нової планети
        Planet newPlanet = new Planet();
        newPlanet.setId("TEST");
        newPlanet.setName("test");
        planetService.savePlanet(newPlanet);
        System.out.println("Планета збережена: " + newPlanet);

        // 4. Отримання клієнта за ID
        Client retrievedClient = clientService.getClient(2L);
        System.out.println("Отриманий клієнт: " + retrievedClient);

        // 4. Отримання планети за ID
        Planet retrievedPlanet = planetService.getPlanet("EARTH");
        System.out.println("Отримана планета: " + retrievedPlanet);

        // 5. Оновлення клієнта
        retrievedClient.setName("Jane Doe");
        clientService.updateClient(retrievedClient);
        System.out.println("Клієнт оновлений: " + retrievedClient);

        // 5. Оновлення планети
        retrievedPlanet.setName("Мати-Земля");
        planetService.updatePlanet(retrievedPlanet);
        System.out.println("Планета оновлена: " + retrievedPlanet);

        // 6. Видалення клієнта
        clientService.deleteClient(5L);
        System.out.println("Клієнт видалений.");

        // 6. Видалення планети
        planetService.deletePlanet("JUPITER");
        System.out.println("Планета видалена.");

        // 7. Закриття Hibernate SessionFactory
        HibernateUtil.getInstance().close();
    }
}
