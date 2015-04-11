class Triangle
  attr_reader :triangle
  def initialize *sides
    @triangle = sides.sort
    raise TriangleError if triangle.any? { |side| side <= 0 }
    raise TriangleError if triangle[0] + triangle[1] <= triangle[2]
  end
  
  def kind
    return :equilateral if @triangle.uniq.length == 1
    return :isosceles if @triangle.uniq.length == 2
    return :scalene if @triangle.uniq.length == 3
    raise TriangleError
  end
end

class TriangleError < StandardError
end
