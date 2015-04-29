class Triangle
  def initialize a, b, c
    @a, @b, @c = [a, b, c].sort
    raise TriangleError if [a, b, c].any? { |side| side <= 0 } || @c >= @a + @b
  end

  def kind
    if @a == @b && @b == @c then :equilateral
    elsif @a == @b || @b == @c || @c == @a then :isosceles
    else :scalene
    end
  end
end

class TriangleError < StandardError
end
