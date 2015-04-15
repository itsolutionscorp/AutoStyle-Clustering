class Triangle
  def initialize(a,b,c)
    @sides = [a , b, c].sort
    raise TriangleError if @sides.any?{|side| side <= 0} || @sides[2] >= (@sides[0] + @sides[1])
  end

  def kind
    num_unique_sides = @sides.uniq.size
    case num_unique_sides
    when 1 then return :equilateral
    when 2 then return :isosceles
    else return :scalene
    end
  end
end

class TriangleError < StandardError; end
