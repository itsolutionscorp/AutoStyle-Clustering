class Hexadecimal
  VALUES = {
    "0" => 0,
    "1" => 1,
    "2" => 2,
    "3" => 3,
    "4" => 4,
    "5" => 5,
    "6" => 6,
    "7" => 7,
    "8" => 8,
    "9" => 9,
    "A" => 10,
    "B" => 11,
    "C" => 12,
    "D" => 13,
    "E" => 14,
    "F" => 15
  }

  def initialize(string)
    @string = string
  end

  def to_decimal
    return 0 if @string.upcase.index(/[^0-9ABCDEF]/)

    @string.upcase
           .chars
           .reverse
           .each_with_index
           .inject(0) { |sum, (digit, index)|
             sum + (VALUES[digit] * (16 ** index))
           }
  end
end
