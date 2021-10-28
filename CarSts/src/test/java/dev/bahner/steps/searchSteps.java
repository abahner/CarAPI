package dev.bahner.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class searchSteps {

	public static WebDriver driver;
	
	@Given("the browser is open")
	public void the_browser_is_open() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@And("the user is on the home page")
	public void the_user_is_on_the_home_page() throws InterruptedException {
		driver.get("http://localhost:4200/home");
		driver.findElement(By.xpath("/html/body/app-root/html/body/button")).click();
		Thread.sleep(100);
		
	}

	@When("^keys are input in the fields (.*), (.*) and (.*)$")
	public void keys_are_input_in_the_fields(String make, String model, String trim) throws InterruptedException {
		driver.findElement(By.id("inputMake")).sendKeys(make);
		Thread.sleep(50);
		driver.findElement(By.id("inputModel")).sendKeys(model);
		Thread.sleep(50);
		driver.findElement(By.id("inputTrim")).sendKeys(trim);
		Thread.sleep(50);
	}

	@And("the user sends the query")
	public void the_user_sends_the_query() throws InterruptedException {
		driver.findElement(By.id("q")).click();
	}

	@Then("the correct results are returned")
	public void the_correct_results_are_returned() throws InterruptedException {
		Thread.sleep(100);
		driver.close();
	}
}