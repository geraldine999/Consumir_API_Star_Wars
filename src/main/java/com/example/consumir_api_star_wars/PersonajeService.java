package com.example.consumir_api_star_wars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonajeService {
    private final PersonajeRepository personajeRepository;

    @Autowired
    public PersonajeService(PersonajeRepository personajeRepository) {
        this.personajeRepository = personajeRepository;
    }

    public Personaje guardar(Personaje personaje) {
        personaje.setId(personaje.getId());
         return personajeRepository.save(personaje);
    }


    public Optional <Personaje> findById(Integer id) {
        return personajeRepository.findById(id);
    }

    public Optional<Personaje> getPersonajeByName(String nombre) {
        return personajeRepository.getByName(nombre);
    }

    public Optional<Personaje> save(Optional<Personaje> personaje) {
        return personajeRepository.save(personaje);
    }

    public List<Personaje> getPersonajes() {
        return personajeRepository.findAll();
    }
}
