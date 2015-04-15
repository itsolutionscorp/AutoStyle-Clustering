class Trinary
  def initialize(trinary)
    @digits = trinary.chars.map(&:to_i)
  end

  def to_decimal
    @digits.reverse_each.with_index.inject(0) do |decimal, (digit, exponent)|
      decimal + digit * 3 ** exponent
    end
  end
end
