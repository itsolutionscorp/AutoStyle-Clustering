class Triangle
  def initialize (a,b,c)
    @shortest, @mid, @longest = [a, b, c].sort 
  end

  def kind
    case
    when invalid_sides?
      raise TriangleError 

    when @shortest == @longest 
      return :equilateral

    when @shortest == @mid ||  @mid == @longest
      return :isosceles

    else
      return :scalene
    end
  end

  def invalid_sides?
    invalid_side || not_a_triangle
    
  end

  def invalid_side
    @shortest <= 0 ||  @mid <= 0 ||  @longest <= 0
  end

  def not_a_triangle
    @longest >= @shortest + @mid
  end
end

class TriangleError < TypeError
end
