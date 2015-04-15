class PhoneNumber
  attr_reader :number
  INVALID_NUMBER = '0000000000'
  DIGITS_IN_LOCAL = 10
  DIGITS_IN_INTERNATIONAL = 11
  INTERNATIONAL_CODE = '1'

  def initialize number
    @number = clean number
  end

  def area_code
    number[0..2]
  end

  def prefix
    number[3..5]
  end

  def identifier
    number[6..9]
  end

  def to_s
    "(#{area_code}) #{prefix}-#{identifier}"
  end

  private

  def clean number
    return INVALID_NUMBER unless valid?(to_digits number)
    to_digits(number)[-DIGITS_IN_LOCAL .. -1]
  end

  def valid? number
    local?(number) || international?(number)
  end

  def to_digits number
    number.scan(/\d+/).join
  end

  def local? number
    number.size == DIGITS_IN_LOCAL
  end

  def international? number
    number.size == DIGITS_IN_INTERNATIONAL &&
    number.start_with?(INTERNATIONAL_CODE)
  end
end
