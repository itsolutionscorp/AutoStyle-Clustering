class Hexadecimal
  attr_reader :hex, :hexdec
  def initialize(hex)
    @hex = hex.chars.reverse
    @hexdec = {
      'a' => 10, 'b' => 11, 'c' => 12,
      'd' => 13, 'e' => 14, 'f' => 15
    }
  end

  def to_decimal
    return 0 unless valid_hex?
    hex.each_with_index.inject(0) do |dec, (digit, pow)|
      dec + (hex_power(pow) * (digit =~ /[a-f]/ ? hexdec[digit] : digit.to_i))
    end
  end

  private
  def hex_power(pow)
    16 ** pow
  end

  def valid_hex?
    hex.all? { |h| h =~ /[0-9a-f]/ }
  end
end
