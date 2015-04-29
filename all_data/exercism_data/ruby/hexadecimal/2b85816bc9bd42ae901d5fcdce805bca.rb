require 'byebug'

class Hexadecimal
  DIGITS = %w{0 1 2 3 4 5 6 7 8 9 a b c d e f}

  def initialize(hex)
    @hex = hex
  end

  def to_decimal
    @hex.chars.reverse.each_with_index.reduce(0) do |sum, (hex_char, index)|
      return 0 unless valid_hex?(hex_char)
      sum + value_of(hex_char) * 16 ** index
    end
  end

  private

  def value_of(hex_char)
    DIGITS.index(hex_char).to_i
  end

  def valid_hex?(digit)
    DIGITS.include?(digit)
  end
end
