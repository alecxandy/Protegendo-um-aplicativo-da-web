package br.com.alexandredvlp.Protegendoumaplicativodaweb.webSecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration//classe de configuração
@EnableWebSecurity//habilitar o suporte de segurança da Web do Spring Security e fornecer a integração Spring MVC
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {//estende WebSecurityConfigurerAdaptere substitui
    // alguns de seus métodos para definir algumas especificações da configuração de segurança da web.

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll()//caminhos que não deve exigir autenticação
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")//pagina de acesso
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Bean
    @Override//método configura um armazenamento de usuário na memória com um único usuário. Esse usuário recebe
    // um nome de usuário de user, uma senha de passworde uma função de USER.
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }

}
