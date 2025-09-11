package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.ArrayList;
import java.util.Collection;

public interface FilmStorage {
    Film addFilm(Film film);

    Film updateFilm(Film film);

    Collection<Film> findAllFilms();

    Film findFilmById(int id);

    Film addLikeToFilm(int id, int userId);

    Film removeLikeFromFilm(int id, int userId);

    ArrayList<Film> getTopFilms(int count);
}
