import org.testng.annotations.*;

public class TestClass {

	@BeforeSuite
	public void setUp() {
		System.out.println("@BeforeSuite");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("@BeforeTest");
	}
	
    @BeforeClass
    public void beforeClass() {
        System.out.println("@BeforeClass");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("@BeforeMethod");
    }

    @Test
    public void testMethod() {
        System.out.println("@Test");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("@AfterMethod");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("@AfterClass");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("@AfterTest");
    }

    @AfterSuite
    public void tearDown() {
        System.out.println("@AfterSuite");
    }
	

}
