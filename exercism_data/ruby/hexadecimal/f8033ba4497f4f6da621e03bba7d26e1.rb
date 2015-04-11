class Hexadecimal
  HEX_VALUES = { '0' => 0, '1' => 1, '2' => 2, '3' => 3, '4' => 4, '5' => 5,
                 '6' => 6, '7' => 7, '8' => 8, '9' => 9, 'a' => 10, 'b' => 11,
                 'c' => 12, 'd' => 13, 'e' => 14, 'f' => 15 }.freeze
  def initialize(hex)
    @hex = hex.downcase
  end

  def to_decimal
    return 0 unless @hex.scan(/[^0-9a-f]/).empty?

    @hex.reverse.each_char.map.with_index { |e, i| (16**i * HEX_VALUES.fetch(e)) }.reduce(:+)
  end
end
