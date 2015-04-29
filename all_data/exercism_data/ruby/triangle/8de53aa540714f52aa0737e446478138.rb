class Triangle
  def initialize (a,b,c)
    @a, @b, @c = a, b, c 
  end

  def kind
    sides = [@a, @b, @c].sort.reverse
    case
    when @a <= 0 ||  @b <= 0 ||  @c <= 0
      raise TriangleError 

    when sides[0] >= sides[1] + sides[2] 
      raise TriangleError

    when @a == @b && @a == @c 
      return :equilateral

    when @a == @b || @a == @c || @b == @c
      return :isosceles

    else
      return :scalene
    end
  end
end

#class TriangleError < TypeError
#end
