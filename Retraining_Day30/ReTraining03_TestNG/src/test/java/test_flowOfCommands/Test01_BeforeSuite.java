package test_flowOfCommands;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class Test01_BeforeSuite {
	
	
	@BeforeSuite
	public void f() {
		
		Test00_POJO.driver = new ChromeDriver();

	}

}
