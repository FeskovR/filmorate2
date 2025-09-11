package ru.yandex.practicum.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.validation.Validator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Component
@Slf4j
public class InMemoryFilmStorage implements FilmStorage {

    private final HashMap<Integer, Film> films = new HashMap<>();
    private final Validator validator = new Validator();
    private final UserStorage userStorage;

    @Autowired
    public InMemoryFilmStorage(UserStorage userStorage) {
        this.userStorage = userStorage;
    }


    @Override
    public Film addFilm(Film film) {
        films.put(film.getId(), film);
        return film;
    }

    @Override
    public Film updateFilm(Film film) {
        validator.checkFilm(films, film.getId());
        films.put(film.getId(), film);
        return film;
    }

    @Override
    public Collection<Film> findAllFilms() {
        return films.values();
    }

    @Override
    public Film findFilmById(int id) {
        validator.checkFilm(films, id);
        return films.get(id);
    }

    @Override
    public Film addLikeToFilm(int id, int userId) {
        validator.checkFilm(films, id);
        userStorage.findUserById(userId);
        Film film = findFilmById(id);
        film.addLikeToFilmFromUser(userId);
        return film;
    }

    @Override
    public Film removeLikeFromFilm(int id, int userId) {
        validator.checkFilm(films, id);
        userStorage.findUserById(userId);
        Film film = findFilmById(id);
        film.removeLikeFromFilm(userId);
        return film;
    }

    @Override
    public ArrayList<Film> getTopFilms(int count) {
        if (count > films.size()) count = films.size();
        return new ArrayList<>(films
                .values()
                .stream()
                .sorted((Film a, Film b) -> b.getUsersLikes().size() - a.getUsersLikes().size())
                .toList()
                .subList(0, count)
        );
    }



}
