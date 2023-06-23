package marko.kladionica.service.selenium;


import lombok.RequiredArgsConstructor;
import marko.kladionica.entity.MatchDifferences;
import marko.kladionica.entity.NumberMatch;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final ExcelService excelService;

    public void sendEmail(List<MatchDifferences> matchDifferencesList, NumberMatch numberMatch, String nameFile) {

        MatchDifferences[] matchDifferences = matchDifferencesList.toArray(new MatchDifferences[0]);
        excelService.writeInExcel(matchDifferences, numberMatch, nameFile);

        String host = "mail.lumenspei.com";
        int port = 587;
        String username = "marko.milosevic@lumenspei.com";
        String password = "Donjev.018";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Kreiranje sesije
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("marko.milosevic2202@gmail.com"));
            message.setSubject("Testna poruka");
            message.setText("Ovo je testna poruka poslana putem JavaMail API1.");

            Multipart multipart = new MimeMultipart();

            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            String filePath = "result/" + nameFile + ".xlsx";
            attachmentBodyPart.attachFile(filePath);
            multipart.addBodyPart(attachmentBodyPart);
            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Poslato");

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
