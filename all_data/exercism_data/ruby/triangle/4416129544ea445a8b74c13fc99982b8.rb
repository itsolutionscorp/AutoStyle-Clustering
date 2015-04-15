class TriangleError < StandardError
end

class Triangle
  def initialize(*sides)
    @sides = sides.sort # sorts asc by default
  end

  def kind
    raise TriangleError, "Sides must be > 0"    if @sides.min <= 0
    raise TriangleError, "sides do not add up"  if @sides[0]+@sides[1] <= @sides[2]

    num_duplicate_sides = @sides.select { |s| @sides.count(s) > 1 }.count

    case num_duplicate_sides
    when 3
      :equilateral
    when 2
      :isosceles
    else
      :scalene
    end
  end
end
