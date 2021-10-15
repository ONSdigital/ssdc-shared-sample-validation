package uk.gov.ons.ssdc.common.validation;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.Test;

public class ISODateTimeRuleTest {

  @Test
  void checkValidityValid() {
    ISODateTimeRule underTest = new ISODateTimeRule();
    Optional<String> validity = underTest.checkValidity("2021-10-09T15:45:33.123Z");
    assertThat(validity.isPresent()).isFalse();
  }

  @Test
  void checkValidityValidNoZ() {
    ISODateTimeRule underTest = new ISODateTimeRule();
    Optional<String> validity = underTest.checkValidity("2021-10-09T12:00+00");
    assertThat(validity.isPresent()).isFalse();
  }

  @Test
  void checkValidityInvalid() {
    ISODateTimeRule underTest = new ISODateTimeRule();
    Optional<String> validity = underTest.checkValidity("66-66-6666:66:66:66.666Z");
    assertThat(validity.isPresent()).isTrue();
    assertThat(validity.get()).isEqualTo("Not a valid ISO date time");
  }
}
