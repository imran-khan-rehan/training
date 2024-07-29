package com.redmath.Bank.App.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User or password incorrect.");
        }
        return new org.springframework.security.core.userdetails.User(
                user.get().getEmail(),
                user.get().getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList(user.get().getRole())
        );
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public boolean alreadyRegister(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public User findUser(Long userId) {
        return userRepository.findById(userId).orElseGet(() -> {
            User newUser = new User();
            newUser.setId(null);
            return newUser;
        });
    }

    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }
}
