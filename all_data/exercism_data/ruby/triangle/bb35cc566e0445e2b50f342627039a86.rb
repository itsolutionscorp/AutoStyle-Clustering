class Triangle
  attr_reader :sides

  def initialize(*sides)
    @sides = sides.sort
    validate_triangle
  end

  def kind
    case sides.uniq.count
    when 3 then :scalene
    when 2 then :isosceles
    when 1 then :equilateral
    end
  end

  def validate_triangle
    if sides.any? { |s| s <= 0 } || sides[0] + sides[1] <= sides[2]
      raise TriangleError
    end
  end
end

class TriangleError < StandardError; end
