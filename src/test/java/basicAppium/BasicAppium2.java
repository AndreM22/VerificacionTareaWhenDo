package basicAppium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BasicAppium2 {

	private AppiumDriver appiumDriver;

	@BeforeEach
	public void openApplication() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Redmi Note 9 Pro");
		capabilities.setCapability("platformVersion", "11 RKQ1.200826.002");
		capabilities.setCapability("appPackage", "com.miui.calculator");
		capabilities.setCapability("appActivity", "com.miui.calculator.cal.CalculatorActivity");
		capabilities.setCapability("platformName", "Android");

		appiumDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

		// implicit
		appiumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

	}

	@AfterEach
	public void closeApplication() {
		appiumDriver.quit();
	}

	@Test
	public void verifyCalculator() throws InterruptedException {
		//AceptarTerminos 

		appiumDriver.findElement(By.xpath("//android.widget.Button[@text='Aceptar']")).click();

		// click 2 
		appiumDriver.findElement(By.xpath("//android.widget.TextView[@text='2']")).click();

		// click + 
		appiumDriver.findElement(By.xpath("//android.widget.ImageView[@content-desc='sumar']")).click();
		
		// click 5 
		appiumDriver.findElement(By.xpath("//android.widget.TextView[@text='5']")).click();
		
		// click = 
		appiumDriver.findElement(By.xpath("//android.widget.ImageView[@content-desc='igual a']")).click();
		
		// verificar 7 

		String expectedResult = "= 7";
		String actualResult = appiumDriver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.miui.calculator:id/result']")).getText();

		Assertions.assertEquals(expectedResult, actualResult, "ERROR la suma es incorrecta");
	}
}
