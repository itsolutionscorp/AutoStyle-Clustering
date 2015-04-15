class TriangleError < ArgumentError
end

class Triangle

  def initialize a, b, c
    fail TriangleError.new if not valid a, b, c
    @sides = [a, b, c].sort
  end

  def kind
    @type ||= type_of
  end

private

  def valid a, b, c
    sum = a+b+c
    return [a,b,c].combination(2).all? { |x, y| sum-x-y < x+y }
  end

  def type_of
    [:equilateral, :isosceles, :scalene][@sides.uniq.count-1]
  end
end
