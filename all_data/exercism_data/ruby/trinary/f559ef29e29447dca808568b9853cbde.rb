class Trinary
  BASE = 3

  def initialize(tri_string)
    @value = tri_string
  end

  def to_decimal
    each_place do |num, index|
      num * BASE ** index
    end
  end

  def each_place
    @value.chars.reverse.each_with_index.reduce(0) do |sum, (num, index)|
      sum + yield(num.to_i, index)
    end
  end

end
