class TriangleError < Exception
end

# class Triangle
class Triangle
  attr_accessor :a, :b, :c
  def initialize(a, b, c)
    raise TriangleError.new if (a + b <= c) || (b + c <= a) || (c + a <= b)
    self.a, self.b, self.c = a, b, c
  end

  def kind
    return :equilateral if a == b && b == c
    return :isosceles if a == b || b == c || a == c
    :scalene
  end
end
