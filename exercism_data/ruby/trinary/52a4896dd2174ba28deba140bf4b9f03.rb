class Trinary
  def initialize(n)
    @n = n.reverse.chars.map(&:to_i)
  end

  def to_decimal
    @n.each_with_index.map { |n, i| n * 3 ** i }.reduce(:+)
  end
end
