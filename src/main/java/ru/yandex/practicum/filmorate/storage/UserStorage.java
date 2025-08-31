package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;

public interface UserStorage {
    public User addUser(User user);
    public User updateUser(User user);
    public Collection<User> findAllUsers();
    public User findUserById(int id);
}
