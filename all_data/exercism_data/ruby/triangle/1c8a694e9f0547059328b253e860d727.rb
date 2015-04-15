class Triangle
  attr_reader :sides
  def initialize(a, b, c)
    @sides = [a,b,c].sort
  end

  def kind
    if sides[0] + sides[1] <= sides[2] || sides.find {|x| x <= 0}
      raise TriangleError
    elsif sides.reverse == sides
      :equilateral
    elsif sides.uniq != sides
      :isosceles
    else
      :scalene
    end
  end

end

class TriangleError < StandardError
end
