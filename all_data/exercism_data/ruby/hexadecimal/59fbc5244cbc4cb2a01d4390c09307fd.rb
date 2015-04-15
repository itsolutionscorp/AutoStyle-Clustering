class Hexadecimal
  attr_reader :to_decimal

  Hexdigits = '0123456789abcdef'.chars
  Hex2dec = Hash[Hexdigits.zip (0...Hexdigits.size)]

  def initialize hex
    @hex = hex.downcase.match(/[^0-9a-f]/) ? '0' : hex
    @to_decimal ||= convert_to_decimal
  end

  private
    def convert_to_decimal
      @hex.each_char.inject(0) { |value, h| value = value*16 + Hex2dec[h] }
    end
end
