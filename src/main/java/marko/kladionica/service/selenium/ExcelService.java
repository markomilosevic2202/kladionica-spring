package marko.kladionica.service.selenium;

import marko.kladionica.entity.MatchDifferences;
import marko.kladionica.entity.NumberMatch;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class ExcelService {
    public static String writeInExcel(MatchDifferences[] matchDifferences, NumberMatch numberMatch, String nameFile) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH mm ss");
        DecimalFormat df = new DecimalFormat("#.##"); // format kvota
        XSSFSheet sheet = workbook.createSheet(time.format(formatter));//formira shit
        sheet.setColumnWidth(2, 5000);
        sheet.setColumnWidth(5, 8000);
        sheet.setColumnWidth(15, 8000);// širi kolonu// širi kolonu
        int rownum = 0;
        Row row = sheet.createRow(rownum++);
        int cellnum = 1;

        Cell cell = row.createCell(2);
        cell.setCellValue("Br. Domacih");
        cell = row.createCell(3);
        cell.setCellValue(numberMatch.getNumberMatchesHome());
        row = sheet.createRow(rownum++);
        cell = row.createCell(2);
        cell.setCellValue("Br. Stranih");
        cell = row.createCell(3);
        cell.setCellValue(numberMatch.getNumberMatchesForeign());
        row = sheet.createRow(rownum++);
        cell = row.createCell(2);
        cell.setCellValue("Br. Nadjenih");
        cell = row.createCell(3);
        cell.setCellValue(numberMatch.getNumberMatchesBingo());
        row = sheet.createRow(rownum++);
        cell = row.createCell(2);
        cell.setCellValue("Br. Nenadjenih");
        cell = row.createCell(3);
        cell.setCellValue(numberMatch.getNumberMatchesNotFound());

        cellnum = 1;
        row = sheet.createRow(rownum++);
        row = sheet.createRow(rownum++);
        row = sheet.createRow(rownum++);
        cell = row.createCell(cellnum++);
        cell.setCellValue("Sifra Domaca");
        cell = row.createCell(cellnum++);
        cell.setCellValue("Uhvacen");
        cell = row.createCell(cellnum++);
        cell.setCellValue("Datum");
        cell = row.createCell(cellnum++);
        cell.setCellValue("Vreme");
        cell = row.createCell(cellnum++);
        cell.setCellValue("Naziv");
        cell = row.createCell(cellnum++);
        cell.setCellValue("Kec Domaca");
        cell = row.createCell(cellnum++);
        cell.setCellValue("Kec Strana");
        cell = row.createCell(cellnum++);
        cell.setCellValue("Kec Razlika");
        cell = row.createCell(cellnum++);
        cell.setCellValue("Dvojka Domaca");
        cell = row.createCell(cellnum++);
        cell.setCellValue("Dvojka Strana");
        cell = row.createCell(cellnum++);
        cell.setCellValue("Dvojka Razlika");
        cell = row.createCell(cellnum++);
        cell.setCellValue("X Domaca");
        cell = row.createCell(cellnum++);
        cell.setCellValue("X Strana");
        cell = row.createCell(cellnum++);
        cell.setCellValue("X Razlika");
        cell = row.createCell(cellnum++);
        cell.setCellValue("Ime Strna");
        cell = row.createCell(cellnum++);
        cell.setCellValue("Kontra kvota");
        cell = row.createCell(cellnum++);
        cell.setCellValue("Kvota Domaca");
        cell = row.createCell(cellnum++);
        cell.setCellValue("Ulog");
        cell = row.createCell(cellnum++);
        cell.setCellValue("Zarada");
        cell = row.createCell(cellnum++);
        cell.setCellValue("Link Orbit");


        for (int i = 0; i < matchDifferences.length; i++) {


            row = sheet.createRow(rownum++);
            MatchDifferences match = matchDifferences[i];
            int cellnum1 = 1;
            Cell cell1 = row.createCell(cellnum1++);
            cell1.setCellValue(match.getCodeHome());
            cell1 = row.createCell(cellnum1++);
            cell1.setCellValue(match.getComparison());
            cell1 = row.createCell(cellnum1++);
            cell1.setCellValue(match.getDate());
            cell1 = row.createCell(cellnum1++);
            cell1.setCellValue(match.getTime());
            cell1 = row.createCell(cellnum1++);
            cell1.setCellValue(match.getName());
            cell1 = row.createCell(cellnum1++);
            cell1.setCellValue(match.getOneHome());
            cell1 = row.createCell(cellnum1++);
            cell1.setCellValue(match.getOneForeign());
            cell1 = row.createCell(cellnum1++);
            cell1.setCellValue(match.getOneDifferences());
            cell1 = row.createCell(cellnum1++);
            cell1.setCellValue(match.getTwoHome());
            cell1 = row.createCell(cellnum1++);
            cell1.setCellValue(match.getTwoForeign());
            cell1 = row.createCell(cellnum1++);
            cell1.setCellValue(match.getTwoDifferences());
            cell1 = row.createCell(cellnum1++);
            cell1.setCellValue(match.getHomeX());
            cell1 = row.createCell(cellnum1++);
            cell1.setCellValue(match.getForeignX());
            cell1 = row.createCell(cellnum1++);
            cell1.setCellValue(match.getDifferencesX());
            cell1 = row.createCell(cellnum1++);
            cell1.setCellValue(match.getNameForeign());
            cell1 = row.createCell(cellnum1++);
            cell1.setCellValue(match.getCounterQuota());
            cell1 = row.createCell(cellnum1++);
            cell1.setCellValue(match.getHigherOdds());
            cell1 = row.createCell(cellnum1++);
            cell1.setCellValue(match.getBet());
            cell1 = row.createCell(cellnum1++);
            cell1.setCellValue(match.getEarnings());
            cell1 = row.createCell(cellnum1++);
            cell1.setCellValue("Link");
            CreationHelper creationHelper = workbook.getCreationHelper();
//           Make link
//           Hyperlink hyperlink = creationHelper.createHyperlink(HyperlinkType.URL);
//           String link = match.getUrlOrbit();
//           hyperlink.setAddress(link);
//           cell1.setHyperlink(hyperlink);
//           cell1 = row.createCell(cellnum1++);


        }
        try {
//            formatting the file name according to the current date and time
//            LocalDate date = LocalDate.now();
//            DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd-MM-yyyy")
//            date.format(formatterData) + " - " + time.format(formatter)
            FileOutputStream out = new FileOutputStream(new File("result/" + nameFile + ".xlsx"));  //+ date.format(formatterData) + " - " + time.format(formatter)

            workbook.write(out);
            out.close();
            return nameFile + ".xlsx";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
