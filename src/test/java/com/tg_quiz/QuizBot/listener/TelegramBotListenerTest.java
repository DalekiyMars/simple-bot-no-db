package com.tg_quiz.QuizBot.listener;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.tg_quiz.QuizBot.constants.TestConstants.URLs.BASE_BOT_URI;
import static io.restassured.RestAssured.given;

public class TelegramBotListenerTest {
    @BeforeEach
    void init(){
        RestAssured.baseURI = BASE_BOT_URI;
    }

    @Test
    void successSendMessage(){
        given()
                .param("text", "rest-assured_TEST")
                .param("chat_id", "616688919")
                .when()
                .get("/sendMessage")
                .then()
                .statusCode(200);
    }

    @Test
    void unsuccessSendMessage(){
        given()
                .param("text", "rest-assured_TEST")
                .param("chat_id", "616688919")
                .param("parse_mode", "Markdown")
                .when()
                .get("/sendMessage")
                .then()
                .statusCode(400);
    }
}
