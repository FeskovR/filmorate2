package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.validation.Validator;

import java.util.Collection;

@Service
@Slf4j
public class FilmService {

    private final FilmStorage filmStorage;
    private int id = 1;
    private final Validator validator = new Validator();

    @Autowired
    public FilmService(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    public Film addFilm(Film film) {
        log.info("Добавление фильма {}", film.getName());
        validator.filmValidation(film);
        film.setId(id++);
        return filmStorage.addFilm(film);
    }

    public Film updateFilm(Film film) {
        validator.filmValidation(film);
        return filmStorage.updateFilm(film);
    }

    public Collection<Film> findAllFilms() {
        log.info("Возвращаем все фильмы");
        return filmStorage.findAllFilms();
    }

    public Film findFilmById(int id) {
        log.info("Возвращаем фильм по id");
        return filmStorage.findFilmById(id);
    }
}
