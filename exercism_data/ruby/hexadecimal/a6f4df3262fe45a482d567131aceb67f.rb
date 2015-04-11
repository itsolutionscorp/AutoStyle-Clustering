class Hexadecimal

  attr_reader :hex

  def initialize(hex)
    @hex = hex
  end

  def to_decimal
    check_illegal || hex.to_i(16)
  end

  def check_illegal
    0 if hex.chars.any? {|char| char > 'f'}
  end

end
