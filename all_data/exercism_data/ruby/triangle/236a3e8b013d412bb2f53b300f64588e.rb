class Triangle
  attr_reader :sides

  def initialize(length, height, width)
    @sides  = [length, height, width]
  end

  def kind
    raise TriangleError if illegal?
    if equilateral?
      :equilateral
    elsif isosceles?
      :isosceles
    else
      :scalene
    end
  end

  def equilateral?
    sides.uniq.count == 1
  end

  def isosceles?
    sides.uniq.count == 2
  end

  def scalene?
    sides.uniq.count == 3
  end

  def illegal?
    invalid? || inequal?
  end

  def invalid?
    sides.any? { |side| side <= 0 }
  end

  def inequal?
    sorted_sides = sides.sort
    sorted_sides[0] + sorted_sides[1] <= sorted_sides[2]
  end
end

class TriangleError < StandardError


end
