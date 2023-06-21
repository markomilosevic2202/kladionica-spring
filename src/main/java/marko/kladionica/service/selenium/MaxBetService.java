package marko.kladionica.service.selenium;

import marko.kladionica.data.DataSet;
import marko.kladionica.example.test_data_bils.Match;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class MaxBetService {
    public List<Match> getAllMatches(){
        List<Match> matches = new ArrayList<>();
        try {
            WebDriver driver;
            System.setProperty(DataSet.data1, DataSet.data2);
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments(DataSet.data3);
            chromeOptions.addArguments(DataSet.data4); // open Browser in maximized mode
            chromeOptions.addArguments(DataSet.data5); // disabling infobars
            chromeOptions.addArguments(DataSet.data6); // disabling extensions
            chromeOptions.addArguments(DataSet.data7); // applicable to windows os only
            chromeOptions.addArguments(DataSet.data8); // overcome limited resource problems
            chromeOptions.addArguments(DataSet.data9); // Bypass OS security model
            driver = new ChromeDriver(chromeOptions);
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            driver.manage().window().maximize();
            driver.get("https://google.com");

        }catch (Exception e){
            e.printStackTrace();
        }
        return matches;
    }
}
