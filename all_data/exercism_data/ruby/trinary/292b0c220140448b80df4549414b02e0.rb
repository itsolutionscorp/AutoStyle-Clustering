class Trinary
  def initialize(trinary)
    @trinary = trinary
  end

  def to_decimal
    @trinary.to_i(3)
  end
end
