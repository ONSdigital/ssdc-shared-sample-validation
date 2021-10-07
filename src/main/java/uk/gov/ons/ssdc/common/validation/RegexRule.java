package uk.gov.ons.ssdc.common.validation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;

public class RegexRule implements Rule {
  private final String expression;

  @JsonCreator
  public RegexRule(@JsonProperty("expression") String expression) {
    this.expression = expression;
  }

  @Override
  public Optional<String> checkValidity(String data) {
    if (!data.matches(expression)) {
      return Optional.of("Value does not match regex expression: " + expression);
    }

    return Optional.empty();
  }

  public String getExpression() {
    return expression;
  }
}
