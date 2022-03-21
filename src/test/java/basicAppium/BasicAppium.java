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

public class BasicAppium {

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
		//AceptarTerminos android:id/button1

		appiumDriver.findElement(By.id("android:id/button1")).click();

		// click 2 com.miui.calculator:id/btn_2_s
		appiumDriver.findElement(By.id("com.miui.calculator:id/btn_2_s")).click();

		// click + com.miui.calculator:id/btn_plus_s
		appiumDriver.findElement(By.id("com.miui.calculator:id/btn_plus_s")).click();

		// click 5 com.miui.calculator:id/btn_5_s
		appiumDriver.findElement(By.id("com.miui.calculator:id/btn_5_s")).click();

		// click = com.miui.calculator:id/btn_equal_s
		appiumDriver.findElement(By.id("com.miui.calculator:id/btn_equal_s")).click();

		// verificar 7 com.miui.calculator:id/result

		String expectedResult = "= 7";
		String actualResult = appiumDriver.findElement(By.id("com.miui.calculator:id/result")).getText();

		Assertions.assertEquals(expectedResult, actualResult, "ERROR la suma es incorrecta");
	}
}
