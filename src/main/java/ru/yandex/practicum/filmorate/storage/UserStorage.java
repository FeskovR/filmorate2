package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public interface UserStorage {
    public User addUser(User user);
    public User updateUser(User user);
    public Collection<User> findAllUsers();
    public User findUserById(int id);
    public User addFriend(int id, int friendId);
    public User deleteFriend(int id, int friendId);
    public ArrayList<User> getAllUserFriends(int id);
    public HashSet<User> getAllCommonFriends(int id, int otherId);
}
