package stepDefinitions;


import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class steps {
    protected static WebDriver driver;

    String text_programming_a;
    String text_programming_hours_a;

    @After
    public void quitBrowser() {
        driver.quit();
    }

    @Given("I enter in the homepage")
    public void I_enter_in_the_homepage() throws Throwable {
        System.setProperty("webdriver.chrome.driver", "src\\test\\browser\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.sky.com.br");
        driver.findElement(By.className("close")).click();
    }

    @When("I to select the programming displayed")
    public void I_to_select_the_programming_displayed() throws Throwable {

        driver.findElement(By.linkText("Programação")).click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement elementVisible = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("close-text")));

        WebElement text_capture = driver.findElement(By.xpath("//*[@id=\"schedules-container\"]/div[4]/div[3]/div/div/div[4]/div[2]/div/div/div/div[15]/div/div[2]/h2"));
        String text_programming = text_capture.getText();

        text_programming_a = text_programming;
        System.out.println("Passo1 texto" + text_programming_a);

        WebElement text_capture_hours = driver.findElement(By.xpath("//*[@id=\"schedules-container\"]/div[4]/div[3]/div/div/div[4]/div[2]/div/div/div/div[15]/div/div[2]/div[2]/div[1]/p"));
        String text_programming_hours = text_capture_hours.getText();

        text_programming_hours_a = text_programming_hours;
        System.out.println("Passo1 hora" + text_programming_hours_a);

       try {
            WebElement waiElement = driver.findElement(By.xpath("//*[@id=\"schedules-container\"]/div[4]/div[3]/div/div/div[4]/div[2]/div/div/div/div[19]/div/div[1]"));
           waiElement.click();

        }
        catch (org.openqa.selenium.StaleElementReferenceException ex)
        {
            WebElement waiElement = driver.findElement(By.xpath("//*[@id=\"schedules-container\"]/div[4]/div[3]/div/div/div[4]/div[2]/div/div/div/div[19]/div/div[1]"));
            waiElement.click();
        }

       WebDriverWait waitPageLoad = new WebDriverWait(driver, 10);
        WebElement elementModal = waitPageLoad.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"modal\"]/div/div[2]/div/div/div[1]/div/div[2]/div[1]/h2")));
    }

    @Then("modal equal to previous text")
    public void modal_equal_to_previous_text() throws Throwable {


        WebElement text_capture_modal = driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div[2]/div/div/div[1]/div/div[2]/div[1]/h2"));
        String text_capture_modal_actual = text_capture_modal.getText();

        WebElement text_capture_hours = driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div[2]/div/div/div[1]/div/div[2]/div[2]/span"));
        String text_capture_modal_hours = text_capture_hours.getText();

        Assert.assertEquals(text_capture_modal_actual,text_programming_a);
        Assert.assertEquals(text_capture_modal_hours,text_programming_hours_a);

        System.out.println(("Passo2 Label do modal" + text_capture_modal_actual));
        System.out.println(("Passo2 Label da página" + text_programming_a));

        System.out.println(("Passo2 Hora do modal" + text_capture_modal_hours));
        System.out.println(("Passo2 Hora da página" + text_programming_hours_a));

    }

}

