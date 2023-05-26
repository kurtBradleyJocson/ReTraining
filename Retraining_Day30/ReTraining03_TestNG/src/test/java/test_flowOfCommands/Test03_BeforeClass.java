package test_flowOfCommands;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

public class Test03_BeforeClass {
	
	WebDriver driver;
	
	@Test
	public void f() {
		driver = Test00_POJO.driver;
	}
}
