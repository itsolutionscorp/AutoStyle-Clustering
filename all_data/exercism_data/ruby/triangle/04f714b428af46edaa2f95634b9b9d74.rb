class TriangleError < StandardError; end

class Triangle
  attr_reader :a, :b, :c

  def initialize(*sides)
    raise TriangleError.new("triangles have exactly three sides") unless sides.length == 3
    @a, @b, @c = sides.sort
    raise TriangleError.new("triangle is invalid") unless valid?
  end

  def kind
    case
    when all_sides_equal? then :equilateral
    when two_sides_equal? then :isosceles
    else                       :scalene
    end
  end

  private

  def all_sides_equal?
    a == b && b == c
  end

  def two_sides_equal?
    a == b || b == c
  end

  def valid?
    ! any_side_negative_or_zero? && ! violates_triangle_inequality?
  end

  def any_side_negative_or_zero?
    a <= 0
  end

  def violates_triangle_inequality?
    a + b <= c
  end
end
