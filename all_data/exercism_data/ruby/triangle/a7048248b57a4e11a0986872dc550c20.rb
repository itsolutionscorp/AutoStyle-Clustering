class Triangle
  attr_reader :sides
  def initialize(a, b, c)
    @sides = [a,b,c].sort
  end

  def kind
    raise TriangleError if invalid_triangle?
    case sides.uniq.length
    when 1
      :equilateral
    when 2
      :isosceles
    else
      :scalene
    end
  end

  private
  def invalid_triangle?
    sides[0] + sides[1] <= sides[2] || sides.find {|x| x <= 0}
  end

end

class TriangleError < StandardError
end
