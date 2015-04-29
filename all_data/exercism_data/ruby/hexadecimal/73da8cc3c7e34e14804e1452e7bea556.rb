class Hexadecimal

  HEX = %w(0 1 2 3 4 5 6 7 8 9 a b c d e f)

  def initialize(hex)
    @hex = hex
  end

  def to_decimal
    return 0 if @hex.match(/[^0-9a-f]/)
    @hex.each_char.reduce(0) { |sum, d| sum * 16 + HEX.index(d) }
  end

end
