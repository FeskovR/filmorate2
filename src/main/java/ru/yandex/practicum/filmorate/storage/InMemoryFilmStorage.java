package ru.yandex.practicum.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.EntityNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;
import java.util.HashMap;

@Component
@Slf4j
public class InMemoryFilmStorage implements FilmStorage{

    private final HashMap<Integer, Film> films = new HashMap<>();

    @Override
    public Film addFilm(Film film) {
        films.put(film.getId(), film);
        return film;
    }

    @Override
    public Film updateFilm(Film film) {
        checkFilm(film.getId());
        films.put(film.getId(), film);
        return film;
    }

    @Override
    public Collection<Film> findAllFilms() {
        return films.values();
    }

    @Override
    public Film findFilmById(int id) {
        checkFilm(id);
        return films.get(id);
    }

    private void checkFilm(int id) {
        if (!films.containsKey(id)) throw new EntityNotFoundException("Фильм не найден");
    }


}
