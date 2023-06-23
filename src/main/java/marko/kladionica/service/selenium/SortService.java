package marko.kladionica.service.selenium;

import marko.kladionica.entity.MatchDifferences;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SortService {

    public MatchDifferences[] sortArrayByEarnings(MatchDifferences[] matchArray) throws IOException {


        for (int i = 0; i < matchArray.length - 1; i++) {

            for (int j = 0; j < matchArray.length - i - 1; j++) {
                if (Double.parseDouble(matchArray[j].getEarnings()) < Double.parseDouble(matchArray[j + 1].getEarnings())) {

                    MatchDifferences temp = matchArray[j];
                    matchArray[j] = matchArray[j + 1];
                    matchArray[j + 1] = temp;
                }
            }
        }
        return matchArray;
    }
}
