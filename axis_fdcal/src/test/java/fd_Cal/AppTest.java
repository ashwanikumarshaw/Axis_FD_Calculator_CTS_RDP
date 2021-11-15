package fd_Cal;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import util.ReadExcel;
import util.Tack_screenshot;

public class AppTest {
	WebDriver driver;

	@BeforeClass
	public void callDriver() {

		String Driverpath = "D:\\INSTALL\\QAE\\eclipse_Workspace\\848849_axis_fdcal\\driver\\chromedriver.exe";

		System.setProperty("webdriver.chrome.driver", Driverpath);
		driver = new ChromeDriver();

		// driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test(priority = 0)
	public void openPage() {
		String url = "https://www.axisbank.com/retail/calculators/fd-calculator";
		driver.get(url);

		String ActualTitle = driver.getTitle();
		String ExpectedTitle = "FD Calculator : Fixed Deposit Interest Rate Calculator Online - Axis Bank ";

		Assert.assertEquals(ExpectedTitle.contains(ActualTitle), true);
	}

	@Test(priority = 1)
	public void fillform() throws InterruptedException {
		// driver.switchTo().alert().dismiss();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id=\"nvpush_cross\"]")).click();
		
		// click radio button
		WebElement SeniorCitizen = driver.findElement(By.xpath("//*[@id=\"RadioButton\"]/label[2]"));
		SeniorCitizen.click();

		// select dropdown
		Select Type = new Select(driver.findElement(By.id("FdepType")));
		Type.selectByVisibleText("Monthly Payout");

		// Enter Amount Deposit ( rupee) from excel
		String filePath = "D:\\INSTALL\\QAE\\eclipse_Workspace\\848849_axis_fdcal";
		String fileName = "Amout.xlsx";
		String sheetName = "Sheet1";
		String amount = null;
		try {
			amount = ReadExcel.readExcel(filePath, fileName, sheetName, 0);
		} catch (IOException e) {

			e.printStackTrace();
		}
System.out.println(amount);
		driver.findElement(By.xpath("//*[@id=\"loan_amount\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"loan_amount\"]")).sendKeys(amount);
		
		//Click anywhere
		driver.findElement(By.xpath("//*[@id=\"ctl00_Body_C022_Col00\"]/div/div[3]/div[2]/div[1]/div/article[2]/h4"))
				.click();

		String RateofInterest = driver.findElement(By.xpath("//*[@id=\"matIntRate\"]")).getText();
		System.out.println("Rate of Interest =" + RateofInterest);

		// Take Screenshot
		int min = 50;
		int max = 1000;
		int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
		String fileWithPath = "D:\\INSTALL\\QAE\\eclipse_Workspace\\848849_axis_fdcal\\Screenshot\\Report" + random_int
				+ ".png";
		try {
			Tack_screenshot.takeSnap(driver, fileWithPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void closeDriver() {
		 driver.close();
	}
}
