class Trinary
  def initialize n
    @n = n.chars.reverse
  end

  def to_decimal
    r = 0
    @n.each_with_index { |n, i| r += n.to_i * 3**i }
    r
  end
end
