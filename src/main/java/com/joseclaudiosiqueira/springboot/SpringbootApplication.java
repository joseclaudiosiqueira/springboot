package com.joseclaudiosiqueira.springboot;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.joseclaudiosiqueira.springboot.domain.Category;
import com.joseclaudiosiqueira.springboot.domain.City;
import com.joseclaudiosiqueira.springboot.domain.Product;
import com.joseclaudiosiqueira.springboot.domain.State;
import com.joseclaudiosiqueira.springboot.repositories.CategoryRepository;
import com.joseclaudiosiqueira.springboot.repositories.CityRepository;
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

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Category category1 = new Category(null, "Informática");
		Category category2 = new Category(null, "Escritório");

		Product product1 = new Product(null, "Computador", 2000.00);
		Product product2 = new Product(null, "Impressora", 800.00);
		Product product3 = new Product(null, "Mouse", 80.00);

		category1.getProducts().addAll(Arrays.asList(product1, product2, product3));
		category2.getProducts().addAll(Arrays.asList(product2));
		/*
		 * Association between produtcs and categories
		 */
		product1.getCategories().addAll(Arrays.asList(category1));
		product2.getCategories().addAll(Arrays.asList(category1, category2));
		product3.getCategories().addAll(Arrays.asList(category1));

		/*
		 * add to repositories
		 */
		categoryRepository.saveAll(Arrays.asList(category1, category2));
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

	}

}
