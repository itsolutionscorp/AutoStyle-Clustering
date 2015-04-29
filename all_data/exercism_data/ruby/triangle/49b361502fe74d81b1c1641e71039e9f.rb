class TriangleError < StandardError
end

class Triangle
  attr_accessor :a, :b, :c

  def initialize a, b, c
    fail TriangleError if [a, b, c].any? { |x| x <= 0 } ||
                          triangle_inequality([a, b, c])
    @a, @b, @c = a, b, c
  end

  def kind
    if a == b && a == c
      :equilateral
    elsif a == b || b == c || a == c
      :isosceles
    elsif a != b && a != c  && b != c
      :scalene
    end
  end

  private

  def triangle_inequality triangle
    3.times do
      return true if triangle[0] + triangle[1] <= triangle[2]
      triangle.rotate!
    end
    false
  end
end
