class Triangle
  attr_reader :side_1, :side_2, :side_3

  def initialize(*sides)
    @side_1, @side_2, @side_3 = sides.sort

    validate
  end

  def kind
    case
    when all_sides_equal?
      :equilateral
    when two_sides_equal?
      :isosceles
    else
      :scalene
    end
  end

  private

  def validate
    unless side_1 > 0
      raise TriangleError, "All sides must have a positive length"
    end

    unless side_1 + side_2 > side_3
      raise TriangleError, "Triangles must satisfy the Triangle Inequality"
    end
  end

  def all_sides_equal?
    side_1 == side_3
  end

  def two_sides_equal?
    side_1 == side_2 || side_2 == side_3
  end
end

class TriangleError < Exception; end
