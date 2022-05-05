package uk.gov.ons.ssdc.common.validation;

import java.net.IDN;
import java.util.Optional;
import java.util.regex.Pattern;

public class EmailRule implements Rule {

  /* Regexes from
    https://github.com/alphagov/notifications-utils/blob/7d48b8f825fafb0db0bad106ccccdd1f889cf657/notifications_utils/__init__.py#L11

    I ran part of the original Python Code locally to get a better idea around the r escape char - and copied them over.
   */
  private static final String EMAIL_REGEX = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~\\-]+@([^.@][^@\\s]+)$";
  private static final String TOP_LEVEL_DOMAIN_REGEX = "^([a-z]{2,63}|xn--([a-z0-9]+-)*[a-z0-9]+)$";
  private static final String HOSTNAME_PART_REGEX = "^(xn|[a-z0-9]+)(-?-[a-z0-9]+)*$";

  private static final int MAX_EMAIL_LENGTH = 320;
  public static final int MAX_HOSTNAME_LENGTH = 253;
  public static final int MAX_PART_LENGTH = 63;

  /*
  The validation code is from
  https://github.com/alphagov/notifications-utils/blob/7d48b8f825fafb0db0bad106ccccdd1f889cf657/notifications_utils/recipients.py#L634

  Their comment:
     almost exactly the same as by https://github.com/wtforms/wtforms/blob/master/wtforms/validators.py,
     with minor tweaks for SES compatibility - to avoid complications we are a lot stricter with the local part
     than neccessary - we don't allow any double quotes or semicolons to prevent SES Technical Failures

    I have not implement the 1st line of:
    email_address = strip_and_remove_obscure_whitespace(email_address)
    This is because in validation we don't want to be fixing things and they (goc notify) handle it anyway
  */
  @Override
  public Optional<String> checkValidity(String data) {

    Pattern emailPattern = Pattern.compile(EMAIL_REGEX);

    if (!emailPattern.matcher(data).matches()) {
      return Optional.of("Email didn't match regex");
    }

    if (data.length() > MAX_EMAIL_LENGTH) {
      return Optional.of("Email longer than: " + MAX_EMAIL_LENGTH);
    }

    if (data.contains("..")) {
      return Optional.of("Email contains consecutive periods");
    }

    String[] emailSplit = data.split("@");
    if (emailSplit.length != 2) {
      return Optional.of(
          "Expected splitting email on @ to equal 2, instead equalled: " + emailSplit.length);
    }

    String hostName = emailSplit[1];

    // idna = "Internationalized domain name" - this encode/decode cycle converts unicode into its accurate ascii
    // representation as the web uses. '例え.テスト'.encode('idna') == b'xn--r8jz45g.xn--zckzah'
    hostName = IDN.toASCII(hostName);

    String[] parts = hostName.split("\\.");

    if (hostName.length() > MAX_HOSTNAME_LENGTH) {
      return Optional.of("Email hostname longer than: " + MAX_HOSTNAME_LENGTH);
    }

    if (parts.length < 2) {
      return Optional.of("Email hostname parts less than 2");
    }

    Pattern hostNamePartPattern = Pattern.compile(HOSTNAME_PART_REGEX, Pattern.CASE_INSENSITIVE);

    for (String part : parts) {
      if (part == null) {
        return Optional.of("part of hostname null");
      }

      if (part.length() > MAX_PART_LENGTH) {
        return Optional.of("Email part longer than: " + MAX_PART_LENGTH);
      }

      if (!hostNamePartPattern.matcher(part).matches()) {
        return Optional.of("part of hostname does not match REGEX");
      }
    }

    Pattern tldPattern = Pattern.compile(TOP_LEVEL_DOMAIN_REGEX, Pattern.CASE_INSENSITIVE);

    if (!tldPattern.matcher(parts[parts.length - 1]).matches()) {
      return Optional.of("Email didn't match regex");
    }

    return Optional.empty();
  }
}
