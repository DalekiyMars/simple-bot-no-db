package com.tg_quiz.QuizBot.common;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Question {
    @SerializedName("question")
    private String question;

}
