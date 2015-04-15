class TriangleError < StandardError; end

class Triangle
  def initialize(a, b, c)
    @sides = [a, b, c].sort
  end

  def kind
    raise TriangleError if no_area? || violates_triangle_inequality?
    if @sides.uniq.one?
      :equilateral
    elsif @sides.uniq.length == 2
      :isosceles
    else
      :scalene
    end
  end

  private

  def no_area?
    @sides.any? { |side| side <= 0 }
  end

  def violates_triangle_inequality?
    @sides[0] + @sides[1] <= @sides[2]
  end
end
