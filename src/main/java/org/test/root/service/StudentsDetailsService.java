package org.test.root.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.test.root.model.User;
import org.test.root.repository.UserRepository;

@Component
public class StudentsDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("В базе нет такого пользователя");
        }
        return new UsersDetails(user.getLogin(), user.getPassword());
    }
}
