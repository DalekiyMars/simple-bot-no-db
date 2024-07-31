package com.tg_quiz.QuizBot.db;

import com.tg_quiz.QuizBot.common.UserState;
import com.tg_quiz.QuizBot.db.service.UserStateService;
import com.tg_quiz.QuizBot.listener.TelegramBotListener;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
@Service
@RequiredArgsConstructor
public class DataBaseListener {
    private final TelegramBotListener telegramBotListener;

    private final UserStateService usersController;

    /**
     * <p>С определенной в application.properties задержкой запускает запись всех пользователей, не допрошедших опрос, в базу данных</p>*/
    @Scheduled(fixedRateString = "${db.interval}")
    private void pullUsersToDB(){

        usersController.saveAllUsers(usersNeededWriteToDb());
    }

    /**
     * <p>Возвращает лист всех юзеров, которые полностью прошли опрос либо не отвечали больше часа</p>*/
    private List<UserState> usersNeededWriteToDb(){
        if (telegramBotListener.getUserStates().isEmpty()){
            log.info("Список пользователей пуст! Нечего проверять и записывать в бд");
            return new ArrayList<>();
        }


        return telegramBotListener.getUserStates()
                .values()
                .stream()
                .filter(x-> x.getLastActivityTime().isBefore(LocalDateTime.now().minusHours(1)) || x.getCurrentQuestion() >= 4)
                .toList();

    }
}
