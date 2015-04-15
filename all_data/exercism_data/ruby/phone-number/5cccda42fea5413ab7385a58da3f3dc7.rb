class PhoneNumber

  INVALID_NUMBER_REPLACEMENT = "0000000000"

  attr_reader :number, :area_code, :exchange, :line

  def initialize(number)
    @number = sanitise(number)
    @area_code = @number[0..2]
    @exchange = @number[3..5]
    @line = @number[6..10]
  end

  def to_s
    "(#{area_code}) #{exchange}-#{line}"
  end

private
  
  def sanitise(number)
    to_valid_number(excluding_valid_prefix(to_digits(number)))
  end

  def to_digits(number)
    number.gsub(/[\D]/, "")
  end

  def excluding_valid_prefix(digits)
    valid_prefix?(digits) ? digits[1..10] : digits
  end

  def to_valid_number(digits)
    digits.length == 10 ? digits : INVALID_NUMBER_REPLACEMENT
  end

  def valid_prefix?(digits)
    digits.length == 11 && digits.start_with?('1')
  end
end
