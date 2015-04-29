class Hexadecimal
  DIGITS = "0123456789abcdef"

  def initialize(string)
    @string = normalize(string)
  end

  def to_decimal
    @string.reverse.chars.with_index.inject(0) { |decimal, (char, i)|
      decimal + DIGITS.index(char) * DIGITS.length**i
    }
  end

  private

  def normalize(string)
    if string.chars.all? { |x| DIGITS.include?(x) }
      string
    else
      "0"
    end
  end
end
