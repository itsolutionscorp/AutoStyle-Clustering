class Triangle

  attr_reader :sides

  def initialize(a, b, c)
    @sides = [a, b, c].sort
    raise TriangleError if invalid_triangle?
  end

  def kind
    [:equilateral, :isosceles, :scalene][sides.uniq.count - 1]
  end

  private

  def invalid_triangle?
    short_sides? || not_equivalent?
  end

  def short_sides?
    sides.any? { |size| size <= 0 }
  end

  def not_equivalent?
    sides[0] <= 0 || sides[0] + sides[1] <= sides[2]
  end

end

class TriangleError < ArgumentError; end
