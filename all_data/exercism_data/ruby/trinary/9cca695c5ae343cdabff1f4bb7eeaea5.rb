class Trinary
  def initialize(trits)
    @trits = trits
  end

  def to_decimal
    @trits.chars.reduce(0) { |sum, trit|
      sum *= 3
      sum += trit.to_i
    }
  end
end
