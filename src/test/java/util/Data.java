package util;

import org.testng.annotations.DataProvider;

public abstract class Data {

    public static String BASE_URL = "https://www.lieferando.de/";
    public static String SEARCH_VALUE_PLACEHOLDER = "Adresse, z.B. Kirchstraße 1";
    public static String DELIVERY_AREA_ERROR_NONEXISTENT = "Die eingegebene Postleitzahl besteht nicht oder ist falsch. Bitte kontrolliere Deine Eingabe und versuche es erneut.";
    public static String DELIVERY_AREA_ERROR_INVALID = "Die eingegebene Adresse/Postleitzahl ist ungültig. Bitte versuche es noch einmal.";
    public static String DROPDOWN_MESSAGE_INVALID_INPUT = "Bitte gib Deine Straße und Hausnummer ein";

    @DataProvider
    public static Object[][] validPostalCodes() {
        return new Object[][] {
                {"10245", "https://www.lieferando.de/lieferservice-berlin-10245"},
                {"10178", "https://www.lieferando.de/lieferservice-berlin-10178"},
                {"10247", "https://www.lieferando.de/lieferservice-berlin-10247"},
                {"10318", "https://www.lieferando.de/lieferservice-berlin-10318"},
                {"10553", "https://www.lieferando.de/lieferservice-berlin-10553"}
        };
    }

    @DataProvider
    public static Object[][] nonExistentPostalCodes() {
        return new Object[][] {
                {"10241", "Non-existent"},
                {"11111", "Non-existent"},
                {"22222", "Non-existent"}
        };
    }

    @DataProvider
    public static Object[][] invalidPostalCodes(){
        return new Object[][] {
                {"1024", "Less than 5 digits"},
                {"102444", "Greater than 5 digits"},
                {"abcd#", "Non-numeric digits"},
                {" ", "Zero digits"}
        };
    }

    @DataProvider
    public static Object[][] streetAddress(){
        return new Object[][] {
                {"Kirchstraße", "", "14199", "Berlin", "https://www.lieferando.de/lieferservice-berlin-14199"},
                {"Kirchstraße", " 1", "46539", "Dinslaken", "https://www.lieferando.de/lieferservice-dinslaken-46539"},
                {"Kirchstraße", "", "47239", "Duisburg", "https://www.lieferando.de/lieferservice-duesseldorf-duisburg-47239"}
        };
    }

    @DataProvider
    public static Object[][] streetNumber(){
        return new Object[][] {
                {"Ringstraße", "1145", "27498", "Helgoland", "https://www.lieferando.de/lieferservice-helgoland-27498"},
                {"Rendsburger Straße", "1145", "24340", "Eckernförde", "https://www.lieferando.de/lieferservice-24340"},
                {"Friedrichstraße", "235", "10969", "Berlin", "https://www.lieferando.de/lieferservice-berlin-10969"}
        };
    }

    @DataProvider
    public static Object[][] cityName(){
        return new Object[][] {
                {"Hamburg Airport", "22335", "Hamburg", "https://www.lieferando.de/lieferservice-hamburg-22335"},
                {"Berlin Hauptbahnhof, Europaplatz", "10557", "Berlin", "https://www.lieferando.de/lieferservice-berlin-10557"},
                {"München Hbf", "80335", "München", "https://www.lieferando.de/lieferservice-oberbayern-muenchen-80335"}
        };
    }
}
