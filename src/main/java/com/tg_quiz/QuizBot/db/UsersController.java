package com.tg_quiz.QuizBot.db;

import com.tg_quiz.QuizBot.common.UserState;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsersController {
    //FIXME ошибка созданя бина, и я совсем не знаю что с этим делать((
    //Пишет, что нужен бин DbRepository, но это же интерфейс((
    private final DbRepository repository;

    public void saveAllUsers(List<UserState> users){
        repository.saveAll(users);
    }
}
