package dev.bahner.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		
		assertEquals(2000, c.getYear());
		assertEquals("Pontiac", c.getMake());
		assertEquals("Trans Am", c.getModel());
		assertEquals("WS6", c.getTrim());	
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
		
		assertEquals(2000, c.getYear());
		assertEquals("Pontiac", c.getMake());
		assertEquals("Trans Am", c.getModel());
		assertEquals("WS6", c.getTrim());
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
		
		assertEquals(2002, c.getYear());
		assertEquals("Pontiac", c.getMake());
		assertEquals("Firebird", c.getModel());
		assertEquals("Formula", c.getTrim());
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
		
		assertEquals(cs.deleteCar(c.getId()), true);
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
		assertNotNull(cars);
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
		assertEquals(cs.getCarMake(c1.getMake()), makeSearch);
		
		Mockito.when(cr.findByModel(c2.getModel())).thenReturn(modelSearch);
		assertEquals(cs.getCarModel(c2.getModel()), modelSearch);
		
	}
	
	@Test
	public void carCarByMakeSuccess() {
		Car c = new Car(2, 2000, "Pontiac", "Trans am", "WS6", 184000, 4200, "img");
		Mockito.when(cr.existsById(c.getId())).thenReturn(true);
		Mockito.when(cr.findById(c.getId())).thenReturn(Optional.of(c));
		
		cs.getCarMake(c.getMake());
		
		assertEquals("Pontiac", c.getMake());
	}
	
	@Test
	public void carCarByMakeFail() {
		Car c = new Car(2, 2000, "Pontiac", "Trans am", "WS6", 184000, 4200, "img");
		Mockito.when(cr.existsById(c.getId())).thenReturn(false);

		assertThat(cs.getCarMake(c.getMake()) == null);
	}
	
	@Test
	public void carCarByModelSuccess() {
		Car c = new Car(2, 2000, "Pontiac", "Trans am", "WS6", 184000, 4200, "img");
		Mockito.when(cr.existsById(c.getId())).thenReturn(true);
		Mockito.when(cr.findById(c.getId())).thenReturn(Optional.of(c));
		
		cs.getCarModel(c.getModel());
		
		assertEquals("Trans am", c.getModel());
	}
	
	@Test
	public void carCarByModelFail() {
		Car c = new Car(2, 2000, "Pontiac", "Trans am", "WS6", 184000, 4200, "img");
		Mockito.when(cr.existsById(c.getId())).thenReturn(false);

		assertThat(cs.getCarModel(c.getModel()) == null);

	}
	
	@Test
	public void carCarByTrimSuccess() {
		Car c = new Car(2, 2000, "Pontiac", "Trans am", "WS6", 184000, 4200, "img");
		Mockito.when(cr.existsById(c.getId())).thenReturn(true);
		Mockito.when(cr.findById(c.getId())).thenReturn(Optional.of(c));
		
		cs.getCarTrim(c.getTrim());
		
		assertEquals("WS6", c.getTrim());
	}
	
	@Test
	public void carCarByTrimFail() {
		Car c = new Car(2, 2000, "Pontiac", "Trans am", "WS6", 184000, 4200, "img");
		Mockito.when(cr.existsById(c.getId())).thenReturn(false);

		assertThat(cs.getCarTrim(c.getTrim()) == null);

	}
	
	@Test
	public void carCarByYearSuccess() {
		Car c = new Car(2, 2000, "Pontiac", "Trans am", "WS6", 184000, 4200, "img");
		Mockito.when(cr.existsById(c.getId())).thenReturn(true);
		Mockito.when(cr.findById(c.getId())).thenReturn(Optional.of(c));
		
		cs.getCarByYear(c.getYear());
		
		assertEquals(2000, c.getYear());
	}
	
	@Test
	public void carCarByYearFail() {
		Car c = new Car(2, 2000, "Pontiac", "Trans am", "WS6", 184000, 4200, "img");
		Mockito.when(cr.existsById(c.getId())).thenReturn(false);

		assertThat(cs.getCarByYear(c.getYear()) == null);

	}
	
	@Test
	public void carCarByPriceSuccess() {
		Car c = new Car(2, 2000, "Pontiac", "Trans am", "WS6", 184000, 4200, "img");
		Mockito.when(cr.existsById(c.getId())).thenReturn(true);
		Mockito.when(cr.findById(c.getId())).thenReturn(Optional.of(c));
		
		cs.getCarByPrice(c.getPrice());
		
		assertEquals(4200, c.getPrice());
	}
	
	@Test
	public void carCarByPriceFail() {
		Car c = new Car(2, 2000, "Pontiac", "Trans am", "WS6", 184000, 4200, "img");
		Mockito.when(cr.existsById(c.getId())).thenReturn(false);

		assertThat(cs.getCarByPrice(c.getPrice()) == null);

	}
	
	@Test
	public void carCarByMileageSuccess() {
		Car c = new Car(2, 2000, "Pontiac", "Trans am", "WS6", 184000, 4200, "img");
		Mockito.when(cr.existsById(c.getId())).thenReturn(true);
		Mockito.when(cr.findById(c.getId())).thenReturn(Optional.of(c));
		
		cs.getCarByMileage(c.getMileage());
		
		assertEquals(184000, c.getMileage());
	}
	
	@Test
	public void carCarByMileageFail() {
		Car c = new Car(2, 2000, "Pontiac", "Trans am", "WS6", 184000, 4200, "img");
		Mockito.when(cr.existsById(c.getId())).thenReturn(false);

		assertThat(cs.getCarByMileage(c.getMileage()) == null);

	}

	
}
