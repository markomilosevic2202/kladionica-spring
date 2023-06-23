package marko.kladionica.page_factory;

import marko.kladionica.entity.Match;
import marko.kladionica.entity.MatchDifferences;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;



public class Foreign {
    @FindBy(xpath = "//*[contains(@class, 'biab_group-markets-table js-inplay-market')]")
    List<WebElement> listMatchWeb;


    @FindBy(xpath = "//a[contains(text(),'Next')]")
    WebElement btnNext;

    @FindBy(className = "biab_search-input")
    WebElement inpSearch;

    @FindBy(xpath = "//a[contains(text(),'Double Chance')]")
    WebElement btnDoubleChance;


    private Actions actions;
    WebDriver driver;

    public Foreign(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }


    public List<Match> findMatches(String hours) throws InterruptedException {
//        Gson gson = new Gson();
//        List<Match> list = new ArrayList<>();
////        for (int i = 0; i < 2; i++) {
//        Thread.sleep(3000);
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        String matchesString = (String) js.executeScript(
//                "let list = [];\n" +
//                        "list = document.querySelectorAll('.biab_group-markets-table.js-inplay-market'); \n" +
//                        "let result = [];\n" +
//                        "list.forEach(e=>{\n" +
//                        "    result.push({\n" +
//                        "                   code: null,\n" +
//                        "                   name: e.querySelector('.biab_market-title-team-names.js-teams').outerText.replace(/\\n/g, ' - '), \n" +
//                        "                   one: e.getElementsByTagName('span')[4].outerText,\n" +
//                        "                   two: e.getElementsByTagName('span')[8].outerText,\n" +
//                        "                   x: e.getElementsByTagName('span')[12].outerText,\n" +
//                        "                   time: e.querySelector('.biab_market-inplay-cell.js-market-inplay-cell').innerText,\n" +
//                        "                   date: null\n" +
//                        "\n" +
//                        "    });\n" +
//                        "});\n" +
//                        "return JSON.stringify(result);"
//        );
//        System.out.println(matchesString);
//        List<Match> matches = gson.fromJson(matchesString, ArrayList.class);
//        System.out.println(matches.size());
//        System.out.println(matches.get(1).toString());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter formatterText = DateTimeFormatter.ofPattern("dd MMM", Locale.ENGLISH);

        LocalDate today = LocalDate.now();
        String todayString = today.format(formatter);
        LocalDate tomorrow = today.plusDays(1);
        String tomorrowStrong = tomorrow.format(formatter);
        LocalDate afterOneDayTomorrow = today.plusDays(2);
        String afterOneDayTomorrowString = afterOneDayTomorrow.format(formatter);
        String afterOneDayTomorrowStringTextMount = afterOneDayTomorrow.format(formatterText);
        LocalDate afterTwoDayTomorrow = today.plusDays(3);
        String afterTwoTomorrowStringTextMount = afterTwoDayTomorrow.format(formatterText);
        LocalDate afterForeDayTomorrow = today.plusDays(5);
        String afterForeTomorrowStringTextMount = afterForeDayTomorrow.format(formatterText);

        String andDate;

        if (hours.equals("3") || hours.equals("5") || hours.equals("12") || hours.equals("24")) {
            andDate = afterOneDayTomorrowStringTextMount;
        } else if (hours.equals("48")) {
            andDate = afterTwoTomorrowStringTextMount;
        } else {
            andDate = afterForeTomorrowStringTextMount;
        }


        List<Match> matches = new ArrayList<>();
        for (int i = 0; i < 1; i++) {

            Thread.sleep(500);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));


            for (int j = 0; j < listMatchWeb.size(); j++) {
                try {
                    String datePage = listMatchWeb.get(j).findElement(By.xpath("tbody/tr/td")).getText();
                    Match match = new Match();
                    match.setTime(datePage);
                    match.setName(listMatchWeb.get(j).findElement(By.xpath("tbody/tr/td[2]")).getText().replace("\n", " - "));
                    match.setOne(listMatchWeb.get(j).findElement(By.xpath("tbody/tr/td[5]/div/div/div/span[1]")).getText());
                    match.setX(listMatchWeb.get(j).findElement(By.xpath("tbody/tr/td[8]/div/div/div/span[1]")).getText());
                    match.setTwo(listMatchWeb.get(j).findElement(By.xpath("tbody/tr/td[11]/div/div/div/span[1]")).getText());
                    if (datePage.contains("Tomorrow")) {
                        match.setDate(tomorrowStrong);
                    } else if (datePage.contains("Wednesday") || datePage.contains("Thursday") || datePage.contains("Friday") || datePage.contains("Saturday")
                            || datePage.contains("Sunday") || datePage.contains("Monday") || datePage.contains("Tuesday")) {
                        match.setDate(afterOneDayTomorrowString);
                    } else match.setDate(todayString);
                    matches.add(match);

                } catch (Throwable e) {

                }
            }
            List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + andDate + "')]"));
            if (list.size() > 0) {
                break;
            }
            btnNext.click();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return matches;
    }

    public MatchDifferences[] addOppositeOdds(List<MatchDifferences> matchDifferencesList)  {
        MatchDifferences[] matchDifferences = matchDifferencesList.toArray(new MatchDifferences[0]);
        String name = null;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        for (int i = 0; i < matchDifferences.length; i++) {

            try {
                MatchDifferences matchDifferences1 = matchDifferences[i];
                String clearIfNameForeign = matchDifferences1.getNameForeign().replaceAll("IF ", "");
                String characterReplacement = matchDifferences1.getNameForeign().replaceAll("-", "v");
                name = characterReplacement;
                int higherOdds = 1;
                String higherOddsString = matchDifferences1.getOneHome();
                if (Double.parseDouble(matchDifferences1.getOneDifferences()) < Double.parseDouble(matchDifferences1.getTwoDifferences())) {
                    higherOdds = 0;
                    higherOddsString = matchDifferences1.getTwoHome();
                }
                if (Double.parseDouble(matchDifferences1.getOneDifferences()) < Double.parseDouble(matchDifferences1.getDifferencesX()) &&
                        Double.parseDouble(matchDifferences1.getTwoDifferences()) < Double.parseDouble(matchDifferences1.getDifferencesX())){
                    higherOdds = 2;
                    higherOddsString = matchDifferences1.getHomeX();
                }

                inpSearch.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
                inpSearch.sendKeys(clearIfNameForeign);

                driver.navigate().refresh();
                new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'biab_search-results js-search-results')]")));
                // Thread.sleep(1000);
                // driver.findElement(By.xpath("//a[contains(text(),'" + characterReplacement + "')]")).click();
                WebElement firstMatchOnList  = driver.findElement(By.xpath("//*[contains(@class, 'biab_search-results js-search-results')]"));
                firstMatchOnList.findElement(By.xpath(".//a")).click();
                //Thread.sleep(2000);
                new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Correct Score')]")));
                btnDoubleChance.click();
                //Thread.sleep(2000);
                new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Double Chance')]")));
                System.out.println(matchDifferences1.getNameForeign());
                WebElement webElement = driver.findElements(By.xpath("//*[contains(@class, 'biab_bet biab_blue-cell js-blue-cell biab_bet-back js-bet-back biab_back-0 js-back-0')]"))
                        .get(higherOdds);
                matchDifferences1.setCounterQuota(webElement.findElement(By.xpath("div/div/div/span[1]")).getText());
                matchDifferences1.setBet(webElement.findElement(By.xpath("div/div/div/span[2]")).getText());
                matchDifferences1.setHigherOdds(higherOddsString);
                matchDifferences1.setEarnings(getEarnings(matchDifferences1.getHigherOdds(),matchDifferences1.getCounterQuota()));
                matchDifferences1.setUrlOrbit(driver.getCurrentUrl());



            } catch (Exception e) {

                MatchDifferences matchDifferences1 = matchDifferences[i];

                matchDifferences1.setCounterQuota("0");
                matchDifferences1.setBet("0");
                matchDifferences1.setHigherOdds("0");
                matchDifferences1.setEarnings("0");
                System.out.println("------------------- " + matchDifferences1.getNameForeign() + " --------------");


            }


        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return matchDifferences;
    }

    public String getEarnings(String higherOdds, String counterQuota) {
        DecimalFormat df = new DecimalFormat("#.##");
        double higherOddsDouble = Double.parseDouble(higherOdds);
        double counterQuotaDouble = Double.parseDouble(counterQuota);
        double earnings = (85.47*higherOddsDouble/(100/(higherOddsDouble*100-100)+1))-(85.47*higherOddsDouble/counterQuotaDouble);
        double earningsRound = Double.parseDouble(df.format(earnings));
        System.out.println(earningsRound);
        return String.valueOf(earningsRound);
    }
}

