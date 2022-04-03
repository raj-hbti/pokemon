package com.myapp.pokemon.translator;

import org.springframework.stereotype.Component;

@Component
public interface ITranslationService {
    String translate(String text, String type);
}
