class Triangle
  attr_reader :sides

  def initialize(*sides)
    @sides = sides
    validate_triangle
  end

  def kind
    uniq_sides = sides.uniq.count
    case uniq_sides
      when 1 then :equilateral
      when 2 then :isosceles
      when 3 then :scalene
    end
  end

  private

  def validate_triangle
    raise TriangleError unless valid_triangle?
  end

  def valid_triangle?
    sides.all? { |side| (sides.inject(:+) - side) > side }
  end
end

class TriangleError < ArgumentError
end
