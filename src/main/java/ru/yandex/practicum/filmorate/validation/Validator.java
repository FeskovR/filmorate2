package ru.yandex.practicum.filmorate.validation;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

public class Validator {

    private final LocalDate filmBD = LocalDate.of(1895, 12, 28);

    public void userValidation(User user) {
        if (user.getEmail().isBlank()) {
            throw new ValidationException("Ошибка в поле E-Mail. Поле не должно быть пустым");
        }

        if (!user.getEmail().contains("@")) {
            throw new ValidationException("Неверный формат E-Mail. Нет символа '@'");
        }

        if (user.getLogin().isEmpty() ||
            user.getLogin().isBlank()) {
            throw new ValidationException("Логин не может быть пустым");
        }

        if (user.getLogin().contains(" ")) {
            throw new ValidationException("В логине не должно быть пробелов");
        }

        if (user.getName() == null) {
            user.setName(user.getLogin());
        }

        if (user.getBirthday().isAfter(LocalDate.now())) {
            throw new ValidationException("Дата рождения не может быть в будущем");
        }
    }

    public void filmValidation(Film film) {
        if (film.getName().isEmpty() ||
            film.getName().isBlank()) {
            throw new ValidationException("Название фильма не может быть пустым");
        }

        if (film.getDescription().length() > 200) {
            throw new ValidationException("Описание фильма не может быть больше 200 символов");
        }

        if (film.getReleaseDate().isBefore(filmBD)) {
            throw new ValidationException("Фильм не может быть выпущен до рождения кино");
        }

        if (film.getDuration() <= 0) {
            throw new ValidationException("Продолжительность фильма не может быть отрицательной");
        }
    }
}
