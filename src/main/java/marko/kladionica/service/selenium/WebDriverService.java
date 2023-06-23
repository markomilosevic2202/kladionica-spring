package marko.kladionica.service.selenium;

import marko.kladionica.data.DataSet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import java.time.Duration;

public class WebDriverService {


    private WebDriver webDriver;
    private static final WebDriverService instance = new WebDriverService();

    private WebDriverService() {
        try {
            if(instance != null){
                return;
            }
            System.setProperty(DataSet.data1, DataSet.data2);
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments(DataSet.data3);
            chromeOptions.addArguments(DataSet.data4); // open Browser in maximized mode
            chromeOptions.addArguments(DataSet.data5); // disabling infobars
            chromeOptions.addArguments(DataSet.data6); // disabling extensions
            chromeOptions.addArguments(DataSet.data7); // applicable to windows os only
            chromeOptions.addArguments(DataSet.data8); // overcome limited resource problems
            chromeOptions.addArguments(DataSet.data9); // Bypass OS security model
            this.webDriver = new ChromeDriver(chromeOptions);
            this.webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            webDriver.manage().window().maximize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static WebDriverService getInstance() {
        return instance;
    }

    public static WebDriver getWebdriver() {
        return getInstance().webDriver;
    }

}
