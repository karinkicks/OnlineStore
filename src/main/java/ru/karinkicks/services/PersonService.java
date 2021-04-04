package ru.karinkicks.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.karinkicks.entity.Person;
import ru.karinkicks.entity.Role;
import ru.karinkicks.repositories.PersonRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class PersonService implements UserDetailsService {
    private PersonRepository personRepository;

    @Autowired
    public void setUserRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person findByUsername(String username) {
        return personRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = findByUsername(username);
        if(person == null) {
            throw new UsernameNotFoundException(String.format("Person '%s' not found", username));
        }
        return new User(person.getUsername(), person.getPassword(),
                mapRolesToAuthorities(person.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
}
