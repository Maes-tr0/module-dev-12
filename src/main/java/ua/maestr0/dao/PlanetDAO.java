package ua.maestr0.dao;

import ua.maestr0.model.Planet;

public class PlanetDAO extends GenericDAO<Planet, String> {
    public PlanetDAO() {
        super(Planet.class);
    }
}
