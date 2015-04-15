class Hexadecimal
  InvalidDigit = Class.new(StandardError)
  def initialize string_representation
    @string = string_representation
  end

  def to_decimal
    digits.map.with_index do |digit, radix|
      digit * 16**radix
    end.inject(:+)
  rescue InvalidDigit
    0
  end

  # returns the hexadecimal digits in integer form
  # least significant nibble is first
  private def digits
    @string.chars
           .map(&method(:char_to_decimal))
           .reverse
  end

  private def char_to_decimal char
    case char
    when '0'..'9'
      char.to_i
    when 'a'..'f'
      char.ord - 'a'.ord + 10
    else
      fail InvalidDigit
    end
  end
end
