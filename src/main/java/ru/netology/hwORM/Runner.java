package ru.netology.hwORM;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.netology.hwORM.models.People;
import ru.netology.hwORM.models.Persons;
import ru.netology.hwORM.repository.ProductRepository;

@SpringBootApplication
public class Runner implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Runner.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(ProductRepository repository) {
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
		return args -> {
			repository.save(michael);
			repository.save(yunus);
			repository.save(eldar);
			repository.save(rustem);
		};

	}

	@Override
	public void run(String... args) throws Exception {

	}
}
