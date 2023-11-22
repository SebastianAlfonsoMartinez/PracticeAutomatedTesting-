import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.RegisterPage;
import org.example.SearchProductAndAddPage;
import org.example.reports.ExtentFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class RegisterTest {
    private WebDriver driver;
    private WebDriverWait wait;
    static ExtentSparkReporter info = new ExtentSparkReporter("src/RegisterTest.html");
    static ExtentReports extent;

    //Profe ejecutar este primero para que el test de busqueda del prodcuto funcione
    // bien ya que tiene que estar logueado. Gracias.

    @BeforeAll
    public static void createReport() {
        extent = ExtentFactory.getInstance();
        extent.attachReporter(info);
    }

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.setUp();
        registerPage.getUrl("https://opencart.abstracta.us/index.php?route=common/home");
    }

    @Test
    @Tag("Registro")
    public void registerSuccessTest()throws InterruptedException{
        ExtentTest test = extent.createTest("Prueba de resgistro de usuario nuevo");
        test.log(Status.INFO, "Iniciando el test");
        RegisterPage registerPage = new RegisterPage(driver, wait);

        try {
            registerPage.clickMyAccount();
            registerPage.clickRegister();
            Assertions.assertEquals(registerPage.getTitleRegistration(), "Your Personal Details");
            test.log(Status.PASS, "Ingreso a la pagina de registro");

            registerPage.writeName("Ralf");
            registerPage.writeSurname("ElDemoledor");
            registerPage.writeMail("demoledorrrrralf@dh.com");
            registerPage.writeTelephone("115547812");
            registerPage.writePassword("admin1234");
            registerPage.writeConfirmPassword("admin1234");

            registerPage.clickNewsletterNo();
            registerPage.clickPrivacyPolicy();

            registerPage.clickContinue();
            test.log(Status.PASS, "Registo de usuario completado");

            Assertions.assertEquals(registerPage.getMessageRegistrationSuccess(),
                    "Congratulations! Your new account has been successfully created!");

        } catch (AssertionError e) {
            test.log(Status.FAIL, "Fallo la validacion" + e.getLocalizedMessage());
            throw e;
        }
    }

    @Test
    @Tag("Registro")
    public void searchProductAddTest()throws InterruptedException{
        ExtentTest test = extent.createTest("Prueba de Busqueda y agregado de producto a carrito");
        test.log(Status.INFO, "Comienza el Test");
        SearchProductAndAddPage searchPage = new SearchProductAndAddPage(driver, wait);

        try {
            searchPage.writeProduct("iphone");
            test.log(Status.INFO, "Texto iphone ingresado para busqueda");
            searchPage.cickSearch();
            Assertions.assertEquals(searchPage.getMessageSearchProduct(),"Search - iphone");
            test.log(Status.PASS, "Pagina en seccion de busqueda");
            searchPage.clickAddProductToCar();
            test.log(Status.PASS, "Producto encontrado y agregado al carrito con exito");

            Assertions.assertEquals(searchPage.getMessageAddProductSuccess(),"Success: You have added iPhone to your shopping cart!\n×");
        }  catch (AssertionError e) {
            test.log(Status.FAIL, "Fallo la validacion" + e.getLocalizedMessage());
            throw e;
        }
    }

    @AfterEach
    public void close() {
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.close();
    }

    @AfterAll
    public static void report() {
        extent.flush();
    }

}
