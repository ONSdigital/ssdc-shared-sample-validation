package uk.gov.ons.ssdc.common.validation;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.Test;

class RegexRuleTest {
  private static final String TEST_REGEX_UK_MOBILE_NUMBER = "^07[0-9]{9}$";
  private static final String TEST_REGEX_UK_MOBILE_NUMBER_ERROR = "Not a valid UK mobile number";

  private static final String TEST_REGEX_PRINTABE_ASCII_ONLY = "[ -~]*";
  private static final String TEST_REGEX_PRINTABE_ASCII_ONLY_ERROR =
      "Email contains non-ASCII characters";

  @Test
  void checkValidityValidPhoneNumber() {
    RegexRule underTest =
        new RegexRule(TEST_REGEX_UK_MOBILE_NUMBER, TEST_REGEX_UK_MOBILE_NUMBER_ERROR);

    Optional<String> actualResult = underTest.checkValidity("07123456789");
    assertThat(actualResult).isNotPresent();
  }

  @Test
  void checkValidityInvalidPhoneNumberNonNumeric() {
    RegexRule underTest =
        new RegexRule(TEST_REGEX_UK_MOBILE_NUMBER, TEST_REGEX_UK_MOBILE_NUMBER_ERROR);

    Optional<String> actualResult = underTest.checkValidity("07123456xxx");
    assertThat(actualResult).isPresent().contains(TEST_REGEX_UK_MOBILE_NUMBER_ERROR);
  }

  @Test
  void checkValidityInvalidPhoneNumberTooShort() {
    RegexRule underTest =
        new RegexRule(TEST_REGEX_UK_MOBILE_NUMBER, TEST_REGEX_UK_MOBILE_NUMBER_ERROR);

    Optional<String> actualResult = underTest.checkValidity("0712345");
    assertThat(actualResult).isPresent().contains(TEST_REGEX_UK_MOBILE_NUMBER_ERROR);
  }

  @Test
  void checkValidityInvalidPhoneNumberTooLong() {
    RegexRule underTest =
        new RegexRule(TEST_REGEX_UK_MOBILE_NUMBER, TEST_REGEX_UK_MOBILE_NUMBER_ERROR);

    Optional<String> actualResult = underTest.checkValidity("07123456789123456789");
    assertThat(actualResult).isPresent().contains(TEST_REGEX_UK_MOBILE_NUMBER_ERROR);
  }

  @Test
  void checkValidityValidEmailContainsPrintableAsciiOnly() {
    RegexRule underTest =
        new RegexRule(TEST_REGEX_PRINTABE_ASCII_ONLY, TEST_REGEX_PRINTABE_ASCII_ONLY_ERROR);

    Optional<String> actualResult = underTest.checkValidity("valid@mail.com");
    assertThat(actualResult).isNotPresent();
  }

  @Test
  void checkValidityInvalidEmailContainsNonAscii() {
    RegexRule underTest =
        new RegexRule(TEST_REGEX_PRINTABE_ASCII_ONLY, TEST_REGEX_PRINTABE_ASCII_ONLY_ERROR);

    Optional<String> actualResult = underTest.checkValidity("\u00F6invalid@mail.com");
    assertThat(actualResult).isPresent().contains(TEST_REGEX_PRINTABE_ASCII_ONLY_ERROR);
  }

  @Test
  void checkValidityInvalidEmailContainsNonPrintableAscii() {
    RegexRule underTest =
        new RegexRule(TEST_REGEX_PRINTABE_ASCII_ONLY, TEST_REGEX_PRINTABE_ASCII_ONLY_ERROR);

    Optional<String> actualResult = underTest.checkValidity((char) 31 + "invalid@mail.com");
    assertThat(actualResult).isPresent().contains(TEST_REGEX_PRINTABE_ASCII_ONLY_ERROR);

    actualResult = underTest.checkValidity((char) 127 + "invalid@mail.com");
    assertThat(actualResult).isPresent().contains(TEST_REGEX_PRINTABE_ASCII_ONLY_ERROR);
  }
}
