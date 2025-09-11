package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public interface UserStorage {
    User addUser(User user);

    User updateUser(User user);

    Collection<User> findAllUsers();

    User findUserById(int id);

    User addFriend(int id, int friendId);

    User deleteFriend(int id, int friendId);

    ArrayList<User> getAllUserFriends(int id);

    HashSet<User> getAllCommonFriends(int id, int otherId);
}
