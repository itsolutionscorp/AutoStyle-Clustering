class Triangle

  attr_reader :sides

  def initialize(a, b, c)
    @sides = [a, b, c].sort
    raise TriangleError if invalid_triangle?
  end

  def kind
    types[sides.uniq.count - 1]
  end

  private

  def types
    [
      :equilateral,
      :isosceles,
      :scalene
    ]
  end

  def invalid_triangle?
    sides.any? { |size| size <= 0 }
    sides[0] <= 0 || sides[0] + sides[1] <= sides[2]
  end

end

class TriangleError < ArgumentError; end
