class PhoneNumber

  attr_accessor :number

  def initialize(number)
    @number = parse_as_phone_number number.to_s
  end

  def to_s
    "(#{area_code}) #{prefix}-#{line_number}"
  end

  def area_code
    number[0..2]
  end

  def prefix
    number[3..5]
  end

  def line_number
    number[6..9]
  end

  private

  BAD_NUMBER = '0'*10

  def parse_as_phone_number(number)
    digits = extract_digits number

    return BAD_NUMBER unless good_number_format?(digits)
    good_number_that_needs_trimming?(digits) ? without_leading_one(digits) : digits
  end

  def extract_digits(number)
    number.scan(/\d/).join
  end

  def good_number_format?(digits)
    standard_good_number?(digits) || good_number_that_needs_trimming?(digits)
  end

  def standard_good_number?(digits)
    digits.length == 10
  end

  def good_number_that_needs_trimming?(digits)
    digits.length == 11 && digits[0] == '1'
  end

  def without_leading_one(digits)
    digits[1..-1]
  end

end
