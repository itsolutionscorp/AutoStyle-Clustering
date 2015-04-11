class TriangleError < ArgumentError
end

class Triangle

  def initialize a, b, c
    validate a, b, c
    @sides = [a, b, c].sort
  end

  def kind
    return @kind ||= [:equilateral, :isosceles, :scalene][@sides.uniq.count-1]
  end

private

  def validate a, b, c
    sum = a+b+c
    if [a,b,c].combination(2).any? { |x, y| sum-x-y >= x+y }
      fail TriangleError.new
    end
  end
end
