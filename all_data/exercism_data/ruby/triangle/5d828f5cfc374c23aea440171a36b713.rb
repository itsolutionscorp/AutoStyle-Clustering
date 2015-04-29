class Triangle

  def initialize(a, b, c)
    @sides = [a, b, c].sort
    raise TriangleError if @sides.any? { |side| side <= 0}
    raise TriangleError if @sides[0] + @sides[1] <= @sides[2]
    @a, @b, @c = a, b, c
  end

  def kind
    return :equilateral if @a == @b && @b == @c
    return :isosceles   if @a == @b || @a == @c || @b == @c
    return :scalene
  end
end

class TriangleError < ArgumentError

end
