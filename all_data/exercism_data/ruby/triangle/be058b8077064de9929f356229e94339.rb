# triangle.rb
# Triangle exercise

class TriangleError < Exception; end

class Triangle
  def initialize(a,b,c)
    @sides = [a,b,c].sort!
    @sides[0] > 0 or raise TriangleError	# All sides must be positive length
    @sides[0]+@sides[1] > @sides[2] or raise TriangleError # Sides must fit together
  end
  
  def kind
    case @sides.uniq.length
    when 1; :equilateral
    when 2; :isosceles
    when 3; :scalene
    end
  end
end
