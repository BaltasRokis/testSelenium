package org.example;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.UUID;

public class TEST3 {

    public static String registeremail = "kazkas"+ UUID.randomUUID()+"@gmail.com";
    public static String registerpassword = "kazkas";
    private WebDriver driver;


    @BeforeAll
    public static void registeruser() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://demowebshop.tricentis.com/");

        var login = driver.findElement(new By.ByXPath("/descendant::a[@href='/login']"));
        login.click();

        var register = driver.findElement(new By.ByXPath("/descendant::input[@class = 'button-1 register-button']"));
        register.click();

        var gender = driver.findElement(new By.ById("gender-male"));
        gender.click();

        var firstname = driver.findElement(new By.ById("FirstName"));
        firstname.sendKeys("Tavo");

        var lastname = driver.findElement(new By.ById("LastName"));
        lastname.sendKeys("Mama");

        var email = driver.findElement(new By.ById("Email"));
        email.sendKeys(registeremail);

        var password = driver.findElement(new By.ById("Password"));
        password.sendKeys(registerpassword);

        var confirmpassword = driver.findElement(new By.ById("ConfirmPassword"));
        confirmpassword.sendKeys(registerpassword);

        var registerbutton = driver.findElement(new By.ById("register-button"));
        registerbutton.click();

        var continuebutton = driver.findElement(new By.ByXPath("/descendant::input[@class = 'button-1 register-continue-button']"));
        continuebutton.click();
        driver.quit();
    }


    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://demowebshop.tricentis.com/");

        var login = driver.findElement(new By.ByXPath("/descendant::a[@href='/login']"));
        login.click();

        var email = driver.findElement(new By.ById("Email"));
        email.sendKeys(registeremail);
        var password = driver.findElement(new By.ById("Password"));
        password.sendKeys(registerpassword);

        var loginbutton = driver.findElement(new By.ByXPath("/descendant::input[@class='button-1 login-button']"));
        loginbutton.click();

    }
    @Test
    public void test1()throws IOException{
        buyitems("text1.txt");
    }

    @Test
    public void test2()throws IOException{
        buyitems("text2.txt");
    }

    public void buyitems (String filename) throws IOException {

        var digital = driver.findElement(new By.ByXPath("/descendant::div[@class='listbox']/descendant::a[@href='/digital-downloads']"));
        digital.click();

        var wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/"+filename));
        String product;
        while ((product = reader.readLine()) != null){
            try{
                var element = driver.findElement(new By.ByXPath("/descendant::div[@class= 'item-box'][descendant::a[text()= '" + product +"']]/descendant::input"));
                element.click();
                wait.until(ExpectedConditions.invisibilityOfElementLocated(new By.ByXPath("/descendant::div[@class='ajax-loading-block-window']")));
            } catch (Exception e){
                System.out.println("Product not found: "+product);
            }
        }
        reader.close();

        var shoppingcart = driver.findElement(new By.ByXPath("/descendant::a[@href= '/cart']/descendant::span[@class='cart-label']"));
        shoppingcart.click();

        var tos = driver.findElement(new By.ById("termsofservice"));
        tos.click();

        var checkout = driver.findElement(new By.ById("checkout"));
        checkout.click();
        try{
            var dropdowncountry = driver.findElement(new By.ById("BillingNewAddress_CountryId"));
            Select dropdown = new Select(dropdowncountry);
            dropdown.selectByContainsVisibleText("Lithuania");

            var city = driver.findElement(new By.ById("BillingNewAddress_City"));
            city.sendKeys("Vilnius");

            var address = driver.findElement(new By.ById("BillingNewAddress_Address1"));
            address.sendKeys("Didlaukio g. 47");

            var zip = driver.findElement(new By.ById("BillingNewAddress_ZipPostalCode"));
            zip.sendKeys("08303");

            var phone = driver.findElement(new By.ById("BillingNewAddress_PhoneNumber"));
            phone.sendKeys("+37069420420");
        }
        catch (Exception e){
            System.out.println();
        }


        var continue1 = driver.findElement(new By.ByXPath("/descendant::input[@title = 'Continue']"));
        continue1.click();

        var paymentmethod = driver.findElement(new By.ByXPath("/descendant::input[@class='button-1 payment-method-next-step-button']"));
        wait.until(ExpectedConditions.elementToBeClickable(paymentmethod));
        paymentmethod.click();

        var paymentinformation = driver.findElement(new By.ByXPath("/descendant::input[@class='button-1 payment-info-next-step-button']"));
        wait.until(ExpectedConditions.elementToBeClickable(paymentinformation));
        paymentinformation.click();

        var confirmorder = driver.findElement(new By.ByXPath("/descendant::input[@class='button-1 confirm-order-next-step-button']"));
        wait.until(ExpectedConditions.elementToBeClickable(confirmorder));
        confirmorder.click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/descendant::div[contains(@class, 'order-completed')]/descendant::a[contains(@href, '/orderdetails/')]")));

        var end = driver.findElement(new By.ByXPath("/descendant::div[@class='section order-completed']/descendant::div[@class='title']/descendant::strong"));


        Assertions.assertEquals("Your order has been successfully processed!", end.getText(), "testas praejo gerai");
        if(end.getText().equals("Your order has been successfully processed!")){
            System.out.println("testas praejo gerai");
        }

    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }



}
