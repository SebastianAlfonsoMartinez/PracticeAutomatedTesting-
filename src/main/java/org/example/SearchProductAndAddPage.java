package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchProductAndAddPage extends BasePage{

    private final By searchBar = By.xpath("//*[@id='search']/input");
    private final By searchBottomProduct = By.className("input-group-btn");
    private final By searchResultProduct = By.xpath("//*[@id='content']/h1");
    private final By searchAddToCar = By.xpath("//*[@id='content']/div[3]/div/div/div[2]/div[2]/button[1]");
    private final By searchAddProductoSuccess = By.className("alert-success");


    public SearchProductAndAddPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void cickSearch() throws InterruptedException{
        Thread.sleep(1000);
        this.click(searchBottomProduct);
    }

    public void writeProduct(String searchProduct) throws InterruptedException{
        Thread.sleep(1000);
        this.sendText(searchProduct, searchBar );
    }

    public void clickAddProductToCar ()throws InterruptedException{
        Thread.sleep(1000);
        this.click(searchAddToCar);
    }

    public String getMessageSearchProduct() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Mensaje de producto agregado: " + getText(searchResultProduct));
        return this.getText(searchResultProduct);
    }

    public String getMessageAddProductSuccess() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Mensaje de producto agregado: " + getText(searchAddProductoSuccess));
        return this.getText(searchAddProductoSuccess);
    }


}
