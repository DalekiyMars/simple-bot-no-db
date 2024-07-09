package com.tg_quiz.QuizBot.command.all_commands;

import com.tg_quiz.QuizBot.command.DefaultCommand;
import com.tg_quiz.QuizBot.common.Context;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageSender implements DefaultCommand<SendMessage> {
    //FIXME переделать не на хуйню
    @Override
    public SendMessage executeCommand(Context context) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(context.getUpdate().getMessage().getChatId()));
        String message = "Хохочешь?";
        sendMessage.setText(message);
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline1 = getInlineKeyboardButtons();
        rowsInline.add(rowInline1);
        markupInline.setKeyboard(rowsInline);
        sendMessage.setReplyMarkup(markupInline);
        return sendMessage;
    }

    private static List<InlineKeyboardButton> getInlineKeyboardButtons() {
        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Хехе");
        inlineKeyboardButton1.setCallbackData("ХЕХЕ");
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("Не хехе(");
        inlineKeyboardButton2.setCallbackData("НЕ ХЕХЕ");
        rowInline1.add(inlineKeyboardButton1);
        rowInline1.add(inlineKeyboardButton2);
        return rowInline1;
    }
}
