package com.tg_quiz.QuizBot.mapper;

import com.tg_quiz.QuizBot.common.Context;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;
import java.util.Optional;

@Component
public class ContextMapper {
    public Context mapContext(Update update) {
        final Context context = new Context(update);
        context.setCommand(getCommandFromUpdate(update.getMessage().getText()).orElse(null));
        return context;
    }

    private Optional<String> getCommandFromUpdate(String text) {
        return Arrays.stream(text.split(" "))
                .findFirst();
    }
}
