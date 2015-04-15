class PhoneNumber

  attr_reader :raw_number

  def initialize(raw_phone_number)
    @raw_number = raw_phone_number
  end

  def number
    @phone_number ||= clean_raw_number
  end

  def area_code
    number[0,3]
  end

  def to_s
    "(#{area_code}) #{number[3,3]}-#{number[6,4]}"
  end
  
  private

  def clean_raw_number
    remove_non_numeric_characters
    replace_invalid_numbers
    remove_leading_usa_country_code
    replace_numbers_with_invalid_country_code
    raw_number
  end

  def remove_non_numeric_characters
    @raw_number = raw_number.tr('^0-9', '')
  end

  def replace_invalid_numbers
    @raw_number = '0000000000' if invalid?
  end

  def remove_leading_usa_country_code
    @raw_number = raw_number[1, 10] if has_leading_usa_country_code?
  end

  def replace_numbers_with_invalid_country_code
    @raw_number = '0000000000' if has_leading_foriegn_country_code?
  end

  def has_leading_usa_country_code?
    has_country_code? && usa_country_code?
  end

  def has_leading_foriegn_country_code?
    has_country_code? && !usa_country_code?
  end

  def has_country_code?
    raw_number.length == 11
  end

  def usa_country_code?
    raw_number[0] == '1'
  end

  def invalid?
    too_short? || too_long?
  end

  def too_long?
    raw_number.length > 11
  end

  def too_short?
    raw_number.length < 10
  end

end
