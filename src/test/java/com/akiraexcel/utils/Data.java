package com.akiraexcel.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.akiraexcel.model.Employee;
import com.akiraexcel.model.Person;

public final class Data {

	@SuppressWarnings("deprecation")
	public static List<Employee> fakeEmployes() {
		List<Employee> employees = new ArrayList<>(3);

		Employee employee1 = new Employee();
		employee1.setEmployeeId(123923L);
		employee1.setName("Joe");
		employee1.setSurname("Doe");
		employee1.setSingle(true);
		employee1.setAge(30);
		employee1.setBirthday(new Date("4/9/1987"));
		employee1.setWeight("80kg");
		employee1.setHeight("1m50");
		employees.add(employee1);

		Employee employee2 = new Employee();
		employee2.setEmployeeId(123123L);
		employee2.setName("Sophie");
		employee2.setSurname("Derue");
		employee2.setSingle(false);
		employee2.setAge(20);
		employee2.setBirthday(new Date("5/3/1997"));
		employee2.setWeight("90kg");
		employee2.setHeight("1m60");
		employees.add(employee2);

		Employee employee3 = new Employee();
		employee3.setEmployeeId(135923L);
		employee3.setName("Paul");
		employee3.setSurname("Raul");
		employee3.setSingle(false);
		employee3.setAge(31);
		employee3.setBirthday(new Date("4/9/1986"));
		employee3.setWeight("100kg");
		employee3.setHeight("1m70");
		employees.add(employee3);

		return employees;
	}

	public static List<Person> fakePerson() {
		List<Person> people = new ArrayList<>(3);

		Person person = new Person();
		person.setHeight("1m8");
		person.setWeight("75kg");
		people.add(person);

		Person person1 = new Person();
		person1.setHeight("1m7");
		person1.setWeight("60kg");
		people.add(person1);

		return people;
	}
}
