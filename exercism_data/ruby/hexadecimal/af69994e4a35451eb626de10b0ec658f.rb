class Hexadecimal
  def initialize hex
    @hex = hex =~ /[^0-9a-f]/i ? '0' : hex
  end

  def to_decimal
    @hex.chars.inject(0) { |sum, n| sum * 16 + to_num(n) }
  end

  private

  def to_num x
    '0123456789abcdef'.index(x.downcase)
  end
end
