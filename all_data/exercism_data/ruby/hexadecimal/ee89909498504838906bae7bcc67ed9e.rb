class Hexadecimal
  HEX_DIGITS = %w(0 1 2 3 4 5 6 7 8 9 A B C D E F)

  def initialize(hex)
    @hex = validate(hex)
  end

  def to_decimal
    @hex.each_char.reduce(0) { |sum, digit| sum * 16 + HEX_DIGITS.index(digit) }
  end

  private

  def validate(hex)
    return "0" if hex =~ /\H/
    hex.upcase
  end
end
