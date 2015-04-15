class TriangleError < Exception; end

class Triangle

  def initialize(*sides)
    @sides = sides
  end

  def kind
    assert_valid_triangle
    [:equilateral, :isosceles, :scalene][@sides.uniq.count - 1]
  end

private

  def assert_valid_triangle
    sum = @sides.inject(:+)
    raise TriangleError if @sides.find { |s| s <= 0 || s >= sum / 2.0 }
  end
end
