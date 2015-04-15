class TriangleError < StandardError; end

class Triangle

  def initialize(a, b, c)
    if not ((a + b > c) && (b + c > a) && (c + a > b))
      raise TriangleError
    end
    @sides = [a,b,c].sort
  end

  def kind
    case @sides.uniq.length
    when 1
      return :equilateral
    when 2
      return :isosceles
    else
      return :scalene
    end
  end

end
