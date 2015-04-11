class Triangle
  attr_reader :sides
  def initialize(*sides)
    @sides = sides.sort
    raise TriangleError if invalid_sides?
  end

  def kind
    case sides.uniq.size
    when 1 then :equilateral
    when 2 then :isosceles
    when 3 then :scalene
    end
  end

private
  def invalid_sides?
    sides.any?{|x| x<=0} || sides[2] >= sides[0] + sides[1]
  end
end

TriangleError = Class.new(StandardError)
