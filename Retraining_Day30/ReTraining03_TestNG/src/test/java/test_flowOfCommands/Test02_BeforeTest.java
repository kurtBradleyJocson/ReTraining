package test_flowOfCommands;

import org.testng.annotations.*;

public class Test02_BeforeTest {

	@BeforeTest
	public void f() {
		System.out.println("This is before test");
	}
}
