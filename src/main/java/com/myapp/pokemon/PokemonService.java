package com.myapp.pokemon;

import com.myapp.pokemon.translator.ITranslationService;
import com.myapp.pokemon.translator.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import static com.myapp.pokemon.Constants.*;

@Component()
public class PokemonService {
    static final String SERVICE_URL = "https://pokeapi.co/api/v2/pokemon/";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    TranslationService translationService;

    Pokemon getPokemonByName(String name)
    {
        String urlTemplate = SERVICE_URL + name;

        PokemonResponse pokemonMap = restTemplate.getForObject(urlTemplate,PokemonResponse.class);

        String url = pokemonMap.getSpecies().getUrl();

        SpeciesObj speciesObj = restTemplate.getForObject(url, SpeciesObj.class);

        List<Map<String, Object>> textEntries = speciesObj.getFlavor_text_entries();

        String desc = EMPTY_STRING;

        for(Map<String, Object> textEntry : textEntries)
        {
            Map<String, String> lang = (Map<String, String>)textEntry.get(LANGUAGE);
            if(lang != null && Constants.LANG_EN.equals(lang.get(NAME)))
            {
                desc = (String)textEntry.get(FLAVOR_TEXT);
                break;
            }
        }
        Pokemon pokemon = new Pokemon();
        pokemon.setName(name);
        pokemon.setDescription(desc);
        pokemon.setHabitat(speciesObj.getHabitat().getName());
        pokemon.setLegendary(speciesObj.isIs_legendary());
        return pokemon;
    }

    Pokemon getTranslatedPokemonByName(String name)
    {
        Pokemon pokemon = this.getPokemonByName(name);
        String translationType = getTranslationType(pokemon);
        String translatedDesc = translationService.translate(pokemon.getDescription(), translationType);
        pokemon.setDescription(translatedDesc);
        return pokemon;
    }

    private String getTranslationType(Pokemon pokemon) {
        return "cave".equals(pokemon.getHabitat()) || pokemon.isLegendary() ? "yoda" : "shakespeare";
    }
}
