package com.tg_quiz.QuizBot.config;

import com.tg_quiz.QuizBot.listener.TelegramBotListener;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

import static com.tg_quiz.QuizBot.constants.Constants.Commands.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class MenuInitializer {
    private final TelegramBotListener botListener;

    @PostConstruct
    public void initializeCommandsMenu() {
        final List<BotCommand> commands = new ArrayList<>();
        commands.add(new BotCommand(START, START_DESCRIPTION));
        commands.add(new BotCommand(GET_STARTED, GET_STARTED_DESCRIPTION));

        try {
            botListener.execute(new SetMyCommands(commands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
}
