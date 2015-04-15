class Triangle

  def initialize(a, b, c)
    @sides = [a, b, c].sort
    raise TriangleError if @sides.any? { |side| side <= 0}
    raise TriangleError if @sides[0] + @sides[1] <= @sides[2]
  end

  def kind
    case @sides.uniq.count
    when 3 then :scalene
    when 2 then :isosceles
    when 1 then :equilateral
    end
  end
end

class TriangleError < ArgumentError

end
