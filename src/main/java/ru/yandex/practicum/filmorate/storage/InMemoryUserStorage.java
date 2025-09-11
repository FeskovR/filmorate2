package ru.yandex.practicum.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.EntityNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validation.Validator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

@Component
@Slf4j
public class InMemoryUserStorage implements UserStorage {

    private HashMap<Integer, User> users = new HashMap<>();
    private final Validator validator = new Validator();

    public User addUser(User user) {
        users.put(user.getId(), user);
        return user;
    }

    public User updateUser(User user) {
        if (users.containsKey(user.getId())) {
            log.info("Пользователь найден. Обновляем данные");
            users.put(user.getId(), user);
            return user;
        }
        throw new EntityNotFoundException("Пользователь не найден");
    }

    public Collection<User> findAllUsers() {
        return users.values();
    }

    public User findUserById(int id) {
        validator.checkUser(users, id);
        return users.get(id);
    }

    public User addFriend(int id, int friendId) {
        validator.checkUser(users, id);
        User user = findUserById(id);
        validator.checkUser(users, friendId);
        User friend = findUserById(friendId);
        user.addFriend(friendId);
        friend.addFriend(id);
        return user;
    }

    public User deleteFriend(int id, int friendId) {
        validator.checkUser(users, id);
        User user = findUserById(id);
        validator.checkUser(users, friendId);
        User friend = findUserById(friendId);
        user.deleteFriend(friendId);
        friend.deleteFriend(id);
        return user;
    }

    public ArrayList<User> getAllUserFriends(int id) {
        ArrayList<User> allFriends = new ArrayList<>();
        User user = findUserById(id);
        for (Integer friend : user.getFriends()) {
            allFriends.add(findUserById(friend));
        }

        return allFriends;
    }

    public HashSet<User> getAllCommonFriends(int id, int otherId) {
        User user = findUserById(id);
        User otherUser = findUserById(otherId);
        HashSet<User> commonFriends = new HashSet<>();

        for (Integer friendId : user.getFriends()) {
            if (otherUser.getFriends().contains(friendId)) {
                commonFriends.add(findUserById(friendId));
            }
        }

        return commonFriends;
    }
}
