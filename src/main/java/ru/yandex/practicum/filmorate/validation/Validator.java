package ru.yandex.practicum.filmorate.validation;

import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

public class Validator {

    private final LocalDate filmBD = LocalDate.of(1895, 12, 28);

    public void userValidation(User user) {

        if (user.getLogin().contains(" ")) {
            throw new ValidationException("В логине не должно быть пробелов");
        }

        if (user.getName() == null ||
            user.getName().isEmpty()) {
            user.setName(user.getLogin());
        }
    }

    public void filmValidation(Film film) {

        if (film.getReleaseDate().isBefore(filmBD)) {
            throw new ValidationException("Фильм не может быть выпущен до рождения кино");
        }
    }
}
