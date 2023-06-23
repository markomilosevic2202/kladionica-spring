package marko.kladionica.service.selenium;

import com.google.gson.Gson;
import marko.kladionica.entity.Match;
import marko.kladionica.entity.MatchDifferences;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class JsonService {

    public void writeJsonFileMatchList(List<Match> matches, String nameFile) {
        Gson gson = new Gson();
        String json = gson.toJson(matches);
        try (FileWriter fileWriter = new FileWriter("src/main/resources/json/" + nameFile + ".json")) {
            fileWriter.write(json);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeJsonFileMatchDifferencesList(List<MatchDifferences> matches, String nameFile) {

        Gson gson = new Gson();
        String json = gson.toJson(matches);
        try (FileWriter fileWriter = new FileWriter("src/main/resources/json/" + nameFile + ".json")) {
            fileWriter.write(json);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
