package com.bnov.bfb.bfbcore.authentication;

import com.bnov.bfb.bfbcore.dao.UserRepository;
import com.bnov.bfb.bfbcore.model.User;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void givenLoginNotExistsInDB_shouldThrowException() {
        when(userRepository.findByLogin("notExistingLogin")).thenReturn(null);
        expectedException.expectMessage("User with given login not found!");
        expectedException.expect(UsernameNotFoundException.class);

        UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl(userRepository);
        userDetailsService.loadUserByUsername("notExistingLogin");
    }

    @Test
    public void givenLoginExists_shouldReturnUserDetails() {
        String login = "existingLogin";
        String password = "secret";
        when(userRepository.findByLogin(login))
                .thenReturn(new User(login, password));


        UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl(userRepository);
        UserDetails result = userDetailsService.loadUserByUsername(login);


        assertThat(result.getUsername()).isEqualTo(login);
        assertThat(result.getPassword()).isEqualTo(password);
        assertThat(result.getAuthorities()).isEmpty();
        assertThat(result.isAccountNonExpired()).isTrue();
        assertThat(result.isAccountNonLocked()).isTrue();
        assertThat(result.isCredentialsNonExpired()).isTrue();
        assertThat(result.isEnabled()).isTrue();
    }
}