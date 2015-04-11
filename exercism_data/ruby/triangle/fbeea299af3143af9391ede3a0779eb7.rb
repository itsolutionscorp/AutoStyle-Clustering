class TriangleError < StandardError
end

class Triangle

  def initialize(side1, side2, side3)
    @a = side1
    @b = side2 
    @c = side3
  end

  def is_invalid?
    true if [@a, @b, @c].min <= 0
    x, y, z = [@a, @b, @c].sort 
    true if x + y <= z
  end

  def triangle_type
    return :scalene if @a != @b && @b != @c && @a != @c
    return :equilateral if @a == @b && @b == @c
    return :isosceles if @a == @b || @b == @c || @a == @c
  end

  def kind
    if is_invalid?
      raise TriangleError
    else
      triangle_type
    end
  end

end
