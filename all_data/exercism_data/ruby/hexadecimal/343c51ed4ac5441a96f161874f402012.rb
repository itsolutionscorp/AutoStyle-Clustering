class Hexadecimal
  attr_reader :hex_code

  def initialize hex_code
    @hex_code = hex_code.match(/[^0-9a-f]/) ? '0' : hex_code
  end

  def to_decimal
    hex_code.chars.reverse_each.with_index.reduce 0 do |sum, (d, i)|
      sum + %w(0 1 2 3 4 5 6 7 8 9 a b c d e f).index(d) * 16**i
    end
  end
end
