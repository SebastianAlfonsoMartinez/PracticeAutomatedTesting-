package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    private final By searchMyAccount = By.xpath("//*[@id='top-links']/ul/li[2]/a/span[1]");
    private final By searchRegister = By.xpath("//*[@id='top-links']/ul/li[2]/ul/li[1]/a");
    private final By searchLogin = By.xpath("//ul[@id='top-links']//a[text()='Login']");
    private final By searchLogo = By.id("logo");


    public static WebDriver driver;
    public static WebDriverWait wait;

    public BasePage(WebDriver driver, WebDriverWait wait) {
        BasePage.driver = driver;
        BasePage.wait = wait;
    }

    public void setUp(){driver.manage().window().maximize();}

    public void getUrl(String url){driver.get(url);}
    public void close() {
        driver.quit();
    }
    protected WebElement elementFind(By locator) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElement(locator);
    }
    protected void sendText(String imputText, By locator) throws InterruptedException {
        this.elementFind(locator).clear();
        this.elementFind(locator).sendKeys(imputText);
    }
    protected void sendKey(CharSequence key, By locator) throws InterruptedException {
        this.elementFind(locator).sendKeys(key);
    }
    protected void click(By locator) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        this.elementFind(locator).click();
    }

    protected String getText(By locator) throws InterruptedException {
        return this.elementFind(locator).getText();
    }

    public void clickMyAccount() throws InterruptedException {
        this.click(searchMyAccount);
    }

    public void clickRegister() throws InterruptedException {
        this.click(searchRegister);
    }
    public String checkWindowPopupRegister() throws InterruptedException {
        System.out.println("Contenido ventana emergente: " + this.getText(searchRegister));
        return this.getText(searchRegister);
    }

    public String checkWindowPopupLogin() throws InterruptedException {
        System.out.println("Contenido ventana emergente: " + this.getText(searchLogin));
        return this.getText(searchLogin);
    }

    public void clickLogo() throws InterruptedException {
        this.click(searchLogo);
    }


}
