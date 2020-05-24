package com.hzzx.updatepassword;

import com.hzzx.config.Constants;
import com.hzzx.login.LoginElements;
import com.hzzx.utils.ExcelUtil;
import com.hzzx.utils.WebDriverUtil;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UpdatePaswordTest {
    public WebDriverUtil mWebDriverUtil;

    @DataProvider(name = "updatePasswordTestData")
    public static Object[][] getPasswordData() {
        String path = Constants.projectBaseUrl + "/src/main/java/com/jnf/credit/data/测试数据.xls";
        return ExcelUtil.readExcel(path, "修改密码");
    }

    @Test(dependsOnMethods = {"testLogin"}, dataProvider = "updatePasswordTestData")
    public void testUpdatePasswor(String oldPasword, String newPasword, String confimPassword, String result) throws InterruptedException {
        Thread.sleep(500);
        // 系统设置
        mWebDriverUtil.findElementAndClick(UpdatePasswordElements.sysManagerBy);
        // 人员管理
        mWebDriverUtil.findElementAndClick(UpdatePasswordElements.peopleMangerBy);
        mWebDriverUtil.switchToFrameById(UpdatePasswordElements.iframe1Id);
        mWebDriverUtil.switchToFrameById(UpdatePasswordElements.iframe2Id);

        // 某个员工
        mWebDriverUtil.findElementByXpathAndClick(UpdatePasswordElements.userXpath);
        // 修改密码
        mWebDriverUtil.findElementAndClick(UpdatePasswordElements.changePassBy);

        Thread.sleep(500);
        mWebDriverUtil.findElementByIdAndClearSendkeys(UpdatePasswordElements.newPasswordId, newPasword);
        mWebDriverUtil.findElementByIdAndClearSendkeys(UpdatePasswordElements.confirmPasswordId, confimPassword);
    }

    @Test
    public void testLogin() {
        mWebDriverUtil.openBrowser(Constants.baseUrl);
        mWebDriverUtil.maxBrowser();

        mWebDriverUtil.findElementByXpathAndClearSendkeys(LoginElements.usernameXpath, "用户名");
        mWebDriverUtil.findElementByXpathAndClearSendkeys(LoginElements.passwordXpath, "密码");
        mWebDriverUtil.findElementByXpathAndClick(LoginElements.loginBtnXpath);
        Boolean exists = mWebDriverUtil.waitUntilPageContainText("退出", 5000, LoginElements.existBy);
        Assert.assertTrue(exists);
    }

    @BeforeClass
    public void beforeMethod() {
        mWebDriverUtil = new WebDriverUtil();
    }

    @AfterClass
    public void afterMethod() {
        mWebDriverUtil.quitBrowser();
    }
}
