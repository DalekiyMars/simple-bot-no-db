package com.tg_quiz.QuizBot.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileHandler {
    private final Gson gson;

    public JsonObject readJsonFromFile(String path) {
        JsonObject jsonObject = null;

        try (FileReader reader = new FileReader(path)) {
            jsonObject = gson.fromJson(reader, JsonObject.class);
        } catch (IOException | NullPointerException e) {
            log.info("Не удалось прочитать файл. Ошибка = {}", e.getMessage());
        }

        return jsonObject;
    }
}
