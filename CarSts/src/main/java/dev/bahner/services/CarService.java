package dev.bahner.services;

import java.util.List;

import dev.bahner.beans.Car;

public interface CarService {

	public Car addCar(Car c);
	public Car getCar(int id);
	public List<Car> getAllCars();
	public Car updateCar(Car change);
	public boolean deleteCar(int id);
	public List<Car> getCarMake(String make);
	public List<Car> getCarModel(String model);
	public List<Car> getCarMakeAndModel(String make, String model);
	
}
