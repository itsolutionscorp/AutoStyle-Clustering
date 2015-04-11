class Trinary
  attr_reader :trinary

  def initialize(n)
    @trinary = n.to_i
  end

  def to_decimal
    digits = trinary.to_s.chars.map { |x| x.to_i }
    digi = proc { |sum, (digit, index)| sum += digit * (3**index) }
    digits.reverse.each.with_index.reduce(0, &digi)
  end
end
