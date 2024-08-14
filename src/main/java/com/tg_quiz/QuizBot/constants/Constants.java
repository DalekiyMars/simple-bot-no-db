package com.tg_quiz.QuizBot.constants;


public class Constants {

    public static class Commands {
        public static final String START = "/start";
        public static final String GET_STARTED = "/ready";
        public static final String START_DESCRIPTION = "Начало диалога";
        public static final String GET_STARTED_DESCRIPTION = "Начало опроса";
        public static final String RESTART_QUIZ = "/restart";
        public static final String RESTART_QUIZ_DESCRIPTION = "Пройти опрос заново";
        public static final String NEXT_QUESTION = "/next";

    }

    public static class Messages {
        public static final String NEEDED_ANSWER_FOR_PREVIOUS_QUESTION_ANSWER = "Вы уже пользовались этой командой. Ответьте на предыдущий вопрос.";
        public static final String START_MESSAGE = "Hell0, %s, we present you our new bot to accept your request about creating telegram bot!\nAre you ready?";
        public static final String QUIZ_ENDED_MESSAGE = "Вы прошли опрос. Ожидайте, с вами свяжутся. Если вы хотите перепройти вопросы, воспользуйтесь командой\n\t" + Commands.RESTART_QUIZ;
        public static final String QUIZ_RESTARTED_MESSAGE = "Вы можете перепройти опрос!\nВведите для этого команду\n\t" + Commands.GET_STARTED;
        public static final String USER_ALL_ANSWERS_MESSAGE = "Пользователь прошел опрос:\n{}";
        public static final int ANSWERS_NUMBER = 4;
    }

    public static class Paths {
        public static final String QUESTIONS_PATH = "src/main/resources/json/questions.json";
        public static final String QUESTIONS = "Quests";
    }

    public static class SpetialTelegramTags {
        public static final long PRODUCER_CHAT_ID = 616688919;
    }
}