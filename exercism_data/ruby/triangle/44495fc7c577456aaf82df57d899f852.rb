class TriangleError < ArgumentError; end

class Triangle
  attr_reader :a, :b, :c

  def initialize(a, b, c)
    @a = a
    @b = b
    @c = c  
    validate_triangle
  end

  def kind
    return :equilateral if equilateral?
    return :isosceles   if isosceles?
    :scalene
  end

  def equilateral?
    a == b && a == c
  end

  def isosceles?
    a == b || a == c || b == c
  end

  private

  def validate_triangle
    raise TriangleError unless valid_sides? && valid_triangle?
  end

  def valid_sides?
    [a,b,c].all? { |side| side > 0 }
  end

  def valid_triangle?
    (a+b) > c && (a+c) > b && (b+c) > a
  end
end
