TriangleError = Class.new(Exception)

class Triangle
  attr_reader :a, :b, :c
  attr_reader :triangle

  def initialize(a, b, c)
    @a, @b, @c = a, b, c
    @triangle = [a, b, c]
  end

  def kind
    raise TriangleError if illegal_size? || inequality?

    return :equilateral if a & b & c == a
    return :isosceles   if a == b || a == c || b == c
    return :scalene
  end

  private
  def illegal_size?
    triangle.any?{|i| i <= 0 }
  end

  def inequality?
    a + b <= c || a + c <= b || b + c <= a
  end
end
