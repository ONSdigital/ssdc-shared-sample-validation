package uk.gov.ons.ssdc.common.validation;

import java.io.Serializable;
import java.util.Optional;

@FunctionalInterface
public interface Rule extends Serializable {
  Optional<String> checkValidity(String data);
}
