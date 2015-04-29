class TriangleError < Exception; end

class Triangle

  def initialize(*sides)
    @sides = sides
    assert_valid
  end

  def kind
    [:equilateral, :isosceles, :scalene][@sides.uniq.count - 1]
  end

private

  def assert_valid
    side_limit = perimeter / 2.0
    raise TriangleError if @sides.find { |side| side <= 0 || side >= side_limit }
  end

  def perimeter
    @sides.inject(:+)
  end

end
