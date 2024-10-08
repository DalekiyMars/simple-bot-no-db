package com.tg_quiz.QuizBot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource("application.properties")
public class Config {

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;
}
