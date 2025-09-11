package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
public class Film {
    private int id;
    @NotNull
    @NotBlank
    private String name;
    @Size(max = 200)
    private String description;
    @PastOrPresent
    private LocalDate releaseDate;
    @Positive
    private int duration;
    private ArrayList<Integer> usersLikes = new ArrayList<>();

    public void addLikeToFilmFromUser(int userId) {
        if (usersLikes.contains((Integer) userId)) {
            throw new RuntimeException("Лайк уже был поставлен этим пользователем");
        }
        usersLikes.add(userId);
    }

    public void removeLikeFromFilm(int userId) {
        if (usersLikes.contains(userId)) {
            usersLikes.remove((Integer) userId);
        }
    }
}
