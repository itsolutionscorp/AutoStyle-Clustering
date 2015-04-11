class TriangleError < StandardError; end

class Triangle

  def initialize(a, b, c)
    if not ((a + b > c) && (b + c > a) && (c + a > b))
      raise TriangleError
    end
    @a = a
    @b = b
    @c = c
  end

  def kind
    case
    when (@a == @b) && (@b == @c)
      return :equilateral
    when (@a == @b) || (@b == @c) || (@a == @c)
      return :isosceles
    else
      return :scalene
    end
  end

end
