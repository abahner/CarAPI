package dev.bahner.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src\\test\\java\\features",
		glue = {"dev.bahner.steps", "searchSteps"})
public class SearchRunner {

	
	
}
