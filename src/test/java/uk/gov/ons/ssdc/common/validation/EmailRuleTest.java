package uk.gov.ons.ssdc.common.validation;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class EmailRuleTest {

  EmailRule underTest = new EmailRule();

  @Test
  public void testAll() {
    executeAll("joe.bloggs@gms.com", "Good email address", true);
    executeAll("joe.bloggs@ gms.com", "email address with whitespace after", false);
    executeAll("fed @domain.com", "email address with whitespace before @", false);
    executeAll("joe.bloggs@gms.com.", "email address ending with period", false);
    executeAll("joe\\bloggs@gms.com", "doesn't allow back slashes", false);
  }

  private void executeAll(String email, String message, boolean expectedEmpty) {
    //    if (underTest.eqCopied(email).isEmpty() == expectedEmpty) {
    ////      System.out.println("EQ passed: " + message + " for email: [" + email + "]");
    //    } else {
    //      System.out.println("EQ FAILED: " + message + " for email: [" + email + "]");
    //    }
    //
    //    if (underTest.baeldungRegex(email).isEmpty() == expectedEmpty) {
    ////      System.out.println("BD passed: " + message + " for email: [" + email + "]");
    //    } else {
    //      System.out.println("BD FAILED: " + message + " for email: [" + email + "]");
    //    }

    if (underTest.RHRegex(email).isEmpty() == expectedEmpty) {
      System.out.println("RH passed: " + message + " for email: [" + email + "]");
    } else {
      System.out.println("RH FAILED: " + message + " for email: [" + email + "]");
    }
  }

  //
  //  @Test
  //  public void simpleEmailTestBD() {
  //    assertThat(underTest.baeldungRegex("joe.bloggs@somewhere.gov.uk")).as("should be a good
  // email").isEmpty();
  //  }
  //
  @Test
  public void allowWhiteSpaceBD() {
    assertThat(underTest.baeldungRegex("fed @domain.com"))
        .as("Allowed whitespace in email addresss")
        .isNotEmpty();
  }

  @Test
  public void simpleEmailTestEQ() {
    assertThat(underTest.eqCopied("joe.bloggs@somewhere.gov.uk")).isEmpty();
  }
  //
  //    @Test
  //    public void allowWhiteSpaceEQ() {
  //      assertThat(underTest.checkValidity("fed @domain.com"))
  //              .as("Allowed whitespace in email addresss")
  //              .isNotEmpty();
  //    }
  //
  //  @Test
  //  public void testEmailEndingWithPeriodEQ() {
  //    assertThat(underTest.checkValidity("fed@domain.com."))
  //        .as("Allowed email to end with .")
  //        .isNotEmpty();
  //  }
  //
  //  @Test
  //  public void multipleAmpersandsEQ() {
  //    assertThat(underTest.checkValidity("A@b@c@domain.com"))
  //        .as("Allowed multiple ampersand")
  //        .isNotEmpty();
  //  }
  //
  //  @Test void testStartingWithPeriodEQ() {
  //    assertThat(underTest.checkValidity(".fed@domain.com"))
  //            .as("Allowed email to start with .")
  //            .isNotEmpty();
  //  }
  //
  //  @Test void testSingleDomainEQ() {
  //    assertThat(underTest.checkValidity("fed@com"))
  //            .as("Allowed single Domain")
  //            .isNotEmpty();
  //  }
}
