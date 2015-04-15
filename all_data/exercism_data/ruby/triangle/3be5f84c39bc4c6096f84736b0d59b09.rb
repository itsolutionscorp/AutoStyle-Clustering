# Thank you http://www.mathsisfun.com/triangle.html

TriangleError = Class.new(StandardError)

class Triangle
  attr_reader :sides
  private :sides

  def initialize(a, b, c)
    @sides = [ a, b, c ]
    if invalid?
      raise TriangleError
    end
  end

  def kind
    case count_uniq_sides
    when 1
      :equilateral
    when 2
      :isosceles
    else
      :scalene
    end
  end

  private

  def invalid?
    sides_zero? || side_negative? || violates_inequality?
  end

  def sides_zero?
    sides.all? { |side| side == 0 }
  end

  def side_negative?
    sides.any? { |side| side  < 0 }
  end

  def violates_inequality?
    *smaller_sides, longest_side = sides.sort
    smaller_sides.reduce(:+) <= longest_side
  end

  def count_uniq_sides
    sides.uniq.size
  end
end
