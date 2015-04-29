class Triangle
  attr_reader :kind

  def initialize(a,b,c)
    @a, @b, @c = [a, b, c].sort
    raise TriangleError if error?
    @kind = find_kind
  end

private

  def find_kind
    return :equilateral if @a == @b && @b == @c
    return :scalene     if @a != @b && @b != @c
    :isosceles
  end

  def error?
    return true if @a <= 0 || @b <= 0 || @c <= 0
    return true if (@a + @b) <= @c
    false
  end
end

class TriangleError < StandardError
end
