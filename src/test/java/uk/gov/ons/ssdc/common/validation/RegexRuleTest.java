package uk.gov.ons.ssdc.common.validation;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.Test;

class RegexRuleTest {
  @Test
  void checkValidityValidPhoneNumber() {
    RegexRule underTest = new RegexRule("^07[0-9]{9}$");

    Optional<String> actualResult = underTest.checkValidity("07123456789");
    assertThat(actualResult.isPresent()).isFalse();
  }

  @Test
  void checkValidityInvalidPhoneNumberNonNumeric() {
    RegexRule underTest = new RegexRule("^07[0-9]{9}$");

    Optional<String> actualResult = underTest.checkValidity("07123456xxx");
    assertThat(actualResult.isPresent()).isTrue();
    assertThat(actualResult.get()).isEqualTo("Value does not match regex expression: ^07[0-9]{9}$");
  }

  @Test
  void checkValidityInvalidPhoneNumberTooShort() {
    RegexRule underTest = new RegexRule("^07[0-9]{9}$");

    Optional<String> actualResult = underTest.checkValidity("0712345");
    assertThat(actualResult.isPresent()).isTrue();
    assertThat(actualResult.get()).isEqualTo("Value does not match regex expression: ^07[0-9]{9}$");
  }

  @Test
  void checkValidityInvalidPhoneNumberTooLong() {
    RegexRule underTest = new RegexRule("^07[0-9]{9}$");

    Optional<String> actualResult = underTest.checkValidity("07123456789123456789");
    assertThat(actualResult.isPresent()).isTrue();
    assertThat(actualResult.get()).isEqualTo("Value does not match regex expression: ^07[0-9]{9}$");
  }
}
