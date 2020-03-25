package Transport4Future.TokenManagement;

public class ErrorMessage {

    //Field invalid format
    public static String driverVersionInvalidFormat = "The driverVersion field uses an invalid format.";
    public static String typeDeviceInvalidFormat = "Error: typeDevice uses an invalid format.";
    public static String serialNumberInvalidFormat = "The serialNumber field uses an invalid format.";
    public static String macAddressInvalidFormat = "The macAddress field uses an invalid format.";
    public static String emailInvalidFormat = "The email is in an invalid format.";
    public static String deviceNameInvalidFormat = "Error: deviceName uses an invalid format.";


    public static String jsonParsingError = "Error: error reading the json file.";
    public static String jsonExtraTagError = "An extra tag was detected";
    public static String closeFileError = "Error: input file could not be closed.";
    public static String readFileError = "Error: input file could not be read.";
    public static String inputFileNotFoundError = "Error: input file not found.";
    public static String jsonFieldNotFound = "Error: invalid input data in JSON structure.";
    public static String invalidDateFormat = "The date is not in the correct format";

    //Algorithm errors
    public static String md5AlgorithmNotFound = "The MD5 algorithm was not found";
    public static String sha256AlgorithmNotFound = "The SHA-256 algorithm was not found";

    //Token store

    public static String storeDirCreationError = "Could not create the directory 'store'";
    public static String jsonStoreFileCreationError = "Could not create the license store file tokenStore.json";
    public static String closeStoreFileError = "Error: Unable to open internal licenses store file";
    public static String writeStoreFileError = "Error: Unable to write to license store file";
    public static String readStoreFileError = "Error: Unable to read to license store file";

    public static String base64EncodingError = "Error enconding 64URL.";




}
