package com.tg_quiz.QuizBot.util;

import com.tg_quiz.QuizBot.common.Context;
import com.tg_quiz.QuizBot.common.Question;
import com.tg_quiz.QuizBot.common.UserState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class QuestionFormat {
    public SendMessage mapMessage(Context context, Question question, UserState user) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        SendMessage sendMessage = SendMessage.builder()
                                .chatId(String.valueOf(context.getUpdate().getMessage().getChatId()))
                                .text(question.getQuestion())
                                .build();

        markupInline.setKeyboard(answersHandler(context, user, question));
        sendMessage.setReplyMarkup(markupInline);
        return sendMessage;
    }

    private List<InlineKeyboardButton> getInlineKeyboardButtons(String answer) { //TODO добавить проверку на null
        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        rowInline1.add(InlineKeyboardButton.builder().text(answer).callbackData(answer.toUpperCase()).build());
        return rowInline1;
    }
    private List<List<InlineKeyboardButton>> answersHandler(Context context, UserState user, Question question){
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        if (question.getAnswers().isEmpty()){
            switch (user.getCurrentQuestion()){
                case 0:
                    log.info("Выведено имя пользователя в кнопках");
                    rowsInline.add(getInlineKeyboardButtons(context.getUpdate().getMessage().getChat().getFirstName()));
                    break;
                case 1:
                    log.info("Выведено BIO в кнопках");
                    rowsInline.add(getInlineKeyboardButtons(context.getUpdate().getMessage().getChat().getUserName()));
                    break;
            }
        } else {
            for (var elem :
                    question.getAnswers()) {
                rowsInline.add(getInlineKeyboardButtons(elem));
            }
        }
        return rowsInline;
    }
}
