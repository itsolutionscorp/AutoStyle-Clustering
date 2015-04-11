class Triangle
  attr_reader(:a, :b, :c)

  def initialize(a, b, c)
    @a, @b, @c = [a, b, c].sort
    not_a_triangle?
    check_length?
  end

  def kind
    if equilateral?
      :equilateral
    elsif isosceles?
      return :isosceles
    else
      :scalene
    end
  end

  def equilateral?
    a == b && a == c
  end

  def isosceles?
    a == b || b == c
  end

  def not_a_triangle?
    if (a + b) <= c
      raise TriangleError
    end
  end

  def check_length?
    [a, b, c].each do |side|
      if side <= 0
        raise TriangleError
      end
    end
  end
end

class TriangleError < Exception
end
