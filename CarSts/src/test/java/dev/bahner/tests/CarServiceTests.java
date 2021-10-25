package dev.bahner.tests;

import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import dev.bahner.beans.Car;
import dev.bahner.repositories.CarRepo;
import dev.bahner.services.CarService;

@SpringBootTest(classes = dev.bahner.runner.CarApiApplication.class)
public class CarServiceTests {

	@Autowired
	public CarService cs;
	
	@MockBean
	CarRepo cr;
	
	@Test
	public void addCarSuccess() {
		Car c = new Car(2, 2000, "Pontiac", "Trans Am", "WS6", 184000, 4200, "img");
		Mockito.when(cr.save(c)).thenReturn(new Car(c.getYear(), c.getMake(), c.getModel(), c.getTrim(), c.getMileage(), c.getPrice(), c.getImg()));
		
		c = cs.addCar(c);
		
		Assertions.assertEquals(2000, c.getYear());
		Assertions.assertEquals("Pontiac", c.getMake());
		Assertions.assertEquals("Trans Am", c.getModel());
		Assertions.assertEquals("WS6", c.getTrim());	
	}
	
	@Test
	public void addCarFail() {
		Car c = new Car(2, 2000, "Pontiac", "Trans Am", "WS6", 184000, 4200, "img");
		Mockito.when(cr.existsById(c.getId())).thenReturn(true);
		
		assertThrows(ResponseStatusException.class, () -> {
			cs.addCar(c);
		});
	}
	
	@Test
	public void getCarSuccess() {
		Car c = new Car(2, 2000, "Pontiac", "Trans Am", "WS6", 184000, 4200, "img");
		Mockito.when(cr.existsById(c.getId())).thenReturn(true);
		Mockito.when(cr.findById(c.getId())).thenReturn(Optional.of(c));
		
		cs.getCar(c.getId());
		
		Assertions.assertEquals(2000, c.getYear());
		Assertions.assertEquals("Pontiac", c.getMake());
		Assertions.assertEquals("Trans Am", c.getModel());
		Assertions.assertEquals("WS6", c.getTrim());
	}
	
	@Test
	public void getCarFail() {
		Car c = new Car(2, 2000, "Pontiac", "Trans Am", "WS6", 184000, 4200, "img");
		Mockito.when(cr.existsById(c.getId())).thenReturn(false);
		
		assertThrows(ResponseStatusException.class, () -> {
			cs.getCar(c.getId());
		});
	}
	
	@Test
	public void updateCarSuccess() {
		Car c = new Car(2, 2000, "Pontiac", "Trans am", "WS6", 184000, 4200, "img");
		Mockito.when(cr.existsById(c.getId())).thenReturn(true);
		Mockito.when(cr.save(c)).thenReturn(new Car(2, 2002, "Pontiac", "Firebird", "Formula", 211000, 420, "img"));
		c = cs.updateCar(c);
		
		Assertions.assertEquals(2002, c.getYear());
		Assertions.assertEquals("Pontiac", c.getMake());
		Assertions.assertEquals("Firebird", c.getModel());
		Assertions.assertEquals("Formula", c.getTrim());
	}
	
	@Test
	public void updateCarFail() {
		Car c = new Car(2, 2000, "Pontiac", "Trans am", "WS6", 184000, 4200, "img");
		Mockito.when(cr.existsById(c.getId())).thenReturn(false);

		assertThrows(ResponseStatusException.class, () -> {
			cs.updateCar(c);
		});
	}
	
	@Test
	public void deleteCarSuccess() {
		Car c = new Car(2, 2000, "Pontiac", "Trans am", "WS6", 184000, 4200, "img");
		Mockito.when(cr.existsById(c.getId())).thenReturn(true);
		
		Assertions.assertEquals(cs.deleteCar(c.getId()), true);
	}
	
	@Test
	public void deleteCarFail() {
		Car c = new Car(2, 2000, "Pontiac", "Trans am", "WS6", 184000, 4200, "img");
		Mockito.when(cr.existsById(c.getId())).thenReturn(false);
		
		assertThrows(ResponseStatusException.class, () -> {
			cs.deleteCar(c.getId());
		});
	}
	
	@Test
	public void getAllCars() {
		Car c = new Car(2, 2000, "Pontiac", "Trans am", "WS6", 184000, 4200, "img");
		List<Car> cars = new ArrayList<Car>();
		cars.add(c);
		Mockito.when(cr.findAll()).thenReturn(cars);
		Assertions.assertNotNull(cars);
	}
	
	@Test public void search() {
		Car c1 = new Car(2, 2000, "Pontiac", "Trans am", "WS6", 184000, 4200, "img");
		Car c2 = new Car(3, 2012, "Ford", "Fusion", "SE", 245000, 900, "img");
		List<Car> makeSearch = new ArrayList<Car>();
		List<Car> modelSearch = new ArrayList<Car>();
		List<Car> makeModelSearch = new ArrayList<Car>();
		makeSearch.add(c1);
		modelSearch.add(c2);
		makeModelSearch.add(c1);
		
		Mockito.when(cr.findByMake(c1.getMake())).thenReturn(makeSearch);
		Assertions.assertEquals(cs.getCarMake(c1.getMake()), makeSearch);
		
		Mockito.when(cr.findByModel(c2.getModel())).thenReturn(modelSearch);
		Assertions.assertEquals(cs.getCarModel(c2.getModel()), modelSearch);
		
		Mockito.when(cr.findByMakeAndModel(c1.getMake(), c1.getModel())).thenReturn(makeModelSearch);
		Assertions.assertEquals(cs.getCarMakeAndModel(c1.getMake(), c1.getModel()), makeModelSearch);
	}

	
}
