package ru.netology.hwORM;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.netology.hwORM.models.Customers;
import ru.netology.hwORM.models.Orders;
import ru.netology.hwORM.models.People;
import ru.netology.hwORM.models.Persons;
import ru.netology.hwORM.repository.CustomerRepository;
import ru.netology.hwORM.repository.OrderRepository;
import ru.netology.hwORM.repository.ProductRepository;

import java.util.Date;

@SpringBootApplication
public class Runner implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Runner.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(ProductRepository repository, CustomerRepository customerRepository, OrderRepository orderRepository) {
		return args -> {
			Persons michael = Persons.builder()
					.people(People.builder()
							.name("Michael")
							.surname("Adebayo")
							.age(21)
							.build())
					.phone_number("+79566321247")
					.city_of_living("Warshaw")
					.build();

			Persons yunus = Persons.builder()
					.people(People.builder()
							.name("Yunus")
							.surname("Babacan")
							.age(22)
							.build())
					.phone_number("+79241213412")
					.city_of_living("Moscow")
					.build();

			Persons eldar = Persons.builder()
					.people(People.builder()
							.name("Eldar")
							.surname("Zaripov")
							.age(26)
							.build())
					.phone_number("+79655914084")
					.city_of_living("Kazan").build();
			Persons rustem = Persons.builder()
					.people(People.builder()
							.name("Rustem")
							.surname("Zaripov")
							.age(22).build())
					.phone_number("+79520333552")
					.city_of_living("Moscow")
					.build();
			repository.save(michael);
			repository.save(yunus);
			repository.save(eldar);
			repository.save(rustem);
			Customers alexey = Customers.builder()
					.name("alexey")
					.surname("Ivanov")
					.age(23)
					.phone_number("+79233546476")
					.build();
			Customers rico = Customers.builder()
					.name("Rico")
					.surname("Hernandez")
					.age(32)
					.build();
			Customers alex = Customers.builder()
					.name("ALEXEY")
					.surname("PETROV")
					.age(65)
					.build();
			Customers alfredo = Customers.builder()
					.name("Alfredo")
					.surname("De Costa")
					.age(28)
					.phone_number("+79666844852")
					.build();
			customerRepository.save(alexey);
			customerRepository.save(rico);
			customerRepository.save(alex);
			customerRepository.save(alfredo);
			Orders laptop = Orders.builder()
					.date(new Date())
					.product_name("laptop")
					.amount(1)
					.customer(alexey)
					.build();
			Orders phone = Orders.builder()
					.date(new Date())
					.product_name("Phone")
					.amount(2)
					.customer(rico)
					.build();
			Orders phoneForAlex = Orders.builder()
					.date(new Date())
					.product_name("Phone")
					.amount(1)
					.customer(alex)
					.build();
			Orders pc = Orders.builder()
					.date(new Date())
					.product_name("PC")
					.customer(alfredo)
					.amount(1)
					.build();
			orderRepository.save(laptop);
			orderRepository.save(phone);
			orderRepository.save(phoneForAlex);
			orderRepository.save(pc);
		};

	}

	@Override
	public void run(String... args) throws Exception {

	}
}
