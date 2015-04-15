class Triangle
  attr_reader :kind

  def initialize(a, b, c)
    raise TriangleError unless a + b > c && a + c > b && b + c > a
    @kind = if a == b && b == c
              :equilateral
            elsif a == b || a == c || b == c
              :isosceles
            else
              :scalene
            end
  end
end

class TriangleError < StandardError; end
