package com.myapp.pokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PokemonController {
    @Autowired()
    PokemonService pokemonService;

    @GetMapping(value = "/pokemon/translated/{name}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Pokemon getTranslatedPokemonByName(@PathVariable String name)
    {
        return pokemonService.getTranslatedPokemonByName(name);
    }

    @GetMapping(value = "/pokemon/{name}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Pokemon getPokemonByName(@PathVariable String name)
    {
        return pokemonService.getPokemonByName(name);
    }

}
