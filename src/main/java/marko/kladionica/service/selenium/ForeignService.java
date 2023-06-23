package marko.kladionica.service.selenium;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import marko.kladionica.entity.Match;
import marko.kladionica.entity.MatchDifferences;
import marko.kladionica.page_factory.Foreign;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ForeignService {


    private static Foreign foreign;

    protected List<Match> getAllMatchesForeignOrdinary(String hoursOfReview) {
        try {
            foreign = new Foreign(WebDriverService.getWebdriver());
            WebDriverService.getWebdriver().get("https://www.orbitxch.com/customer/sport/1");
            return foreign.findMatches("48");
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    protected void getBestMatch() throws IOException, InterruptedException {


    }

    public List<MatchDifferences> returnMatchDefenseOdds(List<MatchDifferences> matchDifferencesList) {
        try {
            foreign = new Foreign(WebDriverService.getWebdriver());
            WebDriverService.getWebdriver().get("https://www.orbitxch.com/customer/sport/1");
            matchDifferencesList = List.of(foreign.addOppositeOdds(matchDifferencesList));
            return matchDifferencesList;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }


    }
}

