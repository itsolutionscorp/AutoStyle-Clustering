class Triangle
  def initialize(*sides)
    @sides = sides.sort
  end
  attr_reader :sides

  def kind
    raise TriangleError if sides[0] + sides[1] <= sides[2]
    case sides.uniq.count
    when 3 then :scalene
    when 2 then :isosceles
    when 1 then :equilateral
    end
  end
end

class TriangleError < StandardError; end
