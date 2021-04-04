package ru.karinkicks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.karinkicks.entity.Person;
import ru.karinkicks.entity.Role;
import ru.karinkicks.repositories.PersonRepository;
import ru.karinkicks.repositories.RoleRepository;

import java.util.Collection;
import java.util.NoSuchElementException;

@Controller
public class PersonHtmlController {
    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public PersonHtmlController(PersonRepository personRepository, RoleRepository roleRepository) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/all_persons")
    public String getPersons(Model model){
        model.addAttribute("persons",personRepository.findAll());
        return "all_persons";
    }

    @GetMapping("/add_person")
    public String getForm(Model uiModel){
        uiModel.addAttribute("person", new Person());
        return "add_person";
    }

    @PostMapping("/add_person")
    public String createPerson(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        personRepository.save(person);
        return "redirect:/all_persons";
    }

    @GetMapping ("/set_role")
    public String addRoleToPerson(@RequestParam(required = false, name = "person_id") Long person_id,
                                  @RequestParam(required = false, name = "role_id") Long role_id){
        if(person_id==null || role_id==null){
            return "set_role";
        }
        Person person =personRepository.findById(person_id).get();
        person.addRole(roleRepository.findById(role_id).get());
        personRepository.save(person);
        return "redirect:/all_persons";
    }
}
