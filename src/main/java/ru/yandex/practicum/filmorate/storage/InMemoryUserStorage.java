package ru.yandex.practicum.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.EntityNotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validation.Validator;

import java.util.Collection;
import java.util.HashMap;

@Component
@Slf4j
public class InMemoryUserStorage implements UserStorage {

    private HashMap<Integer, User> users = new HashMap<>();
    private final Validator validator = new Validator();

    public User addUser(User user) {
        users.put(user.getId(), user);
        return user;
    }

    public User updateUser(User user) {
        if (users.containsKey(user.getId())) {
            log.info("Пользователь найден. Обновляем данные");
            users.put(user.getId(), user);
            return user;
        }
        throw new ValidationException("Пользователь не найден");
    }

    public Collection<User> findAllUsers() {
        return users.values();
    }

    public User findUserById(int id) {
        checkUser(id);
        return users.get(id);
    }

    private void checkUser(int id) {
        if (!users.containsKey(id)) throw new EntityNotFoundException("Пользователь не найден");
    }
}
