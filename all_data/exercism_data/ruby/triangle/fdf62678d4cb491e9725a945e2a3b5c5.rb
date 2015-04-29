class Triangle
  attr_reader :a, :b, :c
  def initialize(a,b,c)
    @a, @b, @c = a, b, c
    validate!
  end

  def kind
    if a == b && b == c
      :equilateral
    elsif a != b && b != c && c != a
      :scalene
    else
      :isosceles
    end
  end

  private

  def validate!
    raise TriangleError unless check_sides_greater_than_zero && check_triangle_inequality
  end

  def check_sides_greater_than_zero
    [a,b,c].all? { |side| side > 0 }
  end

  def check_triangle_inequality
    long_side_length = [a,b,c].sort[2]
    shorter_sides_length = [a,b,c].sort[0,2].inject(&:+)
    long_side_length < shorter_sides_length
  end

  def opposite(side1, side2)
    [side1, side2].each do |del|
      [a,b,c].delete_at([a,b,c].index(del))
    end[0]
  end
end

class TriangleError < StandardError; end
