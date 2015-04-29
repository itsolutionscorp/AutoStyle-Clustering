class Hexadecimal
  attr_reader :to_decimal

  Hexdigits = '0123456789abcdef'.chars
  Value = Hash[*(Hexdigits.map{ |d| [d, Hexdigits.index(d)] }).flatten ]

  def initialize hex
    @hex = hex.downcase.match(/[^0-9a-f]/) ? '0' : hex
    @to_decimal ||= convert_to_decimal
  end

  private
    def convert_to_decimal
      @hex.each_char.inject(0) { |value, d| value = value*16 + Value[d] }
    end
end
