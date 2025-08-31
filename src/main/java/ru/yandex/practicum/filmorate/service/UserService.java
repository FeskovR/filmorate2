package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;
import ru.yandex.practicum.filmorate.validation.Validator;

import java.util.Collection;

@Service
@Slf4j
public class UserService {
    private final UserStorage userStorage;
    private int id = 1;
    private final Validator validator = new Validator();

    @Autowired
    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public User addUser(User user) {
        log.info("Добавления нового пользователя с именем {}", user.getName());
        validator.userValidation(user);
        user.setId(id++);
        return userStorage.addUser(user);
    }

    public User updateUser(User user) {
        validator.userValidation(user);
        return userStorage.updateUser(user);
    }

    public Collection<User> findAllUsers() {
        log.info("Получение списка всех пользователей");
        return userStorage.findAllUsers();
    }

    public User findUserById(int id) {
        log.info("Получение пользоватебя с id: {}", id);
        return userStorage.findUserById(id);
    }
}
