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
    void testDateManyDayCharacters(){

        String path = "src/resources/02/dateManyDayCharacters.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.invalidDateFormat, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test many hours in date part
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that an invalid date was detected.
     */
    void testDateManyHourCharacters(){

        String path = "src/resources/02/dateManyHourCharacters.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.invalidDateFormat, e.getMessage());

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test many minutes in date part
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that an invalid date was detected.
     */
    void testDateManyMinuteCharacters(){

        String path = "src/resources/02/dateManyMinuteCharacters.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.invalidDateFormat, e.getMessage());

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test many month numbers in date part
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that an invalid date was detected.
     */
    void testDateManyMonthCharacters(){

        String path = "src/resources/02/dateManyMonthCharacters.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.invalidDateFormat, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test many second numbers in date part
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that an invalid date was detected.
     */
    void testDateManySecondCharacters(){

        String path = "src/resources/02/dateManySecondCharacters.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.invalidDateFormat, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test Missing days in date part
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that an invalid date was detected.
     */
    void testDateMissingDayCharacters(){

        String path = "src/resources/02/dateMissingDayCharacters.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.invalidDateFormat, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test Missing hours in date part
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that an invalid date was detected.
     */
    void testDateMissingHourCharacters(){

        String path = "src/resources/02/dateMissingHourCharacters.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.invalidDateFormat, e.getMessage());

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test Missing minutes in date part
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that an invalid date was detected.
     */
    void testDateMissingMinuteCharacters(){

        String path = "src/resources/02/dateMissingMinuteCharacters.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.invalidDateFormat, e.getMessage());

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test Missing month numbers in date part
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that an invalid date was detected.
     */
    void testDateMissingMonthCharacters(){

        String path = "src/resources/02/dateMissingMonthCharacters.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.invalidDateFormat, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test Missing second numbers in date part
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that an invalid date was detected.
     */
    void testDateMissingSecondCharacters(){

        String path = "src/resources/02/dateMissingSecondCharacters.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.invalidDateFormat, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test json that has no tags on it
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that no information in in the file.
     */
    void testEmptyJSON(){

        String path = "src/resources/02/emptyFILE.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.emptyFileError, e.getMessage());

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a empty file
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that no information in in the file.
     */
    void testEmptyFile(){

        String path = "src/resources/02/emptyFILE.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.emptyFileError, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a date with invalid days
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the date is not in a valid format.
     */
    void testErrorDateInvalidDay(){

        String path = "src/resources/02/errorDateInvalidDay.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.invalidDateFormat, e.getMessage());

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a date with missing day bar
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the date is not in a valid format.
     */
    void testErrorDateInvalidDayBar(){

        String path = "src/resources/02/errorDateInvalidDayBar.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.invalidDateFormat, e.getMessage());

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a date with an invalid hour value
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the date is not in a valid format.
     */
    void testErrorDateInvalidHour(){

        String path = "src/resources/02/errorDateInvalidHour.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.invalidDateFormat, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a date with an invalid minute value
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the date is not in a valid format.
     */
    void testErrorDateInvalidMinute(){

        String path = "src/resources/02/errorDateInvalidMinute.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.invalidDateFormat, e.getMessage());

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a date with an invalid month value
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the date is not in a valid format.
     */
    void testErrorDateInvalidMonth(){

        String path = "src/resources/02/errorDateInvalidMonth.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.invalidDateFormat, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a date with an invalid second value
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the date is not in a valid format.
     */
    void testErrorDateInvalidSecond(){

        String path = "src/resources/02/errorDateInvalidSecond.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.invalidDateFormat, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test email with an invalid domain value
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the email is not in a valid format.
     */
    void testErrorIncorrectWrittenDomain(){

        String path = "src/resources/02/errorIncorrectWrittenDomain.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.emailInvalidFormat, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test email with an invalid first part before @ value
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the email is not in a valid format.
     */
    void testErrorIncorrectWrittenEmail(){

        String path = "src/resources/02/errorIncorrectWrittenEmail.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.emailInvalidFormat, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a badly written token.
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the token is not in a valid format.
     */
    void testErrorIncorrectWrittenToken(){

        String path = "src/resources/02/errorIncorrectWrittenToken.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.invalidTokenRequest, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a token request with missing characters.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the token is not in a valid format.
     */
    void testErrorMissingTokenCharacters(){

        String path = "src/resources/02/errorMissingTokenCharacters.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.invalidTokenRequest, e.getMessage());

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a email with a too large domain.
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the email is not in a valid format.
     */
    void testErrorTooLargeEmail(){

        String path = "src/resources/02/errorTooLargeEmail.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.emailInvalidFormat, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a token with a too many characters.
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the token is not in a valid format.
     */
    void testErrorTooLargeToken(){

        String path = "src/resources/02/errorTooLargeToken.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.invalidTokenRequest, e.getMessage());

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a email with a repeated '@' symbol.
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the email is not in a valid format.
     */
    void testExtraAt(){

        String path = "src/resources/02/extra@.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.emailInvalidFormat, e.getMessage());

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a json with a extra comma in a random tag.
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the json is not in a valid format.
     */
    void testExtraComma(){

        String path = "src/resources/02/extraComma.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.jsonParsingError, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a date with a extra day separator.
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the date is not in a valid format.
     */
    void testExtraDayBar(){

        String path = "src/resources/02/extraDayBar.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.invalidDateFormat, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a json with a extra ':' separator.
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the json is not in a valid format.
     */
    void testExtraEqual(){

        String path = "src/resources/02/extraEqual.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.jsonParsingError, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a json with a extra '{' at the beginning.
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the json is not in a valid format.
     */
    void testExtraFirstBrace(){

        String path = "src/resources/02/extraFirstBrace.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.jsonParsingError, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a date with a extra ':' in the date between the hours and minutes part.
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the date is not in a valid format.
     */
    void testExtraHourEqual(){

        String path = "src/resources/02/extraHourEqual.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.invalidDateFormat, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a json where a tag text is duplicated.
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the json is not in a valid format.
     */
    void testDuplicatedInnerTagName(){

        String path = "src/resources/02/duplicatedInnerTagName.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals( ErrorMessage.jsonTagMismatchError, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a json where left quotations are duplicated.
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the json is not in a valid format.
     */
    void testExtraTagLeftQuotes(){

        String path = "src/resources/02/extraTagLeftQuotes.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.jsonParsingError, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a json where right quotations are duplicated.
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the json is not in a valid format.
     */
    void testExtraTagRightQuotes(){

        String path = "src/resources/02/extraTagRightQuotes.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.jsonParsingError, e.getMessage());

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a json where a tag right value is duplicated.
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the json is not in a valid format.
     */
    void testExtraValue(){

        String path = "src/resources/02/extraValue.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.invalidTokenRequest, e.getMessage());

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a json where a tag right value has double quotations on the left.
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the json is not in a valid format.
     */
    void testExtraValueLeftQuotes(){

        String path = "src/resources/02/extraValueLeftQuotes.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.jsonParsingError, e.getMessage());

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a json where a tag right value has double quotations on the right.
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the json is not in a valid format.
     */
    void testExtraValueRightQuotes(){

        String path = "src/resources/02/extraValueRightQuotes.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.jsonParsingError, e.getMessage());

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a json where a tag right value has double quotations on the right.
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the json is not in a valid format.
     */
    void testExtraCommaAtEnd(){

        String path = "src/resources/02/extraCommaAtEnd.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.jsonParsingError, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a date where there is a '.' instead of '/' as a separator.
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the date in not in a valid format.
     */
    void testIncorrectDateDayBar(){

        String path = "src/resources/02/incorrectDateDayBar.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.invalidDateFormat, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a date where there is a '.' instead of ':' as a separator for the hour.
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the date in not in a valid format.
     */
    void testIncorrectDateHourEqual(){

        String path = "src/resources/02/incorrectDateHourEqual.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.invalidDateFormat, e.getMessage());

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a json where the date tag is incorrectly written.
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the json tags do not match.
     */
    void testIncorrectDateTag(){

        String path = "src/resources/02/incorrectDateTag.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.jsonTagMismatchError, e.getMessage());

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a email where the domain separation point is duplicated.
     * Derivation Tree Node: <Value/s>
     * Type of case: Repetition
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the email is not in the valid format.
     */
    void testIncorrectDot(){

        String path = "src/resources/02/incorrectDot.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.emailInvalidFormat, e.getMessage());

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a json where the email tag name wrongly written.
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating a json tag mismatch.
     */
    void testIncorrectEmailTag(){

        String path = "src/resources/02/incorrectEmailTag.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.jsonTagMismatchError, e.getMessage());

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a email where the domain part has an invalid char.
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the email is not in the valid format.
     */
    void incorrectTLD(){

        String path = "src/resources/02/incorrectTLD.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.emailInvalidFormat, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a json where the first brace is '\' instead of '{'.
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the json is not in the valid format.
     */
    void testIncorrectFirstBrace(){

        String path = "src/resources/02/incorrectFirstBrace.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.jsonParsingError, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a json where the first brace is '/' instead of '}'.
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the json is not in the valid format.
     */
    void testIncorrectLastBrace(){

        String path = "src/resources/02/incorrectLastBrace.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.jsonParsingError, e.getMessage());

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a json where the first tag left part left quotes are '/' instead of '"'.
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the json is not in the valid format.
     */
    void testIncorrectTagLeftQuotes(){

        String path = "src/resources/02/incorrectTagLeftQuotes.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.jsonParsingError, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a json where the first tag name is not valid.
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating there is a json tag mismatch.
     */
    void testIncorrectTokenTag(){

        String path = "src/resources/02/incorrectTokenTag.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.jsonTagMismatchError, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a json where the value left quotes are replaced by another symbol.
     * Derivation Tree Node: <Value/s>
     * Type of case: Modification
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the json is invalid.
     */
    void testIncorrectValueLeftQuotes(){

        String path = "src/resources/02/incorrectValueLeftQuotes.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.jsonParsingError, e.getMessage());

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a email with missing '@'.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the email is invalid.
     */
    void noAt(){

        String path = "src/resources/02/no@.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.emailInvalidFormat, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a json with missing comma.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the json is invalid.
     */
    void testNoComma(){

        String path = "src/resources/02/noComma.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.jsonParsingError, e.getMessage());

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a date with missing day.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the date is invalid.
     */
    void testNoDay(){

        String path = "src/resources/02/noDay.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.invalidDateFormat, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a email with missing domain part.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the email is invalid.
     */
    void testNoDomain(){

        String path = "src/resources/02/noDomain.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.emailInvalidFormat, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a email with missing body part.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the email is invalid.
     */
    void testNoEmailBody(){

        String path = "src/resources/02/noEmailBody.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.emailInvalidFormat, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a email with missing '.' separating domain from tld.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the email is invalid.
     */
    void testNoEmailDot(){

        String path = "src/resources/02/noEmailDot.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.emailInvalidFormat, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a json with missing ending quotes in the right part of the tag.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the json is invalid.
     */
    void testNoEndingTagRightQuotes(){

        String path = "src/resources/02/noEndingTagRightQuotes.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.jsonParsingError, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a json with missing ':' symbol separator.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the json is invalid.
     */
    void testNoEqual(){

        String path = "src/resources/02/noEqual.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.jsonParsingError, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a email with missing top level domain part.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the email is invalid.
     */
    void testNoTLD(){

        String path = "src/resources/02/noTLD.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.emailInvalidFormat, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a json with missing starting brace.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the json is invalid.
     */
    void testNoFirstBrace(){

        String path = "src/resources/02/noFirstBrace.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.jsonParsingError, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a date with missing hour.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the date is invalid.
     */
    void testNoHour(){

        String path = "src/resources/02/noHour.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.invalidDateFormat, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a json with missing last brace.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the json is invalid.
     */
    void testNoLastBrace(){

        String path = "src/resources/02/noLastBrace.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.jsonParsingError, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a date with missing minutes part.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the date is invalid.
     */
    void testNoMinute(){

        String path = "src/resources/02/noMinute.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.invalidDateFormat, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a date with missing month part.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the date is invalid.
     */
    void testNoMonth(){

        String path = "src/resources/02/noMonth.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.invalidDateFormat, e.getMessage());

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a date with missing second part.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating the date is invalid.
     */
    void testNoSecond(){

        String path = "src/resources/02/noSecond.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.invalidDateFormat, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a json with missing tag text content.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating a json tag mismatch
     */
    void testNoTag(){

        String path = "src/resources/02/noTag.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.jsonTagMismatchError, e.getMessage());

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a json with missing left tag quotes.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the json is invalid
     */
    void testNoTagLeftQuotes(){

        String path = "src/resources/02/noTagLeftQuotes.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.jsonParsingError, e.getMessage());

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a json whose Token Request value that is empty.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the token request is invalid
     */
    void testNoValue(){

        String path = "src/resources/02/noValue.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.invalidTokenRequest, e.getMessage());

    }

    @Test
    /** Test case: TM-RF-02-I1 - Test a json with missing value left quotes in Token Request.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the json is invalid
     */
    void testNoValueLeftQuotes(){

        String path = "src/resources/02/noValueLeftQuotes.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.jsonParsingError, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a json with missing value right quotes in Token Request.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the json is invalid
     */
    void testNoValueRightQuotes(){

        String path = "src/resources/02/noValueRightQuotes.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.jsonParsingError, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a date with missing year.
     * Derivation Tree Node: <Value/s>
     * Type of case: Omission
     * Testing technique: Syntax Analysis
     * Expected value: Exception thrown stating that the date is invalid
     */
    void testNoYear(){

        String path = "src/resources/02/noYear.json";
        TokenManagementException e =  assertThrows(TokenManagementException.class,
                () -> tokenManager.RequestToken(path));

        assertEquals(ErrorMessage.invalidDateFormat, e.getMessage());

    }


    @Test
    /** Test case: TM-RF-02-I1 - Test a normal case.
     * Derivation Tree Node: <Value/s>
     * Type of case: Regular Value
     * Testing technique: Syntax Analysis
     * Expected value: The operation work without exceptions.
     */
    void validCase(){

        String path = "src/resources/02/validCase.json";
        String result;
        try{
            result = tokenManager.RequestToken(path);
        } catch (Exception e){
            System.out.println(e.getMessage());
            fail();
        }

    }



}
