package com.hzzx.login;

import com.hzzx.config.Constants;
import com.hzzx.utils.ExcelUtil;
import com.hzzx.utils.WebDriverUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest {
    public WebDriverUtil mWebDriverUtil;
    public int currentRow = 1;

    @DataProvider(name = "loginTestData")
    public static Object[][] getLoginData() {
        String path = Constants.projectBaseUrl + "/src/main/java/com/jnf/credit/data/测试数据.xls";
        return ExcelUtil.readExcel(path, "登录");
    }

    @Test(dataProvider = "loginTestData")
    public void testCase(String username, String password, String result) throws InterruptedException {
        mWebDriverUtil.openBrowser(Constants.baseUrl);
        mWebDriverUtil.maxBrowser();

        mWebDriverUtil.findElementByXpathAndClearSendkeys(LoginElements.usernameXpath, username);
        mWebDriverUtil.findElementByXpathAndClearSendkeys(LoginElements.passwordXpath, password);
        mWebDriverUtil.findElementByXpathAndClick(LoginElements.loginBtnXpath);

        Thread.sleep(500);
        Boolean exists = mWebDriverUtil.waitUntilPageContainText("退出", 5000, LoginElements.existBy);
        String isSuccess = exists ? "成功" : "失败";
        if (!isSuccess.equals(result)) {
            // 修改行的颜色
            String path = Constants.projectBaseUrl + "/src/main/java/com/jnf/credit/data/测试数据.xls";
            ExcelUtil.write2Excel(path, "登录", currentRow);
        }
        currentRow++;
        Assert.assertTrue(exists);
    }

    @BeforeMethod
    public void beforeMethod() {
        mWebDriverUtil = new WebDriverUtil();
    }

    @AfterMethod
    public void afterMethod() {
        mWebDriverUtil.quitBrowser();
    }
}
