package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TEST1 {
    public static void main(String[] args) {

        var driver = new ChromeDriver();
        driver.navigate().to("https://demowebshop.tricentis.com/");

        var giftcardsection = driver.findElement(new By.ByXPath("/descendant::div[@class='listbox']/descendant::a[@href='/gift-cards']"));
        giftcardsection.click();

        var selectGC = driver.findElement(new By.ByXPath("/descendant::div[@class='item-box'][descendant::span[contains(@class,'price')][text()>'99']]/descendant::a[contains(@href,'physical-gift-card')]"));
        selectGC.click();

        var recipientName = driver.findElement  (new By.ByXPath("/descendant::input[@class='recipient-name']"));
        recipientName.sendKeys("Tavo mama");

        var yourName = driver.findElement(new By.ByXPath("/descendant::input[@class='sender-name']"));
        yourName.sendKeys("Amogus");

        var qty =driver.findElement(new By.ById("addtocart_4_EnteredQuantity"));
        qty.clear();
        qty.sendKeys("5000");

        var addtocart = driver.findElement(new By.ById("add-to-cart-button-4"));
        addtocart.click();

        var wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(new By.ByXPath("/descendant::div[@class='ajax-loading-block-window']")));
        var wishlistbutton = wait.until(ExpectedConditions.elementToBeClickable(new By.ByXPath("/descendant::input[contains(@class,'add-to-wishlist')]")));;
        wishlistbutton.click();

        var jewelry = driver.findElement(new By.ByXPath("/descendant::div[@class='listbox']/descendant::a[@href='/jewelry']"));
        jewelry.click();

        var jewelryyourself = driver.findElement(new By.ByXPath("/descendant::a[@href='/create-it-yourself-jewelry']"));
        jewelryyourself.click();

        var dropdownmaterial = driver.findElement(new By.ById("product_attribute_71_9_15"));
        Select dropdown = new Select(dropdownmaterial);
        dropdown.selectByContainsVisibleText("Silver (1 mm)");

        var length = driver.findElement(new By.ById("product_attribute_71_10_16"));
        length.sendKeys("80");

        var pendantstar = driver.findElement(new By.ById("product_attribute_71_11_17_50"));
        pendantstar.click();

        var qty2 =driver.findElement(new By.ById("addtocart_71_EnteredQuantity"));
        qty2.clear();
        qty2.sendKeys("26");

        var addtocart2 = driver.findElement(new By.ById("add-to-cart-button-71"));
        addtocart2.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(new By.ByXPath("/descendant::div[@class='ajax-loading-block-window']")));
        var wishlistbutton2 = wait.until(ExpectedConditions.elementToBeClickable(new By.ByXPath("/descendant::input[contains(@class,'add-to-wishlist')]")));
        wishlistbutton2.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(new By.ByXPath("/descendant::div[@class='ajax-loading-block-window']")));
        var wishlist = driver.findElement(new By.ByXPath("/descendant::div[@class='header-links']/descendant::a[@href='/wishlist']"));
        wishlist.click();

        var checkboxes = driver.findElements(new By.ByXPath("/descendant::td[@class='add-to-cart']/descendant::input[@type='checkbox']"));
        for(var checkbox : checkboxes) {
            checkbox.click();
        }

        var wishlistcart = driver.findElement(new By.ByXPath("/descendant::input[@name='addtocartbutton']"));
        wishlistcart.click();

        var subtotal = driver.findElement(new By.ByXPath("/descendant::tr[descendant::span[text()='Sub-Total:']]/descendant::span[@class='product-price']"));
        var subTotalText = subtotal.getText();
        if (subTotalText.equals("1002600.00")) {
            System.out.println("Passed");
        } else {
            System.out.println("Not 1002600.00");
        }
        driver.quit();
    }
}