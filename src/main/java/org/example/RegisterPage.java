package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage {
    private final By searchFormRegister = By.xpath("//*[@id='account']/legend");
    private final By searchFirstName = By.id("input-firstname");
    private final By searchLastName = By.id("input-lastname");
    private final By searchEmail = By.id("input-email");
    private final By searchTelephone = By.id("input-telephone");
    private final By searchPassword = By.id("input-password");
    private final By searchPasswordConfirm = By.id("input-confirm");

    private final By searchNewsletterNo = By.xpath("//*[@id='content']/form/fieldset[3]/div/div/label[2]");

    private final By searchPrivacyPolicy = By.xpath("//input[@name='agree']");
    private final By searchBottomContinue = By.cssSelector(".btn.btn-primary");
    private final By searchRegisterSuccessfully = By.xpath("//*[@id='content']/p[1]");


    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public String getTitleRegistration() throws InterruptedException {
        System.out.println("Me encuentro en la página de Registro: " + getText(searchFormRegister));
        return this.getText(searchFormRegister);
    }
    public String getMessageRegistrationSuccess() throws InterruptedException {
        System.out.println("Se creo la cuenta: " + getText(searchRegisterSuccessfully));
        return this.getText(searchRegisterSuccessfully);
    }

    public void writeName(String name) throws InterruptedException {
        sendText(name, searchFirstName);
    }

    public void writeSurname(String lastName) throws InterruptedException {
        sendText(lastName, searchLastName);
    }

    public void writeMail(String mail) throws InterruptedException {
        sendText(mail, searchEmail);
    }

    public void writeTelephone(String number) throws InterruptedException {
        sendText(number, searchTelephone);
    }

    public void writePassword(String clave) throws InterruptedException {
        sendText(clave, searchPassword);
    }

    public void writeConfirmPassword(String clave) throws InterruptedException {
        sendText(clave, searchPasswordConfirm);
    }

    public void clickNewsletterNo() throws InterruptedException {
        click(searchNewsletterNo);
    }

    public void clickPrivacyPolicy() throws InterruptedException {
        click(searchPrivacyPolicy);
    }


    public void clickContinue() throws InterruptedException {
        click(searchBottomContinue);
    }
}
