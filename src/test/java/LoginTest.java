import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;


public class LoginTest {

    WebDriver driver;

@BeforeEach
public void setup() {
    WebDriverManager.chromedriver().setup();

    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless");
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");

WebDriver driver = new ChromeDriver(options);
    
}

@AfterEach
public void teardown() {
    driver.quit();
}

    @Test
    public void realizarLoginComSucesso() throws InterruptedException {
       
        driver.get("https://the-internet.herokuapp.com/login");

        WebElement campoUsuario = driver.findElement(By.id("username"));
        campoUsuario.sendKeys("tomsmith");

        WebElement campoSenha = driver.findElement(By.id("password"));
        campoSenha.sendKeys("SuperSecretPassword!");

        WebElement botaoLogin = driver.findElement(By.cssSelector("button[type='submit']"));
        botaoLogin.click(); 

        Thread.sleep(2000);

        WebElement mensagem = driver.findElement(By.id("flash"));
        String textoMensagem = mensagem.getText();

        Assertions.assertTrue(textoMensagem.contains("You logged into a secure area!"));

    }

    @Test
    public void realizarLoginComSenhaInvalida() throws InterruptedException {
        
        driver.get("https://the-internet.herokuapp.com/login");

        WebElement campoUsuario = driver.findElement(By.id("username"));
        campoUsuario.sendKeys("tomsmith");

        WebElement campoSenha = driver.findElement(By.id("password"));
        campoSenha.sendKeys("senhaErrada");

        WebElement botaoLogin = driver.findElement(By.cssSelector("button[type='submit']"));
        botaoLogin.click(); 

        Thread.sleep(2000);

        WebElement mensagem = driver.findElement(By.id("flash"));
        String textoMensagem = mensagem.getText();

        Assertions.assertTrue(textoMensagem.contains("Your password is invalid!"));

    }
}