package dev.bahner.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.bahner.beans.Car;

@Repository
public interface CarRepo extends CrudRepository<Car, Integer> {
	
	public boolean existsById(int id);
	public List<Car> findByMake(String make);
	public List<Car> findByModel(String model);
	public List<Car> findByTrim(String trim);
	public List<Car> findByMakeAndModel(String make, String model);
	public List<Car> findByMakeAndModelAndTrim(String make, String model, String trim);


	

}
