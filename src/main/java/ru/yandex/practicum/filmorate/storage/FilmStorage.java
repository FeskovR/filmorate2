package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.ArrayList;
import java.util.Collection;

public interface FilmStorage {
    public Film addFilm(Film film);
    public Film updateFilm(Film film);
    public Collection<Film> findAllFilms();
    public Film findFilmById(int id);
    public Film addLikeToFilm(int id, int userId);
    public Film removeLikeFromFilm(int id, int userId);
    public ArrayList<Film> getTopFilms(int count);
}
