class PhoneNumber

  attr_reader :current_digits

  def initialize(raw_phone_number)
    @current_digits = raw_phone_number
  end

  def number
    @phone_number ||= clean_raw_number
    valid? ? @phone_number : '0000000000'
  end

  def area_code
    number[0,3]
  end

  def to_s
    "(#{area_code}) #{number[3,3]}-#{number[6,4]}"
  end

  private

  def valid?
    !too_short? && !too_long? && !has_leading_foriegn_country_code?
  end

  def clean_raw_number
    remove_non_numeric_characters
    remove_leading_usa_country_code
    current_digits
  end

  def remove_non_numeric_characters
    @current_digits = current_digits.tr('^0-9', '')
  end

  def remove_leading_usa_country_code
    @current_digits = current_digits[1, 10] if has_leading_usa_country_code?
  end

  def has_leading_usa_country_code?
    has_country_code? && usa_country_code?
  end

  def has_leading_foriegn_country_code?
    has_country_code? && !usa_country_code?
  end

  def has_country_code?
    current_digits.length == 11
  end

  def usa_country_code?
    current_digits[0] == '1'
  end

  def too_long?
    current_digits.length > 11
  end

  def too_short?
    current_digits.length < 10
  end

end
