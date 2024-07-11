package com.tg_quiz.QuizBot.util;

import com.tg_quiz.QuizBot.common.Context;
import com.tg_quiz.QuizBot.common.Question;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionFormat { //FIXME кнопки не выводятся, если их больше 1
    public SendMessage mapMessage(Context context, Question question) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(context.getUpdate().getMessage().getChatId()));
        sendMessage.setText(question.getQuestion());
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline1 = getInlineKeyboardButtons(question);
        rowsInline.add(rowInline1);
        markupInline.setKeyboard(rowsInline);
        sendMessage.setReplyMarkup(markupInline);
        return sendMessage;
    }

    private List<InlineKeyboardButton> getInlineKeyboardButtons(Question question) { //TODO добавить проверку на null
        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        for (var elem :
                question.getAnswers()) {
            inlineKeyboardButton1.setText(elem);
            inlineKeyboardButton1.setCallbackData(elem);
        }
        rowInline1.add(inlineKeyboardButton1);
        return rowInline1;
    }
}
