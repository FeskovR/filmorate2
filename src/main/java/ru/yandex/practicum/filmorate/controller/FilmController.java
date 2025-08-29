package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.validation.Validator;

import java.util.Collection;
import java.util.HashMap;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {
    private final HashMap<Integer, Film> films = new HashMap<>();
    private int id = 1;
    private final Validator validator = new Validator();

    /**
     * Добавление фильма
     * @param film объект фильма
     * @return объект добавленного фильма
     */
    @PostMapping
    public Film addFilm(@RequestBody Film film) {
        log.info("Добавление фильма {}", film.getName());
        validator.filmValidation(film);
        film.setId(id++);
        films.put(film.getId(), film);
        return film;
    }

    /**
     * Обновление фильма
     */
    @PutMapping
    public Film updateFilm(@RequestBody Film film) {
        if (films.containsKey(film.getId())) {
            log.info("Фильм для обновления найден. Обновляется...");
            validator.filmValidation(film);
            films.put(film.getId(), film);
            return film;
        }
        throw new ValidationException("Фильм не найден");
    }

    /**
     * Получение всех фильмов
     */
    @GetMapping
    public Collection<Film> findAllFilms() {
        log.info("Возвращаем все фильмы");
        return films.values();
    }
}
