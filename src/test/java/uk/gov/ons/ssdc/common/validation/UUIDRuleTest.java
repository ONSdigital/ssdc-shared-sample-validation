package uk.gov.ons.ssdc.common.validation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class UUIDRuleTest {

  @Test
  void checkValidityValid() {
    UUIDRule underTest = new UUIDRule();
    Optional<String> validity = underTest.checkValidity(UUID.randomUUID().toString());
    assertThat(validity.isPresent()).isFalse();
  }

  @Test
  void checkValidityInvalid() {
    UUIDRule underTest = new UUIDRule();
    Optional<String> validity = underTest.checkValidity("this clearly is not a UUID");
    assertThat(validity.isPresent()).isTrue();
    assertThat(validity.get()).isEqualTo("Not a valid UUID");
  }
}
