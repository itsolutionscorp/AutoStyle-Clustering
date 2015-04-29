class Triangle
  attr_reader :sides

  def initialize(a, b, c)
    @sides = [a, b, c].sort
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
    sides[0] == sides[1] && sides[0] == sides[2]
  end

  def isosceles?
    sides[0] == sides[1] || sides[1] == sides[2]
  end

  def validate_triangle
    validate_positive_length
    validate_triangle_inequality
  end

  def validate_positive_length
    sides.each do |side|
      raise TriangleError if side <= 0
    end
  end

  def validate_triangle_inequality
    if (sides[0] + sides[1]) <= sides[2]
      raise TriangleError
    end
  end
end

class TriangleError < Exception
end
