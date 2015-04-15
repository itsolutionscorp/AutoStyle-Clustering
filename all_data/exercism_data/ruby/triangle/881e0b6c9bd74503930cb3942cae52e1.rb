class Triangle
  attr_reader :a, :b, :c
  def initialize(a, b, c)
    @a, @b, @c = a, b, c
    raise TriangleError unless any_two_sides_greater_than_third?
  end

  def kind
    case
    when all_sides_equal? then :equilateral
    when two_sides_equal? then :isosceles
    else :scalene
    end
  end

private

  def all_sides_equal?
    a == b and b == c
  end

  def two_sides_equal?
    a == b or b == c or c == a
  end

  def any_two_sides_greater_than_third?
    a + b > c and b + c > a and a + c > b
  end

end
class TriangleError < Exception; end
