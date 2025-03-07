package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TEST4 {
    @Test
    public void test() {
        var driver = new ChromeDriver();
        driver.navigate().to("https://demowebshop.tricentis.com/");
        driver.manage().window().maximize();

        var books = driver.findElement(By.xpath("/descendant::div[@class='header-menu']/descendant::a[@href='/books']"));
        books.click();
        var book1 = driver.findElement(By.xpath("/descendant::h2/descendant::a[@href='/computing-and-internet']"));
        book1.click();
        var addtocompare = driver.findElement(By.xpath("/descendant::input[@class='button-2 add-to-compare-list-button']"));
        addtocompare.click();

        books.click();
        var book2 = driver.findElement(By.xpath("/descendant::h2/descendant::a[@href='/copy-of-computing-and-internet']"));
        book2.click();
        addtocompare.click();

        books.click();
        var book3 = driver.findElement(By.xpath("/descendant::h2/descendant::a[@href='/fiction']"));
        book3.click();
        addtocompare.click();

        books.click();
        var book4 = driver.findElement(By.xpath("/descendant::h2/descendant::a[@href='/fiction-ex']"));
        book4.click();
        addtocompare.click();

        books.click();
        var book5 = driver.findElement(By.xpath("/descendant::h2/descendant::a[@href='/health']"));
        book5.click();
        addtocompare.click();

        var text = driver.findElement(By.xpath("/descendant::tr[@class='product-name']"));

        Assertions.assertFalse(text.getText().contains("Computing and Internet"));

        driver.quit();

    }
}
