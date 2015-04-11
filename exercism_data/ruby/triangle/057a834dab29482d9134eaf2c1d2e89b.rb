TriangleError = Class.new(StandardError)

class Triangle
  attr_reader :side_lengths

  def initialize(*side_lengths)
    @side_lengths = side_lengths

    raise TriangleError if invalid?
  end

  def kind
    case side_lengths.uniq.count
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
    side_lengths.any? { |side| side <= 0 }
  end

  def violates_triangle_inequality?
    hypotenuse >= sum_of_short_sides
  end

  def hypotenuse
    side_lengths.max
  end

  def sum_of_short_sides
    short_sides.reduce(:+)
  end

  def short_sides
    side_lengths.sort.slice(0..1)
  end
end
