package com.tg_quiz.QuizBot.db.service;

import com.tg_quiz.QuizBot.common.UserState;
import com.tg_quiz.QuizBot.db.repository.UserStateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserStateService {
    private final UserStateRepository repository;

    public void saveAllUsers(List<UserState> users) {
        repository.saveAll(users);
    }

    public UserState saveUserState(UserState user) {
        return repository.save(user);
    }

    public UserState createNewUserState(long id) {
        var userState = new UserState(id);
        return repository.save(userState);
    }

    public UserState getUserStateByChatId(long chatId) {
        var a = repository.findById(chatId);
        if (a.isPresent()) {
            return a.get();
        }
        throw new RuntimeException();
    }
}
