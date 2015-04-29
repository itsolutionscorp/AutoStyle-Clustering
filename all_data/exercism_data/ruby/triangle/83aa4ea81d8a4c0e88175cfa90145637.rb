class Triangle
  attr_reader :a, :b, :c

  def initialize(a, b, c)
    @a, @b, @c = [a, b, c].sort
    validate_triangle
  end

  def kind
    if equilateral?
      return :equilateral
    elsif isosceles?
      return :isosceles
    else
      return :scalene
    end
  end

  private

  def equilateral?
    a == b && a == c
  end

  def isosceles?
    a == b || b == c
  end

  def validate_triangle
    validate_positive_length
    validate_triangle_inequality
  end

  def validate_positive_length
    [a, b, c].each do |side|
      raise TriangleError if side <= 0
    end
  end

  def validate_triangle_inequality
    if (a + b) <= c
      raise TriangleError
    end
  end
end

class TriangleError < Exception
end
