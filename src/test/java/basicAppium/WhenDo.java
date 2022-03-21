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

public class WhenDo {

	private AppiumDriver appiumDriver;

	@BeforeEach
	public void openApplication() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Redmi Note 9 Pro");
		capabilities.setCapability("platformVersion", "11 RKQ1.200826.002");
		capabilities.setCapability("appPackage", "com.vrproductiveapps.whendo");
		capabilities.setCapability("appActivity", "com.vrproductiveapps.whendo.ui.HomeActivity");
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
		
		String tituloTarea= "TareaFAMM";
		String notas= "Tarea de crecion de nueva lista";
		
		appiumDriver.findElement(By.id("com.vrproductiveapps.whendo:id/fab")).click();
		
		appiumDriver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.vrproductiveapps.whendo:id/noteTextTitle']")).sendKeys(tituloTarea);
		
		appiumDriver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.vrproductiveapps.whendo:id/noteTextNotes']")).sendKeys(notas);

		appiumDriver.findElement(By.id("com.vrproductiveapps.whendo:id/saveItem")).click();
		
		String expectedResult = tituloTarea;
		String actualResult = appiumDriver.findElement(By.xpath("\r\n" + 
				"//android.widget.TextView[@text='"+tituloTarea+"']")).getText();

		Assertions.assertEquals(expectedResult, actualResult, "ERROR no se creó la tarea");
		
	}
}
