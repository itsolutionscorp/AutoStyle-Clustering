class TriangleError < ArgumentError ; end

class Triangle
  def initialize(a, b, c)
    @sides = [a, b, c]
    raise TriangleError if invalid?
  end

  def kind
    @kind ||= [:equilateral, :isosceles, :scalene][@sides.uniq.length - 1]
  end

  private

  def invalid?
    @sides.any? { |a| a >= @sides.reduce(:+) - a }
  end
end
