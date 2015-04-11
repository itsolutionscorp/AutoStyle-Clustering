class Hexadecimal
  DIGITS = "0123456789abcdef"

  def initialize(hex_as_string)
    @hex_as_string = normalize(hex_as_string)
  end

  def to_decimal
    @hex_as_string.reverse.chars.each_with_index.inject(0) { |decimal, (char, i)|
      decimal + DIGITS.index(char) * DIGITS.length**i
    }
  end

  private

  def normalize(hex_as_string)
    if hex_as_string.chars.all? { |x| DIGITS.include?(x) }
      hex_as_string
    else
      "0"
    end
  end
end
