class Triangle
  def initialize(a, b, c)
    @points = [a, b, c].sort
    raise TriangleError unless @points.min > 0
    raise TriangleError unless @points.last < @points.first(2).reduce(:+)
  end

  def kind
    case @points.uniq.length
    when 1 then :equilateral
    when 2 then :isosceles
    when 3 then :scalene
    end
  end
end

class TriangleError < Exception; end
