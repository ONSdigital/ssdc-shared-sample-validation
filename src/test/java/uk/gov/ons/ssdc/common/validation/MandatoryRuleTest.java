package uk.gov.ons.ssdc.common.validation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import org.junit.jupiter.api.Test;

class MandatoryRuleTest {

  @Test
  void checkValidityValid() {
    MandatoryRule underTest = new MandatoryRule();
    Optional<String> validity = underTest.checkValidity("foo");
    assertThat(validity.isPresent()).isFalse();
  }

  @Test
  void checkValidityInvalid() {
    MandatoryRule underTest = new MandatoryRule();
    Optional<String> validity = underTest.checkValidity("");
    assertThat(validity.isPresent()).isTrue();
    assertThat(validity.get()).isEqualTo("Mandatory value missing");
  }
}
