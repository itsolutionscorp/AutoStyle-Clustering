class Hexadecimal
  def initialize(hex_string)
    @hex_string = hex_string.to_s.downcase
  end

  HEX_CHARS = %w[0 1 2 3 4 5 6 7 8 9 a b c d e f]
  def to_decimal
    return 0 unless @hex_string =~ /^[0-9a-f]*$/
    chars = @hex_string.reverse.split('')
    chars.map.with_index do |char, i|
      HEX_CHARS.index(char) * 16**i
    end.inject(:+)
  end

end
