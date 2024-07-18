package com.tg_quiz.QuizBot.common;

import com.tg_quiz.QuizBot.listener.TelegramBotListener;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Data
@Service
@RequiredArgsConstructor
public class DataBaseListener {
    private final TelegramBotListener telegramBotListener;

    /**
     * <p>С определенной в application.properties задержкой запускает запись всех пользователей, не допрошедших опрос, в базу данных</p>*/
    @Scheduled(fixedRateString = "${bot.timer}")
    private void pullUsersToDB(){
        //FIXME прописать проверку на пустую хэшмапу
        log.info("задержка работает"); // TODO прописать перекидывание из хэшмапы ТГБотЛисенера данных в БД

        // Пример для проверки - выводит каждый раз список активных пользователей, которые еще не вынесены в БД
        //  значит можем использовать для выгрузки всех пользователей раз в определенное кол-во времени
//        if (telegramBotListener.getUserStates() != null){
//            for (var item :
//                    telegramBotListener.getUserStates().values()) {
//                log.info(item.getTelegramTag());
//            }
//        } else {
//            log.info("Задержка работает, но список пуст");
//        }

    }

    public static void enterUserDataToDB(UserState state){
        //TODO реализация записи юзера в БД
    }
}
