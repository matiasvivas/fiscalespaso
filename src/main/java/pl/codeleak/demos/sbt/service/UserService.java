package pl.codeleak.demos.sbt.service;

import java.util.Arrays;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.codeleak.demos.sbt.model.Role;
import pl.codeleak.demos.sbt.model.User;
import pl.codeleak.demos.sbt.repository.RoleRepository;
import pl.codeleak.demos.sbt.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRole("");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

    public User findUserById(Integer userId) {
        return userRepository.findUserById(userId);
    }
}