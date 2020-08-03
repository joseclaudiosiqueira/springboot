package com.joseclaudiosiqueira.springboot;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.joseclaudiosiqueira.springboot.domain.Address;
import com.joseclaudiosiqueira.springboot.domain.BilletPayment;
import com.joseclaudiosiqueira.springboot.domain.CardPayment;
import com.joseclaudiosiqueira.springboot.domain.Category;
import com.joseclaudiosiqueira.springboot.domain.City;
import com.joseclaudiosiqueira.springboot.domain.Client;
import com.joseclaudiosiqueira.springboot.domain.Order;
import com.joseclaudiosiqueira.springboot.domain.OrderItem;
import com.joseclaudiosiqueira.springboot.domain.Payment;
import com.joseclaudiosiqueira.springboot.domain.Product;
import com.joseclaudiosiqueira.springboot.domain.State;
import com.joseclaudiosiqueira.springboot.domain.enums.ClientType;
import com.joseclaudiosiqueira.springboot.domain.enums.PaymentState;
import com.joseclaudiosiqueira.springboot.repositories.AddressRepository;
import com.joseclaudiosiqueira.springboot.repositories.CategoryRepository;
import com.joseclaudiosiqueira.springboot.repositories.CityRepository;
import com.joseclaudiosiqueira.springboot.repositories.ClientRepository;
import com.joseclaudiosiqueira.springboot.repositories.OrderItemRepository;
import com.joseclaudiosiqueira.springboot.repositories.OrderRepository;
import com.joseclaudiosiqueira.springboot.repositories.PaymentRepository;
import com.joseclaudiosiqueira.springboot.repositories.ProductRepository;
import com.joseclaudiosiqueira.springboot.repositories.StateRepository;

@SpringBootApplication
public class SpringbootApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Category category1 = new Category(null, "Informática");
		Category category2 = new Category(null, "Escritório");
		Category category3 = new Category(null, "Bed & Breakfest :-)");
		Category category4 = new Category(null, "Electronics");
		Category category5 = new Category(null, "Gardening");
		Category category6 = new Category(null, "Ornament");
		Category category7 = new Category(null, "Office");

		Product product1 = new Product(null, "Computador", 2000.00);
		Product product2 = new Product(null, "Impressora", 800.00);
		Product product3 = new Product(null, "Mouse", 80.00);

		category1.getProducts().addAll(Arrays.asList(product1, product2, product3));
		category2.getProducts().addAll(Arrays.asList(product2));
		/*
		 * Association between products and categories
		 */
		product1.getCategories().addAll(Arrays.asList(category1));
		product2.getCategories().addAll(Arrays.asList(category1, category2));
		product3.getCategories().addAll(Arrays.asList(category1));

		/*
		 * add to repositories
		 */
		categoryRepository
				.saveAll(Arrays.asList(category1, category2, category3, category4, category5, category6, category7));
		productRepository.saveAll(Arrays.asList(product1, product2, product3));

		/*
		 * adding cities and states
		 */
		State state1 = new State(null, "Minas Gerais", "MG");
		State state2 = new State(null, "São Paulo", "SP");

		City city1 = new City(null, "Uberlândia", state1);
		City city2 = new City(null, "São Paulo", state2);
		City city3 = new City(null, "Campinas", state2);

		/*
		 * Association between states and cities
		 */
		state1.getCities().addAll(Arrays.asList(city1));
		state2.getCities().addAll(Arrays.asList(city2, city3));

		stateRepository.saveAll(Arrays.asList(state1, state2));
		cityRepository.saveAll(Arrays.asList(city1, city2, city3));

		/*
		 * Association between clients, addresses and cities
		 */
		Client client1 = new Client(null, "Maria Silva", "maria@gmail.com", "36378912377", ClientType.NATURALPERSON);

		client1.getPhoneNumbers().addAll(Arrays.asList("27363323", "93838393"));

		Address address1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", client1, city1);
		Address address2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", client1, city2);

		client1.getAddresses().addAll(Arrays.asList(address1, address2));

		clientRepository.saveAll(Arrays.asList(client1));
		addressRepository.saveAll(Arrays.asList(address1, address2));

		/*
		 * Instance of Orders and Payments
		 */
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Order order1 = new Order(null, simpleDateFormat.parse("30/09/2017 10:32"), client1, address1);
		Order order2 = new Order(null, simpleDateFormat.parse("10/10/2017 19:35"), client1, address2);

		Payment payment1 = new CardPayment(null, PaymentState.PAIDOUT, order1, 6);
		Payment payment2 = new BilletPayment(null, PaymentState.PENDING, order2,
				simpleDateFormat.parse("20/10/2017 00:00"), null);

		order1.setPayment(payment1);
		order2.setPayment(payment2);

		client1.getOrders().addAll(Arrays.asList(order1, order2));

		orderRepository.saveAll(Arrays.asList(order1, order2));
		paymentRepository.saveAll(Arrays.asList(payment1, payment2));

		OrderItem orderItem1 = new OrderItem(order1, product1, 0.00, 1, 2000.00);
		OrderItem orderItem2 = new OrderItem(order1, product2, 0.00, 2, 80.00);
		OrderItem orderItem3 = new OrderItem(order2, product2, 100.00, 1, 800.00);

		order1.getItems().addAll(Arrays.asList(orderItem1, orderItem2));
		order2.getItems().addAll(Arrays.asList(orderItem3));

		product1.getItems().addAll(Arrays.asList(orderItem1));
		product2.getItems().addAll(Arrays.asList(orderItem3));
		product3.getItems().addAll(Arrays.asList(orderItem2));

		orderItemRepository.saveAll(Arrays.asList(orderItem1, orderItem2, orderItem3));
	}

}
