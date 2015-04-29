class TriangleError < ArgumentError; end

class Triangle
  attr_reader :kind

  def initialize(a, b, c)
    a, b, c = [a, b, c].sort
    fail TriangleError unless a + b > c
    @kind = %i(equilateral isosceles scalene)[[a, b, c].uniq.count.pred]
  end
end
