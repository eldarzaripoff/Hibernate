package ru.netology.hwORM.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.hwORM.models.Persons;
import ru.netology.hwORM.repository.ProductRepository;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    private ProductRepository repository;

    @GetMapping("/persons/by-city")
    public List<Persons> getPersonsByCity(@RequestParam String city) {
        return repository.getPersonByCity(city);
    }

}
