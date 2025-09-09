package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;
import ru.yandex.practicum.filmorate.validation.Validator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

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

    public User addFriend(int id, int friendId) {
        log.info("Добавление пользователю {} друга {}", id, friendId);
        return userStorage.addFriend(id, friendId);
    }

    public User deleteFriend(int id, int friendId) {
        log.info("Удаление друга {} у пользователя {}", friendId, id);
        return userStorage.deleteFriend(id, friendId);
    }

    public ArrayList<User> getAllUserFriends(int id) {
        log.info("Получение списка всех друзей пользователя {}", id);
        return userStorage.getAllUserFriends(id);
    }

    public HashSet<User> getAllCommonFriends(int id, int otherId) {
        log.info("Получение списка общих друзей пользователя {} и {}", id, otherId);
        return userStorage.getAllCommonFriends(id, otherId);
    }
}
