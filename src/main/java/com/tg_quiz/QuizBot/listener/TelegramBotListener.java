package com.tg_quiz.QuizBot.listener;

import com.tg_quiz.QuizBot.command.CommandFactory;
import com.tg_quiz.QuizBot.common.UserState;
import com.tg_quiz.QuizBot.config.Config;
import com.tg_quiz.QuizBot.mapper.ContextMapper;
import com.tg_quiz.QuizBot.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class TelegramBotListener extends TelegramLongPollingBot {

    private final Config config;
    private final CommandFactory commandFactory;
    private final ContextMapper mapper;
    private final Map<Long, UserState> userStates;
    private final MessageService messageService;

    public TelegramBotListener(Config config, CommandFactory commandFactory, ContextMapper mapper, MessageService messageService) {
        super(config.getBotToken());
        this.config = config;
        this.commandFactory = commandFactory;
        this.mapper = mapper;
        this.messageService = messageService;
        this.userStates = new HashMap<>();
    }

    @Override
    public void onUpdateReceived(Update update) { //TODO обработка кнопок, нажатых пользователем
        if (update.hasMessage() && update.getMessage().hasText()) {
            UserState user = findUser(update.getMessage().getChatId());

            final var context = mapper.mapContext(update);
            try {
                if (context.getCommand() == null){
                    this.execute(messageService.createMessage(context, user));
                }
                this.execute(commandFactory.getCommand(context.getCommand()).executeCommand(context, user));
            } catch (Exception e) {
                log.error("Произошла ошибка {}", e.getMessage());
            }
        }
    }

    private UserState findUser(long chatId){
        if (!userStates.containsKey(chatId)) {
            UserState userState = new UserState(chatId);
            userStates.put(chatId, userState);
        }
        return userStates.get(chatId);

    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }
}
