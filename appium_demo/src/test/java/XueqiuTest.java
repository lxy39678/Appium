import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import junit.framework.TestCase;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

public class XueqiuTest {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("deviceName", "hogwarts");
        desiredCapabilities.setCapability("appPackage", "com.xueqiu.android");
        desiredCapabilities.setCapability("appActivity", ".view.WelcomeActivityAlias");

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void sampleTest() {
        MobileElement el1 = (MobileElement) driver.findElementById("com.xueqiu.android:id/agree");
        el1.click();
        MobileElement el2 = (MobileElement) driver.findElementById("com.xueqiu.android:id/tv_search");
        el2.click();
        MobileElement el3 = (MobileElement) driver.findElementById("com.xueqiu.android:id/search_input_text");
        el3.sendKeys("alibaba");

        driver.findElementById("stockName").click();
        //MobileElement el4 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.support.v4.view.ViewPager/android.widget.RelativeLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView");
        //el4.click();
    }

    @Test
    public void testSwipe(){
        driver.findElementById("com.xueqiu.android:id/agree").click();
        new TouchAction(driver)
                .press(PointOption.point(600, 2000))
                .moveTo(PointOption.point(200, 2000))
                .release()
                .perform();
        new TouchAction(driver)
                .press(PointOption.point(600, 2000))
                .moveTo(PointOption.point(600, 200))
                .release()
                .perform();

    }

    @Test
    public void testSwipeNew() throws InterruptedException, IOException {
        Thread.sleep(3000);
        for(int i=0;i<20;i++){
            swipe(0.8,0.8,0.4,0.4);
            FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE).getCanonicalFile(),new File(i+".png"));
        }

    }


    @Test
    public void testToast() throws InterruptedException {
        driver.findElementByXPath("//*[@text='Views']").click();
        driver.findElementByAndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(" +
                        "new UiSelector().text(\"Popup Menu\").instance(0));").click();
        driver.findElementByXPath("//*[contains(@text, 'Make')]").click();
        driver.findElementByXPath("//*[@text='Search']").click();
        //System.out.println(driver.findElementByClassName("android.widget.Toast").getText());
        System.out.println(driver.findElementByXPath("//*[@class='android.widget.Toast']").getText());

    }

//    @Test
//    public void testToastNew() throws InterruptedException {
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        Dimension screenSize=driver.manage().window().getSize();
//
//        driver.findElementByAccessibilityId("Views").click();
//        Thread.sleep(2000);
//        (new TouchAction(driver))
//                .press( (int)(screenSize.width*0.5), (int)(screenSize.height*0.8))
//                .moveTo((int)(screenSize.width*0.5), (int)(screenSize.height*0.3))
//                .release()
//                .perform();
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@content-desc='Popup Menu']"))).click();
//        //driver.findElementByAccessibilityId("Popup Menu").click();
//        driver.findElementByAccessibilityId("Make a Popup!").click();
//        //locate("//*[contains(@text, 'MAKE')]").click();
//        Thread.sleep(2000);
//        driver.findElementByXPath("//*[contains(@text,'Search')]").click();
//
//        String toastXPath="//*[@class='android.widget.Toast']";
//        System.out.println(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(toastXPath))));
//        System.out.println(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(toastXPath))).getText());
//        System.out.println(driver.findElementByXPath(toastXPath).getText());
//        //System.out.println(locate(toastXPath).getText());
//        for(int i =0;i <10;i++){
//            int size=driver.findElementsByXPath(toastXPath).size();
//            System.out.println(size);
//            Thread.sleep(500);
//        }
//    }

    @Test
    public void testWait(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElementByXPath("//*[@text='自选']").click();
    }


//    @Test
//    public void testWait2(){
//        WebDriverWait wait=new WebDriverWait(driver, 10);
//        for(int i=0;i<10;i++) {
//            System.out.println(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='自选']"))).getLocation());
//        }
//        driver.findElementByXPath("//*[@text='自选']").click();
//    }



    @Test
    public void swipe(Double startX,Double startY,Double endX,Double endY) throws InterruptedException{
        Dimension size = driver.manage().window().getSize();
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point((int)(size.width*startX),(int)(size.height*startY)));
        touchAction.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)));
        touchAction.moveTo(PointOption.point((int)(size.width*endX),(int)(size.height*endY)));
        touchAction.release();
        touchAction.perform();
        Thread.sleep(1000);

    }

    @Test
    public void webview(){
        driver.findElementByXPath("//android.widget.TextView[@text='沪深' and @resource-id='com.xueqiu.android:id/button_text']").click();
        driver.findElementByAccessibilityId("立即开户").click();
        driver.findElementByAccessibilityId("开始").click();
    }

    @Test
    public void context() throws InterruptedException {
        for(Object c : driver.getContextHandles()){
            System.out.println(c.toString());
        }
        System.out.println(driver.getPageSource());
        driver.findElementByXPath("//android.widget.TextView[@text='沪深' and @resource-id='com.xueqiu.android:id/button_text']").click();
        for(Object c : driver.getContextHandles()){
            System.out.println(c.toString());
        }
        Thread.sleep(3000);
        for(Object c : driver.getContextHandles()){
            System.out.println(c.toString());
        }

        driver.context("WEBVIEW_com.xueqiu.android");
        System.out.println(driver.getPageSource());
        driver.findElementByCssSelector(".inner").click();
    }

    @Test
    public void testRotate() throws InterruptedException {
        driver.rotate(ScreenOrientation.LANDSCAPE);
        driver.rotate(ScreenOrientation.PORTRAIT);
        driver.findElementByXPath("//*[@text='App']").click();
        driver.navigate().back();
        driver.openNotifications();
        Thread.sleep(10000);
    }

    @Test
    private void testLogs(){
        System.out.println(driver.manage().logs().getAvailableLogTypes());
        System.out.println(driver.manage().logs().get("logcat").getAll().toArray().toString());
        for (Object l: driver.manage().logs().get("logcat").getAll().toArray()){
            System.out.println(l);
        }
    }

    @Test
    public void testPerformance() throws Exception {
        System.out.println(driver.getSupportedPerformanceDataTypes());
        driver.getPerformanceData("io.appium.android.apis","memoryinfo",10);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}