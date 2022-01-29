package utils;

public class Properties {

    private static final PropertiesReader propertiesReader = new PropertiesReader();

    public static final String baseUrl = propertiesReader.getBaseUrl();
    public static final String username = propertiesReader.getUserName();
    public static final String password = propertiesReader.getPassword();
    public static final String wrongUserName = propertiesReader.getWrongUserName();
    public static final String wrongPassword = propertiesReader.getWrongPassword();
    public static final String googleEmail = propertiesReader.getGoogleEmail();
    public static final String googlePassword = propertiesReader.getGooglePassword();
    public static final String hotelcity = propertiesReader.getHotelCity();;


}
