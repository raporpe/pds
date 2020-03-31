package Transport4Future.TokenManagement;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest_2 {


    private static TokenManager tokenManager;

    @BeforeAll
    static void TM_RF_01() {
        tokenManager = new TokenManager();
    }



    /**
     *   .--.      .-'.      .--.      .--.      .--.      .--.      .`-.      .--.
     * :::::.\::::::::.\:::::::::\::::PART 2 TESTS:::\::::::::.\::::::::.\::::::::.\
     * '      `--'      `.-'      `--'      `--'      `--'      `-.'      `--'      `
     */

    //Standard
    /** Test case: TM-RF-02-<id> - <Name>
     * Derivation Tree Node: <Value/s>
     * Type of case: <Regular Value | Omission | Repetition | Modification>
     * Testing technique: Syntax Analysis
     * Expected value: <Description>
     */

    @Test
    /** Test case: TM-RF-02-I1 - Test many days in date part
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that an invalid date was detected.
     */
    void testDateManyDayCharacters_02(){

        String path = "src/resources/02/dateManyDayCharacters_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test many hours in date part
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that an invalid date was detected.
     */
    void testDateManyHourCharacters_02(){

        String path = "src/resources/02/dateManyHourCharacters_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test many minutes in date part
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that an invalid date was detected.
     */
    void testDateManyMinuteCharacters_02(){

        String path = "src/resources/02/dateManyMinuteCharacters_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test many month numbers in date part
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that an invalid date was detected.
     */
    void testDateManyMonthCharacters_02(){

        String path = "src/resources/02/dateManyMonthCharacters_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test many second numbers in date part
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that an invalid date was detected.
     */
    void testDateManySecondCharacters_02(){

        String path = "src/resources/02/dateManySecondCharacters_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test Missing days in date part
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that an invalid date was detected.
     */
    void testDateMissingDayCharacters_02(){

        String path = "src/resources/02/dateMissingDayCharacters_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test Missing hours in date part
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that an invalid date was detected.
     */
    void testDateMissingHourCharacters_02(){

        String path = "src/resources/02/dateMissingHourCharacters_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test Missing minutes in date part
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that an invalid date was detected.
     */
    void testDateMissingMinuteCharacters_02(){

        String path = "src/resources/02/dateMissingMinuteCharacters_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test Missing month numbers in date part
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that an invalid date was detected.
     */
    void testDateMissingMonthCharacters_02(){

        String path = "src/resources/02/dateMissingMonthCharacters_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test Missing second numbers in date part
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that an invalid date was detected.
     */
    void testDateMissingSecondCharacters_02(){

        String path = "src/resources/02/dateMissingSecondCharacters_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test json that has no tags on it
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that no information in in the file.
     */
    void testEmptyJSON_02(){

        String path = "src/resources/02/emptyFILE_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.emptyFileError);

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a empty file
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that no information in in the file.
     */
    void testEmptyFile_02(){

        String path = "src/resources/02/emptyFILE_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.emptyFileError);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a date with invalid days
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the date is not in a valid format.
     */
    void testErrorDateInvalidDay_02(){

        String path = "src/resources/02/errorDateInvalidDay_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a date with missing day bar
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the date is not in a valid format.
     */
    void testErrorDateInvalidDayBar_02(){

        String path = "src/resources/02/errorDateInvalidDayBar_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a date with an invalid hour value
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the date is not in a valid format.
     */
    void testErrorDateInvalidHour_02(){

        String path = "src/resources/02/errorDateInvalidHour_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a date with an invalid minute value
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the date is not in a valid format.
     */
    void testErrorDateInvalidMinute_02(){

        String path = "src/resources/02/errorDateInvalidMinute_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a date with an invalid month value
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the date is not in a valid format.
     */
    void testErrorDateInvalidMonth_02(){

        String path = "src/resources/02/errorDateInvalidMonth_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a date with an invalid second value
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the date is not in a valid format.
     */
    void testErrorDateInvalidSecond_02(){

        String path = "src/resources/02/errorDateInvalidSecond_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test email with an invalid domain value
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the email is not in a valid format.
     */
    void testErrorIncorrectWrittenDomain_02(){

        String path = "src/resources/02/errorIncorrectWrittenDomain_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.emailInvalidFormat);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test email with an invalid first part before @ value
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the email is not in a valid format.
     */
    void testErrorIncorrectWrittenEmail_02(){

        String path = "src/resources/02/errorIncorrectWrittenEmail_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.emailInvalidFormat);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a badly written token.
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the token is not in a valid format.
     */
    void testErrorIncorrectWrittenToken_02(){

        String path = "src/resources/02/errorIncorrectWrittenToken_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.invalidTokenRequest);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a token request with missing characters.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the token is not in a valid format.
     */
    void testErrorMissingTokenCharacters_02(){

        String path = "src/resources/02/errorMissingTokenCharacters_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.invalidTokenRequest);

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a email with a too large domain.
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the email is not in a valid format.
     */
    void testErrorTooLargeEmail_02(){

        String path = "src/resources/02/errorTooLargeEmail_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.emailInvalidFormat);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a token with a too many characters.
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the token is not in a valid format.
     */
    void testErrorTooLargeToken_02(){

        String path = "src/resources/02/errorTooLargeToken_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.invalidTokenRequest);

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a email with a repeated '@' symbol.
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the email is not in a valid format.
     */
    void testExtraAt_02(){

        String path = "src/resources/02/extra@_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.emailInvalidFormat);

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a json with a extra comma in a random tag.
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the json is not in a valid format.
     */
    void testExtraComma_02(){

        String path = "src/resources/02/extraComma_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a date with a extra day separator.
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the date is not in a valid format.
     */
    void testExtraDayBar_02(){

        String path = "src/resources/02/extraDayBar_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a json with a extra ':' separator.
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the json is not in a valid format.
     */
    void testExtraEqual_02(){

        String path = "src/resources/02/extraEqual_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a json with a extra '{' at the beginning.
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the json is not in a valid format.
     */
    void testExtraFirstBrace_02(){

        String path = "src/resources/02/extraFirstBrace_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a date with a extra ':' in the date between the hours and minutes part.
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the date is not in a valid format.
     */
    void testExtraHourEqual_02(){

        String path = "src/resources/02/extraHourEqual_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a json with an extra '}' at the end.
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the json is not in a valid format.
     */
    void testExtraLastBrace_02(){

        String path = "src/resources/02/extraLastBrace_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a json where a tag text is duplicated.
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the json is not in a valid format.
     */
    void testDuplicatedInnerTagName_02(){

        String path = "src/resources/02/duplicatedInnerTagName_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.jsonTagMismatchError);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a json where left quotations are duplicated.
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the json is not in a valid format.
     */
    void testExtraTagLeftQuotes_02(){

        String path = "src/resources/02/extraTagLeftQuotes_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a json where right quotations are duplicated.
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the json is not in a valid format.
     */
    void testExtraTagRightQuotes_02(){

        String path = "src/resources/02/extraTagRightQuotes_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a json where a tag right value is duplicated.
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the json is not in a valid format.
     */
    void testExtraValue_02(){

        String path = "src/resources/02/extraValue_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.invalidTokenRequest);

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a json where a tag right value has double quotations on the left.
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the json is not in a valid format.
     */
    void testExtraValueLeftQuotes_02(){

        String path = "src/resources/02/extraValueLeftQuotes_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a json where a tag right value has double quotations on the right.
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the json is not in a valid format.
     */
    void testExtraValueRightQuotes_02(){

        String path = "src/resources/02/extraValueRightQuotes_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

    }

    //amorcito
    @Test
    /** Test case: TM-RF-02-I1 - Test a json where a tag right value has double quotations on the right.
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the json is not in a valid format.
     */
    void testHasComma_02(){

        String path = "src/resources/02/hasComma_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a json where a tag right value has double quotations on the right.
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the json is not in a valid format.
     */
    void testHasEqual_02(){

        String path = "src/resources/02/hasEqual_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a json where a tag right value has double quotations on the right.
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the json is not in a valid format.
     */
    void testHasExtraIncorrectComma_02(){

        String path = "src/resources/02/hasExtraIncorrectComma_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

    }

    //hasta aqui amorcito

    @Test
    /** Test case: TM-RF-02-I1 - Test a date where there is a '.' instead of '/' as a separator.
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the date in not in a valid format.
     */
    void testIncorrectDateDayBar_02(){

        String path = "src/resources/02/incorrectDateDayBar_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a date where there is a '.' instead of ':' as a separator for the hour.
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the date in not in a valid format.
     */
    void testIncorrectDateHourEqual_02(){

        String path = "src/resources/02/incorrectDateHourEqual_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a json where the date tag is incorrectly written.
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the json tags do not match.
     */
    void testIncorrectDateTag_02(){

        String path = "src/resources/02/incorrectDateTag_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.jsonTagMismatchError);

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a email where the domain separation point is duplicated.
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the email is not in the valid format.
     */
    void testIncorrectDot_02(){

        String path = "src/resources/02/incorrectDot_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.emailInvalidFormat);

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a json where the email tag name wrongly written.
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating a json tag mismatch.
     */
    void testIncorrectEmailTag_02(){

        String path = "src/resources/02/incorrectEmailTag_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.jsonTagMismatchError);

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a email where the domain part has an invalid char.
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the email is not in the valid format.
     */
    void incorrectExtension_02(){

        String path = "src/resources/02/incorrectExtension_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.emailInvalidFormat);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a json where the first brace is '\' instead of '{'.
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the json is not in the valid format.
     */
    void testIncorrectFirstBrace_02(){

        String path = "src/resources/02/incorrectFirstBrace_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a json where the first brace is '/' instead of '}'.
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the json is not in the valid format.
     */
    void testIncorrectLastBrace_02(){

        String path = "src/resources/02/incorrectLastBrace_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a json where the first tag left part left quotes are '/' instead of '"'.
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the json is not in the valid format.
     */
    void testIncorrectTagLeftQuotes_02(){

        String path = "src/resources/02/incorrectTagLeftQuotes_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a json where the first tag name is not valid.
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating there is a json tag mismatch.
     */
    void testIncorrectTokenTag_02(){

        String path = "src/resources/02/incorrectTokenTag_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.jsonTagMismatchError);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a json where the value left quotes are replaced by another symbol.
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the json is invalid.
     */
    void testIncorrectValueLeftQuotes_02(){

        String path = "src/resources/02/incorrectValueLeftQuotes_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a email with missing '@'.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the email is invalid.
     */
    void noAt_02(){

        String path = "src/resources/02/no@_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.emailInvalidFormat);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a json with missing comma.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the json is invalid.
     */
    void testNoComma_02(){

        String path = "src/resources/02/noComma_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a date with missing day.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the date is invalid.
     */
    void testNoDay_02(){

        String path = "src/resources/02/noDay_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a email with missing domain part.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the email is invalid.
     */
    void testNoDomain_02(){

        String path = "src/resources/02/noDomain_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.emailInvalidFormat);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a email with missing body part.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the email is invalid.
     */
    void testNoEmailBody_02(){

        String path = "src/resources/02/noEmailBody_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.emailInvalidFormat);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a email with missing '.' separating domain from tld.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the email is invalid.
     */
    void testNoEmailDot_02(){

        String path = "src/resources/02/noEmailDot_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.emailInvalidFormat);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a json with missing ending quotes in the right part of the tag.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the json is invalid.
     */
    void testNoEndingTagRightQuotes_02(){

        String path = "src/resources/02/noEndingTagRightQuotes_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a json with missing ':' symbol separator.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the json is invalid.
     */
    void testNoEqual_02(){

        String path = "src/resources/02/noEqual_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a email with missing top level domain part.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the email is invalid.
     */
    void testNoExtension_02(){

        String path = "src/resources/02/noExtension_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.emailInvalidFormat);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a json with missing starting brace.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the json is invalid.
     */
    void testNoFirstBrace_02(){

        String path = "src/resources/02/noFirstBrace_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a date with missing hour.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the date is invalid.
     */
    void testNoHour_02(){

        String path = "src/resources/02/noHour_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a json with missing last brace.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the json is invalid.
     */
    void testNoLastBrace_02(){

        String path = "src/resources/02/noLastBrace_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a date with missing minutes part.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the date is invalid.
     */
    void testNoMinute_02(){

        String path = "src/resources/02/noMinute_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a date with missing month part.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the date is invalid.
     */
    void testNoMonth_02(){

        String path = "src/resources/02/noMonth_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a date with missing second part.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the date is invalid.
     */
    void testNoSecond_02(){

        String path = "src/resources/02/noSecond_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a json with missing tag text content.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating a json tag mismatch
     */
    void testNoTag_02(){

        String path = "src/resources/02/noTag_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.jsonTagMismatchError);

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a json with missing left tag quotes.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the json is invalid
     */
    void testNoTagLeftQuotes_02(){

        String path = "src/resources/02/noTagLeftQuotes_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a json whose Token Request value that is empty.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the token request is invalid
     */
    void testNoValue_02(){

        String path = "src/resources/02/noValue_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.invalidTokenRequest);

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a json with missing value left quotes in Token Request.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the json is invalid
     */
    void testNoValueLeftQuotes_02(){

        String path = "src/resources/02/noValueLeftQuotes_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a json with missing value right quotes in Token Request.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the json is invalid
     */
    void testNoValueRightQuotes_02(){

        String path = "src/resources/02/noValueRightQuotes_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a date with missing year.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the date is invalid
     */
    void testNoYear_02(){

        String path = "src/resources/02/noYear_02.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a normal case.
     * Derivation Tree Node: <Value/s>
     * Type of case: Regular Value
     * Testing technique: Syntax Analysis
     * Expected value: The operation work without exceptions.
     */
    void validCase_02(){

        String path = "src/resources/02/validCase_02.json";
        String result;
        try{
            result = tokenManager.RequestToken(path);
        } catch (Exception e){
            e.printStackTrace();
            fail();
        }

    }



}
