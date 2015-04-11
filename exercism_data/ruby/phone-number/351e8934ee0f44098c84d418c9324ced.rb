class PhoneNumber
  attr_accessor :area_code, :prefix, :line_number

  def initialize(numbers_string)
    @numbers_string = normalize(numbers_string)
    @phone_number = validate_phone_number
  end

  def number
    @phone_number
  end

  def to_s
    "(#{@area_code}) #{@prefix}-#{@line_number}"
  end

  private

  def validate_phone_number
    strip_country_code if country_code_digit_and_length?
    if phone_number_length?
      assign_phone_number
    else
      empty_phone_string
    end
  end

  def strip_country_code
    @numbers_string.gsub!(/^1/, '')
  end

  def assign_phone_number
    captures = @numbers_string.match(/(\d{0,3})(\d{3,3})(\d{4,6})/i).captures
    @area_code, @prefix, @line_number = [$1, $2, $3]
    captures.join
  end

  def country_code_digit_and_length?
    @numbers_string.length == 11 && !!(@numbers_string =~ /^1/)
  end

  def phone_number_length?
    @numbers_string.length == 10
  end

  def normalize(string)
    string.scan(/\d/).join
  end

  def empty_phone_string
    '0000000000'
  end
end
