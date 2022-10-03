package uk.gov.ons.ssdc.common.validation;

import java.util.Optional;

public class NumericRule implements Rule {
  @Override
  public Optional<String> checkValidity(String data) {
    if (data.matches("\\d*")) {
      return Optional.empty();
    }

    return Optional.of("Contains non digit characters");
  }
}
