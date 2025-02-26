package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.Instant;

public class TEST22 {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.navigate().to("https://demoqa.com/");

        var elements = driver.findElement(new By.ByXPath("/descendant::h5[text()='Elements']"));
        elements.click();

        var webtables = driver.findElement(new By.ByXPath("/descendant::span[text()='Web Tables']"));
        webtables.click();

        var pagenumber = driver.findElement(new By.ByXPath("/descendant::span[@class='-totalPages']"));


        var next = driver.findElement(new By.ByXPath("/descendant::button[text()='Next']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", next);
        //((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 100);");

        while(!pagenumber.getText().equals("2")) {
            var addbutton = driver.findElement(new By.ById("addNewRecordButton"));
            addbutton.click();

            var firstname = driver.findElement(new By.ById("firstName"));
            firstname.sendKeys("Tavo");
            var lastname = driver.findElement(new By.ById("lastName"));
            lastname.sendKeys("Mama");
            var email = driver.findElement(new By.ById("userEmail"));
            email.sendKeys("tavo.mama@gmail.com");
            var age = driver.findElement(new By.ById("age"));
            age.sendKeys("69");
            var salary = driver.findElement(new By.ById("salary"));
            salary.sendKeys("420");
            var department = driver.findElement(new By.ById("department"));
            department.sendKeys("Epic one");

            var submitbutton = driver.findElement(new By.ById("submit"));
            submitbutton.click();
        }
        next.click();

        var deletes = driver.findElements(new By.ByXPath("/descendant::span[@title='Delete']"));
        for (var delete : deletes) {
            delete.click();
        }

        if(pagenumber.getText().equals("1")){
            System.out.println("Passed");
        } else {
            System.out.println("Not 1");
        }
        driver.quit();

    }
}
