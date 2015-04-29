class Triangle
  
  def initialize(side1, side2, side3)
    @a = side1
    @b = side2 
    @c = side3
  end

  def kind
    raise TriangleError if [@a, @b, @c].min <= 0
    x, y, z = [@a, @b, @c].sort 
    raise TriangleError if x + y <= z

    return :scalene if @a != @b && @b != @c && @a != @c
    return :equilateral if @a == @b && @b == @c
    return :isosceles if @a == @b || @b == @c || @a == @c
  end

end
