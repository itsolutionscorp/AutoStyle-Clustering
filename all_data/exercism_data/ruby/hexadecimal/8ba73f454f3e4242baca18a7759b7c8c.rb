class Hexadecimal
  attr_reader :hex

  def initialize(hex)
    @hex = hex 
  end

  def to_decimal
    valid? || hex.to_i(16)
  end

  def valid?
    0 if hex.chars.any? { |letter| letter > "f"}
  end
end
