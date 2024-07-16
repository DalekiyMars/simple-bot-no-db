package com.tg_quiz.QuizBot.constants;


public class Constants {

    public static class Commands {
        public static final String START = "/start";
        public static final String GET_STARTED = "/ready";
        public static final String START_DESCRIPTION = "Начало диалога";
        public static final String GET_STARTED_DESCRIPTION = "Начало опроса";
        public static final String RESTART_QUIZ = "/restart";
        public static final String RESTART_QUIZ_DESCRIPTION = "Пройти опрос заново";

    }

    public static class Paths {
        public static final String QUESTIONS_PATH = "src/main/resources/json/questions.json";
        public static final String QUESTIONS = "Quests";

    }

    public static class Tests{
        public static final String BASE_BOT_URI = "https://api.telegram.org/bot7370127154:AAFwISqf9Z08VV1UGBuc8eCHrmdqzoQPsXs";
    }

}
