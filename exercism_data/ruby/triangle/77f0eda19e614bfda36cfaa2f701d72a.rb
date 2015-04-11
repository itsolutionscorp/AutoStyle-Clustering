TriangleError = Class.new(StandardError)

class Triangle
  def initialize side1, side2, side3
    @sides = [side1, side2, side3]
    ensure_positive_sides
    ensure_triangle_inequality
  end

  attr_reader :sides

  def kind
    case number_of_equal_sides
    when 3
      :equilateral
    when 2
      :isosceles
    else
      :scalene
    end
  end

  private

  def ensure_positive_sides
    sides.all? {|side| side > 0 } or
      fail TriangleError, "Sides have to have a positive length"
  end

  def ensure_triangle_inequality
    sides.inject(:+) - sides.max > sides.max or
      fail TriangleError, "Sides must obey the triangle inequality"
  end

  def number_of_equal_sides
    4 - sides.uniq.length
  end
end
