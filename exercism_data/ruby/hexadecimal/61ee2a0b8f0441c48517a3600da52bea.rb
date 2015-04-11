class Hexadecimal
  HEX_DEC = { 'a' => 10, 'b' => 11, 'c' => 12, 'd' => 13, 'e' => 14, 'f' => 15 }

  def initialize(hex)
    @hex = (hex?(hex) ? hex.downcase.chars.reverse : [])
  end

  def to_decimal
    @hex.each_with_index.reduce(0) { |a, (ch, idx)| a + decimal(ch) * 16**idx }
  end

  private

  def hex?(input)
    input =~ /^[0-9a-f]*$/i
  end

  def decimal(hex_symbol)
    hex_symbol =~ /\d/ ? hex_symbol.to_i : HEX_DEC[hex_symbol]
  end
end
