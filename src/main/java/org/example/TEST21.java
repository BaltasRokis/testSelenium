package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
public class TEST21 {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.navigate().to("https://demoqa.com/");

        var widgets = driver.findElement(new By.ByXPath("/descendant::h5[text()='Widgets']"));
        widgets.click();

        var progressbar = driver.findElement(new By.ByXPath("/descendant::span[text()='Progress Bar']"));
        progressbar.click();

        var start = driver.findElement(new By.ById("startStopButton"));
        start.click();

        var wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(new By.ById("resetButton")));
        var reset  = driver.findElement(new By.ById("resetButton"));
        reset.click();

        var check = driver.findElement(new By.ByXPath("/descendant::div[@role='progressbar']"));
        var checktext = check.getText();
        if(checktext.equals("0%")){
            System.out.println("Passed");
        } else {
            System.out.println("Not 0 %");
        }
        driver.quit();


    }
}
