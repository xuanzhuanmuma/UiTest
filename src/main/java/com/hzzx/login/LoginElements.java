package com.hzzx.login;

import org.openqa.selenium.By;

public class LoginElements {
    public static By usernameBy = By.xpath(".//*[@id='username']");
    public static By passwordBy = By.xpath(".//*[@id='password']");
    public static By loginBtnBy = By.xpath(".//*[@id='login_btn']");
    public static By existBy = By.id("out");

    public static String usernameXpath = ".//*[@id='username']";
    public static String passwordXpath = ".//*[@id='password']";
    public static String loginBtnXpath = ".//*[@id='login_btn']";
    public static String existXpath = ".//*[@id='out']";
}
