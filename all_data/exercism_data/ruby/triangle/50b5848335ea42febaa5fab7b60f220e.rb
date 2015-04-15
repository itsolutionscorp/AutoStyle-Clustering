class TriangleError < StandardError; end

class Triangle

  def initialize a, b, c
    @a, @b, @c = a, b, c
  end

  def kind
    raise TriangleError unless valid?
    case
    when isosceles? then :isosceles
    when equilateral? then :equilateral
    else :scalene
    end
  end

  private

  attr_reader :a, :b, :c

  def equilateral?
    a == b && b == c
  end

  def isosceles?
    (a == b || a == c || b == c)  && !equilateral?
  end

  def valid?
    !zero_sized? && positive_sides? && triangle_equality?
  end

  def zero_sized?
    a == 0 && b == 0 && c == 0
  end

  def positive_sides?
    a > 0 && b > 0 && c > 0
  end

  def triangle_equality?
    a + b > c && a + c > b && b + c > a
  end
end
