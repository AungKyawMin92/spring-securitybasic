package mm.com.springboot.oauthjwtsecurity.ak.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import mm.com.springboot.oauthjwtsecurity.ak.entity.User;
import mm.com.springboot.oauthjwtsecurity.ak.repository.UserRepository;

@Component
public class AppUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName);
        if(user == null) {
            throw new UsernameNotFoundException(String.format("The username %s doesn't exist", userName));
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));       
        // add to spring security user detail for map
        UserDetails userDetails = new org.springframework.security.core.userdetails.
                User(user.getUsername(), user.getPassword(), authorities);
        
        
    	/*List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        UserDetails userDetails = new org.springframework.security.core.userdetails.
                User("ak", "$2a$10$mU8VnBk17B1Hecq9MouaBulwHPa03LIaOCZFBbwrUe8KHOpXBkImO", authorities);
    	 */
        // comment for in memory login ( no mysql need )
        
        return userDetails;
    }
}
