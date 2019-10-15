package test.java;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class UiTest {
    static WebDriver driver;

    @BeforeMethod
    public static void setDriver() {
        driver = new ChromeDriver();
        System.setProperty("webdriver.crome.driver", "chromedriver.exe");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);

    }


    @Test(priority = 0)
    public static void uiTestPositive() throws InterruptedException {
        driver.get("http://iteaua-develop.demo.gns-it.com/");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement callbackbtn = driver.findElement(By.className("callback-btn"));
        WebElement preloader = driver.findElement(By.id("preload-it"));
        wait.until(ExpectedConditions.invisibilityOf(preloader));
        wait.until(ExpectedConditions.visibilityOf(callbackbtn));
        wait.until(ExpectedConditions.elementToBeClickable(callbackbtn));
        callbackbtn.click();
        WebElement nameField = driver.findElement(By.id("b-contacte__full-name"));
        nameField.sendKeys("Test");

        WebElement phoneField = driver.findElement(By.id("b-contacte-phone-tel"));
        phoneField.sendKeys("0638141284");

        WebElement sendBtn = driver.findElement(By.xpath("//*[@id=\"callback-form\"]/input[4]"));
        sendBtn.click();

        String expectedValue = "Спасибо!\n" + "Наш менеджер свяжется с Вами.";
        WebElement systemResponse = driver.findElement(By.xpath("//div[@class='b-header-contacte-phone-thank']"));
        String actualValue = systemResponse.getText();
        Assert.assertEquals(actualValue, expectedValue);

        WebElement closeBtn = driver.findElement(By.xpath("//*[@id=\"footer\"]/div[3]/div/div[1]"));
        closeBtn.click();

    }

    @Test(priority = 1)
    public static void uiTestNegative() throws InterruptedException {
        driver.get("http://iteaua-develop.demo.gns-it.com/");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement callbackbtn = driver.findElement(By.className("callback-btn"));
        WebElement preloader = driver.findElement(By.id("preload-it"));
        wait.until(ExpectedConditions.invisibilityOf(preloader));
        wait.until(ExpectedConditions.visibilityOf(callbackbtn));
        wait.until(ExpectedConditions.elementToBeClickable(callbackbtn));
        callbackbtn.click();
        WebElement sendBtn = driver.findElement(By.xpath("//*[@id=\"callback-form\"]/input[4]"));
        sendBtn.click();
        WebElement formColor = driver.findElement(By.cssSelector("[style = \"border-color: red;\"]"));
        wait.until(ExpectedConditions.visibilityOf(formColor));
        System.out.println("Vvedite name and telephone number!!!");
    }


    @AfterMethod
    public static void tearDown() {
        driver.quit();
    }
}

