class TriangleError < ArgumentError; end;  
class Triangle
  def initialize(a,b,c)
    @a, @b, @c  = a, b, c
  end
  
  def kind
    raise TriangleError if @a <= 0 || @b <=0 || @c <= 0 || violates_inequality
    return :equilateral if @a==@b and @a==@c  
    return :isosceles  if @a==@b || @a==@c || @b==@c 
    :scalene 
  end
  
  def violates_inequality
    sides = [@a,@b,@c].sort
    sides[0]+sides[1] < sides[2]
  end  
  
end
