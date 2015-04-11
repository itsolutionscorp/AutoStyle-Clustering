class Trinary
  def initialize(value)
    @value = value
  end

  def to_decimal
    @value.to_i(3)
  end
end
