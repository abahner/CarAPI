package dev.bahner.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.server.ResponseStatusException;

import dev.bahner.beans.Car;
import dev.bahner.repositories.CarRepo;

@CrossOrigin
@Service
public class CarServiceImpl implements CarService {
	
	@Autowired
	public CarRepo cr;

	@Override
	public Car addCar(Car c) {
		if (cr.existsById(c.getId())) {
			//log here
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		} else {
			c = cr.save(c);
			return c;
		}
	}

	@Override
	public Car getCar(int id) {
		if (cr.existsById(id)) {
			return cr.findById(id).get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<Car> getAllCars() {
		return (List<Car>) cr.findAll();
	}

	@Override
	public Car updateCar(Car change) {
		if (cr.existsById(change.getId())) {
			return cr.save(change);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public boolean deleteCar(int id) {
		if (cr.existsById(id)) {
			cr.deleteById(id);
			return true;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<Car> getCarMake(String make) {
		return cr.findByMake(make);
	}

	@Override
	public List<Car> getCarModel(String model) {
		return cr.findByModel(model);
	}


	@Override
	public List<Car> getCarTrim(String trim) {
		return cr.findByTrim(trim);
	}


	@Override
	public List<Car> getCarByPrice(int price) {
		return cr.findByPrice(price);
	}

	@Override
	public List<Car> getCarByMileage(int mileage) {
		return cr.findByMileage(mileage);
	}

	@Override
	public List<Car> getCarByYear(int year) {
		return cr.findByYear(year);
	}

	
}
