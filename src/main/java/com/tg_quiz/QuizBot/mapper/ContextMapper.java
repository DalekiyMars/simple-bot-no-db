package com.tg_quiz.QuizBot.mapper;

import com.tg_quiz.QuizBot.common.Context;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;
import java.util.Optional;

@Component
public class ContextMapper {
    /**
     * <p>Сплитит сообщение пользователя и пытается установить первое слово как команду для выполнения, иначе ставит команду null</p>*/
    public Context mapContext(Update update) {
        final Context context = new Context(update);
        context.setCommand(getCommandFromUpdate(update.getMessage().getText()).orElse(null));
        return context;
    }

    /**
     * <p>Разбивает строку на подстроки по пробелу и возвращает первую</p>*/
    private Optional<String> getCommandFromUpdate(String text) {
        return Arrays.stream(text.split(" "))
                .findFirst();
    }
}
