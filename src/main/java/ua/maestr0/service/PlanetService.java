package ua.maestr0.service;

import ua.maestr0.dao.PlanetDAO;
import ua.maestr0.model.Planet;

public class PlanetService {
    private final PlanetDAO planetDAO;

    public PlanetService(PlanetDAO planetDAO) {
        this.planetDAO = planetDAO;
    }

    public PlanetService() {
        this(new PlanetDAO());
    }

    public Planet getPlanet(String id) {
        return planetDAO.get(id);
    }

    public void savePlanet(Planet planet) {
        planetDAO.save(planet);
    }

    public void updatePlanet(Planet planet) {
        planetDAO.update(planet);
    }

    public void deletePlanet(String id) {
        planetDAO.delete(id);
    }
}
