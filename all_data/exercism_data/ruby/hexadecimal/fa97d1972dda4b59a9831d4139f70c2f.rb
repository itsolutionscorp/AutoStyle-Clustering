class Hexadecimal
  def initialize(hex_string)
    @hex_string = hex_string.upcase
  end

  def to_decimal
    try_convert_to_decimal rescue 0
  end

  private

  def hex_digit_2_dec(hex_digit)
    digit_value = HEX_DIGITS.index(hex_digit)
    fail ArgumentError unless digit_value
    digit_value
  end

  def try_convert_to_decimal
    reversed_hex = @hex_string.reverse.chars

    digit_values = reversed_hex.map{|d| hex_digit_2_dec(d)}

    weighted_digits = digit_values.map.with_index do |digit, index|
      weight = 16 ** index
      digit * weight
    end

    weighted_digits.reduce :+
  end

  HEX_DIGITS = [*('0'..'9'), *('A'..'F')]
end
