package edu.depaul.coffeeapp.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Fetch from repository. Method should either return UserDetail or throw exception.
     * @param username                      user's username
     * @return                              userRepo.findByUsername()
     * @throws Exception                    User not found in repository
     */
    public UserDetails loadByUsername(String username) throws Exception {
        throw new Exception("not implemented yet");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getUserType()));
        return new org.springframework.security.core.userdetails.User(
                String.valueOf(user.getId()),
                user.getUsername(),
                authorities
        );
    }
}
