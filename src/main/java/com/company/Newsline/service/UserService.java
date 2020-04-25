package com.company.Newsline.service;

import com.company.Newsline.config.BCryptSingleton;
import com.company.Newsline.entity.Role;
import com.company.Newsline.entity.User;
import com.company.Newsline.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class UserService implements UserDetailsService {

    @PersistenceContext
    private EntityManager em;

    UserRepository userRepository;
    RoleService roleService;

    //loads user by his/her username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    //checks role of the user
    public boolean checkRole(String roleName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            for(GrantedAuthority r : userDetails.getAuthorities()){
                if (r.getAuthority().equals(roleName)){
                    return true;
                }
            }
        }
        return false;
    }

    //gets user by his/her username
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    //gets current user
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return getByUsername(userDetails.getUsername());
    }

    //gets user by userId or empty User object
    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    //adds a new user to DB with encoding a password
    public User save(User user) {
        user.setPassword(BCryptSingleton.getInstance().encode(user.getPassword()));
        return userRepository.save(user);
    }

    //gets all users
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    //adds a new user to DB with ROLE_USER if it does not exists already
    public boolean saveUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }
        Role userRole = roleService.findRoleByName("ROLE_USER");
        user.setRoles(Collections.singleton(userRole));
        user.setPassword(BCryptSingleton.getInstance().encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    //deletes a user with userId
    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public List<User> usergtList(Long idMin) {
        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }
}
