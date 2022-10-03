package uk.gov.ons.ssdc.common.validation;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class LengthRuleTest {

  @Test
  void checkValidityValid() {
    LengthRule underTest = new LengthRule(5);
    Optional<String> validity = underTest.checkValidity("12345");
    assertThat(validity).isNotPresent();
  }

  @Test
  void checkValidityInvalid() {
    LengthRule underTest = new LengthRule(5);
    Optional<String> validity = underTest.checkValidity("1234567890");
    assertThat(validity).isPresent().contains("Exceeded max length of 5");
  }
}
