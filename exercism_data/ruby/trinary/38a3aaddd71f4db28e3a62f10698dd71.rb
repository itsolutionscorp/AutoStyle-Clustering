class Trinary

  def initialize(trinary)
    @trinary = trinary
  end

  def to_decimal
    @trinary.chars.map(&:to_i).reverse.map.with_index { |d, i| d * 3 ** i }.inject(:+)
  end

end
