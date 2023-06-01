package com.example.reservationsystem.userapi;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void saveUser(User user) {
        UserEntity userEntity = userMapper.userToEntity(user);
        validateEmailAddress(user.getEmailAddress());
        userRepository.save(userEntity);
    }

    void validateEmailAddress(String emailAddress) {
        boolean notValidEmailAddress = !EmailValidator.getInstance().isValid(emailAddress);
        if (notValidEmailAddress)
            throw new UserException(HttpStatus.BAD_REQUEST, "Email Address not valid");
    }

    public User getUser(String nationalId) {
        Optional<UserEntity> optional = userRepository.findById(nationalId);
        if (optional.isPresent())
            return userMapper.userEntityToUser(optional.get());
        else
            throw new UserException(HttpStatus.NOT_FOUND, "User not found");
    }

    public List<User> getAllUsers() {
        List<UserEntity> userEntityList = new ArrayList<>();
        userRepository.findAll().forEach(userEntityList::add);
        return userMapper.userEntityListToUserList(userEntityList);
    }
}
