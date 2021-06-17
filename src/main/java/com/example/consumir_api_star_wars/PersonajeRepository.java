package com.example.consumir_api_star_wars;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Integer> {

    Optional<Personaje> getByName(String nombre);
}
