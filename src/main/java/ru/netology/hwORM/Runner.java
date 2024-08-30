package ru.netology.hwORM;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.netology.hwORM.models.Customers;
import ru.netology.hwORM.models.Orders;
import ru.netology.hwORM.repository.CustomerRepository;
import ru.netology.hwORM.repository.OrderRepository;
import java.util.Date;

@SpringBootApplication
public class Runner implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Runner.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CustomerRepository customerRepository, OrderRepository orderRepository) {
		return args -> {
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
					.phone_number("+79655201457")
					.build();
			Customers alex = Customers.builder()
					.name("ALEXEY")
					.surname("PETROV")
					.age(65)
					.phone_number("+79235455652")
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
