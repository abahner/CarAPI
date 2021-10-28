package dev.bahner.runner;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src\\test\\java\\features",
		glue = {"dev.bahner.steps", "searchSteps"})
class CarApiApplicationTests {

	@Test
	void contextLoads() {
	}

}
