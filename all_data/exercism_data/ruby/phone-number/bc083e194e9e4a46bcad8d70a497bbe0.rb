class PhoneNumber
  attr_reader :as_string

  INVALID_NUMBER = '0000000000'

  def initialize(as_string)
    @as_string = as_string
  end

  def number
    return INVALID_NUMBER unless valid?(as_numbers)
    sanitized_number
  end

  def valid?(number_string)
    number_string.length == 10 ||
    (number_string.length == 11 && number_string[0] == '1')
  end

  def area_code
    sanitized_number[0..2]
  end

  def to_s
    "(#{area_code}) #{local_number_to_s}"
  end

  def local_number
    sanitized_number[3..-1]
  end

  def local_number_to_s
    first_part = local_number[0..2]
    second_part = local_number[3..-1]
    "#{first_part}-#{second_part}"
  end

  private

  def as_numbers
    @numbers ||= as_string.gsub(/[^0-9]*/, '')
  end

  def sanitized_number
    if as_numbers.length == 11
      as_numbers[1..-1]
    else
      as_numbers
    end
  end
end
