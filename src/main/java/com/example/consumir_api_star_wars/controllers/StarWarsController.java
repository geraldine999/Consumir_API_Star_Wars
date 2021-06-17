package com.example.consumir_api_star_wars.controllers;

import com.example.consumir_api_star_wars.Personaje;
import com.example.consumir_api_star_wars.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
public class StarWarsController {

    private final PersonajeService personajeService;

    @Autowired
    public StarWarsController(PersonajeService personajeService) {
        this.personajeService = personajeService;
    }

    @GetMapping(value="/personaje/{id}/guardar", produces = "application/json")
    public Personaje leerPersonajeAPIYGuardarlo(@PathVariable Integer id){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity <Personaje> personajeRE = restTemplate.getForEntity("https://swapi.dev/api/people/"+id+"/", Personaje.class);
        Personaje personaje = personajeRE.getBody();
        return personajeService.guardar(personaje);
    }


    @GetMapping(value="/personaje/{id}", produces = "application/json")
    public Personaje getPersonajeLocalODeApi(@PathVariable Integer id){
        Optional<Personaje> personaje = personajeService.findById(id);
        return personaje.orElse(
                leerPersonajeAPIYGuardarlo(id)
        );

    }


    @GetMapping(value="/personajeAPI/{id}/nombre", produces = "application/json")
    public String getPersonajeName(@PathVariable Integer id){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Personaje> personaje = restTemplate.getForEntity("https://swapi.dev/api/people/"+id+"/", Personaje.class);
        return  personaje.getBody().getName();

    }
}
