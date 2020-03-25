package Transport4Future.TokenManagement;

public class ErrorMessage {

    // Tag invalid format part 1
    public static String driverVersionInvalidFormat = "The driverVersion tag uses an invalid format.";
    public static String typeDeviceInvalidFormat = "The typeDevice tag uses an invalid format.";
    public static String serialNumberInvalidFormat = "The serialNumber tag uses an invalid format.";
    public static String macAddressInvalidFormat = "The macAddress tag uses an invalid format.";
    public static String emailInvalidFormat = "The email tag is in an invalid format.";
    public static String deviceNameInvalidFormat = "The deviceName tag uses an invalid format.";

    // JSON-related read errors
    public static String jsonParsingError = "Error parsing the json file.";
    public static String jsonExtraTagError = "An extra json tag was detected";
    public static String jsonMissingTagError = "Could not find the json tag ";
    public static String emptyFileError = "The file is empty";

    // File operation errors
    public static String closeFileError = "Input file could not be closed.";
    public static String readFileError = "Input file could not be read.";
    public static String inputFileNotFoundError = "Input file not found.";

    // Hash algorithm errors
    public static String md5AlgorithmNotFound = "The MD5 hashing algorithm was not found";
    public static String sha256AlgorithmNotFound = "The SHA-256 hashing algorithm was not found";

    // Token store errors
    public static String storeDirCreationError = "Could not create the directory 'store'";
    public static String jsonStoreFileCreationError = "Could not create the license store file 'tokenStore.json'";
    public static String closeStoreFileError = "Unable to open the licenses store file";
    public static String writeStoreFileError = "Unable to write to the license store file";
    public static String readStoreFileError = "Unable to read the license store file";

    // Base 64 encoding
    public static String base64EncodingError = "Error encoding the string in Base64.";

    // Part 2
    public static String invalidDateFormat = "The date is not in the correct format";



}
