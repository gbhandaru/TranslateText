package com.krishna.translation_demo.controller;

import com.krishna.translation_demo.dto.Translate;
import com.krishna.translation_demo.service.TranslationService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/translateText")
public class TranslationController {

    @Autowired
    TranslationService translationService;

    @PostMapping
    public String getTranslationFromAI(@RequestBody Translate request) throws IOException, JSONException {

        return translationService.translate(request);
    }
}
