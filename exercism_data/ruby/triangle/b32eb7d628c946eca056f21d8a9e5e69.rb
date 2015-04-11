class TriangleError < ArgumentError; end

class Triangle
  def initialize(a, b, c)
    @sides = [a, b, c].sort

    fail TriangleError, 'sides must be positive' unless
      @sides.all? { |s| s > 0 }
    fail TriangleError, 'not physically possible' unless
      @sides[0] + @sides[1] > @sides[2]
  end

  def kind
    case @sides.uniq.count
    when 1 then :equilateral
    when 2 then :isosceles
    when 3 then :scalene
    end
  end
end
