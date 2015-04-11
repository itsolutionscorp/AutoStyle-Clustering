class TriangleError < Exception
end

class Triangle

  def initialize(a,b,c)
    @sides = [a, b, c]
  end

  def kind
    a, b, c = @sides
    if a + b <= c or a + c <= b or b + c <= a then
      raise TriangleError
    end
    if a == b and b == c then
      return :equilateral
    elsif a == b or b == c or a == c then
      return :isosceles
    else
      return :scalene
    end
  end

  attr_reader :sides

end
