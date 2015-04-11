class Hexadecimal
  def initialize(hex_string)
    @value = convert(hex_string)
  end

  def to_decimal
    @value
  end

  private

  HEX_DIGITS = ('0'..'9').to_a.concat ('a'..'f').to_a
  HEX_BASE = 16

  def convert(hex_string)
    value = 0
    hex_string.downcase.each_char do |hex_digit|
      value *= HEX_BASE
      value += digit_value(hex_digit)
    end
    value
  rescue ArgumentError
    0
  end

  def digit_value(hex_digit)
    HEX_DIGITS.index(hex_digit) or raise ArgumentError
  end

end
