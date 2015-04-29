TriangleError = Class.new(StandardError)

class Triangle
  attr_reader :sides

  def initialize(*sides)
    @sides = sides

    raise TriangleError if invalid?
  end

  def kind
    case sides.uniq.count
    when 1 then :equilateral;
    when 2 then :isosceles;
    else :scalene
    end
  end

  private

  def invalid?
    invalid_lengths? || violates_triangle_inequality?
  end

  def invalid_lengths?
    sides.any? { |side| side <= 0 }
  end

  def violates_triangle_inequality?
    sum_of_sides <= (2 * hypotenuse)
  end

  def hypotenuse
    sides.max
  end

  def sum_of_sides
    sides.reduce(:+)
  end
end
