package com.hzzx.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 基于selenium的二次封装
 * https://www.cnblogs.com/wang1001/p/9556158.html
 */
public class WebDriverUtil {
    private static WebDriver sDriver = null;
    private static Select sSelect = null;
    private static Alert sAlert = null;
    private static WebElement sElement = null;
    private static List<WebElement> sElementList = null;
    private static long sTimeOutInSeconds = 10;
    //==================自定义常量=================
    public final String LINE = "\r\n";
    public final String smile = "^_^";
    public final String sad = "*o*";

    public WebDriverUtil() {
    }

    /**
     * 指定浏览器打开URL
     */
    public void openBrowser(String url) {
        sDriver = initBrowser();
        sDriver.manage().timeouts().implicitlyWait(sTimeOutInSeconds, TimeUnit.SECONDS);
        sDriver.get(url);
    }

    /**
     * 初始化浏览器方式1
     * firefox
     */
    public WebDriver initBrowser() {
        String path = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", path + "/drivers/chromedriver.exe");
        sDriver = new ChromeDriver();
        return sDriver;
    }

    // ========================元素相关=======================
    /**
     * 查找元素
     * @param by      传入一个类型        例如：name
     * @param byValue 传入一个类型值      例如：username
     */
    public WebElement findElement(String by, String byValue) {
        try {
            switch (by) {
                case "id":
                    sElement = sDriver.findElement(By.id(byValue));
                    break;
                case "name":
                    sElement = sDriver.findElement(By.name(byValue));
                    break;
                case "class":
                    sElement = sDriver.findElement(By.className(byValue));
                    break;
                case "tag":
                    sElement = sDriver.findElement(By.tagName(byValue));
                    break;
                case "link":
                    sElement = sDriver.findElement(By.linkText(byValue));
                    break;
                case "partiallinktext":
                    sElement = sDriver.findElement(By.partialLinkText(byValue));
                    break;
                case "css":
                    sElement = sDriver.findElement(By.cssSelector(byValue));
                    break;
                case "xpath":
                    sElement = sDriver.findElement(By.xpath(byValue));
                    break;
                default:
                    throw new RuntimeException("输入的定位类型未在程序中定义，类型为：" + byValue);
            }
        } catch (Exception e) {
            System.out.println("没有找到元素：" + byValue);
        }
        return sElement;
    }

    /**
     * 查找一组元素
     * @param byType         例如：name
     * @param byValue    例如：username
     * @return
     */
    public List<WebElement> findElements(String byType, String byValue) {
        try {
            switch (byType) {
                case "id":
                    sElementList = sDriver.findElements(By.id(byValue));
                    break;
                case "name":
                    sElementList = sDriver.findElements(By.name(byValue));
                    break;
                case "class":
                    sElementList = sDriver.findElements(By.className(byValue));
                    break;
                case "tag":
                    sElementList = sDriver.findElements(By.tagName(byValue));
                    break;
                case "link":
                    sElementList = sDriver.findElements(By.linkText(byValue));
                    break;
                case "partiallinktext":
                    sElementList = sDriver.findElements(By.partialLinkText(byValue));
                    break;
                case "css":
                    sElementList = sDriver.findElements(By.cssSelector(byValue));
                    break;
                case "xpath":
                    sElementList = sDriver.findElements(By.xpath(byValue));
                    break;
                default:
                    throw new RuntimeException("输入的定位类型未在程序中定义，类型为：" + byValue);
            }
        } catch (Exception e) {
            System.out.println("没有找到元素：" + byValue);
        }
        return sElementList;
    }

    /**
     * 获取单个元素
     */
    public WebElement findElementById(String id) {
        return sDriver.findElement(By.id(id));
    }
    public WebElement findElementByName(String name) {
        return sDriver.findElement(By.name(name));
    }
    public WebElement findElementByClassName(String name) {
        return sDriver.findElement(By.className(name));
    }
    public WebElement findElementByTagName(String tag) {
        return sDriver.findElement(By.tagName(tag));
    }
    public WebElement findElementByLinkText(String text) {
        return sDriver.findElement(By.linkText(text));
    }
    public WebElement findElementByPartialLinkText(String text) {
        return sDriver.findElement(By.partialLinkText(text));
    }
    public WebElement findElementByXpath(String xpath) {
        return sDriver.findElement(By.xpath(xpath));
    }
    public WebElement findElementByCssSelector(String css) {
        return sDriver.findElement(By.cssSelector(css));
    }

    /**
     * 获取多个元素
     */
    public List<WebElement> findElementsById(String id) {
        return sDriver.findElements(By.id(id));
    }
    public List<WebElement> findElementsByName(String name) {
        return sDriver.findElements(By.name(name));
    }
    public List<WebElement> findElementsByClassName(String name) {
        return sDriver.findElements(By.className(name));
    }
    public List<WebElement> findElementsByTag(String tag) {
        return sDriver.findElements(By.tagName(tag));
    }
    public List<WebElement> findElementsByText(String text) {
        return sDriver.findElements(By.linkText(text));
    }
    public List<WebElement> findElementsByPartialText(String text) {
        return sDriver.findElements(By.partialLinkText(text));
    }
    public List<WebElement> findElementsByXpath(String xpath) {
        return sDriver.findElements(By.xpath(xpath));
    }
    public List<WebElement> findElementsByCss(String css) {
        return sDriver.findElements(By.cssSelector(css));
    }

    /**
     * 获取一组元素中的指定元素
     */
    public WebElement findByElements(By by, int index) {
        WebElement element = null;
        if (this.elementsExists(by)) {
            element = sDriver.findElements(by).get(index);
        }
        return element;
    }

    /**
     * 查找元素并点击
     * @param byType      传入一个类型        例如：name
     * @param byValue 传入一个类型值       例如：username
     */
    public boolean findElementClick(String byType, String byValue) {
        try {
            switch (byType) {
                case "id":
                    sDriver.findElement(By.id(byValue)).click();
                    return true;
                case "name":
                    sDriver.findElement(By.name(byValue)).click();
                    return true;
                case "class":
                    sDriver.findElement(By.className(byValue)).click();
                    return true;
                case "tag":
                    sDriver.findElement(By.tagName(byValue)).click();
                    return true;
                case "link":
                    sDriver.findElement(By.linkText(byValue)).click();
                    return true;
                case "partiallinktext":
                    sDriver.findElement(By.partialLinkText(byValue)).click();
                    return true;
                case "xpath":
                    sDriver.findElement(By.xpath(byValue)).click();
                    return true;
                case "css":
                    sDriver.findElement(By.cssSelector(byValue)).click();
                    return true;
                default:
                    throw new RuntimeException("输入的定位类型未在程序中定义，类型为：" + byValue);
            }
        } catch (Exception e) {
            System.out.println("*****没有找到元素,类型为：:" + byType + "属性值为：" + byValue + "  的元素或者该元素无法点击****");
            return false;
        }
    }

    /**
     * 定位元素并点击
     */
    public void findElementAndClick(By by) {
        sDriver.findElement(by).click();
    }
    public void findElementByIdAndClick(String id) {
        sDriver.findElement(By.id(id)).click();
    }
    public void findElementByNameAndClick(String name) {
        sDriver.findElement(By.name(name)).click();
    }
    public void findElementByClassNameAndClick(String className) {
        sDriver.findElement(By.className(className)).click();
    }
    public void findElementByTagNameAndClick(String tagName) {
        sDriver.findElement(By.tagName(tagName)).click();
    }
    public void findElementBylinkTextAndClick(String linkText) {
        sDriver.findElement(By.linkText(linkText)).click();
    }
    public void findElementBypartialLinkTextAndClick(String partialLinkText) {
        sDriver.findElement(By.partialLinkText(partialLinkText)).click();
    }
    public void findElementByXpathAndClick(String xpath) {
        sDriver.findElement(By.xpath(xpath)).click();
    }
    public void findElementByCssSelectorAndClick(String cssSelector) {
        sDriver.findElement(By.cssSelector(cssSelector)).click();
    }

    /**
     * 查找元素并清除文本内容
     * @param byType      传入一个类型        例如：name
     * @param byValue 传入一个类型值       例如：username
     */
    public boolean findElementClear(String byType, String byValue) {
        try {
            switch (byType) {
                case "id":
                    sDriver.findElement(By.id(byValue)).clear();
                    return true;
                case "name":
                    sDriver.findElement(By.name(byValue)).clear();
                    return true;
                case "class":
                    sDriver.findElement(By.className(byValue)).clear();
                    return true;
                case "tag":
                    sDriver.findElement(By.tagName(byValue)).clear();
                    return true;
                case "link":
                    sDriver.findElement(By.linkText(byValue)).clear();
                    return true;
                case "partiallinktext":
                    sDriver.findElement(By.partialLinkText(byValue)).clear();
                    return true;
                case "xpath":
                    sDriver.findElement(By.cssSelector(byValue)).clear();
                    return true;
                case "css":
                    sDriver.findElement(By.xpath(byValue)).clear();
                    return true;
                default:
                    throw new RuntimeException("输入的定位类型未在程序中定义，类型为：" + byValue);
            }
        } catch (Exception e) {
            System.out.println("*****没有找到元素,类型为：:" + byType + "属性值为：" + byValue + "  的元素或者该元素没有输入值****");
            return false;
        }
    }

    /**
     * 查找元素并输入值
     * @param byType   传入一个类型        例如：name
     * @param byValue 传入一个类型值       例如：username
     * @param key     填写要输入的值        例如：zhangsan
     */
    public boolean findElementSendKeys(String byType, String byValue, String key) {
        try {
            switch (byType) {
                case "id":
                    sDriver.findElement(By.id(byValue)).sendKeys(key);
                    return true;
                case "name":
                    sDriver.findElement(By.name(byValue)).sendKeys(key);
                    return true;
                case "class":
                    sDriver.findElement(By.className(byValue)).sendKeys(key);
                    return true;
                case "tag":
                    sDriver.findElement(By.tagName(byValue)).sendKeys(key);
                    return true;
                case "link":
                    sDriver.findElement(By.linkText(byValue)).sendKeys(key);
                    return true;
                case "partiallinktext":
                    sDriver.findElement(By.partialLinkText(byValue)).sendKeys(key);
                    return true;
                case "xpath":
                    sDriver.findElement(By.xpath(byValue)).sendKeys(key);
                    return true;
                case "css":
                    sDriver.findElement(By.cssSelector(byValue)).sendKeys(key);
                    return true;
                default:
                    throw new RuntimeException("输入的定位类型未在程序中定义，类型为：" + byValue);
            }
        } catch (Exception e) {
            System.out.println("*****没有找到元素,类型为：:" + byType + "属性值为：" + byValue + "    的元素或者该元素无法输入****");
            return false;
        }
    }

    /**
     * 查找元素并输入值
     * @param byType      传入一个类型        例如：name
     * @param byValue 传入一个类型值       例如：username
     * @param key     填写要输入的值        例如：zhangsan
     */
    public boolean findElementClearAndSendKeys(String byType, String byValue, String key) {
        try {
            switch (byType) {
                case "id":
                    findElementClear(byType, byValue);
                    sDriver.findElement(By.id(byValue)).sendKeys(key);
                    return true;
                case "name":
                    findElementClear(byType, byValue);
                    sDriver.findElement(By.name(byValue)).sendKeys(key);
                    return true;
                case "class":
                    findElementClear(byType, byValue);
                    sDriver.findElement(By.className(byValue)).sendKeys(key);
                    return true;
                case "tag":
                    findElementClear(byType, byValue);
                    sDriver.findElement(By.tagName(byValue)).sendKeys(key);
                    return true;
                case "link":
                    findElementClear(byType, byValue);
                    sDriver.findElement(By.linkText(byValue)).sendKeys(key);
                    return true;
                case "partiallinktext":
                    findElementClear(byType, byValue);
                    sDriver.findElement(By.partialLinkText(byValue)).sendKeys(key);
                    return true;
                case "xpath":
                    findElementClear(byType, byValue);
                    sDriver.findElement(By.xpath(byValue)).sendKeys(key);
                    return true;
                case "css":
                    findElementClear(byType, byValue);
                    sDriver.findElement(By.cssSelector(byValue)).sendKeys(key);
                    return true;
                default:
                    throw new RuntimeException("输入的定位类型未在程序中定义，类型为：" + byValue);
            }
        } catch (Exception e) {
            System.out.println("*****没有找到元素,类型为：:" + byType + "属性值为：" + byValue + "    的元素或者该元素无法输入****");
            return false;
        }
    }

    /**
     * 定位元素并清空文本内容，输入相应的值
     */
    public void findElementClearAndSendKeys(By by, String text) {
        sDriver.findElement(by).clear();
        sDriver.findElement(by).sendKeys(text);
    }
    public void findElementByIdAndClearSendkeys(String id, String text) {
        sDriver.findElement(By.id(id)).clear();
        sDriver.findElement(By.id(id)).sendKeys(text);
    }
    public void findElementByNameAndClearSendkeys(String name, String text) {
        sDriver.findElement(By.name(name)).clear();
        sDriver.findElement(By.name(name)).sendKeys(text);
    }
    public void findElementByClassNameAndClearSendkeys(String className, String text) {
        sDriver.findElement(By.className(className)).clear();
        sDriver.findElement(By.className(className)).sendKeys(text);
    }
    public void findElementByTagNameAndClearSendkeys(String tagName, String text) {
        sDriver.findElement(By.tagName(tagName)).clear();
        sDriver.findElement(By.tagName(tagName)).sendKeys(text);
    }
    public void findElementBylinkTextAndClearSendkeys(String linkText, String text) {
        sDriver.findElement(By.linkText(linkText)).clear();
        sDriver.findElement(By.linkText(linkText)).sendKeys(text);
    }
    public void findElementByPartialLinkTextAndClearSendkeys(String partialLinkText, String text) {
        sDriver.findElement(By.partialLinkText(partialLinkText)).clear();
        sDriver.findElement(By.partialLinkText(partialLinkText)).sendKeys(text);
    }
    public void findElementByXpathAndClearSendkeys(String xpath, String text) {
        sDriver.findElement(By.xpath(xpath)).clear();
        sDriver.findElement(By.xpath(xpath)).sendKeys(text);
    }
    public void findElementByCssSelectorAndClearSendkeys(String cssSelector, String text) {
        sDriver.findElement(By.cssSelector(cssSelector)).clear();
        sDriver.findElement(By.cssSelector(cssSelector)).sendKeys(text);
    }

    /**
     * 定位元素，并获取其文本内容
     */
    public String getTextById(String id) {
        return findElementById(id).getText();
    }
    public String getTextByName(String name) {
        return findElementByName(name).getText();
    }
    public String getTextByClassName(String className) {
        return findElementByClassName(className).getText();
    }
    public String getTextByTagName(String tagName) {
        return findElementByTagName(tagName).getText();
    }
    public String getTextByLinkText(String linkText) {
        return findElementByLinkText(linkText).getText();
    }
    public String getTextByPartialLinkText(String partialLinkText) {
        return findElementByPartialLinkText(partialLinkText).getText();
    }
    public String getTextByXpath(String xpath) {
        return findElementByXpath(xpath).getText();
    }
    public String getTextByCssSelector(String css) {
        return findElementByCssSelector(css).getText();
    }

    /**
     * 定位元素，并制定点击次数（连续点击）
     */
    public boolean clickById(String id, int clickCount) {
        try {
            for (int i = 0; i < clickCount; i++) {
                sDriver.findElement(By.id(id)).click();
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean clickByName(String name, int clickCount) {
        try {
            for (int i = 0; i < clickCount; i++) {
                sDriver.findElement(By.name(name)).click();
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean clickByClassName(String className, int clickCount) {
        try {
            for (int i = 0; i < clickCount; i++) {
                sDriver.findElement(By.className(className)).click();
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean clickByTagName(String tagName, int clickCount) {
        try {
            for (int i = 0; i < clickCount; i++) {
                sDriver.findElement(By.tagName(tagName)).click();
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean clickByLinkText(String linkText, int clickCount) {
        try {
            for (int i = 0; i < clickCount; i++) {
                sDriver.findElement(By.linkText(linkText)).click();
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean clickByPartialLinkText(String partialLinkText, int clickCount) {
        try {
            for (int i = 0; i < clickCount; i++) {
                sDriver.findElement(By.partialLinkText(partialLinkText)).click();
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean clickByXpath(String xpath, int clickCount) {
        try {
            for (int i = 0; i < clickCount; i++) {
                sDriver.findElement(By.xpath(xpath)).click();
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean clickByCssSelector(String cssSelector, int clickCount) {
        try {
            for (int i = 0; i < clickCount; i++) {
                sDriver.findElement(By.cssSelector(cssSelector)).click();
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * 判断一个元素是否存在
     */
    public boolean exists(By by) {
        sDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);// 设置查询组件等待时间
        try {
            sDriver.findElement(by);
            sDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);// 设置查询组件等待时间
            return true;
        } catch (Exception e) {
            sDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);// 设置查询组件等待时间
            return false;
        }
    }

    /**
     * 判断一个元素是否存在
     */
    public boolean isElementExist(By by) {
        try {
            sDriver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * 判断一组元素是否存在
     */
    public boolean elementsExists(By by) {
        return (sDriver.findElements(by).size() > 0) ? true : false;
    }

    // ===========================判断页面是否包含指定文本===================
    /**
     * 指定时间内等待直到页面包含文本字符串
     * @param text   期望出现的文本
     * @param seconds  超时时间
     * @return Boolean  检查给定文本是否存在于指定元素中, 超时则捕获抛出异常TimeoutException并返回false
     */
    public static Boolean waitUntilPageContainText(String text, long seconds, By by) {
        if (seconds <= 0) {
            seconds = sTimeOutInSeconds;
        }
        try {
            return new WebDriverWait(sDriver, seconds)
                    .until(ExpectedConditions.textToBePresentInElement(
                            sDriver.findElement(by), text));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static Boolean waitUntilPageContainText(String text, By by) {
        return waitUntilPageContainText(text, 0, by);
    }

    // ============================元素判断========================
    /**
     * 指定时间内等待直到元素存在于页面的DOM上并可见, 可见性意味着该元素不仅被显示, 而且具有大于0的高度和宽度
     * @param by 元素定位器
     * @param seconds  超时时间
     * @return Boolean 检查给定元素的定位器是否出现, 超时则捕获抛出异常TimeoutException并返回false
     */
    public static Boolean waitUntilElementVisible(By by, long seconds) {
        if (seconds <= 0) {
            seconds = sTimeOutInSeconds;
        }
        try {
            new WebDriverWait(sDriver, seconds)
                    .until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static Boolean waitUntilElementVisible(By by) {
        return waitUntilElementVisible(by, 0);
    }

    /**
     * 判断元素是否显示
     */
    public boolean getDisplayStatById(String id) {
        return sDriver.findElement(By.id(id)).isDisplayed();
    }
    public boolean getDisplayStatByName(String name) {
        return sDriver.findElement(By.name(name)).isDisplayed();
    }
    public boolean getDisplayStatByClassName(String className) {
        return sDriver.findElement(By.className(className)).isDisplayed();
    }
    public boolean getDisplayStatByTagName(String tagName) {
        return sDriver.findElement(By.tagName(tagName)).isDisplayed();
    }
    public boolean getDisplayStatByLinkText(String linkText) {
        return sDriver.findElement(By.linkText(linkText)).isDisplayed();
    }
    public boolean getDisplayStatByPartialLinkText(String partialLinkText) {
        return sDriver.findElement(By.partialLinkText(partialLinkText)).isDisplayed();
    }
    public boolean getDisplayStatByXpath(String xpath) {
        return sDriver.findElement(By.xpath(xpath)).isDisplayed();
    }
    public boolean getDisplayStatByCssSelector(String cssSelector) {
        return sDriver.findElement(By.cssSelector(cssSelector)).isDisplayed();
    }

    /**
     * 判断元素是否可写
     */
    public boolean getEnableStatById(String id) {
        return sDriver.findElement(By.id(id)).isEnabled();
    }
    public boolean getEnableStatByName(String name) {
        return sDriver.findElement(By.name(name)).isEnabled();
    }
    public boolean getEnableStatByClassName(String className) {
        return sDriver.findElement(By.className(className)).isEnabled();
    }
    public boolean getEnableStatByTagName(String tagName) {
        return sDriver.findElement(By.tagName(tagName)).isEnabled();
    }
    public boolean getEnableStatByLinkText(String linkText) {
        return sDriver.findElement(By.linkText(linkText)).isEnabled();
    }
    public boolean getEnableStatByPartialLinkText(String partialLinkText) {
        return sDriver.findElement(By.partialLinkText(partialLinkText)).isEnabled();
    }
    public boolean getEnableStatByXpath(String xpath) {
        return sDriver.findElement(By.xpath(xpath)).isEnabled();
    }
    public boolean getEnableStatByCssSelector(String cssSelector) {
        return sDriver.findElement(By.cssSelector(cssSelector)).isEnabled();
    }

    /**
     * 判断袁术是否选中
     */
    public boolean getSelectStatById(String id) {
        return sDriver.findElement(By.id(id)).isSelected();
    }
    public boolean getSelectStatByName(String name) {
        return sDriver.findElement(By.name(name)).isSelected();
    }
    public boolean getSelectStatByClassName(String className) {
        return sDriver.findElement(By.className(className)).isSelected();
    }
    public boolean getSelectStatByTagName(String tagName) {
        return sDriver.findElement(By.tagName(tagName)).isSelected();
    }
    public boolean getSelectStatByLinkText(String linkText) {
        return sDriver.findElement(By.linkText(linkText)).isSelected();
    }
    public boolean getSelectStatByPartialLinkText(String partialLinkText) {
        return sDriver.findElement(By.partialLinkText(partialLinkText)).isSelected();
    }
    public boolean getSelectStatByXpath(String xpath) {
        return sDriver.findElement(By.xpath(xpath)).isSelected();
    }
    public boolean getSelectStatByCssSelector(String cssSelector) {
        return sDriver.findElement(By.cssSelector(cssSelector)).isSelected();
    }

    /**
     * 获取当前页面焦点元素的属性值(name,value,id,src等等)
     */
    public String getFocusAttributeValue(String attribute) {
        String value = "";
        try {
            Thread.sleep(333);
        } catch (Exception e) {
            e.printStackTrace();
        }
        value = sDriver.switchTo().activeElement().getAttribute(attribute);
        return value;
    }

    /**
     * 等待元素可用再点击
     */
    public void waitForEnabledByIdAndClick(String id) throws InterruptedException {
        boolean key = true;
        while (key) {
            if (findElementById(id).isEnabled() && findElementById(id).isDisplayed()) {
                clickByJsById(id);
                key = false;
            } else {
                sleep(0);
            }
        }
    }
    public void waitForEnabledByNameAndClick(String name) throws InterruptedException {
        boolean key = true;
        while (key) {
            if (findElementByName(name).isEnabled() && findElementByName(name).isDisplayed()) {
                clickByJsByName(name);
                key = false;
            } else {
                sleep(0);
            }
        }
    }
    public void waitForEnabledByClassNameAndClick(String className) throws InterruptedException {
        boolean key = true;
        while (key) {
            if (findElementByClassName(className).isEnabled() && findElementByClassName(className).isDisplayed()) {
                clickByJsByClassName(className);
                key = false;
            } else {
                sleep(0);
            }
        }
    }
    public void waitForEnabledByTagNameAndClick(String tagName) throws InterruptedException {
        boolean key = true;
        while (key) {
            if (findElementByTagName(tagName).isEnabled() && findElementByTagName(tagName).isDisplayed()) {
                clickByJsByTagName(tagName);
                key = false;
            } else {
                sleep(0);
            }
        }
    }
    public void waitForEnabledByLinkTextAndClick(String linkText) throws InterruptedException {
        boolean key = true;
        while (key) {
            if (findElementByLinkText(linkText).isEnabled() && findElementByLinkText(linkText).isDisplayed()) {
                clickByJsByLinkText(linkText);
                key = false;
            } else {
                sleep(0);
            }
        }
    }
    public void waitForEnabledByPartialLinkTextAndClick(String partialLinkText) throws InterruptedException {
        boolean key = true;
        while (key) {
            if (findElementByPartialLinkText(partialLinkText).isEnabled() && findElementByPartialLinkText(partialLinkText).isDisplayed()) {
                clickByJsByPartialLinkText(partialLinkText);
                key = false;
            } else {
                sleep(0);
            }
        }
    }
    public void waitForEnabledByXpathAndClick(String xpath) throws InterruptedException {
        boolean key = true;
        while (key) {
            if (findElementByXpath(xpath).isEnabled() && findElementByXpath(xpath).isDisplayed()) {
                clickByJsByXpath(xpath);
                key = false;
            } else {
                sleep(0);
            }
        }
    }
    public void waitForEnabledByCssSelectorAndClick(String cssSelector) throws InterruptedException {
        boolean key = true;
        while (key) {
            if (findElementByCssSelector(cssSelector).isEnabled() && findElementByCssSelector(cssSelector).isDisplayed()) {
                clickByJsByCssSelector(cssSelector);
                key = false;
            } else {
                sleep(0);
            }
        }
    }
    /**
     * 自定义等待时间
     */
    public static void sleep(int key) throws InterruptedException {
        switch (key) {
            case 0:
                Thread.sleep(500);
                break;
            case 1:
                Thread.sleep(2000);
                break;
            case 2:
                Thread.sleep(5000);
                break;
            default:
                System.out.println("错误");
                break;
        }
    }

    // ===========================下拉列表操作=======================
    /**
     * 根据id获取下拉框，根据index选择选项
     */
    public void findSelectByIdAndSelectByIndex(String id, int index) {
        Select select = new Select(findElementById(id));
        select.selectByIndex(index);
    }
    /**
     * 根据id获取下拉框，根据text选择选项
     */
    public void findSelectByIdAndSelectByValue(String id, String value) {
        Select select = new Select(findElementById(id));
        select.selectByValue(value);
    }
    /**
     * 根据id获取下拉框，根据index选择选项
     */
    public void findSelectByIdAndSelectByText(String id, String text) {
        Select select = new Select(findElementById(id));
        select.selectByVisibleText(text);
    }

    /**
     * 根据name获取下拉框，根据index选择选项
     */
    public void findSelectByNameAndSelectByIndex(String name, int index) {
        Select select = new Select(findElementByName(name));
        select.selectByIndex(index);
    }
    /**
     * 根据name获取下拉框，根据text选择选项
     */
    public void findSelectByNameAndSelectByValue(String name, String value) {
        Select select = new Select(findElementByName(name));
        select.selectByValue(value);
    }
    /**
     * 根据name获取下拉框，根据index选择选项
     */
    public void findSelectByNameAndSelectByText(String name, String text) {
        Select select = new Select(findElementByName(name));
        select.selectByVisibleText(text);
    }

    /**
     * 根据className获取下拉框，根据index选择选项
     */
    public void findSelectByClassNameAndSelectByIndex(String className, int index) {
        Select select = new Select(findElementByClassName(className));
        select.selectByIndex(index);
    }
    /**
     * 根据className获取下拉框，根据text选择选项
     */
    public void findSelectByClassNameAndSelectByValue(String className, String value) {
        Select select = new Select(findElementByClassName(className));
        select.selectByValue(value);
    }
    /**
     * 根据className获取下拉框，根据index选择选项
     */
    public void findSelectByClassNameAndSelectByText(String className, String text) {
        Select select = new Select(findElementByClassName(className));
        select.selectByVisibleText(text);
    }

    /**
     * 根据tagName获取下拉框，根据index选择选项
     */
    public void findSelectByTagNameAndSelectByIndex(String tagName, int index) {
        Select select = new Select(findElementByTagName(tagName));
        select.selectByIndex(index);
    }
    /**
     * 根据tagName获取下拉框，根据text选择选项
     */
    public void findSelectByTagNameAndSelectByValue(String tagName, String value) {
        Select select = new Select(findElementByTagName(tagName));
        select.selectByValue(value);
    }
    /**
     * 根据tagName获取下拉框，根据index选择选项
     */
    public void findSelectByTagNameAndSelectByText(String tagName, String text) {
        Select select = new Select(findElementByTagName(tagName));
        select.selectByVisibleText(text);
    }

    /**
     * 根据linkText获取下拉框，根据index选择选项
     */
    public void findSelectByLinkTextAndSelectByIndex(String linkText, int index) {
        Select select = new Select(findElementByLinkText(linkText));
        select.selectByIndex(index);
    }
    /**
     * 根据linkText获取下拉框，根据text选择选项
     */
    public void findSelectByLinkTextAndSelectByValue(String linkText, String value) {
        Select select = new Select(findElementByLinkText(linkText));
        select.selectByValue(value);
    }
    /**
     * 根据linkText获取下拉框，根据index选择选项
     */
    public void findSelectByLinkTextAndSelectByText(String linkText, String text) {
        Select select = new Select(findElementByLinkText(linkText));
        select.selectByVisibleText(text);
    }

    /**
     * 根据partialLinkText获取下拉框，根据index选择选项
     */
    public void findSelectByPartialLinkTextAndSelectByIndex(String partialLinkText, int index) {
        Select select = new Select(findElementByPartialLinkText(partialLinkText));
        select.selectByIndex(index);
    }
    /**
     * 根据partialLinkText获取下拉框，根据text选择选项
     */
    public void findSelectByPartialLinkTextAndSelectByValue(String partialLinkText, String value) {
        Select select = new Select(findElementByPartialLinkText(partialLinkText));
        select.selectByValue(value);
    }
    /**
     * 根据partialLinkText获取下拉框，根据index选择选项
     */
    public void findSelectByPartialLinkTextAndSelectByText(String partialLinkText, String text) {
        Select select = new Select(findElementByPartialLinkText(partialLinkText));
        select.selectByVisibleText(text);
    }

    /**
     * 根据xPath获取下拉框，根据index选择选项
     */
    public void findSelectByXpathAndSelectByIndex(String xPath, int index) {
        Select select = new Select(findElementByXpath(xPath));
        select.selectByIndex(index);
    }
    /**
     * 根据xPath获取下拉框，根据text选择选项
     */
    public void findSelectByXpathAndSelectByValue(String xPath, String value) {
        Select select = new Select(findElementByXpath(xPath));
        select.selectByValue(value);
    }
    /**
     * 根据xPath获取下拉框，根据index选择选项
     */
    public void findSelectByXpathAndSelectByText(String xPath, String text) {
        Select select = new Select(findElementByXpath(xPath));
        select.selectByVisibleText(text);
    }

    /**
     * 根据cssSelector获取下拉框，根据index选择选项
     */
    public void findSelectByCssSelctorAndSelectByIndex(String cssSelector, int index) {
        Select select = new Select(findElementByCssSelector(cssSelector));
        select.selectByIndex(index);
    }
    /**
     * 根据cssSelector获取下拉框，根据text选择选项
     */
    public void findSelectByCssSelctorAndSelectByValue(String cssSelector, String value) {
        Select select = new Select(findElementByCssSelector(cssSelector));
        select.selectByValue(value);
    }
    /**
     * 根据cssSelector获取下拉框，根据index选择选项
     */
    public void findSelectByCssSelctorAndSelectByText(String cssSelector, String text) {
        Select select = new Select(findElementByCssSelector(cssSelector));
        select.selectByVisibleText(text);
    }

    /**
     * 定位by并选中对应index的option
     * @param by
     * @param index
     */
    public static void selectByIndex(By by, int index) {
        sSelect = new Select(sDriver.findElement(by));
        sSelect.selectByIndex(index);
    }
    /**
     * 定位by并选中对应value值的option
     * @param by
     * @param value
     */
    public static void selectByValue(By by, String value) {
        sSelect = new Select(sDriver.findElement(by));
        sSelect.selectByValue(value);
    }
    /**
     * 定位by并选中对应text的option
     * @param by
     * @param text
     */
    public static void selectByText(By by, String text) {
        sSelect = new Select(sDriver.findElement(by));
        sSelect.selectByVisibleText(text);
    }

    // =========================弹框操作============================
    // 判断是否有弹框
    public boolean isAlertPresent() {
        try {
            sAlert = sDriver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException ex) {
            return false;
        }
    }
    // 接受弹出框
    public void acceptAlert() {
        if (this.isAlertPresent()) {
            sAlert.accept();
        }
    }
    // 取消弹出框
    public void dimissAlert() {
        if (this.isAlertPresent()) {
            sAlert.dismiss();
        }
    }
    // 获取弹出内容
    public String getAlertText() {
        String text = "";
        if (this.isAlertPresent()) {
            text = sAlert.getText();
        } else {
            // TODO:LOG
        }
        return text;
    }
    // 弹出对话框输入文本字符串
    public void inputTextToAlert(String text) {
        if (this.isAlertPresent()) {
            sAlert.sendKeys(text);
        } else {
            // TODO:LOG
        }
    }

    // ==========================窗口和iframe====================
    // 切换到当前页面
    public static void switchToCurrentPage() {
        String handle = sDriver.getWindowHandle();
        for (String tempHandle : sDriver.getWindowHandles()) {
            if (tempHandle.equals(handle)) {
                sDriver.close();
            } else {
                sDriver.switchTo().window(tempHandle);
            }
        }
    }
    // 切换到指定title的窗口
    public void switchToWindow(String windowTtitle) {
        Set<String> windowHandles = sDriver.getWindowHandles();
        for (String handler : windowHandles) {
            sDriver.switchTo().window(handler);
            String title = sDriver.getTitle();
            if (windowTtitle.equals(title)) {
                break;
            }
        }
    }
    // 切换至父级frame
    public static void switchToParentFrame() {
        sDriver.switchTo().parentFrame();
    }
    /**
     * 切换默认最外层frame或者窗口
     * @return 这个驱动程序聚焦在顶部窗口/第一个frame上
     */
    public static void switchToDefault() {
        sDriver.switchTo().defaultContent();
    }
    // 切换到指定iframe
    public void switchToFrameById(String frameId) {
        sDriver.switchTo().frame(frameId);
    }
    public void switchToFrameByIndex(int index) {
        sDriver.switchTo().frame(index);
    }
    public void switchToframeByElement(By by) {
        sDriver.switchTo().frame(sDriver.findElement(by));
    }

    //提交表达
    public static void submitForm(By by) {
        sDriver.findElement(by).submit();
    }

    //上传文件
    public static void uploadFile(By locator, String filePath) {
        sDriver.findElement(locator).sendKeys(filePath);
    }

    // ===========================js操作=============================
    // js点击指定元素
    public void clickByJs(WebElement element) {
        ((JavascriptExecutor) sDriver).executeScript("arguments[0].click()", element);
    }
    // 定位元素触发JS点击事件
    public void clickByJsById(String id) {
        clickByJs(findElementById(id));
    }
    public void clickByJsByName(String name) {
        clickByJs(findElementByName(name));
    }
    public void clickByJsByClassName(String className) {
        clickByJs(findElementByClassName(className));
    }
    public void clickByJsByTagName(String tagName) {
        clickByJs(findElementByTagName(tagName));
    }
    public void clickByJsByLinkText(String linkText) {
        clickByJs(findElementByLinkText(linkText));
    }
    public void clickByJsByPartialLinkText(String partialLinkText) {
        clickByJs(findElementByPartialLinkText(partialLinkText));
    }
    public void clickByJsByXpath(String xpath) {
        clickByJs(findElementByXpath(xpath));
    }
    public void clickByJsByCssSelector(String cssSelector) {
        clickByJs(findElementByCssSelector(cssSelector));
    }

    // 滚动到窗口最上方
    public void scrollToTop() {
        ((JavascriptExecutor) sDriver).executeScript("window.scrollTo(0,0);");
    }
    // 滚动到页面底部
    public void scrollToBottom(String id) {
        ((JavascriptExecutor) sDriver).executeScript("window.scrollTo(0,10000);");
    }
    // 滚动到某个元素
    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) sDriver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    // js给指定元素value赋值
    public void inputTextByJs(String text, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) sDriver;
        js.executeScript("arguments[0].value=" + text + "\"", element);
    }
    // js使元素显示
    public void makeElementDisplay(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) sDriver;
        js.executeScript("arguments[0].style=arguments[1]", element, "display: block;");
    }

    // ===========================浏览器操作========================
    /**
     * 关闭当前浏览器
     */
    public void closeBrowser() {
        sDriver.close();
    }

    /**
     * 关闭所有selenium驱动打开的浏览器
     */
    public void quitBrowser() {
        sDriver.quit();
    }

    /**
     * 最大化浏览器
     */
    public void maxBrowser() {
        sDriver.manage().window().maximize();
    }

    /**
     * 自定义设置浏览器尺寸
     */
    public void setBrowser(int width, int height) {
        sDriver.manage().window().setSize(new Dimension(width, height));
    }

    /**
     * 获取网页的title
     */
    public String getTitle() {
        return sDriver.getTitle();
    }

    /**
     * 获取当前url
     */
    public String getRUL() {
        return sDriver.getCurrentUrl();
    }

    /**
     * 上一个页面（点击浏览器返回）
     */
    public void returnToPreviousPage() {
        sDriver.navigate().back();
    }

    /**
     * 下一个页面（如果没有下一个页面则什么都不做）
     */
    public void forwartToNextPage() {
        sDriver.navigate().forward();
    }

    /**
     * 刷新页面
     */
    public void refreshPage() {
        sDriver.navigate().refresh();
    }

    /**
     * 强制刷新页面
     */
    public void strongRefresh() {
        Actions ctrl = new Actions(sDriver);
        ctrl.keyDown(Keys.CONTROL).perform();
        try {
            pressKeyEvent(KeyEvent.VK_F5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ctrl.keyUp(Keys.CONTROL).perform();
    }

    // 判断是否加载有JQuery
    public Boolean JQueryLoaded() {
        Boolean loaded;
        JavascriptExecutor js = (JavascriptExecutor) sDriver;
        try {
            loaded = (Boolean) js.executeScript("return" + "JQuery()!=null");
        } catch (WebDriverException e) {
            loaded = false;
        }
        return loaded;
    }

    // ===========================屏幕截图========================
    public void screenShot(WebDriver driver) {
        String dir_name = "screenshot";
        if (!(new File(dir_name).isDirectory())) {
            new File(dir_name).mkdir();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
        String time = sdf.format(new Date());
        try {
            // 执行截屏
            File source_file = (((TakesScreenshot) sDriver).getScreenshotAs(OutputType.FILE));
            FileUtils.copyFile(source_file, new File(dir_name + File.separator + time + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 截图命名为当前时间保存桌面
    public void takeScreenshotByNow() throws IOException {
        File srcFile = ((TakesScreenshot) sDriver).getScreenshotAs(OutputType.FILE);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
        String time = sdf.format(new Date());
        // TODO:修改成自己的路径
        String file = "C:\\Users\\zhangsan\\Desktop\\picture\\" + time + ".png";
        FileUtils.copyFile(srcFile, new File(file));
    }
    // 截图重命名保存至桌面
    public void takeScreenshotByName(String name) throws IOException {
        File srcFile = ((TakesScreenshot) sDriver).getScreenshotAs(OutputType.FILE);
        // TODO:修改成自己的路径
        String file = "C:\\Users\\zhangsan\\Desktop\\picture\\" + name + ".png";
        FileUtils.copyFile(srcFile, new File(file));
    }

    // =========================键盘操作=====================
    /**
     * 获取键盘
     */
    public Keyboard getKeyboard() {
        return ((HasInputDevices) sDriver).getKeyboard();
    }
    /**
     * 模拟ctrl+f5
     */
    public void refreshWithCtrlF5() {
        getKeyboard().sendKeys(Keys.CONTROL, Keys.F5);
    }
    /**
     * 按物理按键（KeyEvent类中查找相关的常量)
     * 例如：Robot robot = new Robot();robot.keyPress(KeyEvent.VK_ENTER);//按下enter键
     */
    public void pressKeyEvent(int keycode) throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(keycode);
    }

    // =============================鼠标操作=======================
    //鼠标悬浮指定元素并点击
    public void moveToElemnt(By by){
        Actions actions = new Actions(sDriver);
        actions.moveToElement(sDriver.findElement(by)).perform();
    }
    public void moveToElemntById(String id) {
        Actions actions = new Actions(sDriver);
        actions.moveToElement(findElementById(id)).perform();
    }
    public void moveToElemntByName(String name) {
        Actions actions = new Actions(sDriver);
        actions.moveToElement(findElementByName(name)).perform();
    }
    public void moveToElemntByClassName(String className) {
        Actions actions = new Actions(sDriver);
        actions.moveToElement(findElementByClassName(className)).perform();
    }
    public void moveToElemntByTagName(String tagName) {
        Actions actions = new Actions(sDriver);
        actions.moveToElement(findElementByTagName(tagName)).perform();
    }
    public void moveToElemntByLinkText(String linkText) {
        Actions actions = new Actions(sDriver);
        actions.moveToElement(findElementByLinkText(linkText)).perform();
    }
    public void moveToElemntByPartialLinkText(String partialLinkText) {
        Actions actions = new Actions(sDriver);
        actions.moveToElement(findElementByPartialLinkText(partialLinkText)).perform();
    }
    public void moveToElemntByXpath(String xpath) {
        Actions actions = new Actions(sDriver);
        actions.moveToElement(findElementByXpath(xpath)).perform();
    }
    public void moveToElemntByCssSelector(String cssSelector) {
        Actions actions = new Actions(sDriver);
        actions.moveToElement(findElementByCssSelector(cssSelector)).perform();
    }

    // 鼠标右键点击
    public void rightClickWebElement(By by) {
        Actions actions = new Actions(sDriver);
        actions.contextClick(sDriver.findElement(by)).perform();
    }
    public void rightClickWebElementById(String id) {
        Actions actions = new Actions(sDriver);
        actions.contextClick(findElementById(id)).perform();
    }

    // 鼠标双击
    public void doubleClickWebElement(By by) {
        Actions actions = new Actions(sDriver);
        actions.contextClick(sDriver.findElement(by)).perform();
    }
    public void doubleClickWebElementById(String id) {
        Actions actions = new Actions(sDriver);
        actions.doubleClick(findElementById(id)).perform();
    }

    /**
     * 模拟点击键盘上的键
     * keyDown()按下
     * keyUp()抬起,松开
     * 常见的键： Keys.SHIFT    Keys.ALT   Keys.Tab
     */
    public void clickCtrl(String id) {
        Actions actions = new Actions(sDriver);
        actions.keyDown(Keys.CONTROL);//按下control键
        actions.keyUp(Keys.CONTROL);//松开control键
    }

    // 模拟键盘输入关键字到输入框
    public void sendText(By by, String text) {
        Actions actions = new Actions(sDriver);
        actions.sendKeys(sDriver.findElement(by), text).perform();
    }

    // 模拟鼠标移动到指定元素并点击
    public void moveToElementAndClick(By by, String text) {
        Actions actions = new Actions(sDriver);
        actions.moveToElement(sDriver.findElement(by)).click().perform();
    }

    // 模拟鼠标点击和释放
    public void clickHoldAndRelease(By by) {
        Actions actions = new Actions(sDriver);
        actions.clickAndHold(sDriver.findElement(by)).perform();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        actions.release(sDriver.findElement(by)).perform();
    }

    // ========================cookie操作=========================
    // 获取当前域所有的cookies
    public Set<Cookie> getAllCookies() {
        return sDriver.manage().getCookies();
    }
    // 输出cookies信息
    public void outputCookie() {
        Set<Cookie> cookie = sDriver.manage().getCookies();
        System.out.println(cookie);
    }

    // 添加cookie信息
    public void addCookie(Map<String, String> args) {
        Set<String> keys = args.keySet();
        for (String key : keys) {
            sDriver.manage().addCookie(new Cookie(key, args.get(key)));
        }
    }
    /**
     * 用给定的name和value创建默认路径的Cookie并添加, 永久有效
     * @param name
     * @param value
     */
    public void addCookie(String name, String value) {
        sDriver.manage().addCookie(new Cookie(name, value));
    }
    /**
     * 用给定的name和value创建指定路径的Cookie并添加, 永久有效
     * @param name cookie名称
     * @param value cookie值
     * @param path  cookie路径
     */
    public void addCookie(String name, String value, String path) {
        sDriver.manage().addCookie(new Cookie(name, value, path));
    }

    // 根据cookie名称删除cookie
    public void deleteCookie(String name) {
        sDriver.manage().deleteCookieNamed(name);
    }
    // 删除当前域的所有Cookie
    public void deleteAllCookies() {
        sDriver.manage().deleteAllCookies();
    }

    /**
     * 根据名称获取指定cookie
     * @param name cookie名称
     * @return Map&lt;String, String>, 如果没有cookie则返回空, 返回的Map的key值如下:
     * <ul>
     *     <li><tt>name</tt> <tt>cookie名称</tt>
     *     <li><tt>value</tt> <tt>cookie值</tt>
     *     <li><tt>path</tt> <tt>cookie路径</tt>
     *     <li><tt>domain</tt> <tt>cookie域</tt>
     *     <li><tt>expiry</tt> <tt>cookie有效期</tt>
     * </ul>
     */
    public static Map<String, String> getCookieByName(String name) {
        Cookie cookie = sDriver.manage().getCookieNamed(name);
        if (cookie != null) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("name", cookie.getName());
            map.put("value", cookie.getValue());
            map.put("path", cookie.getPath());
            map.put("domain", cookie.getDomain());
            map.put("expiry", cookie.getExpiry().toString());
            return map;
        }
        return null;
    }

    // =============================远程================================
    /**
     * 进入测试，打开浏览器，输入网址，打开网页
     * @param remoteUrl 远程服务器地址
     * @param pageUrl   测试页面地址
     */
    public boolean startTest(String remoteUrl, String pageUrl) {
        try {
            try {
                sDriver = new RemoteWebDriver(new URL(remoteUrl), DesiredCapabilities.firefox());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            sDriver.get(pageUrl);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
    }
    /**
     * 进入测试，打开浏览器，输入网址，打开网页
     * @param explore   调用的浏览器，需要启动不同的server
     *                  如：firefox，需要运行selenium-server-standalone-2.33.0.jar。
     *                  IE，则需运行IEDriverServer.exe
     * @param remoteUrl 远程服务器地址
     * @param pageUrl   测试页面地址
     */
    public boolean startTest(String explore, String remoteUrl, String pageUrl) {
        try {
            try {
                if ("f".equals(explore)) {
                    sDriver = new RemoteWebDriver(new URL(remoteUrl), DesiredCapabilities.firefox());
                } else if ("ie".equals(explore)) {
                    DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
                    sDriver = new RemoteWebDriver(new URL(remoteUrl), cap);
                } else {
                    System.out.println("firefox");
                    sDriver = new RemoteWebDriver(new URL(remoteUrl), DesiredCapabilities.firefox());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            sDriver.get(pageUrl);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
