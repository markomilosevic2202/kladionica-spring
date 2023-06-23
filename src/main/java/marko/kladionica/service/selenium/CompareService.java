package marko.kladionica.service.selenium;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import marko.kladionica.entity.Match;
import marko.kladionica.entity.MatchDifferences;
import marko.kladionica.entity.NumberMatch;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompareService {
    private final JsonService jsonService;
    public static NumberMatch numberMatch;

    public List<MatchDifferences> compare(List<Match> matchesHomeBettingList, List<Match> matchsForeignBettingList, String nameFile) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        Match[] matchesHomeBetting = objectMapper.readValue(new File("src/main/resources/json/homeBonusBetting.json"), Match[].class);
        Match[] matchsForeignBetting = objectMapper.readValue(new File("src/main/resources/json/foreignBetting.json"), Match[].class);

        List<MatchDifferences> matchesBingo = new ArrayList<>();
        List<Match> matchesDiscard = new ArrayList<>();

        System.out.println(matchesHomeBetting.length);
        System.out.println(matchsForeignBetting.length);
        for (int i = 0; i < matchesHomeBetting.length; i++) {

            Match matchHomeBetting = matchesHomeBetting[i];
            String nameHomeBetting = matchHomeBetting.getName();
            String dateHomeBetting = matchHomeBetting.getDate();
            String timeHomeBetting = matchHomeBetting.getTime();
            String nameHomeHomeBetting = nameHomeBetting.substring(0, nameHomeBetting.indexOf(" -"));
            String nameGuestHomeBetting = nameHomeBetting.substring(nameHomeBetting.lastIndexOf("- ") + 1);
            Boolean bingo = false;

            for (int j = 0; j < matchsForeignBetting.length; j++) {
                try {


                    Match matchForeignBetting = matchsForeignBetting[j];
                    String nameForeignBetting = matchForeignBetting.getName();
                    String nameHomeForeignBetting = nameForeignBetting.substring(0, nameForeignBetting.indexOf(" -"));
                    String nameGuestForeignBetting = nameForeignBetting.substring(nameForeignBetting.lastIndexOf("- ") + 1);
                    String timeForeignBetting = matchForeignBetting.getTime();
                    String dateForeignBetting = matchForeignBetting.getDate().substring(0, 5);

                    if (nameForeignBetting.contains(nameHomeHomeBetting) && nameForeignBetting.contains(nameGuestHomeBetting) && timeForeignBetting.contains(timeHomeBetting) && dateHomeBetting.equals(dateForeignBetting)) {
//
                        matchesBingo.add(differences(matchHomeBetting, matchForeignBetting, "home guest"));
                        bingo = true;
                        break;
                    } else if (nameHomeForeignBetting.contains(nameHomeHomeBetting) && timeForeignBetting.contains(timeHomeBetting) && dateHomeBetting.equals(dateForeignBetting)) {
//
                        matchesBingo.add(differences(matchHomeBetting, matchForeignBetting, "home"));
                        bingo = true;
                        break;
                    } else if (nameGuestForeignBetting.contains(nameGuestHomeBetting) && timeForeignBetting.contains(timeHomeBetting) && dateHomeBetting.equals(dateForeignBetting)) {

                        matchesBingo.add(differences(matchHomeBetting, matchForeignBetting, "guest"));
                        bingo = true;
                        break;
                    }

                    try {


                        if (nameHomeForeignBetting.contains(nameHomeHomeBetting.substring(0, 6)) && timeForeignBetting.contains(timeHomeBetting) && dateHomeBetting.equals(dateForeignBetting)) {
                            matchesBingo.add(differences(matchHomeBetting, matchForeignBetting, "home6"));
                            bingo = true;
                            break;
                        } else if (nameGuestForeignBetting.contains(nameGuestHomeBetting.substring(0, 6)) && timeForeignBetting.contains(timeHomeBetting) && dateHomeBetting.equals(dateForeignBetting)) {
                            matchesBingo.add(differences(matchHomeBetting, matchForeignBetting, "guest6"));
                            bingo = true;
                            break;
                        }
                    } catch (Throwable t) {

                    }


                    try {


                        if (nameHomeForeignBetting.contains(nameHomeHomeBetting.substring(0, 5)) && timeForeignBetting.contains(timeHomeBetting) && dateHomeBetting.equals(dateForeignBetting)) {
                            matchesBingo.add(differences(matchHomeBetting, matchForeignBetting, "home5"));
                            bingo = true;
                            break;
                        } else if (nameGuestForeignBetting.contains(nameGuestHomeBetting.substring(0, 5)) && timeForeignBetting.contains(timeHomeBetting) && dateHomeBetting.equals(dateForeignBetting)) {
                            matchesBingo.add(differences(matchHomeBetting, matchForeignBetting, "guest5"));
                            bingo = true;
                            break;
                        }
                    } catch (Throwable t) {

                    }

                    try {


                        if (nameHomeForeignBetting.contains(nameHomeHomeBetting.substring(0, 4)) && nameGuestForeignBetting.contains(nameGuestHomeBetting.substring(0, 2)) && timeForeignBetting.contains(timeHomeBetting)) {
                            matchesBingo.add(differences(matchHomeBetting, matchForeignBetting, "home4go"));
                            bingo = true;
                            break;
                        } else if (nameGuestForeignBetting.contains(nameGuestHomeBetting.substring(0, 4)) && nameHomeForeignBetting.contains(nameHomeHomeBetting.substring(0, 2)) && timeForeignBetting.contains(timeHomeBetting)) {
                            matchesBingo.add(differences(matchHomeBetting, matchForeignBetting, "guest4go"));
                            bingo = true;
                            break;
                        }
                    } catch (Throwable t) {

                    }
                    try {


                        if (nameHomeForeignBetting.contains(nameHomeHomeBetting.substring(0, 4)) && timeForeignBetting.contains(timeHomeBetting)) {
                            matchesBingo.add(differences(matchHomeBetting, matchForeignBetting, "home4"));
                            bingo = true;
                            break;
                        } else if (nameGuestForeignBetting.contains(nameGuestHomeBetting.substring(0, 4)) && timeForeignBetting.contains(timeHomeBetting)) {
                            matchesBingo.add(differences(matchHomeBetting, matchForeignBetting, "guest4"));
                            bingo = true;
                            break;
                        }
                    } catch (Throwable t) {

                    }

                } catch (Throwable t) {

                }
            }

            if (bingo.equals(false)) {
                matchesDiscard.add(matchHomeBetting);
            }

        }
        this.numberMatch = new NumberMatch(matchesHomeBetting.length, matchsForeignBetting.length, matchesBingo.size(), matchesDiscard.size());

        return matchesBingo;
    }


    static MatchDifferences differences(Match matchHomeBetting, Match matchForeignBetting, String comparison) {

        MatchDifferences matchDifferences = new MatchDifferences();
        matchDifferences.setNameForeign(matchForeignBetting.getName());
        matchDifferences.setComparison(comparison);
        matchDifferences.setTime(matchHomeBetting.getTime());
        matchDifferences.setCodeHome(matchHomeBetting.getCode());
        matchDifferences.setDate(matchHomeBetting.getDate());
        matchDifferences.setName(matchHomeBetting.getName());
        matchDifferences.setOneHome(matchHomeBetting.getOne());
        matchDifferences.setOneForeign(matchForeignBetting.getOne());
        double one = Double.parseDouble(matchHomeBetting.getOne()) - Double.parseDouble(matchForeignBetting.getOne());
        matchDifferences.setOneDifferences(Double.toString(Math.round(one * 100.0) / 100.0));

        matchDifferences.setTwoHome(matchHomeBetting.getTwo());
        matchDifferences.setTwoForeign(matchForeignBetting.getTwo());
        double two = Double.parseDouble(matchHomeBetting.getTwo()) - Double.parseDouble(matchForeignBetting.getTwo());
        matchDifferences.setTwoDifferences(Double.toString(Math.round(two * 100.0) / 100.0));

        matchDifferences.setHomeX(matchHomeBetting.getX());
        matchDifferences.setForeignX(matchForeignBetting.getX());
        double x = Double.parseDouble(matchHomeBetting.getX()) - Double.parseDouble(matchForeignBetting.getX());
        matchDifferences.setDifferencesX(Double.toString(Math.round(x * 100.0) / 100.0));

        return matchDifferences;

    }

    public static NumberMatch getNumberMatch() {
        return numberMatch;
    }
}
