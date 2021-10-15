package uk.gov.ons.ssdc.common.validation;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.Test;

public class ISODateRuleTest {

  @Test
  void checkValidityValid() {
    ISODateRule underTest = new ISODateRule();
    Optional<String> validity = underTest.checkValidity("2021-10-09");
    assertThat(validity.isPresent()).isFalse();
  }

  @Test
  void checkValidityInvalid() {
    ISODateRule underTest = new ISODateRule();
    Optional<String> validity = underTest.checkValidity("66-66-6666");
    assertThat(validity.isPresent()).isTrue();
    assertThat(validity.get()).isEqualTo("Not a valid ISO date");
  }
}
