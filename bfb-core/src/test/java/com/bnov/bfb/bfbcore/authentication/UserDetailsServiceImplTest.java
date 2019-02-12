package com.bnov.bfb.bfbcore.authentication;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
        "bfb.core.authentication.login=testLogin",
        "bfb.core.authentication.password=secret"
})
public class UserDetailsServiceImplTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Test
    public void givenLoginNotExistsInDB_shouldThrowException() {
        expectedException.expect(UsernameNotFoundException.class);
        expectedException.expectMessage("User with given login not found!");

        userDetailsService.loadUserByUsername("notExistingLogin");
    }

    @Test
    public void givenLoginIsNull_shouldThrowException() {
        expectedException.expect(UsernameNotFoundException.class);
        expectedException.expectMessage("User with given login not found!");

        userDetailsService.loadUserByUsername(null);
    }

    @Test
    public void givenLoginExists_shouldReturnUserDetails() {
        String login = "testLogin";
        String password = "secret";
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