package com.world.covid.util;

import javax.ejb.Stateless;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Stateless
public class GenUtil {

    private String language;
    private Locale localization;


    public String getAlphaNumericString() {
        int n = 20;
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }

    public long generateRandom(int length) {
        Random random = new Random();
        char[] digits = new char[length];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < length; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        return Long.parseLong(new String(digits));
    }

    public Locale changeLocal(String ln) {
        this.language = ln;
        switch (this.language) {
            case "en":
                this.localization = Locale.ENGLISH;
                break;
            case "bn":
                this.localization = new Locale("bn");
                //FacesContext.getCurrentInstance().getViewRoot().setLocale(localization);
                break;
        }
        return this.localization;
    }

    public String getMACAddrPC() {
        String macAddr = null;
        String command = null;
        String sOsName = System.getProperty("os.name");
        if (sOsName.startsWith("Windows")) {
            command = "getmac";
        } else {

            if ((sOsName.startsWith("Linux")) || (sOsName.startsWith("Mac"))
                    || (sOsName.startsWith("HP-UX"))) {
                command = "/sbin/ifconfig";
            } else {
                System.out.println("The current operating system '" + sOsName
                        + "' is not supported.");
            }
        }
        Pattern p = Pattern.compile("([a-fA-F0-9]{1,2}(-|:)){5}[a-fA-F0-9]{1,2}");
        try {
            Process pa = Runtime.getRuntime().exec(command);
            pa.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(pa.getInputStream()));
            String line;
            Matcher m;
            while ((line = reader.readLine()) != null) {
                m = p.matcher(line);

                if (!m.find()) {
                    continue;
                }
                line = m.group();
                break;
            }
            System.out.println(line);
            macAddr = line;
        } catch (IOException | InterruptedException ex) {
            System.out.println("Exception In MAC GET::" + ex);
        }
        return macAddr;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Locale getLocalization() {
        return localization;
    }

    public void setLocalization(Locale localization) {
        this.localization = localization;
    }
}
