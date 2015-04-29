class Hexadecimal
  def initialize(hex)
    @hex = hex
  end

  def to_decimal
    decimal = 0
    hex_digits = split_into_hex_digits(@hex)
    digits_in_decimal = digits_to_decimal(hex_digits)
    return 0 if digits_in_decimal.include?(nil)
    digits_in_decimal.reverse.each_with_index do |digit, index|
      decimal += digit * (16 ** index)
    end
    return decimal
  end

  def split_into_hex_digits(hex)
    digits = hex.scan(/./)
  end

  def digits_to_decimal(hex_digits)
    digits_in_decimal = hex_digits.map do |digit|
      case digit
      when "0".."9"
        digit.to_i
      when "a"
        10
      when "b"
        11
      when "c"
        12
      when "d"
        13
      when "e"
        14
      when "f"
        15
      end
    end
    return digits_in_decimal
  end
end
