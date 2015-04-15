# class to convert hex string to decimal
class Hexadecimal
  HEX2DEC = {
    '0' => 0, '1'  => 1, '2'  => 2, '3'  => 3,
    '4' => 4, '5'  => 5, '6'  => 6, '7'  => 7,
    '8' => 8, '9'  => 9, 'a'  => 10, 'b' => 11,
    'c' => 12, 'd' => 13, 'e' => 14, 'f' => 15
  }

  HEXBASE = 16

  def initialize(hex_string)
    @hex = hex_string.chars.reverse
  end

  def to_decimal
    validate.each_with_index.reduce(0) do |result, (char, idx)|
      result + HEX2DEC[char] * (HEXBASE**idx)
    end
  end

  def validate
    (@hex - HEX2DEC.keys).empty? ? @hex : []
  end
end
