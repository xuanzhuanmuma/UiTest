package com.hzzx.updatepassword;

import org.openqa.selenium.By;

public class UpdatePasswordElements {
    public static By sysManagerBy = By.xpath(".//*[@id='menu']/li[8]/a/span");
    public static By peopleMangerBy = By.xpath(".//*[@id='collapse-44c896e862264fc883b7dbe35a56ea55']/div/ul/li[2]/a");
    public static String iframe1Id = "mainFrame";
    public static String iframe2Id = "officeContent";
    public static String userXpath= ".//*[@id='contentTable']/tbody/tr[4]/td[7]/a[1]";
    public static By changePassBy = By.linkText("修改密码");

    public static String oldPasswordId = "";
    public static String newPasswordId = "newPassword";
    public static String confirmPasswordId = "confirmNewPassword";
    public static String saveId = "btnSubmit";
    public static String cancelId = "";
}
