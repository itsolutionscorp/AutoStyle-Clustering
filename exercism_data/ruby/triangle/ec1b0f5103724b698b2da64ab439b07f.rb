class Triangle
  attr_reader :sides
  def initialize(*sides)
    @sides = sides
    raise TriangleError if invalid_sides?
  end

  def kind
    case sides.uniq.size
    when 3 then :scalene
    when 1 then :equilateral
    else :isosceles
    end
  end

private
  def invalid_sides?
    sides.any?{|x| x<=0} || sides.max >= sides.inject(:+) / 2.0
  end
end

TriangleError = Class.new(StandardError)
