class Triangle

  def initialize *sides
    raise unless sides.size == 3
    @sides = sides.sort
  end

  def kind
    raise TriangleError if @sides.any? { |side| side <= 0 } ||
                           @sides[0]+@sides[1] <= @sides[2] ||

    return :equilateral if @sides.uniq.size == 1
    return :isosceles   if @sides.uniq.size == 2
    return :scalene     if @sides.uniq.size == 3

  end
end

class TriangleError < StandardError
end
