package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validation.Validator;

import java.util.Collection;
import java.util.HashMap;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    private HashMap<Integer, User> users = new HashMap<>();
    private int id = 1;
    private final Validator validator = new Validator();

    /**
     * Создание пользователя
     */
    @PostMapping
    public User addUser(@RequestBody User user) {
        log.info("Добавления нового пользователя с именем {}", user.getName());
        validator.userValidation(user);
        user.setId(id++);
        users.put(user.getId(), user);
        return user;
    }

    /**
     * Обновление пользователя
     */
    @PutMapping
    public User updateUser(@RequestBody User user) {
        if (users.containsKey(user.getId())) {
            log.info("Пользователь найден. Обновляем данные");
            validator.userValidation(user);
            users.put(user.getId(), user);
            return user;
        }
        throw new ValidationException("Пользователь не найден");
    }

    /**
     * Получение списка всех пользователей
     */
    @GetMapping
    public Collection<User> findAllUsers() {
        log.info("Получение списка всех пользователей");
        return users.values();
    }
}
