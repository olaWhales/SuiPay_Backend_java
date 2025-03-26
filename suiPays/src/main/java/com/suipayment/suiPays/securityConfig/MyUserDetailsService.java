package com.suipayment.suiPays.securityConfig;



import com.suipayment.suiPays.data.model.UserPrincipal;
import com.suipayment.suiPays.data.model.Users;
import com.suipayment.suiPays.data.repository.SignUpRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final SignUpRepository signUpRepository;

    public MyUserDetailsService(SignUpRepository signUpRepository) {
        this.signUpRepository = signUpRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = signUpRepository.findUsersByUserName(username).
                orElseThrow(()-> new IllegalArgumentException("Please input a match username"));
        return new UserPrincipal(user);
    }
}
