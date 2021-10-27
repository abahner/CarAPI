package dev.bahner.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.bahner.beans.Car;
import dev.bahner.services.CarService;

@CrossOrigin
@RestController
public class CarController {
	
	@Autowired
	CarService cs;
	
	@GetMapping(value = "/cars", produces = "application/json")
	public List<Car> getAllCars() {
		System.out.println("Getting all cars");
		return cs.getAllCars();
	}
	
	@GetMapping("/cars/{id}")
	public Car getCar(@PathVariable("id") String id) {
		return cs.getCar(Integer.parseInt(id));
	}
	
	@GetMapping("cars/search")
	public List<Car> searchCar(@RequestParam(required = false) String make, @RequestParam(required = false) String model, @RequestParam(required = false) String trim, @RequestParam(required = false) int year) {
		
		if (make != null && model != null) {
			if (trim != null) {
				return cs.getCarMakeAndModelAndTrim(make, model, trim);
			}
			return cs.getCarMakeAndModel(make, model);
		} else if (make == null && model == null) {
			return cs.getCarTrim(trim);
		} else if (model == null && trim == null) {
			return cs.getCarMake(make);
		} else if (make == null && trim == null){
			return cs.getCarModel(model);
		} else {
			return new ArrayList<Car>();
		}
	}
	
	@PostMapping(value = "/cars", consumes = "application/json", produces = "application/json")
	public Car addCar(@RequestBody Car c) {
		return cs.addCar(c);
	}
	
	@PutMapping(value = "/cars/{id}", consumes = "application/json", produces = "application/json")
	public Car updateCar(@PathVariable int id, @RequestBody Car change) {
		change.setId(id);
		return cs.updateCar(change);
	}
	
	@DeleteMapping("/cars/{id}")
	public boolean deleteCar(@PathVariable int id) {
		System.out.println("Deleting car with id: " + id);
		return cs.deleteCar(id);
	}
	
	
}
	

