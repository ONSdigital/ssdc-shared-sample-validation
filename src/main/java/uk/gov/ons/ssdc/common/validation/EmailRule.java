package uk.gov.ons.ssdc.common.validation;


import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//public class EmailRule implements Rule {
public class EmailRule {

  // Python email_regex = re.compile(r"^.+@([^.@][^@\s]+)$")  r means treat \ as not an escaped character.
  private String basic_email_regex_from_eq = "^.+@([^.@][^@\s]+)$";

//  Just one from here
  private String fromBaeldung = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

  private String rh_email_validation_pattern = "(^[^@\s]+@[^@\s]+\\.[^@\s]+$)";

//  @Override
//  public Optional<String> checkValidity(String data) {
//
//    if(data.matches(basic_email_regex_from_eq)) {
//      return Optional.empty();
//    }
//
//      return Optional.of("Email didn't match regex");
//  }

  public Optional<String> eqCopied(String data) {

    Pattern mypattern = Pattern.compile(basic_email_regex_from_eq);
    Matcher mymatcher = mypattern.matcher(data);

  // doesn't split out groups like it does in Python.  So would have to do a split?

//    Matcher mymatcher= mypattern.matcher(mystring);
//
//
//    if(data.matches(basic_email_regex_from_eq)) {
//
//      Pattern mypattern = Pattern.compile(MYREGEX, Pattern.CASE_INSENSITIVE);
//      Matcher mymatcher= mypattern.matcher(mystring);
//      parts = hostname.split(".")
//      if len(parts) > 1 and not tld_part_regex.match(parts[-1]):
//      print("TLD error")

//
//      return Optional.empty();
//    }

    return Optional.of("Email didn't match regex");
  }

  public Optional<String> baeldungRegex(String data) {

    if(data.matches(fromBaeldung)) {
      return Optional.empty();
    }

    return Optional.of("Email didn't match regex");
  }

  public Optional<String> RHRegex(String data) {

    if(data.matches(rh_email_validation_pattern)) {
      return Optional.empty();
    }

    return Optional.of("Email didn't match regex");
  }

//  not email_validation_pattern.fullmatch(str(data.get('email'))):
//          if display_region == 'cy':
//  flash(request, WEBFORM_MISSING_EMAIL_INVALID_MSG_CY)
//            else:
//  flash(request, WEBFORM_MISSING_EMAIL_INVALID_MSG)


}

//tld_part_regex = re.compile( r"^([a-z]{2,63}|xn--([a-z0-9]+-)*[a-z0-9]+)$", re.IGNORECASE )
//        email_regex = re.compile(r"^.+@([^.@][^@\s]+)$")


//
//class EmailTLDCheck:
//        def __init__(self, message: Optional[str] = None):
//        self.message = message or error_messages["INVALID_EMAIL_FORMAT"]
//
//        def __call__(self, form: "QuestionnaireForm", field: StringField) -> None:
//        if match := email_regex.match(field.data):
//        hostname = match.group(1)
//        try:
//        hostname = hostname.encode("idna").decode("ascii")
//        except UnicodeError as exc:
//        raise validators.StopValidation(self.message) from exc
//        parts = hostname.split(".")
//        if len(parts) > 1 and not tld_part_regex.match(parts[-1]):
//        raise validators.StopValidation(self.message)
