package com.tg_quiz.QuizBot.command;

import com.tg_quiz.QuizBot.command.all_commands.RestartCommand;
import com.tg_quiz.QuizBot.command.all_commands.StartCommand;
import com.tg_quiz.QuizBot.command.all_commands.StartQuizCommand;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.tg_quiz.QuizBot.constants.Constants.Commands.*;

@Service
@Data
public class CommandFactory {
    private static Map<String, DefaultCommand<? extends BotApiMethodMessage>> commands = new HashMap<>();

    public CommandFactory(StartCommand startCommand, StartQuizCommand startQuizCommand, RestartCommand restartCommand) {
        commands.put(START, startCommand);
        commands.put(GET_STARTED, startQuizCommand);
        commands.put(RESTART_QUIZ, restartCommand);
    }

    public DefaultCommand<? extends BotApiMethodMessage> getCommand(String commandName) {
        return Optional.ofNullable(commands.get(commandName))
                .orElseThrow(() -> new IllegalArgumentException("Поступила неизвестная команда " + commandName));
    }
}
