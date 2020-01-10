package com.jd.pokerSessions.boostrap;

import com.jd.pokerSessions.model.Card;
import com.jd.pokerSessions.model.Hand;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public abstract class Utils {
    private static Set<Hand> csvReader(BufferedReader br) throws IOException {
        String line = "";

        Set<Hand> handSet = new HashSet<>();

        br.readLine(); // skip 1st line

        while ((line = br.readLine()) != null) {
            String[] hand = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            handSet.add(parseHandString(hand));
        }
        return handSet;
    }

    public static Set<Hand> tmpReaderCsv(String filename) {

        System.out.println("Starting tmpReaderCsv");
        BufferedReader br = null;

        try {
            File serverFile = new File(filename);
            FileReader fr = new FileReader(serverFile);
            br = new BufferedReader(fr);
            return csvReader(br);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new HashSet<>();
    }

    public static Set<Hand> resourceReaderCsv(String filename) {

        System.out.println("Starting resourceReaderCsv");
        BufferedReader br = null;

        try {
            Resource resource = new ClassPathResource(filename);
            FileReader fr = new FileReader(resource.getFile());
            br = new BufferedReader(fr);
            return csvReader(br);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new HashSet<>();
    }

    private static Hand parseHandString(String[] handString) {

        Hand returnHand = new Hand();

        for (int i = 0; i < handString.length; i++) {
            handString[i] = handString[i].replace("\"", "");
        }

        returnHand.setHandId(handString[0]);
        returnHand.setHandDate(getDate(handString[1]));
        returnHand.setHandTime(getTime(handString[1]));
        returnHand.setH1(Card.valueOf("_" + handString[29].split(" ")[0].replace("10", "T")));
        returnHand.setH2(Card.valueOf("_" + handString[29].split(" ")[1].replace("10", "T")));
        if (handString[30].split(" ").length >= 3) {
            returnHand.setB1(Card.valueOf("_" + handString[30].split(" ")[0].replace("10", "T")));
            returnHand.setB2(Card.valueOf("_" + handString[30].split(" ")[1].replace("10", "T")));
            returnHand.setB3(Card.valueOf("_" + handString[30].split(" ")[2].replace("10", "T")));
            returnHand.setSawFlop(true);
            if (!handString[31].replace("\\s+", "").equals("")) {
                returnHand.setB4(Card.valueOf("_" + handString[31].replace("10", "T")));
                returnHand.setSawTurn(true);
                if (!handString[32].replace("\\s+", "").equals("")) {
                    returnHand.setB5(Card.valueOf("_" + handString[32].replace("10", "T")));
                    returnHand.setSawRiver(true);
                } else {
                    returnHand.setSawRiver(false);
                }
            } else {
                returnHand.setSawTurn(false);
            }
        } else {
            returnHand.setSawFlop(false);
        }
        returnHand.setSawShowdown(Boolean.parseBoolean(handString[13]));
        returnHand.setPosition(handString[8]);

        return returnHand;
    }

    private static LocalDate getDate(String dateString) {
        String date = dateString.split(" ")[0];

        LocalDate returnDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        return returnDate;
    }

    private static LocalTime getTime(String timeString) {
        String time = timeString.replace("  ", " ").split(" ")[1]
                + " " + timeString.replace("  ", " ").split(" ")[2].toUpperCase();
        if (time.length() == 7) {
            time = "0" + time;
        }

        LocalTime returnTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("hh:mm a"));

        return returnTime;
    }
}
