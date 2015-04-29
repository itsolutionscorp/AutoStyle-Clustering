class Triangle
  def initialize(*sides)
    @sides = sides
    validate
  end

  def kind
    case number_of_equal_sides
    when 0 then :scalene
    when 2 then :isosceles
    when 3 then :equilateral
    end
  end

  private

  def validate
    ensure_trinity
    ensure_positivity
    ensure_triangle_inequality
  end

  def ensure_trinity
    unless sides.length == 3
      raise TriangleError, "Triangles have 3 sides, fool!"
    end
  end

  def ensure_positivity
    if sides.any? { |side| side <= 0 }
      raise TriangleError, "Sides must be positive!"
    end
  end

  def ensure_triangle_inequality
    if sides.any? { |side| perimeter - side <= side }
      raise TriangleError, "The sum of any two sides must exceed the third!"
    end
  end

  def perimeter
    sides.inject(:+)
  end

  def number_of_equal_sides
    case sides.uniq.length
    when 3 then 0
    when 2 then 2
    when 1 then 3
    end
  end

  def sides
    @sides
  end
end


class TriangleError < StandardError
end
