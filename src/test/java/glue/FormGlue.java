package glue;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utils.WebDriverFactory;

public class FormGlue {
	
	WebDriver driver = WebDriverFactory.getInstance().getDriver(BrowserType.CHROME);
	
	@Given("^the application (.+)$")
	public void the_application_a(String url) throws Throwable {
		driver.get("http://"+url);
	}

	@When("^I fill the form with (.*), (.*), (.*), (.*)$")
	public void i_fill_the_form(String name, String email, String subject, String message) throws Throwable {
		driver.findElement(By.id("name")).sendKeys(name);
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("subject")).sendKeys(subject);
		driver.findElement(By.id("message")).sendKeys(message);

    }

	@When("^click submit button$")
	public void click_submit_button() throws Throwable {
		driver.findElement(By.id("submit")).click();
	}

	@Then("^shows a success message$")
	public void the_application_shows_a_success_message() throws Throwable {
		String result = driver.findElement(By.id("feedback")).getText().trim();
		assertTrue("Everything OK", "Success".equals(result));
	}
}
