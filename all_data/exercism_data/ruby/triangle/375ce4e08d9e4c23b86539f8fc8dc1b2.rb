class Triangle
  def initialize(one, two, three)
    @sides = [one, two, three]
    raise TriangleError if impossible_side_length? || triangle_inequal?
  end

  def kind
    case sides.uniq.size
    when 1 then :equilateral
    when 2 then :isosceles
    when 3 then :scalene
    end
  end

  private

  attr_reader :sides

  def impossible_side_length?
    sides.any? { |side| side <= 0 }
  end

  def triangle_inequal?
    max_side = sides.max
    sides.reduce(:+) - max_side <= max_side
  end
end

class TriangleError < StandardError
end
