package com.myapp.pokemon;

import org.springframework.stereotype.Component;

@Component
public interface IPokemonService {
    Pokemon getPokemonByName(String name);
    Pokemon getTranslatedPokemonByName(String name);
}
