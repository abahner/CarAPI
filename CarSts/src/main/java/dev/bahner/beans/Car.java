package dev.bahner.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "c_id", updatable = false)
	private int id;
	
	private int year;
	
	private String make;
	
	private String model;
	
	private String trim;
	
	private int mileage;
	
	private int price;
	
	private String img;
	
	public Car() {
		super();
	}

	public Car(int id, int year, String make, String model, String trim, int mileage, int price, String img) {
		super();
		this.id = id;
		this.year = year;
		this.make = make;
		this.model = model;
		this.trim = trim;
		this.mileage = mileage;
		this.price = price;
	}

	public Car(int year, String make, String model, String trim, int mileage, int price, String img) {
		this.year = year;
		this.make = make;
		this.model = model;
		this.trim = trim;
		this.mileage = mileage;
		this .price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getTrim() {
		return trim;
	}

	public void setTrim(String trim) {
		this.trim = trim;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "Car [id=" + id + ", year=" + year + ", make=" + make + ", model=" + model + ", trim=" + trim
				+ ", mileage=" + mileage + ", price=" + price + "]";
	}
	
}

