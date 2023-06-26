package marko.kladionica.service.selenium;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import marko.kladionica.entity.Address;
import marko.kladionica.entity.MatchDifferences;
import marko.kladionica.page_factory.MaxBet;
import marko.kladionica.entity.Match;
import marko.kladionica.service.AddressServiceImpl;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaxBetService {

    private final JsonService jsonService;
    private final EmailService emailService;
    private final ForeignService foreignService;
    private final CompareService compareService;
    private final AddressServiceImpl addressService;
    private WebDriver driver;


    public void getAllMatchesMaxBetBonus(String address, String hoursOfReview) {
        try {

            List<Match> listMaxbetBonusMatch = getListMaxbetOrdinaryMatch(address, hoursOfReview);
//            if (listMaxbetBonusMatch.isEmpty()) {
//                // TODO: posalji mejl da je nesto puklo
//            }
//            jsonService.writeJsonFileMatchList(listMaxbetBonusMatch, "homeBonusBetting");
//
//            List<Match> listForeignAllMatch = foreignService.getAllMatchesForeignOrdinary(hoursOfReview);
//            if (listMaxbetBonusMatch.isEmpty()) {
//                // TODO: posalji mejl da je nesto puklo
//            }
//            jsonService.writeJsonFileMatchList(listMaxbetBonusMatch, "foreignBetting");
//
//            List<MatchDifferences> listBingoMatch = compareService.compare(listMaxbetBonusMatch, listForeignAllMatch, "bonusQuotaDifferences");
//            foreignService.returnMatchDefenseOdds(listBingoMatch);
//            emailService.sendEmail(listBingoMatch,compareService.getNumberMatch() , "bonus");
//            jsonService.writeJsonFileMatchDifferencesList(listBingoMatch, "plusBonusQuotaDifferences");
//
        } catch (Exception e) {
            e.printStackTrace();
        }
       // driver.quit();


    }

    private List<Match> getListMaxbetOrdinaryMatch(String address, String hoursOfReview) {
        try {
            Address theAddress = addressService.findByName("orbitxch");
            System.out.println(theAddress.getAddress());

            this.driver = WebDriverService.getWebdriver();
            MaxBet maxBet = new MaxBet(driver);
            maxBet.goAddress(address);
//            maxBet.clickSlider(hoursOfReview);
//            Thread.sleep(1000);
//            maxBet.clickFootball();
//            Thread.sleep(2000);
//            maxBet.clickMaxBonus();
//            Thread.sleep(2000);
//            maxBet.waitForPageToLoad();
           return null; //maxBet.writeBonusMatch();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


}
