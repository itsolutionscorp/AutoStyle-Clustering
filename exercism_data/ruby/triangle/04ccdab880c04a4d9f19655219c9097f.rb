class TriangleError < StandardError
end

class Triangle
  attr_accessor :a, :b, :c

  def initialize *args
    fail TriangleError if args.any? { |x| x <= 0 } || triangle_inequality(args)
    @a, @b, @c = args
  end

  def kind
    equilateral? || scalene? || isosceles?
  end

  private

  def equilateral?
    :equilateral if a == b && a == c
  end

  def isosceles?
    :isosceles if a == b || b == c || c == a
  end

  def scalene?
    :scalene if a != b && b != c && c != a
  end

  def triangle_inequality triangle
    3.times do
      return true if triangle[0] + triangle[1] <= triangle[2]
      triangle.rotate!
    end
    false
  end
end
