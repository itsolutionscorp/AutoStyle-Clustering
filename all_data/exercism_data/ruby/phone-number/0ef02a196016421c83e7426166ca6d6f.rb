class PhoneNumber

  def initialize(numbers_string)
    @numbers_string = normalize(numbers_string)
  end

  def number
    valid_phone_number
  end

  def area_code
    valid_phone_number[0...3]
  end

  def to_s
    "(#{area_code}) #{prefix}-#{line_number}"
  end

  private

  def valid_phone_number
    strip_country_code if country_code_length?
    set_phone_number
  end

  def set_phone_number
    phone_number_length? ? @numbers_string : empty_phone_string
  end

  def phone_number_length?
    @numbers_string.length == 10
  end

  def country_code_length?
    @numbers_string[0] == '1' && @numbers_string.length == 11
  end

  def strip_country_code
    @numbers_string.gsub!(/^1/, '')
  end

  def normalize(string)
    string.scan(/\d/).join
  end

  def prefix
    valid_phone_number[3..5]
  end

  def line_number
    valid_phone_number[6..-1]
  end

  def empty_phone_string
    '0000000000'
  end
end
