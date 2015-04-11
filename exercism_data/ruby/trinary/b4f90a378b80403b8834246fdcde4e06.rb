class Trinary
  def initialize(trinary)
    @trinary = trinary
  end

  def to_decimal
    numbers = @trinary.chars.map(&:to_i).reverse
    numbers.each_index.reduce(0) { |sum, index| sum += numbers[index] * (3 ** index) }
  end
end
