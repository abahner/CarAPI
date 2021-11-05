package dev.bahner.steps;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import dev.bahner.runner.SearchRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class searchSteps {


	WebDriver driver = SearchRunner.driver;
	
	@Given("the browser is open")
	public void the_browser_is_open() throws InterruptedException {
		
		driver.get("http://localhost:4200");
		Thread.sleep(50);
	}

	@And("the user is on the home page")
	public void the_user_is_on_the_home_page() throws InterruptedException {
		driver.findElement(By.xpath("/html/body/app-root/html/body/button")).click();
		Thread.sleep(50);
		
	}

	@When("^keys are input in the fields (.*), (.*), (.*), (.*), (.*) and (.*)$")
	public void keys_are_input_in_the_fields(String make, String model, String trim, String year, String price, String mileage) throws InterruptedException {
		driver.findElement(By.id("inputMake")).sendKeys(make);
		Thread.sleep(50);
		driver.findElement(By.id("inputModel")).sendKeys(model);
		Thread.sleep(50);
		driver.findElement(By.id("inputTrim")).sendKeys(trim);
		Thread.sleep(50);
		driver.findElement(By.id("inputYear")).sendKeys(year);
		Thread.sleep(50);
		driver.findElement(By.id("inputPrice")).sendKeys(price);
		Thread.sleep(50);
		driver.findElement(By.id("inputMileage")).sendKeys(mileage);
		Thread.sleep(50);
		
	}

	@And("the user sends the query")
	public void the_user_sends_the_query() throws InterruptedException {
		driver.findElement(By.id("q")).click();
		Thread.sleep(200);
	}

	@Then("the correct results are returned")
	public void the_correct_results_are_returned() throws InterruptedException {
		Thread.sleep(100);
		assertThat(driver.findElement(By.xpath("/html/body/app-root/app-car/div[2]/table/tbody/tr[1]")).isDisplayed());
		
	}
	

}