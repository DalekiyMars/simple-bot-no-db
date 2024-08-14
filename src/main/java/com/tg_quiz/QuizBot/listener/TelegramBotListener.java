package com.tg_quiz.QuizBot.listener;

import com.tg_quiz.QuizBot.command.CommandFactory;
import com.tg_quiz.QuizBot.command.all_commands.SendUserStateToMyTgCommand;
import com.tg_quiz.QuizBot.common.UserState;
import com.tg_quiz.QuizBot.config.Config;
import com.tg_quiz.QuizBot.constants.Constants;
import com.tg_quiz.QuizBot.mapper.ContextMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class TelegramBotListener extends TelegramLongPollingBot {
    private final SendUserStateToMyTgCommand userStateToMyTgCommand;

    private final Config config;
    private final CommandFactory commandFactory;
    private final ContextMapper mapper;

    @Getter
    private final Map<Long, UserState> userStates;

    public TelegramBotListener(SendUserStateToMyTgCommand userStateToMyTgCommand, Config config, CommandFactory commandFactory, ContextMapper mapper) {
        super(config.getBotToken());
        this.userStateToMyTgCommand = userStateToMyTgCommand;
        this.config = config;
        this.commandFactory = commandFactory;
        this.mapper = mapper;
        this.userStates = new HashMap<>();
    }

    @Override
    public void onUpdateReceived(Update update) {
        var user = getChatId(update);
        final var context = mapper.mapContext(update);
        try {
            if (context.getCommand() == null){
                if (user.getCurrentQuestion() > Constants.Messages.ANSWERS_NUMBER){
                    userStateToMyTgCommand.executeCommand(context, user);//TODO нихуя не работает
                }
               user.setAnswerById(user.getCurrentQuestion(), getAnswerFromUser(update));
               context.setCommand(Constants.Commands.NEXT_QUESTION);
               Message message = new Message();
               message.setText(getAnswerFromUser(update));
               update.setMessage(message);
            }
            this.execute(commandFactory.getCommand(context.getCommand()).executeCommand(context, user));
        } catch (Exception e) {
                log.error("Произошла ошибка {}", e.getMessage());
        }
    }

    /**
    *<p>Ищет пользователя по chatID в хэшмапе, если его нет - добавляет его туда
     * @param chatId ID чата бота с пользователем</p>
    */
    private UserState findUser(long chatId){
        if (!userStates.containsKey(chatId)) {
            UserState userState = new UserState(chatId);
            userStates.put(chatId, userState);
        }
        return userStates.get(chatId);
    }

    private UserState getChatId(Update update){
        if (update.hasCallbackQuery()){
            return findUser(update.getCallbackQuery().getMessage().getChatId());
        }
        if (update.hasMessage() && update.getMessage().hasText()) {
            return findUser(update.getMessage().getChatId());
        }
        return new UserState();
    }

    private String getAnswerFromUser(Update update){
        if (update.hasCallbackQuery()){
            return update.getCallbackQuery().getData();
        }
        if (update.hasMessage() && update.getMessage().hasText()) {
            return update.getMessage().getText();
        }
        return null;
    }
    @Override
    public String getBotUsername() {
        return config.getBotName();
    }


}
