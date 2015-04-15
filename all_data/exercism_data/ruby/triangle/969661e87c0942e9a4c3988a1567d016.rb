class Triangle
  def initialize(side1, side2, side3)
    @side1, @side2, @side3 = side1, side2, side3
    validate
  end

  def kind
    case number_of_equal_sides
    when 0
      :scalene
    when 2
      :isosceles
    else
      :equilateral
    end
  end

  private

  def validate
    ensure_positive
    ensure_triangle_inequality
  end

  def ensure_positive
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
    when 0 then 3
    end
  end

  def sides
    [@side1, @side2, @side3]
  end
end


class TriangleError < StandardError
end
