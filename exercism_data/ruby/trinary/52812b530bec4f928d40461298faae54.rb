class Trinary
  def initialize(n)
    @n = n
  end

  def to_decimal
    @n.reverse.chars.each_with_index.map { |c, i| 3**i * c.to_i }.inject(:+) 
  end
end
