class TriangleError < RuntimeError
end

class Triangle
  attr_accessor :triangle
  def initialize(s1, s2, s3)
    @triangle = [s1, s2, s3].sort
    validate_triangle
  end

  def kind
    return :equilateral if equilateral?
    return :isosceles if isosceles?
    :scalene
  end

private
  def validate_triangle
    raise TriangleError if negative_side? || impossible?
  end

  def negative_side?
    triangle[0] < 0 ||
    triangle[1] < 0 ||
    triangle[2] < 0
  end

  def impossible?
    triangle[0] + triangle[1] - triangle[2] <= 0
  end

  def equilateral?
    triangle[0] == triangle[2]
  end

  def isosceles?
    triangle[0] == triangle[1] ||
    triangle[1] == triangle[2]
  end
end
