class Triangle
  TYPES = { 1 => :equilateral, 2 => :isosceles, 3 => :scalene }
  def initialize(*sides)
    @sides = sides
    raise TriangleError if negative_side? || invalid_triangle?
  end

  def kind
    TYPES[@sides.uniq.count]
  end

  def negative_side?
    @sides.any? { |side| side <= 0 }
  end

  def invalid_triangle?
    @sides.any? { |side| @sides.reduce(:+) <= side * 2 }
  end
end

class TriangleError < StandardError
end
