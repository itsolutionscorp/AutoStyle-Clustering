class NumeralSystem
  def valid_digits
    fail "Subclasses should implement valid_digits"
  end

  InvalidDigit = Class.new(StandardError)

  def initialize string_representation
    @as_string = string_representation
  end

  def to_decimal
    digits_as_integer_starting_with_least_significant
      .map.with_index do |digit, radix|
        digit * valid_digits.length**radix
      end.inject(0, :+)
  rescue InvalidDigit
    0
  end

  # returns the hexadecimal digits in integer form
  # least significant digit is first
  private def digits_as_integer_starting_with_least_significant
    @as_string
      .chars
      .map(&method(:char_to_decimal))
      .reverse
  end

  private def char_to_decimal char
    valid_digits.index(char) or fail InvalidDigit
  end
end

class Hexadecimal < NumeralSystem
  def valid_digits
    '0123456789abcdef'
  end
end

class Binary < NumeralSystem
  def valid_digits
    '01'
  end
end
