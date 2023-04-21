package com.qa.springdemo.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SeleniumDemo {

    private WebDriver driver;

    @BeforeEach
    void init() {
        // FirefoxOptions options = new FirefoxOptions();
        // options.addArguments("--remote-allow-origins=*");
        this.driver = new FirefoxDriver();
        this.driver.manage().window().maximize();
    }

    @Test
    void turtleTest() throws Exception {
        this.driver.get("https://www.wikipedia.org/");
        WebElement search = this.driver.findElement(
                By.cssSelector("#searchInput"));
        search.sendKeys("turtle");
        search.sendKeys(Keys.ENTER);

        Thread.sleep(1000);
        WebElement result = this.driver.findElement(
            By.cssSelector("#mw-content-text > div:nth-child(1) > p:nth-child(10) > a:nth-child(1)")
        );

        assertEquals("appeared in myths and folktales",result.getText());
        
    }

    @Test
    void penguinTest() throws Exception {
        this.driver.get("https://www.bing.com/");
        WebElement search = this.driver.findElement(
                By.cssSelector("#sb_form_q"));
        search.sendKeys("penguin");
        Thread.sleep(1000);
        search.sendKeys(Keys.ENTER);

        Thread.sleep(3000);
        WebElement result = this.driver.findElement(
            By.cssSelector(".b_topTitle > a:nth-child(1)")
        );

        assertEquals("Penguin Books UK | Official Website",result.getText());
        
    }

    @AfterEach
    void tearDown() {

    }
}
