package com.myapp.pokemon.translator;

import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class TranslationService implements ITranslationService
{
    public final Logger log =  LoggerFactory.getLogger(TranslationService.class);
    static final String SERVICE_URL = "https://api.funtranslations.com/translate/";

    @Autowired
    RestTemplate restTemplate;

    @Override
    public String translate(String text, String type)
    {
        ResponseEntity<TranslationResponse> responseEntity = null;
        String translatedText = text;
        try
        {
            HttpHeaders headers = new HttpHeaders();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("text", text);
            HttpEntity<JSONObject> request = new HttpEntity<>(jsonObject, headers);
            responseEntity = restTemplate.postForEntity(SERVICE_URL+type, request, TranslationResponse.class);

            if(type.equals(responseEntity.getBody().contents.get("translation")))
            {
                translatedText = responseEntity.getBody().contents.get("translated");
            }
        }
        catch (Exception e)
        {
            log.warn("Failed to translate text : " + text, e);
        }
        return translatedText;
    }
}
