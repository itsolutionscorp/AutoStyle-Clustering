class Trinary
  attr_reader :decimal

  def initialize(decimal)
    @decimal = decimal
  end

  def to_decimal
    decimal.to_i(3)
  end
end
