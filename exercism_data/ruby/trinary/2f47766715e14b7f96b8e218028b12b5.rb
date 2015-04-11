class Trinary
  def initialize(n)
    @n = n.reverse.chars.map(&:to_i)
  end

  def to_decimal
    @n.map.with_index { |e, i| e * (3**i) }.inject(:+)
  end
end
