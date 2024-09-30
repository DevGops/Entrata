package utils;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class RandomGenerator {

	public static Random rand = new Random();
	private static Faker faker = new Faker();


	/**
	 * Generate user Data
	 *
	 * @return Name
	 */
	public static Name personnelInfo() {
		return faker.name();
	}

	/**
	 * Generate user Data
	 *
	 * @return Address
	 */
	public static Address addressInfo() {
		return faker.address();
	}

	/**
	 * Generate user Data
	 *
	 * @return Address
	 */
	public static Faker random() {
		return faker;
	}
}
