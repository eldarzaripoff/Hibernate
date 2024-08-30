package ru.netology.hwORM.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.hwORM.models.Orders;
import ru.netology.hwORM.models.Persons;
import ru.netology.hwORM.repository.OrderRepository;
import ru.netology.hwORM.repository.ProductRepository;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    private ProductRepository repository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/persons/by-city")
    public List<Persons> getPersonsByCity(@RequestParam String city) {
        return repository.getPersonByCity(city);
    }

    @GetMapping("/orders/by-name")
    public List<Orders> getOrdersByName(@RequestParam String name) {
        return orderRepository.getOrderList(name);
    }

}
