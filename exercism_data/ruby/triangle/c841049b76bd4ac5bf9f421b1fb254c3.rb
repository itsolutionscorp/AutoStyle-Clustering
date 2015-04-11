class Triangle
  def initialize(a, b, c)
    @a, @b, @c = a, b, c
  end

  def kind
    fail TriangleError if [@a, @b, @c].any? { |s| s <= 0 }
    fail TriangleError if @a + @b <= @c or @a + @c <= @b or @b + @c <= @a
    return :equilateral if @a == @b and @b == @c
    return :isosceles if @a == @b or @b == @c or @a == @c
    :scalene
  end
end

class TriangleError < StandardError
end
