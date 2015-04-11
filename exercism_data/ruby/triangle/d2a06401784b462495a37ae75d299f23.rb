class TriangleError < StandardError
end

class Triangle
  UNIQUE_SIDE_LENGTHS = {1 => :equilateral, 2 => :isosceles, 3 => :scalene}

  def initialize(a, b, c)
    @small, @medium, @large = [a, b, c].sort
    raise TriangleError, "violates triangle inequality" if violates_triangle_inequality?
    raise TriangleError, "side lengths must be greater than zero" if illegal_sides?
  end

  def kind
    UNIQUE_SIDE_LENGTHS[[small, medium, large].uniq.size] 
  end

  private
  attr_reader :small, :medium, :large
  def violates_triangle_inequality?
    small + medium <= large
  end

  def illegal_sides?
    small <= 0
  end
end
