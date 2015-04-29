class Hexadecimal
  CONVERSION = {
    '0' => 0,
    '1' => 1,
    '2' => 2,
    '3' => 3,
    '4' => 4,
    '5' => 5,
    '6' => 6,
    '7' => 7,
    '8' => 8,
    '9' => 9,
    'a' => 10,
    'b' => 11,
    'c' => 12,
    'd' => 13,
    'e' => 14,
    'f' => 15
  }

  def initialize(hex_num)
    hex_num.clear if hex_num =~ /[^0-9a-f]/
    @hex_num = hex_num
  end

  def to_decimal
    # hex_num.to_i(16)

    hex_num.reverse.each_char.with_index.reduce(0) do |decimal, (hex, index)|
      decimal + (CONVERSION[hex] * (16**index))
    end
  end

  private

  attr_reader :hex_num
end
